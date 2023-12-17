package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainActivity6 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            producer()
            consumer1()
        }
    }

    private fun producer() = flow<Int> {
        val list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
        list.forEach {
            delay(1000)
            emit(it)
        }
    }

    //terminal operator - this function are suspended by default
    private fun consumer1() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            val result = data.first()
            Log.w("TAG", "consumer - $result")
            val result1 = data.toList()
            Log.w("TAG", "consumer - $result1")
//            data.collect {
//                    Log.w("TAG", "consumer - $it")
//                }
        }
    }
}