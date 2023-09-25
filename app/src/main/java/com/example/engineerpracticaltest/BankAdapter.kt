package com.example.engineerpracticaltest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class BankAdapter(var con: Context, var list: List<BankResultItem>): RecyclerView.Adapter<BankAdapter.ViewHolder>() {

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v)
    {
        var image = v.findViewById<ImageView>(R.id.ivBank)
        var txtName = v.findViewById<TextView>(R.id.txtName)
        var txtDesc = v.findViewById<TextView>(R.id.txtDesc)
        var txtAge = v.findViewById<TextView>(R.id.txtAge)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view = LayoutInflater.from(con).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(con).load(list[position].url).into(holder.image)

        holder.txtName.text = list[position].bankName
        holder.txtDesc.text = list[position].description
        holder.txtAge.text = list[position].age.toString()
    }

    override fun getItemCount(): Int {
        return list.count()
    }

}