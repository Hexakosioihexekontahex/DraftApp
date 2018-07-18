package com.test.hex.draftapp.numbered

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l033.*

private const val SAVED_TEXT = "saved_text"

class L033 : AppCompatActivity() {
    lateinit var sPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l033)

        btnSave.setOnClickListener { saveText() }
        btnLoad.setOnClickListener { loadText() }

        loadText()
    }

    private fun saveText(){
        sPref = getSharedPreferences("sPref", MODE_PRIVATE)
        val ed = sPref.edit()
        ed.putString(SAVED_TEXT, etText.text.toString())
        ed.apply()
        Toast.makeText(this,
                "Text saved",
                Toast.LENGTH_SHORT)
                .show()
    }

    private fun loadText(){
        sPref = getSharedPreferences("sPref", MODE_PRIVATE)
        val savedText = sPref.getString(SAVED_TEXT, "")
        etText.setText(savedText)
        Toast.makeText(this,
                "Text loaded", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onDestroy() {
        super.onDestroy()

        saveText()
    }
}
