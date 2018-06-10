package com.test.hex.draftapp.numbered

import android.app.Dialog
import android.app.ProgressDialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Button
import com.test.hex.draftapp.R

class L067 : AppCompatActivity() {

    lateinit var pd: ProgressDialog
    lateinit var h: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l067)

        findViewById<Button>(R.id.bInfinity).setOnClickListener {
            pd = ProgressDialog(this)
            pd.apply {
                setTitle("Wait")
                setMessage("Infinity =)")
                setButton(Dialog.BUTTON_POSITIVE, "OK") { dialog, which -> }
            }
            pd.show()
        }
        findViewById<Button>(R.id.bProgress).setOnClickListener {
            pd = ProgressDialog(this)
            pd.apply {
                setTitle("Wait")
                setMessage("Progress")
                setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
                max = 2345
                isIndeterminate = true
            }
            pd.show()
            h = Handler { _ ->
                run {
                    pd.isIndeterminate = false
                    if (pd.progress < pd.max) {
                        pd.incrementProgressBy(50)
                        pd.incrementSecondaryProgressBy(75)
                        h.sendEmptyMessageDelayed(0, 100)
                    } else {
                        pd.dismiss()
                        false
                    }
                }
            }
            h.sendEmptyMessageDelayed(0, 2000)
        }
    }
}
