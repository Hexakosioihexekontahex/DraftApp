package com.test.hex.draftapp.numbered

import android.app.AlertDialog
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"
private const val DIALOG = 1

class L066 : AppCompatActivity() {
    lateinit var dialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l066)

        findViewById<Button>(R.id.button).setOnClickListener {
            showDialog(DIALOG)
            /*              */
            val h = Handler()
            h.postDelayed({ method1() }, 2000)
            /*
            h.postDelayed(new Runnable() {
                public void run(){
                    method1();
                }
            }, 2000);
            */
            h.postDelayed({ method2() }, 4000)
        }
    }

    private fun method1(){
//        dialog.dismiss()
//        dialog.cancel()
//        dialog.hide()
//        dismissDialog(DIALOG)
        removeDialog(DIALOG)
    }
    private fun method2(){
        showDialog(DIALOG)
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG) {
            Log.d(LOG_TAG, "Create")
            val adb = AlertDialog.Builder(this)
            adb.apply {
                setTitle("Title")
                setMessage("Message")
                setPositiveButton("OK", null)
            }
            dialog = adb.create()
            dialog.apply {
                setOnShowListener { Log.d(LOG_TAG, "Show") }
                setOnCancelListener { Log.d(LOG_TAG, "Cancel") }
                setOnDismissListener { Log.d(LOG_TAG, "Dismiss") }
            }
            return dialog
        }
        return super.onCreateDialog(id)
    }
}
