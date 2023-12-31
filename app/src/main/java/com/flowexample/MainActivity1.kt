package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity1 : AppCompatActivity() {

    val channel = Channel<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            producer()
            consumer()
        }
    }

    private fun producer(){
        CoroutineScope(Dispatchers.Main).launch {
            channel.send(1)
            channel.send(2)
            channel.send(3)
            channel.send(4)
        }
    }

    private fun consumer(){
        CoroutineScope(Dispatchers.Main).launch {
            Log.w("TAG", "consumer: "+channel.receive())
            Log.w("TAG", "consumer: "+channel.receive())
            Log.w("TAG", "consumer: "+channel.receive())
            Log.w("TAG", "consumer: "+channel.receive())
        }
    }

}