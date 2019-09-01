package rumi.com.websocketdemo.order

import rumi.com.websocketdemo.db.WebSocketDatabase
import rumi.com.websocketdemo.db.dao.StockDao
import rumi.com.websocketdemo.db.entities.StockEntity

class OrdersPresenter(val view: OrdersContract.View) : OrdersContract.Presenter {

    lateinit var db: WebSocketDatabase
    lateinit var stockDao: StockDao
    val quantity = listOf(10,23,20,30,40)


    override fun initializeData() {
        db = WebSocketDatabase.getInstance(view.getContext())
        stockDao = db.getStocksDao()
        quantity.forEachIndexed { index, quantity ->
            saveStock(index,quantity)
        }
    }

    private fun saveStock(index: Int, quantity: Int){
        var skuId = index + 1
        if (stockDao.getStockCount(skuId) == 0)
            stockDao.insert(StockEntity(skuId = skuId, quantity = quantity))
    }


    override fun updateStocks(skuId: Int, quantity: Int) {
        stockDao.updateStocks(skuId, quantity)
    }
}