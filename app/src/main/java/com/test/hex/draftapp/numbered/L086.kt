package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l086.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import java.util.concurrent.TimeUnit

class L086 : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l086)

        button1.setOnClickListener {
            async(UI) {
                tvInfo.setText(R.string.begin)
                val result = bg {
                    try {
                        TimeUnit.SECONDS.sleep(2)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                result.await()
                tvInfo.setText(R.string.end)
            }
        }
    }
}
