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
                    "004" -> {
                        intention = Intent(this, L004ActivityMain::class.java)
                        startActivity(intention)
                    }
                    "005" -> {
                        intention = Intent(this, L005Myscreen::class.java)
                        startActivity(intention)
                    }
                    "006_layout" -> {
                        intention = Intent(this, L006Layout::class.java)
                        startActivity(intention)
                    }
                    "006_linear" -> {
                        intention = Intent(this, L006LinearInLinear::class.java)
                        startActivity(intention)
                    }
                    "006_relative" -> {
                        intention = Intent(this, L006RelativeLayout::class.java)
                        startActivity(intention)
                    }
                    "006_table" -> {
                        intention = Intent(this, L006TableLayout::class.java)
                        startActivity(intention)
                    }
                    "007_gravity" -> {
                        intention = Intent(this, L007LayoutGravity::class.java)
                        startActivity(intention)
                    }
                    "007_margin" -> {
                        intention = Intent(this, L007LayoutMargin::class.java)
                        startActivity(intention)
                    }
                    "007_weight" -> {
                        intention = Intent(this, L007LayoutWeight::class.java)
                        startActivity(intention)
                    }
                    "008" -> {
                        intention = Intent(this, L008ViewById::class.java)
                        startActivity(intention)
                    }
                    "009" -> {
                        intention = Intent(this, L009ClickButton::class.java)
                        startActivity(intention)
                    }
                    "010" -> {
                        intention = Intent(this, L010ClickButtons::class.java)
                        startActivity(intention)
                    }
                    "011" -> {
                        intention = Intent(this, L011Resources::class.java)
                        startActivity(intention)
                    }
                    "014" -> {
                        intention = Intent(this, L014Menu::class.java)
                        startActivity(intention)
                    }
                    "015" -> {
                        intention = Intent(this, L015ContextMenu::class.java)
                        startActivity(intention)
                    }
                    "016" -> {
                        intention = Intent(this, L016::class.java)
                        startActivity(intention)
                    }
                    "017" -> {
                        intention = Intent(this, L017CreateDeleteDynamicElements::class.java)
                        startActivity(intention)
                    }
                    "018" -> {
                        intention = Intent(this, L018DynamicWeight::class.java)
                        startActivity(intention)
                    }
                    "019" -> {
                        intention = Intent(this, L019Calculator::class.java)
                        startActivity(intention)
                    }
                    "020" -> {
                        intention = Intent(this, L020ComponentAnimation::class.java)
                        startActivity(intention)
                    }
                    "023" -> {
                        intention = Intent(this, L023::class.java)
                        startActivity(intention)
                    }
                    "026" -> {
                        intention = Intent(this, L026::class.java)
                        startActivity(intention)
                    }
                    "027" -> {
                        intention = Intent(this, L027::class.java)
                        startActivity(intention)
                    }
                    "028" -> {
                        intention = Intent(this, L028::class.java)
                        startActivity(intention)
                    }
                    "029" -> {
                        intention = Intent(this, L029::class.java)
                        startActivity(intention)
                    }
                    "030" -> {
                        intention = Intent(this, L030::class.java)
                        startActivity(intention)
                    }
                    "031" -> {
                        intention = Intent(this, L031::class.java)
                        startActivity(intention)
                    }
                    "032" -> {
                        intention = Intent(this, L032::class.java)
                        startActivity(intention)
                    }
                    "033" -> {
                        intention = Intent(this, L033::class.java)
                        startActivity(intention)
                    }
                    "034" -> {
                        intention = Intent(this, L034::class.java)
                        startActivity(intention)
                    }
                    "036" -> {
                        intention = Intent(this, L036::class.java)
                        startActivity(intention)
                    }
                    "037" -> {
                        intention = Intent(this, L037::class.java)
                        startActivity(intention)
                    }
                    "038" -> {
                        intention = Intent(this, L038::class.java)
                        startActivity(intention)
                    }
                    "039" -> {
                        intention = Intent(this, L039::class.java)
                        startActivity(intention)
                    }
                    "040" -> {
                        intention = Intent(this, L040::class.java)
                        startActivity(intention)
                    }
                    "041" -> {
                        intention = Intent(this, L041::class.java)
                        startActivity(intention)
                    }
                    "042" -> {
                        intention = Intent(this, L042::class.java)
                        startActivity(intention)
                    }
                    "043" -> {
                        intention = Intent(this, L043::class.java)
                        startActivity(intention)
                    }
                    "044" -> {
                        intention = Intent(this, L044::class.java)
                        startActivity(intention)
                    }
                    "045" -> {
                        intention = Intent(this, L045::class.java)
                        startActivity(intention)
                    }
                    "046" -> {
                        intention = Intent(this, L046::class.java)
                        startActivity(intention)
                    }
                    "048" -> {
                        intention = Intent(this, L048::class.java)
                        startActivity(intention)
                    }
                    "049" -> {
                        intention = Intent(this, L049::class.java)
                        startActivity(intention)
                    }
                    "050" -> {
                        intention = Intent(this, L050::class.java)
                        startActivity(intention)
                    }
                    "051" -> {
                        intention = Intent(this, L051::class.java)
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
