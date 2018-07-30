package com.test.hex.draftapp.numbered

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import com.test.hex.draftapp.R
import org.jetbrains.anko.audioManager
import org.jetbrains.anko.find
import java.io.IOException

private const val LOG_TAG = "myLogs"

class L128 : AppCompatActivity(), MediaPlayer.OnCompletionListener, View.OnClickListener {
    lateinit var am: AudioManager
    lateinit var afListenerMusic: AFListener
    lateinit var afListenerSound: AFListener
    var mpMusic: MediaPlayer? = null
    var mpSound: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l128)

        am = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        find<Button>(R.id.btnPlayMusic).setOnClickListener {
            mpMusic = MediaPlayer()
            try {
                mpMusic?.setDataSource("${
                    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)
                }/soad.mp3")
                mpMusic?.prepare()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            mpMusic?.setOnCompletionListener(this)

            afListenerMusic = AFListener(mpMusic, "Music")
            Log.d(LOG_TAG, "Music request focus, result: ${
                am.requestAudioFocus(afListenerMusic, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN)
            }")
            mpMusic?.start()
        }
        find<Button>(R.id.btnPlaySoundG).setOnClickListener(this)
        find<Button>(R.id.btnPlaySoundGT).setOnClickListener(this)
        find<Button>(R.id.btnPlaySoundGTD).setOnClickListener(this)
    }

    override fun onCompletion(mp: MediaPlayer?) {
        if (mp == mpMusic) {
            Log.d(LOG_TAG, "Music: abandon focus")
            am.abandonAudioFocus(afListenerMusic)
        } else if (mp == mpSound) {
            Log.d(LOG_TAG, "Sound: abandon focus")
            am.abandonAudioFocus(afListenerSound)
        }
    }

    override fun onClick(view: View?) {
        mpSound = MediaPlayer.create(this, R.raw.shot)
        mpSound?.setOnCompletionListener(this)

        afListenerSound = AFListener(mpSound, "Sound")
        val durationHint = when (view?.id) {
            R.id.btnPlaySoundG -> AudioManager.AUDIOFOCUS_GAIN
            R.id.btnPlaySoundGT -> AudioManager.AUDIOFOCUS_GAIN_TRANSIENT
            R.id.btnPlaySoundGTD -> AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_MAY_DUCK
            else -> AudioManager.AUDIOFOCUS_GAIN
        }
        Log.d(LOG_TAG, "Sound request focus, result: ${
        am.requestAudioFocus(afListenerSound, AudioManager.STREAM_MUSIC, durationHint)
        }")

        mpSound?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        mpMusic?.release()
        mpSound?.release()
        afListenerMusic.let { audioManager.abandonAudioFocus(it) }
        afListenerSound.let { audioManager.abandonAudioFocus(it) }
    }

    inner class AFListener(val mp: MediaPlayer?, private val label: String) : AudioManager.OnAudioFocusChangeListener {
        override fun onAudioFocusChange(focusChange: Int) {
            val event: String
            when (focusChange) {
                AudioManager.AUDIOFOCUS_LOSS -> {
                    event = "AUDIOFOCUS_LOSS"
                    mp?.pause()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {
                    event = "AUDIOFOCUS_LOSS_TRANSIENT"
                    mp?.pause()
                }
                AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK -> {
                    event = "AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK"
                    mp?.setVolume(0.5F, 0.5F)
                }
                AudioManager.AUDIOFOCUS_GAIN -> {
                    event = "AUDIOFOCUS_GAIN"
                    if (mp?.isPlaying == false) {
                        mp.start()
                    }
                    mp?.setVolume(1F, 1F)
                }
                else -> {event = "null"}
            }
            Log.d(LOG_TAG, "$label onAudioFocusChange: $event")
        }
    }
}
