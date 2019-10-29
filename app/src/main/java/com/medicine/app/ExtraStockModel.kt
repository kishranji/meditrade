package com.medicine.app

import com.google.gson.annotations.SerializedName

class ExtraStockModel {
    @SerializedName("data")
    var data: ArrayList<item>? = ArrayList()

    class item {
        @SerializedName("Medicine_name")
        var Medicine_name: String? = null
        @SerializedName("User_name")
        var User_name: String? = null
        @SerializedName("Number_of_items")
        var Number_of_items: String? = null
        @SerializedName("Medicine_Batch_no")
        var Medicine_Batch_no: String? = null
        @SerializedName("Expiry_date")
        var Expiry_date: String? = null
        @SerializedName("Actual_price")
        var Actual_price: String? = null
        @SerializedName("Price")
        var Price: String? = null
        @SerializedName("Pickup_Address")
        var Pickup_Address: String? = null
    }
}