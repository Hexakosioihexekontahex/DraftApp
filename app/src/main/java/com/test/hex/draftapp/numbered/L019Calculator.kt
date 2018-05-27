package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.test.hex.draftapp.R

class L019Calculator : AppCompatActivity(), View.OnClickListener {

    private val MENU_QUIT_ID = 1
    private val MENU_RESET_ID = 2

    lateinit var etNum1: EditText
    lateinit var etNum2: EditText
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var button4: Button
    lateinit var tvResult: TextView
    private lateinit var oper: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l019_calculator)

        etNum1 = findViewById(R.id.etNum1)
        etNum2 = findViewById(R.id.etNum2)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        tvResult = findViewById(R.id.result)

        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var num1 = 0F
        var num2 = 0F
        var result = 0F

        num1 = etNum1.text.toString().toFloat()
        num2 = etNum2.text.toString().toFloat()
        when (v?.id) {
            R.id.button1 -> {
                oper = "+"
                result = num1 + num2
            }
            R.id.button2 -> {
                oper = "-"
                result = num1 - num2
            }
            R.id.button3 -> {
                oper = "*"
                result = num1 * num2
            }
            R.id.button4 -> {
                oper = "/"
                result = num1 / num2
            }
        }
        if (oper == "/" && num2 == 0F) {
            tvResult.text = "На ноль делить нельзя!"
        } else {
            tvResult.text = "$num1 $oper $num2 = $result"
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add(0, MENU_RESET_ID, 0, "Reset")
        menu?.add(0, MENU_QUIT_ID, 0, "Quit")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_RESET_ID -> {
                etNum1.setText("")
                etNum2.setText("")
                tvResult.text = ""
            }
            MENU_QUIT_ID -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
