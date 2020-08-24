package com.catatancodingku.univ

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.catatancodingku.univ.api.ApiClient
import com.catatancodingku.univ.model.DataList
import com.catatancodingku.univ.model.StudentsResponse
import kotlinx.android.synthetic.main.activity_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val dataIntent = intent.getParcelableExtra<DataList>("data")
        if (dataIntent != null){
            val name = dataIntent.name
            val noHp = dataIntent.noHp
            val address = dataIntent.address

            btn_save.text = getString(R.string.save_change)

            ed_nama_input.editText!!.setText(name)
            ed_address_input.editText!!.setText(address)
            ed_noHp_input.editText!!.setText(noHp)

        }

        btn_save.setOnClickListener {
            val name = ed_nama_input.editText!!.text.toString()
            val noHp = ed_noHp_input.editText!!.text.toString()
            val address = ed_address_input.editText!!.text.toString()


            if (name.isEmpty() || noHp.isEmpty() || address.isEmpty()){
                Toast.makeText(this, "Isi semua field", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(dataIntent != null){
                val id = dataIntent.id
                Log.d("_cek", "Add Act > onCreate: id, name, no, address ( $id, $name, $noHp , $address )")
                serviceEdit(id,name,noHp,address)
            }else{

                serviceAddStudent(name,noHp,address)
            }


            finish()
        }


    }

    private fun serviceEdit(id : String?, name : String?, noHp: String?, address: String?) {


        ApiClient.service.updateData(id ?: "",name ?: "",noHp ?:"",address?:"")
            .enqueue(object : Callback<StudentsResponse> {
                override fun onFailure(call: Call<StudentsResponse>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Failed " + t.localizedMessage,
                        Toast.LENGTH_SHORT
                    ).show()

                }

                override fun onResponse(
                    call: Call<StudentsResponse>,
                    response: Response<StudentsResponse>
                ) {
                    Toast.makeText(applicationContext, "Data berhasil diubah", Toast.LENGTH_SHORT)
                        .show()

                    finish()
                }

            })
    }

    private fun serviceAddStudent(name : String, noHp : String, address : String) {

        ApiClient.service.addDataStudents(name,noHp,address.replace("'",""))
            .enqueue(object : Callback<StudentsResponse>{
                override fun onFailure(call: Call<StudentsResponse>, t: Throwable) {

                    Toast.makeText(this@AddAct, "Failed :${t.localizedMessage}", Toast.LENGTH_SHORT).show()
                    Log.e("_cek", "Create >> onFailure: " + t.message)
                }

                override fun onResponse(
                    call: Call<StudentsResponse>,
                    response: Response<StudentsResponse>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(this@AddAct, "Data berhasil ditambah", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this@AddAct, "Gagal menambah data", Toast.LENGTH_SHORT).show()
                    }
                }

            })
    }
}