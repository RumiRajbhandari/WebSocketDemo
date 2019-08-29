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
import kotlinx.android.synthetic.main.activity_main.btn_order
import kotlinx.android.synthetic.main.activity_order.*
import rumi.com.websocketdemo.R
import java.net.URISyntaxException

class OrderActivity : AppCompatActivity() {

    lateinit var socket: Socket

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
        Toast.makeText(this, "button clicked $req", Toast.LENGTH_SHORT).show()
        socket.emit("orders",req)

    }

    private fun listenOrderEvents(){
        socket.on("orders"){
            runOnUiThread{
                println("order response is $it")
            }
        }
    }
}
