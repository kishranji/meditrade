package com.medicine.app

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_extra_stock.*


class ViewExtraStock : AppCompatActivity() {

    var mStockLits: ExtraStockModel = ExtraStockModel()
    var mSelectedPos: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_extra_stock)
        mSelectedPos = intent.getIntExtra("pos", 0)
        val gson = Gson()
        val value = APPPrefs.getString("mList", "")
        if (!value.isNullOrEmpty()) {
            mStockLits = gson.fromJson<ExtraStockModel>(value, ExtraStockModel::class.java!!)
            medicineName.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Medicine_name!!)
            username.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.User_name!!)
            batchNo.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Medicine_Batch_no!!)
            numberoditems.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Number_of_items!!)
            expiry_date_add.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Expiry_date!!)
            actualPrice.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Actual_price!!)
            price.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Price!!)
            address.text = toEditable(mStockLits.data?.get(mSelectedPos)!!.Pickup_Address!!)
        }
    }

    fun toEditable(value: String): Editable {
        return Editable.Factory.getInstance().newEditable(value)
    }

}
