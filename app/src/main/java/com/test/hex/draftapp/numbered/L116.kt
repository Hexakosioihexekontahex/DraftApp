package com.test.hex.draftapp.numbered

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

abstract class L116 : AppCompatActivity() {
    lateinit var list: MutableList<ActivityManager.RunningTaskInfo>
    lateinit var activityManager: ActivityManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l116)

        title = resources.getString(R.string.app_name) + " : " + localClassName
        activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    }

    fun onInfoClick(v: View) {
        list = activityManager.getRunningTasks(10)
        for (task in list) {
            if (task.baseActivity.flattenToShortString()
                            .startsWith("com.test.hex.draftapp")) {
                Log.d(LOG_TAG, "____________________________________")
                Log.d(LOG_TAG, "Count: ${task.numActivities}")
                Log.d(LOG_TAG, "Root: ${task.baseActivity.flattenToShortString()}")
                Log.d(LOG_TAG, "Top: ${task.topActivity.flattenToShortString()}")
            }
        }
    }

    abstract fun onClick(v: View)
}

class L116A : L116() {
    override fun onClick(v: View) {
        startActivity(Intent(this, L116B::class.java))
    }
}

class L116B : L116() {
    override fun onClick(v: View) {
        startActivity(Intent(this, L116C::class.java))
    }
}

class L116C : L116() {
    override fun onClick(v: View) {
        startActivity(Intent(this, L116D::class.java))
    }
}

class L116D : L116() {
    override fun onClick(v: View) {
        startActivity(Intent(this, L116D::class.java))
    }
}


