package com.test.hex.draftapp.numbered

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.graphics.Color
import android.util.Log
import android.widget.RemoteViews
import com.test.hex.draftapp.R
import java.util.*

private const val LOG_TAG = "myLogs"

class L117Widget : AppWidgetProvider() {

    override fun onEnabled(context: Context) {
        super.onEnabled(context)
        Log.d(LOG_TAG, "onEnabled")
    }

    override fun onUpdate(context: Context?, appWidgetManager: AppWidgetManager, appWidgetIds: IntArray) {
        super.onUpdate(context, appWidgetManager, appWidgetIds)
        Log.d(LOG_TAG, "onUpdate ${Arrays.toString(appWidgetIds)}")

        val sp = context?.getSharedPreferences(WIDGET_PREF, MODE_PRIVATE)
        for (i in 0 until appWidgetIds.size) {
            updateWidget(context, appWidgetManager, sp, appWidgetIds[i])
        }
    }

    override fun onDeleted(context: Context?, appWidgetIds: IntArray) {
        super.onDeleted(context, appWidgetIds)
        Log.d(LOG_TAG, "onDeleted ${Arrays.toString(appWidgetIds)}")

        val editor = context?.getSharedPreferences(WIDGET_PREF, MODE_PRIVATE)?.edit()
        for (i in 0 until appWidgetIds.size) {
            editor?.remove(WIDGET_TEXT + appWidgetIds[i])
            editor?.remove(WIDGET_COLOR + appWidgetIds[i])
        }
        editor?.apply()
    }

    override fun onDisabled(context: Context?) {
        super.onDisabled(context)
        Log.d(LOG_TAG, "onDisables")
    }
}

fun updateWidget(context: Context?, appWidgetManager: AppWidgetManager, sp: SharedPreferences?, widgetID: Int) {
    Log.d(LOG_TAG, "updateWidget")

    val widgetText = sp?.getString(WIDGET_TEXT + widgetID, "DEFAULT")//TODO
    val widgetColor = sp?.getInt(WIDGET_COLOR + widgetID, Color.GRAY)//TODO
    appWidgetManager.updateAppWidget(widgetID,
            RemoteViews(context?.packageName, R.layout.l117_widget).apply {
                setTextViewText(R.id.tvText, widgetText)
                setInt(R.id.tvText, "setBackgroundColor", widgetColor!!)
            }
    )
    Log.d(LOG_TAG, "updateWidget end")
}
