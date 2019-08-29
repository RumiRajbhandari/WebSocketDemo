package rumi.com.websocketdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import rumi.com.websocketdemo.chat.ChatActivity
import rumi.com.websocketdemo.order.OrderActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_chat.setOnClickListener { startActivity(ChatActivity.getIntent(this)) }
        btn_order.setOnClickListener { startActivity(OrderActivity.getIntent(this)) }
    }
}
