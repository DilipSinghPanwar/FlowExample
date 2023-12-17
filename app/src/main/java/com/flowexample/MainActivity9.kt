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

class MainActivity9 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
//            producer1()
//            consumer1()
//            consumer2()
            producer2()
            consumer3()
        }
    }

    //stateflow - returns last value, maintain state
    private fun producer1() : Flow<Int> {
        val mutableStateFlow = MutableStateFlow(0)
        CoroutineScope(Dispatchers.Main).launch {
            val list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
            list.forEach {
                mutableStateFlow.emit(it)
                delay(1000)
            }
        }
        return mutableStateFlow
    }

    //stateflow - returns last value, maintain state
    private fun producer2() : StateFlow<Int> {
        val mutableStateFlow = MutableStateFlow(0)
        CoroutineScope(Dispatchers.Main).launch {
            val list = mutableListOf<Int>(1, 2, 3, 4, 5, 6, 7, 8, 9)
            list.forEach {
                mutableStateFlow.emit(it)
                delay(1000)
            }
        }
        return mutableStateFlow
    }

    private fun consumer3() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer2()
            val value = data.value
            Log.w("TAG", "consumer 3 value- $value")
            data.collect {
                Log.w("TAG", "consumer 3- $it")
            }
        }
    }

    private fun consumer1() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer1()
            data.collect {
                    Log.w("TAG", "consumer 1- $it")
                }
        }
    }

    private fun consumer2() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer1()
            delay(10000)
            data.collect {
                    Log.w("TAG", "consumer 2- $it")
                }
        }
    }
}