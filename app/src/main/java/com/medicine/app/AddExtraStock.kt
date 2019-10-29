package com.medicine.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_extra_stock.*
import kotlinx.android.synthetic.main.inflate_extra_adapter.*


class AddExtraStock : AppCompatActivity() {

    var mStockLits: ExtraStockModel = ExtraStockModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_extra_stock)

        addStockBtn.setOnClickListener {

            if (isValid()) {
                val value = APPPrefs.getString("mList", "")
                if (value.isNullOrEmpty()) {
                    val mItem: ExtraStockModel.item = ExtraStockModel.item()
                    mItem.Medicine_name = medicineName.text.toString()
                    mItem.User_name = username.text.toString()
                    mItem.Medicine_Batch_no = batchNo.text.toString()
                    mItem.Number_of_items = numberoditems.text.toString()
                    mItem.Expiry_date = expiry_date_add.text.toString()
                    mItem.Actual_price = actualPrice.text.toString()
                    mItem.Price = price.text.toString()
                    mItem.Pickup_Address = address.text.toString()

                    var aTempVal: ArrayList<ExtraStockModel.item> = ArrayList()
                    aTempVal.add(mItem)
                    mStockLits.data = aTempVal

                    val gson = Gson()
                    val json = gson.toJson(mStockLits)
                    APPPrefs.putString("mList", json)
                    on_clear_call()
                } else {
                    val gson = Gson()
                    mStockLits =
                        gson.fromJson<ExtraStockModel>(value, ExtraStockModel::class.java!!)
                    val mItem: ExtraStockModel.item = ExtraStockModel.item()
                    mItem.Medicine_name = medicineName.text.toString()
                    mItem.User_name = username.text.toString()
                    mItem.Medicine_Batch_no = batchNo.text.toString()
                    mItem.Number_of_items = numberoditems.text.toString()
                    mItem.Expiry_date = expiry_date_add.text.toString()
                    mItem.Actual_price = actualPrice.text.toString()
                    mItem.Price = price.text.toString()
                    mItem.Pickup_Address = address.text.toString()
                    mStockLits.data?.add(mItem)
                    val json = gson.toJson(mStockLits)
                    APPPrefs.putString("mList", json)
                    on_clear_call()
                }

            }
        }
    }

    fun isValid(): Boolean {
        if (medicineName.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the medicine name", Toast.LENGTH_SHORT).show()
            return false
        }
        if (username.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the user name", Toast.LENGTH_SHORT).show()
            return false
        }

        if (batchNo.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the batch number", Toast.LENGTH_SHORT).show()
            return false
        }

        if (numberoditems.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the quantity", Toast.LENGTH_SHORT).show()
            return false
        }

        if (expiry_date_add.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the expiry date", Toast.LENGTH_SHORT).show()
            return false
        }

        if (actualPrice.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the actual price", Toast.LENGTH_SHORT).show()
            return false
        }

        if (price.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the price", Toast.LENGTH_SHORT).show()
            return false
        }

        if (address.text.toString().isNullOrEmpty()) {
            Toast.makeText(this, "Enter the address", Toast.LENGTH_SHORT).show()
            return false
        }

        return true

    }

    private fun on_clear_call() {
        onBackPressed()
        finish()
    }
}
