package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l019_calculator.*

class L019Calculator : AppCompatActivity(), View.OnClickListener {

    private val MENU_QUIT_ID = 1
    private val MENU_RESET_ID = 2

    private lateinit var oper: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l019_calculator)

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
            button1.id -> {
                oper = "+"
                result = num1 + num2
            }
            button2.id -> {
                oper = "-"
                result = num1 - num2
            }
            button3.id -> {
                oper = "*"
                result = num1 * num2
            }
            button4.id -> {
                oper = "/"
                result = num1 / num2
            }
        }
        if (oper == "/" && num2 == 0F) {
            this.result.text = "На ноль делить нельзя!"
        } else {
            this.result.text = "$num1 $oper $num2 = $result"
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
                result.text = ""
            }
            MENU_QUIT_ID -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
