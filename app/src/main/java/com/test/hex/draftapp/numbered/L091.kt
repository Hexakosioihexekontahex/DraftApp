package com.test.hex.draftapp.numbered

//import android.annotation.SuppressLint
//import android.os.AsyncTask
//import android.support.v7.app.AppCompatActivity
//import android.os.Bundle
//import android.util.Log
//import android.widget.TextView
//import com.test.hex.draftapp.R
//import org.jetbrains.anko.find
//import java.util.concurrent.TimeUnit
/*

class L091 : AppCompatActivity() {
    private var mt: L091Task? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l091)
        Log.d("myLogs", "Create MainActivity")

        mt = lastNonConfigurationInstance as L091Task
        if (mt == null) {
            mt = L091Task()
            mt!!.execute()
        }
        mt!!.link(this)

        Log.d("myLogs", "create L091Task" + mt!!.hashCode())
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        mt!!.unLink()
        return mt
    }

    internal class L091Task : AsyncTask<String, Int, Unit>() {
        var activity: L091? = null
        fun link(activity: L091) {
            this.activity = activity
        }
        fun unLink() {
            activity = null
        }

        override fun doInBackground(vararg strings: String): Unit? {
            try {
                for (i in 0..10) {
                    TimeUnit.SECONDS.sleep(1)
                    publishProgress(i)
                    Log.d("myLogs", "i=" + i + " L091Task(" + this.hashCode() + ")" +
                            " MainActivity(" + activity!!.hashCode() + ")")
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        @SuppressLint("SetTextI18n")
        private fun onProgressUpdate(vararg values: Int) {
            super.onProgressUpdate(*values.toTypedArray())
            activity!!.find<TextView>(R.id.tvText).text = "i = " + values[0]
        }
    }
}

 */
