package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R

class L069 : AppCompatActivity() {

    internal val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l069)

        findViewById<Button>(R.id.bSend).setOnClickListener {
            val l069Obj = L069Object("text", 1)
            val intention = Intent(this, L069Second::class.java)
            intention.putExtra(L069Object::class.java.canonicalName, l069Obj)
            Log.d(LOG_TAG, "startActivity")
            startActivity(intention)
        }
    }
}
