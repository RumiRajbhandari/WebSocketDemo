package rumi.com.websocketdemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import rumi.com.websocketdemo.db.dao.OrdersDao
import rumi.com.websocketdemo.db.dao.StockDao
import rumi.com.websocketdemo.db.entities.OrdersEntity
import rumi.com.websocketdemo.db.entities.StockEntity

@Database(
    entities = [
        StockEntity::class,
        OrdersEntity::class
    ],
    version = 1
)
abstract class WebSocketDatabase : RoomDatabase() {
    companion object {
        var instance: WebSocketDatabase? = null
        fun getInstance(context: Context): WebSocketDatabase {
            if (instance == null) {
                instance = createInstance(context)
            }
            return instance as WebSocketDatabase
        }

        fun createInstance(context: Context): WebSocketDatabase {
            return Room
                .databaseBuilder(context, WebSocketDatabase::class.java, Constants.DB_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun getOrdersDao(): OrdersDao
    abstract fun getStocksDao(): StockDao
}