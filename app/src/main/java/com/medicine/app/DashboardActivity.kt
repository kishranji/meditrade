package com.medicine.app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DashboardActivity : AppCompatActivity(), ItemClickListener {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)


        val rv = findViewById<RecyclerView>(R.id.dashboardRV)
        rv.layoutManager = GridLayoutManager(this, 2)
        val names = ArrayList<String>()
        names.add("Medicines")
        names.add("Medicine Info")
        names.add("Ayurveda")
        names.add("Homeopathy")
        names.add("Extra Stocks")

        val res = ArrayList<Int>()

        res!!.add(R.drawable.ic_medicine)
        res!!.add(R.drawable.ic_info)
        res!!.add(R.drawable.ic_ayur)
        res!!.add(R.drawable.ic_homeo)
        res!!.add(R.drawable.ic_extra)

        var adapter = CommonAdapter(names, res, this)
        rv.adapter = adapter
    }

    override fun OnItemClicked(position: Int) {
        Log.e("pos", position.toString())


        when (position) {
            0 -> {
                val names = ArrayList<String>()
                names.add("1MG")
                names.add("Netmeds")
                names.add("Myra")
                names.add("Medlife")
                names.add("Apollo")
                names.add("Medplus")

                val res = ArrayList<Int>()

                res!!.add(R.drawable.onemg)
                res!!.add(R.drawable.netmeds)
                res!!.add(R.drawable.myra)
                res!!.add(R.drawable.medlife)
                res!!.add(R.drawable.apollo)
                res!!.add(R.drawable.medplus)

                val url = ArrayList<String>()
                url.add("https://www.1mg.com/")
                url.add("https://m.netmeds.com/")
                url.add("https://myramed.in/")
                url.add("https://www.medlife.com/")
                url.add("https://www.apollopharmacy.in/")
                url.add("https://www.medplusmart.com/pharmaHome")

                val mIntent = Intent(
                    this, DetailActivity::class.java
                )
                mIntent.putIntegerArrayListExtra("res", res)
                mIntent.putStringArrayListExtra("names", names)
                mIntent.putStringArrayListExtra("url", url)
                startActivity(mIntent)
            }

            1 -> {
                val names = ArrayList<String>()
                names.add("Tablet Wise")
                val res = ArrayList<Int>()
                res!!.add(R.drawable.tabletwise)
                val url = ArrayList<String>()
                url.add("https://www.tabletwise.com/")
                val mIntent = Intent(
                    this, DetailActivity::class.java
                )
                mIntent.putIntegerArrayListExtra("res", res)
                mIntent.putStringArrayListExtra("names", names)
                mIntent.putStringArrayListExtra("url", url)
                startActivity(mIntent)
            }
            2 -> {

                val names = ArrayList<String>()
                names.add("Pathanjali")
                names.add("VLCC")
                names.add("Himalaya")
                names.add("Forest Essential")
                names.add("Kama Ayurveda")
                names.add("Organic shop")
                names.add("Khadi Natural")
                names.add("The Body Shop")

                val res = ArrayList<Int>()
                res!!.add(R.drawable.patanjali)
                res!!.add(R.drawable.vlcc)
                res!!.add(R.drawable.himalaya)
                res!!.add(R.drawable.forest)
                res!!.add(R.drawable.kama)
                res!!.add(R.drawable.organic)
                res!!.add(R.drawable.khadi)
                res!!.add(R.drawable.thebodyshop)
                val url = ArrayList<String>()
                url.add("https://www.patanjaliayurved.net/")
                url.add("https://www.vlccpersonalcare.com/")
                url.add("https://himalayawellness.in/?gclid=EAIaIQobChMIk8XotcC-5QIVkh0rCh2e6gKGEAAYASAAEgI6UfD_BwE")
                url.add("https://www.forestessentialsindia.com/?gclid=EAIaIQobChMIjqjo28C-5QIVzY6PCh0HgAZXEAAYASAAEgI6J_D_BwE")
                url.add("https://www.kamaayurveda.com/?utm_source=google&utm_medium=cpc&utm_campaign=Branded-search&gclid=EAIaIQobChMIyK_SocG-5QIVRyUrCh3GaA29EAAYASAAEgIpD_D_BwE")
                url.add("https://organicshop.in/")
                url.add("https://www.khadinatural.com/?gclid=EAIaIQobChMI4MyR8sG-5QIVjjgrCh2YewIcEAAYASAAEgL1HfD_BwE")
                url.add("https://www.thebodyshop.in/?utm_source=vCommission&utm_medium=CPV_68954&utm_campaign=EOSS")
                val mIntent = Intent(
                    this, DetailActivity::class.java
                )
                mIntent.putIntegerArrayListExtra("res", res)
                mIntent.putStringArrayListExtra("names", names)
                mIntent.putStringArrayListExtra("url", url)
                startActivity(mIntent)

            }
            3 -> {
                val names = ArrayList<String>()
                names.add("Schwabe India")
                names.add("Health Mug")
                names.add("Homeomart")
                val res = ArrayList<Int>()
                res!!.add(R.drawable.schwable)
                res!!.add(R.drawable.healthmug)
                res!!.add(R.drawable.ic_homeo)
                val url = ArrayList<String>()
                url.add("https://www.schwabeindia.com/?gclid=EAIaIQobChMI4M71yMK-5QIVUw4rCh3xiw4VEAAYASAAEgKTWPD_BwE")
                url.add("https://www.healthmug.com/")
                url.add("https://homeomart.com/")
                val mIntent = Intent(
                    this, DetailActivity::class.java
                )
                mIntent.putIntegerArrayListExtra("res", res)
                mIntent.putStringArrayListExtra("names", names)
                mIntent.putStringArrayListExtra("url", url)
                startActivity(mIntent)
            }
            4 -> {
                val mIntent = Intent(
                    this, ExtraStockActivity::class.java
                )
                startActivity(mIntent)
            }
        }
    }


}
