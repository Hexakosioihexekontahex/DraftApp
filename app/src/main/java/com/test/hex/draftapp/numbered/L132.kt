package com.test.hex.draftapp.numbered

import java.io.IOException

import android.app.Activity
import android.graphics.Matrix
import android.graphics.RectF
import android.hardware.Camera
import android.hardware.Camera.CameraInfo
import android.hardware.Camera.Size
import android.os.Bundle
import android.view.*

import com.test.hex.draftapp.R

private const val CAMERA_ID = 0
private const val FULL_SCREEN = true

class L132 : Activity() {

    private lateinit var sv: SurfaceView
    private lateinit var holder: SurfaceHolder
    private var holderCallback: HolderCallback? = null
    private var camera: Camera? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.l132)

        sv = findViewById(R.id.surfaceView)
        holder = sv.holder
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        holderCallback = HolderCallback()
        holder.addCallback(holderCallback)
    }

    override fun onResume() {
        super.onResume()
        camera = Camera.open(CAMERA_ID)
        setPreviewSize(FULL_SCREEN)
    }

    override fun onPause() {
        super.onPause()
        camera?.release()
        camera = null
    }

    internal inner class HolderCallback : SurfaceHolder.Callback {

        override fun surfaceCreated(holder: SurfaceHolder) {
            try {
                camera!!.setPreviewDisplay(holder)
                camera!!.startPreview()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int,
                                    height: Int) {
            camera!!.stopPreview()
            setCameraDisplayOrientation(CAMERA_ID)
            try {
                camera!!.setPreviewDisplay(holder)
                camera!!.startPreview()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun surfaceDestroyed(holder: SurfaceHolder) {

        }
    }

    private fun setPreviewSize(fullScreen: Boolean) {

        val display = windowManager.defaultDisplay
        val widthIsMax = display.width > display.height

        val size = camera!!.parameters.previewSize

        val rectDisplay = RectF()
        val rectPreview = RectF()

        rectDisplay.set(0f, 0f, display.width.toFloat(), display.height.toFloat())

        if (widthIsMax) {
            rectPreview.set(0f, 0f, size.width.toFloat(), size.height.toFloat())
        } else {
            rectPreview.set(0f, 0f, size.height.toFloat(), size.width.toFloat())
        }

        val matrix = Matrix()
        if (!fullScreen) {
            matrix.setRectToRect(rectPreview, rectDisplay,
                    Matrix.ScaleToFit.START)
        } else {
            matrix.setRectToRect(rectDisplay, rectPreview,
                    Matrix.ScaleToFit.START)
            matrix.invert(matrix)
        }
        matrix.mapRect(rectPreview)

        sv.layoutParams.height = rectPreview.bottom.toInt()
        sv.layoutParams.width = rectPreview.right.toInt()
    }

    internal fun setCameraDisplayOrientation(cameraId: Int) {
        val rotation = windowManager.defaultDisplay.rotation
        var degrees = 0
        when (rotation) {
            Surface.ROTATION_0 -> degrees = 0
            Surface.ROTATION_90 -> degrees = 90
            Surface.ROTATION_180 -> degrees = 180
            Surface.ROTATION_270 -> degrees = 270
        }

        var result = 0

        val info = CameraInfo()
        Camera.getCameraInfo(cameraId, info)

        if (info.facing == CameraInfo.CAMERA_FACING_BACK) {
            result = 360 - degrees + info.orientation
        } else
            if (info.facing == CameraInfo.CAMERA_FACING_FRONT) {
                result = 360 - degrees - info.orientation
                result += 360
            }
        result %= 360
        camera!!.setDisplayOrientation(result)
    }
}