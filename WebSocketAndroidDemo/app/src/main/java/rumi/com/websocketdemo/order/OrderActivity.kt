package rumi.com.websocketdemo.order

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import com.google.gson.Gson
import com.google.gson.JsonElement
import kotlinx.android.synthetic.main.activity_main.btn_order
import kotlinx.android.synthetic.main.activity_order.*
import org.json.JSONObject
import rumi.com.websocketdemo.R
import rumi.com.websocketdemo.db.WebSocketDatabase
import java.net.URISyntaxException

class OrderActivity : AppCompatActivity(), OrdersContract.View {

    lateinit var socket: Socket
    lateinit var presenter: OrdersPresenter

    companion object{
        fun getIntent(context: Context) = Intent(context, OrderActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Order"
        }
        presenter = OrdersPresenter(this)
        presenter.initializeData()

        connectToServer()

        btn_order.setOnClickListener {
            placeOrder()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId){
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun connectToServer(){
        try {
            socket = IO.socket("http://10.168.3.21:4000")
            socket.connect()
            socket.emit("join", "rumi")

            socket.on("userjoinedthechat") {
                runOnUiThread {
                    Toast.makeText(this, it[0].toString(), Toast.LENGTH_SHORT).show()

                }
            }
            listenOrderEvents()

        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }
    }

    private fun placeOrder(){
        val skuId = et_sku_id.text.toString().toInt()
        val quantity = et_quantity.text.toString().toInt()
        val outletId = et_outlet_id.text.toString().toInt()
        val ordersModel = OrdersModel(skuId, quantity, outletId)
        val req = Gson().toJson(ordersModel)
        socket.emit("orders",req)

    }

    private fun listenOrderEvents(){
        socket.on("orders"){
            runOnUiThread{
                val gson = Gson()
                val responseModel = gson.fromJson(it[0].toString(), OrderResponseModel::class.java)
                presenter.updateStocks(responseModel.skuId, responseModel.quantity)
                Toast.makeText(this, "${responseModel.msg}. Only ${responseModel.quantity} no is in stock", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getContext(): Context {
        return this
    }
}
