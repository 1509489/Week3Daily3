package com.pixelarts.week3daily3classexercise

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = "MainActivity"

    lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view : View)
    {

       var serviceIntent = Intent(this, MyIntentService::class.java)

        when(view.id )
        {
            R.id.btnUpdateCounter -> {
                serviceIntent.action = "updateCounter"
                startService(serviceIntent)
            }
        }
    }

    val broadcastReceiver = object : MyReceiver(){
        override fun onReceive(context: Context, intent:Intent) {
            var counterExtra = intent.getIntExtra("counterExtra",0)
            tvDisplayCounter.text = counterExtra.toString()
        }
    }

    override fun onStart() {
        super.onStart()

        intentFilter = IntentFilter()
        intentFilter.addAction("counterBroadcast")
        registerReceiver(broadcastReceiver, intentFilter)

        when(intent.action)
        {
            "counterBroadcast" ->{
                var counter = intent.getIntExtra("counterExtra", 0)
                tvDisplayCounter.text = counter.toString()
            }
        }
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(broadcastReceiver)
    }

}
