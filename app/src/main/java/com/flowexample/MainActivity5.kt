package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class MainActivity5 : AppCompatActivity() {

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

    //events
    private fun consumer1() {
        CoroutineScope(Dispatchers.Main).launch {
            val data = producer()
            data.onStart {
                Log.w("TAG", "consumer - onStart")
            }.onCompletion {
                Log.w("TAG", "consumer - onCompletion")
            }.onEach {
                Log.w("TAG", "consumer - onEach")
            }
                .collect {
                    Log.w("TAG", "consumer - $it")
                }
        }
    }
}