package com.test.hex.draftapp.numbered

import android.app.AlertDialog
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val DIALOG = 1
private val sdf = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)

class L061 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l061)

        findViewById<Button>(R.id.button1).setOnClickListener {
            showDialog(DIALOG)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        Log.d("myLog", "onCreateDialog")
        if (id == DIALOG) {
            val adb = AlertDialog.Builder(this)
            adb.setTitle("Time is")
            adb.setMessage(sdf.format(Date(System.currentTimeMillis())))
            return adb.create()
        }
        return super.onCreateDialog(id)
    }

    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        super.onPrepareDialog(id, dialog)
        Log.d("myLog", "onPrepareDialog")
        if (id == DIALOG) {
            (dialog as AlertDialog).setMessage(sdf.format(Date(System.currentTimeMillis())))
        }
    }
}
