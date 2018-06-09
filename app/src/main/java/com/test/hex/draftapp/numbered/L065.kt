package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.test.hex.draftapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val DIALOG = 1

class L065 : AppCompatActivity() {
    private var btn = 0
    private val sdf = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
    private val textViews: MutableList<TextView> = mutableListOf()

    private lateinit var tvTime: TextView
    private lateinit var llView: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l065)

    }

    fun onClickBtn(view: View) {
        btn = view.id
        showDialog(DIALOG)
    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        adb.setTitle("Custom dialog")
        llView = (layoutInflater.inflate(R.layout.l065_dialog, null)) as LinearLayout
        adb.setView(llView)
        return adb.create()
    }

    @SuppressLint("SetTextI18n")
    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        super.onPrepareDialog(id, dialog)
        if (id == DIALOG) {
            tvTime = dialog?.window?.findViewById(R.id.tvTime)!!
            tvTime.text = sdf.format(Date(System.currentTimeMillis()))
            if (btn == R.id.btnAdd) {
                val tv = TextView(this)
                llView.addView(tv, ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT)
                )
                tv.text = "TextView ${textViews.size + 1}"
                textViews.add(tv)
            } else if (btn == R.id.btnDel) {
                if (textViews.isNotEmpty()) {
                    val tv = textViews[textViews.size - 1]
                    llView.removeView(tv)
                    textViews.remove(tv)
                }
            }
            dialog.window?.findViewById<TextView>(R.id.tvCount)?.text =
                    "${textViews.size} TextView-s"
        }
    }
}
