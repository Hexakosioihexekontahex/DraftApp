package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R

class L024 : AppCompatActivity() {

    val TAG = "Lifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l024)

        Log.d(TAG, "L024 onCreate")
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "L024 onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "L024 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "L024 onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "L024 onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "L024 onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "L024 onRestart")
    }
}
