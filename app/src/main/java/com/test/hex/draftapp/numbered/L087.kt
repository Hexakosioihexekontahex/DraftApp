package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l087.*
import java.util.concurrent.TimeUnit

class L087 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l087)

        findViewById<Button>(R.id.button1).setOnClickListener {
            L087Task().execute("file_path_1", "file_path_2",
                    "file_path_3", "file_path_4")
        }
    }

    @SuppressLint("StaticFieldLeak")
    internal inner class L087Task : AsyncTask<String?, Int?, Unit?>() {
        override fun onPreExecute() {
            super.onPreExecute()
            tvInfo.setText(R.string.begin)
        }

        override fun doInBackground(vararg strings: String?): Unit? {
            try {
                var cnt = 0
                strings.forEach {
                    downloadFile(it)
                    publishProgress(++cnt)
                }
                TimeUnit.SECONDS.sleep(1)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            tvInfo.text = "Downloaded " + values[0] + " files"
        }

        override fun onPostExecute(aUnit: Unit?) {
            super.onPostExecute(aUnit)
            tvInfo.setText(R.string.end)
        }
    }

    private fun downloadFile(url: String?) {
        TimeUnit.SECONDS.sleep(2)
    }
}
