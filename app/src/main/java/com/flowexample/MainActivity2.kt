package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            producer()
            consumer()
        }
    }

    private fun producer() = flow<Int>{
        val list = mutableListOf<Int>(1,2,3,4,5,6,7,8,9)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

    private fun consumer(){
        val job = CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            data.collect{
                Log.w("TAG", "consumer: $it")
            }
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(4500)
            job.cancel()
        }
    }
}