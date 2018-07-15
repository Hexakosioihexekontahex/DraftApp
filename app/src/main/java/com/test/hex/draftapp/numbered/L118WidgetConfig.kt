package com.test.hex.draftapp.numbered

import android.app.Activity
import android.appwidget.AppWidgetManager
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.test.hex.draftapp.R

const val WIDGET_PREF = "widget_pref"
const val WIDGET_TEXT = "widget_text_"
const val WIDGET_COLOR = "widget_color_"
private const val LOG_TAG = "myLogs"

class L118WidgetConfig : AppCompatActivity(), View.OnClickListener {
    private var widgetID = AppWidgetManager.INVALID_APPWIDGET_ID
    private var resultValue: Intent? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l118_widget_config)

        Log.d(LOG_TAG, "onCreate")
        val extras = intent.extras
        if (extras != null) {
            widgetID = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID)
        }
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) { finish() }
        resultValue = Intent().putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
        setResult(RESULT_CANCELED, resultValue)
        setContentView(R.layout.l118_widget_config)

        findViewById<Button>(R.id.btnOk).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btnOk -> {
                val selectedColor = findViewById<RadioGroup>(R.id.rgColor).checkedRadioButtonId
                val color: Int
                color = when (selectedColor) {
                    R.id.rbRed -> Color.RED
                    R.id.rbGreen -> Color.GREEN
                    R.id.rbBlue -> Color.BLUE
                    else -> Color.BLACK
                }
                val writtenText = findViewById<EditText>(R.id.etText).text.toString()

                val sp = getSharedPreferences(WIDGET_PREF, Context.MODE_PRIVATE)
                val editor = sp.edit()
                editor.putString(WIDGET_TEXT + widgetID, writtenText)
                editor.putInt(WIDGET_COLOR + widgetID, color)
                editor.apply()
                setResult(Activity.RESULT_OK, resultValue)

                Log.d(packageName, "finish config + $widgetID")
                finish()
            }
        }
    }
}
