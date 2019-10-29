package com.medicine.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_extra_stock.*
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.google.gson.Gson


class ExtraStockActivity : AppCompatActivity(), ExtraStockAdapter.onItemSeclted {
    override fun clicked(position: Int) {
        val mIntent = Intent(
            this, ViewExtraStock::class.java
        )
        mIntent.putExtra("pos", position)
        startActivity(mIntent)
    }

    var mStockLits: ExtraStockModel = ExtraStockModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_extra_stock)
    }

    override fun onResume() {
        super.onResume()

        val rv = findViewById<RecyclerView>(R.id.extrastockRecycler)
        val horizontalLayoutManagaer =
            LinearLayoutManager(this@ExtraStockActivity, LinearLayoutManager.VERTICAL, false)
        rv.setLayoutManager(horizontalLayoutManagaer)

        val gson = Gson()
        val value = APPPrefs.getString("mList", "")
        if (!value.isNullOrEmpty()) {
            mStockLits = gson.fromJson<ExtraStockModel>(value, ExtraStockModel::class.java!!)
            var adapter = ExtraStockAdapter(mStockLits,this)
            rv.adapter = adapter
        }

        addStock.setOnClickListener {
            val mIntent = Intent(
                this, AddExtraStock::class.java
            )
            startActivity(mIntent)
        }
    }
}
