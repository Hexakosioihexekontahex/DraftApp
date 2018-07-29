package com.test.hex.draftapp.numbered

import android.content.ContentUris
import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.CheckBox
import com.test.hex.draftapp.R
import org.jetbrains.anko.find
import java.io.IOException

class L126 : AppCompatActivity(), MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener {
    private val LOG_TAG = "myLogs"
    private val DATA_HTTP = "http://online.radiorecord.ru:8101/rr_128"
    //private val DATA_STREAM = "http://online.radiorecord.ru:8101/rr_128"
    private val DATA_STREAM = "http://www.radiorecord.ru/player-mini/?st=darkside"
    private val DATA_SD = "${Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)}/soad.mp3"
    private val DATA_URI: Uri = ContentUris
            .withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 13359
            )
    private var mediaPlayer: MediaPlayer? = null
    private var am: AudioManager? = null
    lateinit var chbLoop: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l126)

        am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        chbLoop = find(R.id.chbLoop)
        chbLoop.setOnCheckedChangeListener { _, isChecked ->
            mediaPlayer?.isLooping = isChecked
        }
    }

    fun onClickStart(v: View) {
        releaseMP()
        try {
            when (v.id) {
                R.id.btnStartHttp -> {
                    Log.d(LOG_TAG, "btnStartHttp")
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(DATA_HTTP)
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        Log.d(LOG_TAG, "prepareAsync")
                        setOnPreparedListener(this@L126)
                        prepareAsync()
                    }
                }
                R.id.btnStartStream -> {
                    Log.d(LOG_TAG, "btnStartStream")
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(DATA_STREAM)
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        Log.d(LOG_TAG, "prepareAsync")
                        setOnPreparedListener(this@L126)
                        prepareAsync()
                    }
                }
                R.id.btnStartSD -> {
                    Log.d(LOG_TAG, "btnStartSD")
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(DATA_SD)
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        prepare()
                        start()
                    }
                }
                R.id.btnStartUri -> {
                    Log.d(LOG_TAG, "btnStartUri")
                    mediaPlayer = MediaPlayer().apply {
                        setDataSource(this@L126, DATA_URI)
                        setAudioStreamType(AudioManager.STREAM_MUSIC)
                        prepare()
                        start()
                    }
                }
                R.id.btnStartRaw -> {
                    Log.d(LOG_TAG, "btnStartRaw")
                    mediaPlayer = MediaPlayer.create(this@L126, R.raw.shield)
                    mediaPlayer?.start()
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        mediaPlayer ?: return
        mediaPlayer?.isLooping = chbLoop.isChecked
        mediaPlayer?.setOnCompletionListener(this)
    }

    private fun releaseMP() {
        mediaPlayer?.let {
            try {
                it.release()
                mediaPlayer = null
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onClick(v: View) {
        mediaPlayer?.let {
            when (v.id) {
                R.id.btnPause -> if (it.isPlaying) it.pause() else {}
                R.id.btnResume -> if (!it.isPlaying) it.start() else {}
                R.id.btnStop -> it.stop()
                R.id.btnBackward -> it.seekTo(it.currentPosition - 3000)
                R.id.btnForward -> it.seekTo(it.currentPosition + 3000)
                R.id.btnInfo -> {
                    Log.d(LOG_TAG, "Playing ${it.isPlaying}")
                    Log.d(LOG_TAG, "Time ${it.currentPosition}/${it.duration}")
                    Log.d(LOG_TAG, "Looping ${it.isLooping}")
                    Log.d(LOG_TAG, "Volume ${am?.getStreamVolume(AudioManager.STREAM_MUSIC)}")
                }
                else -> {}
            }
        }
    }

    override fun onCompletion(p0: MediaPlayer?) {
        Log.d(LOG_TAG, "onCompletion")
    }

    override fun onPrepared(mp: MediaPlayer?) {
        Log.d(LOG_TAG, "onPrepared")
        mp?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        releaseMP()
    }
}
