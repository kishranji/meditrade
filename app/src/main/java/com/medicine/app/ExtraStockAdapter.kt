package com.medicine.app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ExtraStockAdapter(val mValue: ExtraStockModel,val callBack:onItemSeclted) :
    RecyclerView.Adapter<ExtraStockAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.inflate_extra_adapter, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return mValue.data!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mMedName.text = "Medicine name: " + mValue.data?.get(position)!!.Medicine_name
        holder.mUserName.text = "User Name: " + mValue.data?.get(position)!!.User_name
        holder.mexpDate.text = "Expiry Date: " + mValue.data?.get(position)!!.Expiry_date
        holder.mRoot.setOnClickListener {
            callBack.clicked(position)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mMedName = itemView.findViewById<TextView>(R.id.medicine_name)
        var mUserName = itemView.findViewById<TextView>(R.id.user_name)
        var mexpDate = itemView.findViewById<TextView>(R.id.expiry_date)
        var mRoot = itemView.findViewById<LinearLayout>(R.id.rootLay)
    }


    interface onItemSeclted{
        fun clicked(position:Int)
    }
}


