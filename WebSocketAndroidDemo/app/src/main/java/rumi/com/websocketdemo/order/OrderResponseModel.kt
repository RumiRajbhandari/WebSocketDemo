package rumi.com.websocketdemo.order

import com.google.gson.annotations.SerializedName

data class OrderResponseModel(
    @SerializedName("msg") val msg: String,
    @SerializedName("outlet_id") val outletId: Int,
    @SerializedName("skuId") val skuId: Int,
    @SerializedName("quantity") val quantity: Int
) {
}