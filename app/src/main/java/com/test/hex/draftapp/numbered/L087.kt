package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l087.*
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import org.jetbrains.anko.coroutines.experimental.asReference
import org.jetbrains.anko.coroutines.experimental.bg
import java.util.concurrent.TimeUnit

class L087 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l087)

        val strings = arrayOf("file_path_1", "file_path_2",
                "file_path_3", "file_path_4")
        val ref = asReference()

        button1.setOnClickListener {
            var cnt = 0
            tvInfo.text = "Start"
            async(UI) {
                strings.forEach {
                    val result = bg {
                        downloadFile(it)
                        ++cnt
                    }
                    result.await()
                    tvInfo.text = "Downloaded $cnt files"
                }
                delay(1000)
                tvInfo.setText(R.string.end)
            }
        }
    }

    private fun downloadFile(url: String?) {
        try { TimeUnit.SECONDS.sleep(2) } catch (e: InterruptedException) {}
    }
}
