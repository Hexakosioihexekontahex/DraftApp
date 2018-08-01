package com.test.hex.draftapp.numbered

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.test.hex.draftapp.R
import android.graphics.Bitmap
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.ImageView
import java.io.File

private const val TYPE_PHOTO = 1
private const val TYPE_VIDEO = 2

private const val REQUEST_CODE_PHOTO = 1
private const val REQUEST_CODE_VIDEO = 2

private const val LOG_TAG = "myLogs"


class L131 : AppCompatActivity() {
    private lateinit var directory: File
    lateinit var ivPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l131)
        createDirectory()
        ivPhoto = findViewById(R.id.ivPhoto)
    }

    fun onClickPhoto(view: View) {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_PHOTO))
        startActivityForResult(intent, REQUEST_CODE_PHOTO)
    }

    fun onClickVideo(view: View) {
        val intent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(TYPE_VIDEO))
        startActivityForResult(intent, REQUEST_CODE_VIDEO)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  intent: Intent?) {
        if (requestCode == REQUEST_CODE_PHOTO) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent == null) {
                    Log.d(LOG_TAG, "Intent is null")
                } else {
                    Log.d(LOG_TAG, "Photo uri: " + intent.data!!)
                    val bndl = intent.extras
                    if (bndl != null) {
                        val obj = intent.extras!!.get("data")
                        if (obj is Bitmap) {
                            Log.d(LOG_TAG, "bitmap " + obj.width + " x "
                                    + obj.height)
                            ivPhoto.setImageBitmap(obj)
                        }
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(LOG_TAG, "Canceled")
            }
        }

        if (requestCode == REQUEST_CODE_VIDEO) {
            if (resultCode == Activity.RESULT_OK) {
                if (intent == null) {
                    Log.d(LOG_TAG, "Intent is null")
                } else {
                    Log.d(LOG_TAG, "Video uri: " + intent.data!!)
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.d(LOG_TAG, "Canceled")
            }
        }
    }

    private fun generateFileUri(type: Int): Uri {
        var file: File? = null
        when (type) {
            TYPE_PHOTO -> file = File(directory.path + "/" + "photo_"
                    + System.currentTimeMillis() + ".jpg")
            TYPE_VIDEO -> file = File(directory.path + "/" + "video_"
                    + System.currentTimeMillis() + ".mp4")
        }
        Log.d(LOG_TAG, "fileName = " + file!!)
        return Uri.fromFile(file)
    }

    private fun createDirectory() {
        directory = File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "MyFolder")
        if (!directory.exists())
            directory.mkdirs()
    }

}
