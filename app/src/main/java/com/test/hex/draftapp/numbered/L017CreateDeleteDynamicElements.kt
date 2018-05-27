package com.test.hex.draftapp.numbered

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.test.hex.draftapp.R

class L017CreateDeleteDynamicElements : AppCompatActivity(), View.OnClickListener {

    lateinit var llMain: LinearLayout
    lateinit var rgGravity: RadioGroup
    lateinit var etName: EditText
    lateinit var btnCreate: Button
    lateinit var btnClear: Button
    var i_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l017_create_delete_dynamic_elements)

        llMain = findViewById(R.id.llMain)
        rgGravity = findViewById(R.id.grGravity)
        etName = findViewById(R.id.etName)
        btnCreate = findViewById(R.id.btnCreate)
        btnClear = findViewById(R.id.btnClear)

        btnCreate.setOnClickListener(this)
        btnClear.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnCreate -> {
                val lParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                )
                var btnGravity = Gravity.START

                when (rgGravity.checkedRadioButtonId) {
                    R.id.rbLeft -> {
                        btnGravity = Gravity.START
                    }
                    R.id.rbCenter -> {
                        btnGravity = Gravity.CENTER
                    }
                    R.id.rbRight -> {
                        btnGravity = Gravity.END
                    }
                }

                lParams.gravity = btnGravity
                val btnNew = Button(this)
                btnNew.text = etName.text.toString()
                btnNew.id = ++i_id
                llMain.addView(btnNew, lParams)
                btnNew.setAllCaps(false)
                btnNew.setOnClickListener(this)
            }
            R.id.btnClear -> {
                llMain.removeAllViews()
                i_id = 0
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show()
            }
            else -> {
                if (v?.id != null) {
                    Toast.makeText(this,
                            "You pressed on ${v.id} button",
                            Toast.LENGTH_SHORT)
                            .show()
                    findViewById<Button>(v.id).setBackgroundColor(Color.BLACK)
                    findViewById<Button>(v.id).setTextColor(Color.WHITE)
                }
            }
        }
    }
}
