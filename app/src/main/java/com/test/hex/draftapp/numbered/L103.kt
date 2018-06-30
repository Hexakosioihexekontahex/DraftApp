package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import com.test.hex.draftapp.R

class L103 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var downPI = 0
        var upPI = 0
        var inTouch = false
        val sb = StringBuilder()
        setContentView(TextView(this).apply {
            textSize = 15F
            setTextColor(resources.getColor(R.color.Gray))
            setOnTouchListener { v, event ->
                when (event.actionMasked) {
                    MotionEvent.ACTION_DOWN -> {
                        inTouch = true
                        downPI = event.actionIndex
                    }
                    MotionEvent.ACTION_POINTER_DOWN -> {
                        downPI = event.actionIndex
                    }
                    MotionEvent.ACTION_UP -> {
                        inTouch = false
                        sb.setLength(0)
                        upPI = event.actionIndex
                    }
                    MotionEvent.ACTION_POINTER_UP -> {
                        upPI = event.actionIndex
                    }
                    MotionEvent.ACTION_MOVE -> {
                        sb.setLength(0)

                        for (i in 0..9) {
                            sb.append("Index = $i")
                            if (i < event.pointerCount) {
                                sb.append(", ID = ${event.getPointerId(i)}")
                                        .append(", X = ${event.getX(i)}")
                                        .append(", Y = ${event.getY(i)}")
                            } else {
                                sb.append(", ID = ")
                                        .append(", X = ")
                                        .append(", Y = ")
                            }
                            sb.append("\r\n")
                        }
                    }
                }
                val result = "down: $downPI\nup: $upPI\n${
                    if (inTouch) "pointerCount = ${event.pointerCount}\n$sb" else ""
                }"
                text = result
                true
            }
        })
    }
}
