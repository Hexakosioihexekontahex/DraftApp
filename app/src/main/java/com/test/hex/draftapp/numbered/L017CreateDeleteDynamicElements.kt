package com.test.hex.draftapp.numbered

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l017_create_delete_dynamic_elements.*

class L017CreateDeleteDynamicElements : AppCompatActivity(), View.OnClickListener {

    var i_id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l017_create_delete_dynamic_elements)

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

                when (grGravity.checkedRadioButtonId) {
                    rbLeft.id -> {
                        btnGravity = Gravity.START
                    }
                    rbCenter.id -> {
                        btnGravity = Gravity.CENTER
                    }
                    rbRight.id -> {
                        btnGravity = Gravity.END
                    }
                }

                lParams.gravity = btnGravity
                val btnNew = Button(this)
                btnNew.text = etName.text.toString()
                btnNew.id = ++i_id
                llMain.addView(btnNew, lParams)
                btnNew.isAllCaps = false
                btnNew.setOnClickListener(this)
            }
            btnClear.id -> {
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
                    v.setBackgroundColor(Color.BLACK)
                    (v as Button).setTextColor(Color.WHITE)
                }
            }
        }
    }
}
