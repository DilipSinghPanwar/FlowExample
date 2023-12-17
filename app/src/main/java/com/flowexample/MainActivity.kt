package com.flowexample

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnStart).setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                getUserList().forEach {
                    Log.w("TAG", it)
                }
            }
        }
    }

    private suspend fun getUserList(): List<String> {
        val userList = mutableListOf<String>()
        userList.add(getUser(1))
        userList.add(getUser(2))
        userList.add(getUser(3))
        userList.add(getUser(4))
        return userList
    }

    private suspend fun getUser(id: Int): String {
        delay(5000)
        return "user: $id"
    }

}