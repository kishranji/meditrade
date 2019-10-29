package com.medicine.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DetailActivity : AppCompatActivity(), ItemClickListener {
   var mUrlList=ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        val rv = findViewById<RecyclerView>(R.id.dashboardRV)
        rv.layoutManager = GridLayoutManager(this, 2)

        val mNames = intent.getStringArrayListExtra("names")
        mUrlList = intent.getStringArrayListExtra("url")
        val mRes = intent.getIntegerArrayListExtra("res")


        var adapter = CommonAdapter(mNames, mRes, this)
        rv.adapter = adapter
    }


    override fun OnItemClicked(position: Int) {
        val  mIntent = Intent(
            this, WebViewActivity::class.java
        )
        mIntent.putExtra("url",mUrlList[position])
        startActivity(mIntent)
    }
}
