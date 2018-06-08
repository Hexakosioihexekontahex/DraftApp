package com.test.hex.draftapp.numbered

import android.app.DatePickerDialog
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.test.hex.draftapp.R

private const val DIALOG_DATE = 1

class L059 : AppCompatActivity() {

    private var myYear = 2005
    private var myMonth = 2
    private var myDay = 13
    lateinit var tvDate: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l059)

        tvDate = findViewById(R.id.tvDate)
        tvDate.setOnClickListener {
            showDialog(DIALOG_DATE)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_DATE) {
            return DatePickerDialog(this, myCallBack,
                    myYear, myMonth, myDay)
        }
        return super.onCreateDialog(id)
    }

    private val myCallBack = DatePickerDialog.OnDateSetListener {
        view, year, month, dayOfMonth ->
        myYear = year
        myMonth = month
        myDay = dayOfMonth
        tvDate.text = "Date is ${
        if (myDay<10) "0" else "" }$myDay/${
        if (myMonth<10) "0" else "" }$myMonth/$myYear"
    }
}
