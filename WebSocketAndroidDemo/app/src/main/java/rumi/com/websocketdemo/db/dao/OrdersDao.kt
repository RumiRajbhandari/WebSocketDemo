package rumi.com.websocketdemo.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import rumi.com.websocketdemo.db.entities.OrdersEntity

@Dao
interface OrdersDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(ordersEntity: OrdersEntity)

    @Query("select * from orders")
    fun getOrders(): List<OrdersEntity>
}