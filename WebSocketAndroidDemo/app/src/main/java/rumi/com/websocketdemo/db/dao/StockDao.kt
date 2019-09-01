package rumi.com.websocketdemo.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rumi.com.websocketdemo.db.entities.StockEntity

@Dao
interface StockDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(stockEntity: StockEntity)

    @Query("select * from stocks")
    fun getStock(): List<StockEntity>

    @Query("update stocks set quantity = :quantity")
    fun updateStocks(quantity: Int)

    @Query("select count(*) from stocks where sku_id = :skuId")
    fun getStockCount(skuId: Int): Int
}