package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R

class L069Second : AppCompatActivity() {

    internal val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l069_second)

        Log.d(LOG_TAG, "getParcelableExtra")
        val l069Obj: L069Object = intent.getParcelableExtra(
                L069Object::class.java.canonicalName
        )
        Log.d(LOG_TAG, "l069Obj: ${l069Obj.s}, ${l069Obj.i}")
    }
}
