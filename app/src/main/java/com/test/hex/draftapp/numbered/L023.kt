package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R


class L023 : AppCompatActivity(), View.OnClickListener {

    val TAG = "lifecycle"
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l023)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)

        Log.d(TAG, "L023 onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "L023 onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "L023 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "L023 onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "L023 onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "L023 onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "L023 onRestart")
    }

    override fun onClick(v: View?) {
        val intention = Intent(this, L024::class.java)
        startActivity(intention)
        Log.d(TAG, "L023 -> startActivity(L024)")
    }
}
