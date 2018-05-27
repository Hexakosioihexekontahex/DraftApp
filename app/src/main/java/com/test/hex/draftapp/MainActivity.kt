package com.test.hex.draftapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.test.hex.draftapp.numbered.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var okButton: Button
    lateinit var clButton: Button
    lateinit var editText: EditText
    lateinit var intention: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.l000)
        okButton = findViewById(R.id.okButton)
        okButton.setOnClickListener(this)
        clButton = findViewById(R.id.clButton)
        clButton.setOnClickListener(this)
        editText = findViewById(R.id.editText)
    }

    override fun onClick(v: View?) {
        val text = editText.text.toString()

        when(v?.id) {
            R.id.okButton -> {
                when (text) {
                    "l004" -> {
                        intention = Intent(this, L004ActivityMain::class.java)
                        startActivity(intention)
                    }
                    "l005" -> {
                        intention = Intent(this, L005Myscreen::class.java)
                        startActivity(intention)
                    }
                    "l006_layout" -> {
                        intention = Intent(this, L006Layout::class.java)
                        startActivity(intention)
                    }
                    "l006_linear" -> {
                        intention = Intent(this, L006LinearInLinear::class.java)
                        startActivity(intention)
                    }
                    "l006_relative" -> {
                        intention = Intent(this, L006RelativeLayout::class.java)
                        startActivity(intention)
                    }
                    "l006_table" -> {
                        intention = Intent(this, L006TableLayout::class.java)
                        startActivity(intention)
                    }
                    "l007_gravity" -> {
                        intention = Intent(this, L007LayoutGravity::class.java)
                        startActivity(intention)
                    }
                    "l007_margin" -> {
                        intention = Intent(this, L007LayoutMargin::class.java)
                        startActivity(intention)
                    }
                    "l007_weight" -> {
                        intention = Intent(this, L007LayoutWeight::class.java)
                        startActivity(intention)
                    }
                    "l008" -> {
                        intention = Intent(this, L008ViewById::class.java)
                        startActivity(intention)
                    }
                    "l009" -> {
                        intention = Intent(this, L009ClickButton::class.java)
                        startActivity(intention)
                    }
                    "l010" -> {
                        intention = Intent(this, L010ClickButtons::class.java)
                        startActivity(intention)
                    }
                    "l011" -> {
                        intention = Intent(this, L011Resources::class.java)
                        startActivity(intention)
                    }
                    "l014" -> {
                        intention = Intent(this, L014Menu::class.java)
                        startActivity(intention)
                    }
                    "l015" -> {
                        intention = Intent(this, L015ContextMenu::class.java)
                        startActivity(intention)
                    }
                    "l016" -> {
                        intention = Intent(this, L016::class.java)
                        startActivity(intention)
                    }
                    "l017" -> {
                        intention = Intent(this, L017CreateDeleteDynamicElements::class.java)
                        startActivity(intention)
                    }
                    "l018" -> {
                        intention = Intent(this, L018DynamicWeight::class.java)
                        startActivity(intention)
                    }
                    "l019" -> {
                        intention = Intent(this, L019Calculator::class.java)
                        startActivity(intention)
                    }
                    "l020" -> {
                        intention = Intent(this, L020ComponentAnimation::class.java)
                        startActivity(intention)
                    }
                    "l023" -> {
                        intention = Intent(this, L023::class.java)
                        startActivity(intention)
                    }
                    "l026" -> {
                        intention = Intent(this, L026::class.java)
                        startActivity(intention)
                    }
                    "l027" -> {
                        intention = Intent(this, L027::class.java)
                        startActivity(intention)
                    }

                    else -> Toast.makeText(this,
                            "Type exist activity name",
                            Toast.LENGTH_SHORT).show()
                }
            }
            R.id.clButton -> {
                editText.setText("")
            }
        }
    }
}
