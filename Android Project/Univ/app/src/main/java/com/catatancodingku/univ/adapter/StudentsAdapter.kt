package com.catatancodingku.univ.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.catatancodingku.univ.R
import com.catatancodingku.univ.model.DataList
import kotlinx.android.synthetic.main.item_row_students.view.*

class StudentsAdapter(
    val context: Context,
    val data : List<DataList>?,
    val itemClick : OnClickCallback
) : RecyclerView.Adapter<StudentsAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName  = itemView.tv_item_name
        val tvPhone = itemView.tv_item_phone
        val tvAddress = itemView.tv_item_address
        val delete  = itemView.ic_delete
        val edit    = itemView.ic_edit
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_row_students,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?:0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text      = data?.get(position)?.name
        holder.tvPhone.text     = data?.get(position)?.noHp
        holder.tvAddress.text   = data?.get(position)?.address

        holder.delete.setOnClickListener {
            itemClick.delete(data?.get(position))
        }

        holder.edit.setOnClickListener {
            itemClick.edit(data?.get(position))
        }

        holder.itemView.setOnClickListener {
            itemClick.detail(data?.get(position))
        }
    }

    interface OnClickCallback {
        fun detail(data : DataList?)
        fun delete (data: DataList?)
        fun edit (data : DataList?)
    }
}