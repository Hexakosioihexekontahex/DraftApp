package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.*
import com.test.hex.draftapp.R

private const val TAG = "myLogs"

class L010ClickButtons : AppCompatActivity() {

    lateinit var textView: TextView
    lateinit var button: Button
    lateinit var button1: Button
    lateinit var button2: Button
    lateinit var button3: Button
    lateinit var checkBox: CheckBox
    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox
    lateinit var textView1: TextView
    lateinit var textView2: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l010_click_buttons)

        textView = findViewById(R.id.textView)
        textView.text = getString(R.string.click_any_button)

        Log.d(TAG, "Найдем три кнопки")

        button1 = findViewById(R.id.button1)
        Log.d(TAG, "Найдена кнопка 1")
        button2 = findViewById(R.id.button2)
        Log.d(TAG, "Найдена кнопка 2")
        button3 = findViewById(R.id.button3)
        Log.d(TAG, "Найдена кнопка 3")

        button1.setOnClickListener {
            textView.text = getString(R.string.text1)
            Log.d(TAG, "Нажата кнопка 1")
            Toast.makeText(this, "Нажата кнопка 1",
                    Toast.LENGTH_SHORT)
                    .centerGravitation()
                    .show()
        }

        button2.setOnClickListener {
            textView.text = getString(R.string.text2)
            Log.d(TAG, "Нажата кнопка 2")
            Toast.makeText(this, "Нажата кнопка 2",
                    Toast.LENGTH_SHORT)
                    .centerGravitation()
                    .show()
        }
        button3.setOnClickListener {
            textView.text = getString(R.string.text3)
            Log.d(TAG, "Нажата кнопка 3")
            Toast.makeText(this, "Нажата кнопка 3",
                    Toast.LENGTH_SHORT)
                    .centerGravitation()
                    .show()
        }
        textView.setOnClickListener {
            textView.text = getString(R.string.click_any_button)
//                    button3.text = getString(R.string.button_number_three)
            val toast = Toast.makeText(this, "Нажат текст",
                    Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 0)
            val toastImage = toast.view as LinearLayout
            val imageView = ImageView(this)
            imageView.setImageResource(R.drawable.image001)
            toastImage.addView(imageView, 0)
            toast.show()
        }
    }

    private fun Toast.centerGravitation(): Toast {
        this.setGravity(Gravity.CENTER, 0, 0)
        return this
    }
}
