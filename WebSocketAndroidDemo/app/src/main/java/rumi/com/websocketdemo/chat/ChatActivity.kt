package rumi.com.websocketdemo.chat

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import kotlinx.android.synthetic.main.activity_chat.*
import org.json.JSONObject
import rumi.com.websocketdemo.R
import java.net.URISyntaxException

class ChatActivity : AppCompatActivity() {

    lateinit var socket: Socket
    var dataList = mutableListOf<ChatModel>()
    var chatAdapter: ChatAdapter? = null
    val USER_NAME = "rumi"

    companion object{
        fun getIntent(context: Context) = Intent(context, ChatActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            title = "Chat"
        }

        try {
            socket = IO.socket("http://10.168.3.21:4000")
            socket.connect()
            socket.emit("join", USER_NAME)
        } catch (e: URISyntaxException) {
            e.printStackTrace()
        }


        socket.on("userjoinedthechat") {
            runOnUiThread {
                Toast.makeText(this, it[0].toString(), Toast.LENGTH_SHORT).show()

            }
        }


        btn_send.setOnClickListener {
            if (!et_msg.text.toString().isNullOrEmpty()) {
                socket.emit("chatting", USER_NAME, et_msg.text.toString())
                et_msg.setText(" ")
            }
        }

        socket.on("chatting") {
            runOnUiThread {
                println("data is ${it[0]}")
                val data = it[0] as JSONObject
                val username = data.getString("username")
                val msg = data.getString("message")
                val message = ChatModel(username, msg)
                dataList.add(message)
                updateRecyclerViewData()
            }
        }
    }

    private fun updateRecyclerViewData() {
        if (chatAdapter != null) {
            chatAdapter!!.notifyDataSetChanged()
        } else {
            chatAdapter = ChatAdapter(dataList)
            rv.adapter = chatAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        socket.disconnect()
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
}
