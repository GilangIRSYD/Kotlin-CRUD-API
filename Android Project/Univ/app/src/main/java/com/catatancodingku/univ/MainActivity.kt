
package com.catatancodingku.univ

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.catatancodingku.univ.adapter.StudentsAdapter
import com.catatancodingku.univ.api.ApiClient
import com.catatancodingku.univ.model.DataList
import com.catatancodingku.univ.model.StudentsResponse
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        callApi()
        fab.shrink()

        fab.setOnClickListener {

            fab.extend()

            fab.setOnClickListener {
                startActivity(Intent(this,AddAct::class.java))
                fab.shrink()
            }
        }








    }

    fun callApi(){
        ApiClient.service.getDataStudents().enqueue(object : Callback<StudentsResponse>{
            override fun onFailure(call: Call<StudentsResponse>, t: Throwable) {

                Log.d("Cek_Response", "onFailure: ${t.localizedMessage}")
            }

            override fun onResponse(
                call: Call<StudentsResponse>,
                response: Response<StudentsResponse>
            ) {

                val status : String = response.message()
                Log.d("Cek_Response", "onResponse: $status")

                val studentResponse : StudentsResponse? = response.body()

                val students     = studentResponse?.data
                Log.d("Cek_Response", "onResponse: $students")

                if (response.isSuccessful){
                   showData(students)
                }

            }
        })
    }

    private fun showData(data: List<DataList>?) {

        val bottomSheet = BottomSheetDialog(this)
        bottomSheet.setContentView(R.layout.bottom_sheet_layout)

        val adapter = StudentsAdapter(this,data,object : StudentsAdapter.OnClickCallback{
            override fun detail(data: DataList?) {
                
                bottomSheet.item_name_dialog.text = data?.name
                bottomSheet.item_noHp_dialog.text = data?.noHp
                bottomSheet.item_address_dialog.text = data?.address
                bottomSheet.btn_dialog.setOnClickListener {
                    bottomSheet.dismiss()
                }
                bottomSheet.close_bottomSheet.setOnClickListener { bottomSheet.dismiss() }
                bottomSheet.show()
            }

            override fun delete(data: DataList?) {

                val alertDelete = AlertDialog.Builder(this@MainActivity)
                alertDelete.setTitle("Yakin hapus data ?")
                alertDelete.setMessage("Data ini akan dihapus permanen dan mungkin tidak bisa dikembalikan lagi")
                alertDelete.setPositiveButton("Hapus",DialogInterface.OnClickListener{d,w ->
                    serviceDelete(data?.id)
                })
                alertDelete.setNeutralButton("Batal", DialogInterface.OnClickListener{ d,w ->

                })

                alertDelete.show()

            }

            override fun edit(data: DataList?) {
                val intent = Intent(this@MainActivity,AddAct::class.java)
                intent.putExtra("data",data)
                startActivity(intent)
            }

        })
        rv_main.adapter = adapter
        rv_main.layoutManager   = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        callApi()
    }

    fun serviceDelete(id : String?){
        ApiClient.service.deleteData(id ?:"")
            .enqueue(object : Callback<StudentsResponse>{
                override fun onFailure(call: Call<StudentsResponse>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Failed : ${t.localizedMessage} ", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onResponse(
                    call: Call<StudentsResponse>,
                    response: Response<StudentsResponse>
                ) {
                    Toast.makeText(applicationContext, "Data dihapus", Toast.LENGTH_SHORT).show()
                    callApi()
                }

            })
    }
}