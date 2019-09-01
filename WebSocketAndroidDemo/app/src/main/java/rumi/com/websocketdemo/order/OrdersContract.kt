package rumi.com.websocketdemo.order

import android.content.Context

interface OrdersContract {
    interface Presenter{
        fun initializeData()
        fun updateStocks(skuId: Int, quantity: Int)
    }

    interface View{
        fun getContext(): Context

    }
}