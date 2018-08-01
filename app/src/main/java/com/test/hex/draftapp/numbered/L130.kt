package com.test.hex.draftapp.numbered

import android.media.AudioFormat
import android.media.AudioRecord
import android.media.MediaRecorder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l130.*
import kotlinx.coroutines.experimental.async

private const val LOG_TAG = "myLogs"

class L130 : AppCompatActivity() {
    private val myBufferSize = 8192
    private var isReading: Boolean = false
        get() {
            Log.d(LOG_TAG, "isReading.get == $field")
            return field
        }
        set(value) {
            Log.d(LOG_TAG, "isReading.set = $value")
            field = value
        }
    private lateinit var audioRecord: AudioRecord

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l130)

        createAudioRecorder()

        Log.d(LOG_TAG, "init state = ${audioRecord.state}")

        btnStartRecord.setOnClickListener {
            Log.d(LOG_TAG, "btnStartRecord")
            audioRecord.startRecording()
            Log.d(LOG_TAG, "recordingState = ${audioRecord.recordingState}")
        }
        btnStopRecord.setOnClickListener {
            Log.d(LOG_TAG, "btnStopRecord")
            audioRecord.stop()
        }
        btnStartReadRecord.setOnClickListener {
            Log.d(LOG_TAG, "btnStartReadRecord")
            isReading = true
            async {
                val myBuffer = ByteArray(myBufferSize)
                var readCount = 0
                var totalCount = 0
                while (isReading) {
                    readCount = audioRecord.read(myBuffer, 0, myBufferSize)
                    totalCount += readCount
                    Log.d(LOG_TAG, "readCount = " + readCount + ", totalCount = "
                            + totalCount)
                }
            }
        }
        btnStopReadRecord.setOnClickListener {
            Log.d(LOG_TAG, "btnStopReadRecord")
            isReading = false
        }
    }

    private fun createAudioRecorder() {
        val sampleRate = 8000
        val channelConfig = AudioFormat.CHANNEL_IN_MONO
        val audioFormat = AudioFormat.ENCODING_PCM_16BIT

        val minInternalBufferSize = AudioRecord.getMinBufferSize(
                sampleRate, channelConfig, audioFormat)
        val internalBufferSize = minInternalBufferSize * 4
        Log.d(LOG_TAG, "minInternalBufferSize = $minInternalBufferSize, internalBufferSize =" +
                " $internalBufferSize, myBufferSize = $myBufferSize")
        audioRecord = AudioRecord(MediaRecorder.AudioSource.MIC, sampleRate, channelConfig,
                audioFormat, internalBufferSize)
    }

    override fun onDestroy() {
        super.onDestroy()
        isReading = false
        audioRecord.release()
    }
}
