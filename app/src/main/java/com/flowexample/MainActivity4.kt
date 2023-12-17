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

class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            producer()
            //multiple consumer
            consumer1()
            consumer2()
        }
    }

    private fun producer() = flow<Int>{
        val list = mutableListOf<Int>(1,2,3,4,5,6,7,8,9)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

    private fun consumer1(){
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            data.collect{
                Log.w("TAG", "consumer - 1: $it")
            }
        }
    }
    private fun consumer2(){
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            delay(4000)
            data.collect{
                Log.w("TAG", "consumer - 2: $it")
            }
        }
    }
}