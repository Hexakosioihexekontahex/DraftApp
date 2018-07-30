package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Environment
import kotlinx.android.synthetic.main.l129.*
import java.io.File


class L129 : AppCompatActivity() {
    private var mediaRecorder: MediaRecorder? = null
    private var mediaPlayer: MediaPlayer? = null
    private var fileName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l129)

        fileName = "${Environment.getExternalStorageDirectory()}/record.3gpp"

        btnRecordStart.setOnClickListener {
            try {
                releaseRecorder()

                val outFile = File(fileName)
                if (outFile.exists()) {
                    outFile.delete()
                }

                mediaRecorder = MediaRecorder().apply {
                    setAudioSource(MediaRecorder.AudioSource.MIC)
                    setOutputFormat(MediaRecorder.OutputFormat.AMR_WB)
                    setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB)
                    setOutputFile(fileName)
                    prepare()
                }
                mediaRecorder?.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        btnRecordStop.setOnClickListener {
            mediaRecorder?.stop()
        }
        btnPlayStart.setOnClickListener {
            try {
                releasePlayer()
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(fileName)
                    prepare()
                    start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        btnPlayStop.setOnClickListener {
            mediaPlayer?.stop()
        }
    }

    private fun releaseRecorder() {
        mediaRecorder?.let {
            it.release()
            mediaRecorder = null
        }
    }

    private fun releasePlayer() {
        mediaPlayer?.let {
            it.release()
            mediaPlayer = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
        releaseRecorder()
    }

}
