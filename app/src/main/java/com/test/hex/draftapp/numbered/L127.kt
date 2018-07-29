package com.test.hex.draftapp.numbered

import android.media.AudioManager
import android.media.SoundPool
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.test.hex.draftapp.R
import org.jetbrains.anko.find
import java.io.IOException

private const val LOG_TAG = "myLogs"

class L127 : AppCompatActivity(), SoundPool.OnLoadCompleteListener {

    private val MAX_STREAMS = 5
    private lateinit var sp: SoundPool
    private var soundIdShot: Int? = null
    private var soundIdExplosion: Int? = null
    private var streamIdShot: Int? = null
    private var streamIdExplosion: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l127)

        sp = SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0)
        sp.setOnLoadCompleteListener(this)

        soundIdShot = sp.load(this, R.raw.shot, 1)
        Log.d(LOG_TAG, "soundIdShot = $soundIdShot")

        try {
            soundIdExplosion = sp.load(assets.openFd("explosion.ogg"), 1)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        Log.d(LOG_TAG, "soundIdExplosion = $soundIdExplosion")

        find<Button>(R.id.btnPlay).setOnClickListener {
            if (soundIdShot != null && soundIdExplosion != null) {
                val finalSoundIdShot = soundIdShot!!
                val finalSoundIdExplosion = soundIdExplosion!!
                sp.play(finalSoundIdShot, 1F,1F,0,5,2F)
                sp.play(finalSoundIdExplosion, 1F,1F,0,2,0.5F)
            }
        }
    }

    override fun onLoadComplete(sp: SoundPool?, sampleId: Int, status: Int) {
        Log.d(LOG_TAG, "onLoadComplete, sampleId = $sampleId, status = $status")
    }

    override fun onDestroy() {
        super.onDestroy()
        sp.release()
    }
}
