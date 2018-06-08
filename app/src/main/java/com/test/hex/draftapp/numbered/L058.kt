package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R

private const val DIALOG_TIME = 1

class L058 : AppCompatActivity() {

    private var myHour = 14
    private var myMinute = 35

    lateinit var tvTime: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l058)

        tvTime = findViewById(R.id.tvTime)
        tvTime.setOnClickListener {
            showDialog(DIALOG_TIME, null)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_TIME) {
            return TimePickerDialog(this, myCallBack,
                    myHour, myMinute, true)
        }
        return super.onCreateDialog(id)
    }

    @SuppressLint("SetTextI18n")
    private var myCallBack = TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
        myHour = hourOfDay
        myMinute = minute
        tvTime.text = "Time is $myHour hours $myMinute minutes"
    }
}
