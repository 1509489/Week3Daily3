package com.pixelarts.week3daily3classexercise

import android.app.IntentService
import android.content.Intent
import android.content.Context
import android.util.Log
import java.util.*
import kotlin.concurrent.thread


class MyIntentService : IntentService("MyIntentService") {
    val TAG : String = "MyIntentService"

    var counter : Int = 0

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate")

    }

    override fun onHandleIntent(intent: Intent?) {
        Log.d(TAG, "onHandleIntent")


        val broadcastIntent = Intent()

        val action = intent?.action
        if (action == "updateCounter")
        {
            Log.d(TAG, "Received Intent action")
            for (i : Int in 1..100)
            {
                try {
                    Thread.sleep(1000)
                    counter++

                    broadcastIntent.action = "counterBroadcast"
                    broadcastIntent.putExtra("counterExtra", counter)
                    sendBroadcast(broadcastIntent)

                    Log.d(TAG, "Counter: $counter")
                }catch (e :InterruptedException)
                {
                    e.printStackTrace()
                }

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "onDestroy")
    }
}
