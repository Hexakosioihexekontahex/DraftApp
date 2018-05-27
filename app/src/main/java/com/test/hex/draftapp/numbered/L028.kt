package com.test.hex.draftapp.numbered

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.test.hex.draftapp.R

class L028 : AppCompatActivity(), View.OnClickListener {

    lateinit var button: Button
    lateinit var etName: EditText
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l028)

        button = findViewById(R.id.button)
        button.setOnClickListener(this)

        etName = findViewById(R.id.etName)
    }

    override fun onClick(v: View?) {
        intention = Intent(this, L028Activity::class.java)
        intention.putExtra("name", etName.text.toString())
        startActivity(intention)
    }
}
