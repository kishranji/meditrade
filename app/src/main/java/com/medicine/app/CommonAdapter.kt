package com.medicine.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class CommonAdapter(val mNames : ArrayList<String>?,val mRes : ArrayList<Int>?,val mCallBack:ItemClickListener) : RecyclerView.Adapter<CommonAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context)
            .inflate(R.layout.inflate_common_adapter, parent, false)
        return ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return mNames!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.mImage.setImageResource(mRes?.get(position)!!)
        holder.mTextView.text = mNames?.get(position)!!
        holder.mRootLay.setOnClickListener {
            mCallBack.OnItemClicked(position)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImage: ImageView = itemView.findViewById(R.id.image_view)
        val mTextView: TextView = itemView.findViewById(R.id.text_view)
        val mRootLay: RelativeLayout = itemView.findViewById(R.id.rootLay)
    }


}

interface ItemClickListener {
    fun OnItemClicked(position:Int)
}
