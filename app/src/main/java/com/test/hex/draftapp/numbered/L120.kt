package com.test.hex.draftapp.numbered

import android.app.Activity
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.RemoteViews
import com.test.hex.draftapp.R
import java.text.SimpleDateFormat
import java.util.*

private const val WIDGETPREF = "widget_pref"
private const val WIDGET_TIME_FORMAT = "widget_time_format_"
private const val WIDGET_COUNT = "widget_count_"
private const val ACTION_CHANGE = "com.test.hex.draftapp.l120clickwidget.change_count"

class L120ConfigActivity : AppCompatActivity() {
    private var widgetID = AppWidgetManager.INVALID_APPWIDGET_ID
    lateinit var resultValue: Intent
    lateinit var sp: SharedPreferences
    lateinit var etFormat: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        widgetID = intent.extras?.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID)
                ?: AppWidgetManager.INVALID_APPWIDGET_ID
        if (widgetID == AppWidgetManager.INVALID_APPWIDGET_ID) finish()

        resultValue = Intent()
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)

        setResult(Activity.RESULT_CANCELED, resultValue)

        setContentView(R.layout.l120_config)

        sp = getSharedPreferences(WIDGETPREF, Context.MODE_PRIVATE)
        etFormat = findViewById(R.id.etFormat)
        etFormat.setText(sp.getString(WIDGET_TIME_FORMAT + widgetID, "HH:mm:ss"))

        if (sp.getInt(WIDGET_COUNT + widgetID, -1) == -1) {
            sp.edit().putInt(WIDGET_COUNT + widgetID, 0).apply()
        }
    }

    fun onClick(v: View?) {
        sp.edit().putString(WIDGET_TIME_FORMAT + widgetID, etFormat.text.toString()).apply()
        setResult(Activity.RESULT_OK, resultValue)
        finish()
    }
}

class L120AppWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(context: Context, appWidgetManager: AppWidgetManager,
                          appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        for (i in 0 until appWidgetIds.size) {
            updateWidget(context, appWidgetManager, appWidgetIds[i])
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)

        val editor: SharedPreferences.Editor? = context?.getSharedPreferences(
                WIDGETPREF, Context.MODE_PRIVATE)?.edit()
        for (i in 0 until appWidgetIds.size) {
            editor?.remove(WIDGET_TIME_FORMAT + appWidgetIds[i])
            editor?.remove(WIDGET_COUNT + appWidgetIds[i])
        }
        editor?.apply()
    }

    override fun onReceive(context: Context, intent: Intent) {
        super.onReceive(context, intent)

        if (intent.action.equals(ACTION_CHANGE, ignoreCase = true)) {
            val mAppWidgetId = intent.extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID)
            if (mAppWidgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
                val sp = context.getSharedPreferences(WIDGETPREF, MODE_PRIVATE)
                var cnt = sp.getInt(WIDGET_COUNT + mAppWidgetId, 0)
                sp.edit().putInt(WIDGET_COUNT + mAppWidgetId, ++cnt).apply()

                updateWidget(context, AppWidgetManager.getInstance(context), mAppWidgetId)
            }
        }
    }
}

fun updateWidget(context: Context, appWidgetManager: AppWidgetManager, widgetID: Int) {
    val sp = context.getSharedPreferences(WIDGETPREF, MODE_PRIVATE)

    val timeFormat = sp.getString(WIDGET_TIME_FORMAT + widgetID, null) ?: return
    val sdf = SimpleDateFormat(timeFormat, Locale.getDefault()) //!
    val currentTime = sdf.format(Date(System.currentTimeMillis()))
    val count = "${sp.getInt(WIDGET_COUNT + widgetID, 0)}"
    val widgetView = RemoteViews(context.packageName, R.layout.l120_widget)
    widgetView.setTextViewText(R.id.tvTime, currentTime)
    widgetView.setTextViewText(R.id.tvCount, count)

    val configIntent = Intent(context, L120ConfigActivity::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_CONFIGURE
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
    }
    var pIntent = PendingIntent.getActivity(context, widgetID, configIntent, 0)
    widgetView.setOnClickPendingIntent(R.id.tvPressConfig, pIntent)

    val updateIntent = Intent(context, L120AppWidgetProvider::class.java).apply {
        action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, IntArray(widgetID))
    }
    pIntent = PendingIntent.getBroadcast(context, widgetID, updateIntent, 0)
    widgetView.setOnClickPendingIntent(R.id.tvPressUpdate, pIntent)

    val countIntent = Intent(context, L120AppWidgetProvider::class.java).apply {
        action = ACTION_CHANGE
        putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetID)
    }
    pIntent = PendingIntent.getBroadcast(context, widgetID, countIntent, 0)
    widgetView.setOnClickPendingIntent(R.id.tvPressCount, pIntent)

    appWidgetManager.updateAppWidget(widgetID, widgetView)
}