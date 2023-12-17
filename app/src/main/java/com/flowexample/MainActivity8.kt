package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class MainActivity8 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            producer()
            consumer1()
            consumer2()
        }
    }

    //sharedflow types - mutable & immutable
    private fun producer() : Flow<Int> {
        val mutableSharedFlow = MutableSharedFlow<Int>()
        CoroutineScope(Dispatchers.Main).launch {
            val list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
            list.forEach {
                mutableSharedFlow.emit(it)
                delay(1000)
            }
        }
        return mutableSharedFlow
    }

    private fun consumer1() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            data.collect {
                    Log.w("TAG", "consumer 1- $it")
                }
        }
    }

    private fun consumer2() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            delay(5000)
            data.collect {
                    Log.w("TAG", "consumer 2- $it")
                }
        }
    }
}