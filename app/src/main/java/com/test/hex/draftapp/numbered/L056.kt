package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.test.hex.draftapp.R

class L056 : AppCompatActivity() {

    val data = arrayOf("one", "two", "three", "four", "five")
    lateinit var aAdapter: ArrayAdapter<String>
//    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l056)

        aAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
                data)
        aAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

//        spinner = findViewById(R.id.spinner)
        findViewById<Spinner>(R.id.spinner).apply{
            adapter = aAdapter
            prompt = "Title"
            setSelection(2)
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    Toast.makeText(baseContext, "Position = $position", Toast.LENGTH_SHORT)
                            .show()
                }
            }
        }
    }
}
