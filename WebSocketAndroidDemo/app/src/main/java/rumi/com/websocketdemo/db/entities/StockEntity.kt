package rumi.com.websocketdemo.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import rumi.com.websocketdemo.db.Constants

@Entity(
    tableName = Constants.TBL_STOCKs
)
data class StockEntity (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Constants.STOCK_ID) var id: Int? = null,
    @ColumnInfo(name = Constants.STOCK_SKU_ID) var skuId: Int,
    @ColumnInfo(name = Constants.STOCK_QUANTITY) var quantity: Int
)