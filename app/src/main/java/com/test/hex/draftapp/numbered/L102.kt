package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import com.test.hex.draftapp.R

class L102 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var xDown = 0F
        var yDown = 0F
        var distance: Double
        var down = ""
        var move = ""
        var up = ""

        setContentView(TextView(this).apply {
            setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        xDown = event.x
                        yDown = event.y
                        down = "Down: ${event.x},${event.y}"
                        move = ""
                        up = ""
                    }
                    MotionEvent.ACTION_MOVE -> {
                        move = "Move: ${event.x},${event.y}"
                    }
                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        distance = Math.sqrt(
                                Math.pow((event.x - xDown).toDouble(), 2.0)
                                        + Math.pow((event.y - yDown).toDouble(), 2.0)
                        )
                        move = "Distance: $distance"
                        up = "Up: ${event.x},${event.y}"
                    }
                }
                val s = down + "\n" + move + "\n" + up
                text = s
            true
            }
        })
    }
}
