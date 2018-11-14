package com.pixelarts.week3daily3classexercise

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String = "MainActivity"

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

    override fun onResume() {
        super.onResume()

        var intent = Intent()
        var counter : Int

        try {
            Thread.sleep(1000)
            counter = intent.getIntExtra("counter", 0)
            tvDisplayCounter.text = counter.toString()

            Log.d(TAG, counter.toString())
        }catch (e : InterruptedException){
            e.printStackTrace()
        }
    }
}
