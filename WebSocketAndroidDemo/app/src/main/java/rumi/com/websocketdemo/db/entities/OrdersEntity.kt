package rumi.com.websocketdemo.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rumi.com.websocketdemo.db.Constants

@Entity(
    tableName = Constants.TBL_ORDERS
)
class OrdersEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.ORDERS_ID) var id: Int?,
    @ColumnInfo(name = Constants.ORDERS_OUTLET_ID) var outletId: Int,
    @ColumnInfo(name = Constants.ORDERS_SKU_ID) var skuId: Int,
    @ColumnInfo(name = Constants.ORDERS_QUANTITY) var quantity: Int
)