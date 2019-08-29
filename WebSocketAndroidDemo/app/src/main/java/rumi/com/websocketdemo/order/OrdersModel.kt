package rumi.com.websocketdemo.order

import com.google.gson.annotations.SerializedName

data class OrdersModel(
    @SerializedName("sku_id") val sku_id: Int,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("outlet_id")val outletId: Int
) {
}