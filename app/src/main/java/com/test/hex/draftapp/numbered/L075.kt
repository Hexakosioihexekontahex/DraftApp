package com.test.hex.draftapp.numbered

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.test.hex.draftapp.R
import java.io.*

class L075 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"
    private val FILE_NAME = "file"
    private val DIR_SD = "DraftAppFiles"
    private val FILE_NAME_SD = "fileSD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l075)

        findViewById<Button>(R.id.button1).setOnClickListener {
            //write file to local storage
            var bufferedWriter: BufferedWriter? = null
            try {
                bufferedWriter = BufferedWriter(OutputStreamWriter(
                        openFileOutput(FILE_NAME, MODE_PRIVATE)
                ))
                bufferedWriter.write("Some file content")
                toastL075(this, "File recorded")
                Log.d(LOG_TAG, "File recorded")
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                toastL075(this, "Write file: " + e::class.java.canonicalName)
            } catch (e: IOException) {
                e.printStackTrace()
                toastL075(this, "Write file: " + e::class.java.canonicalName)
            } finally {
                bufferedWriter?.close()
            }
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            //read file from local storage
            var bufferedReader: BufferedReader? = null
            try {
                bufferedReader = BufferedReader(InputStreamReader(
                        openFileInput(FILE_NAME)
                ))
                bufferedReader.forEachLine {
                    Log.d(LOG_TAG, it)
                    toastL075(this, it)
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                toastL075(this, "Read file: " + e::class.java.canonicalName)
            } catch (e: IOException) {
                e.printStackTrace()
                toastL075(this, "Read file: " + e::class.java.canonicalName)
            } finally {
                bufferedReader?.close()
            }
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            //write file to SDcard
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                Log.d(LOG_TAG, "SDcard is not mounted")
                toastL075(this, "SDcard is not mounted: ${Environment.getExternalStorageState()}")
                return@setOnClickListener
            }
            var sdPath = Environment.getExternalStorageDirectory()
            sdPath = File(sdPath.absolutePath + "/" + DIR_SD)
            sdPath.mkdirs()
            val sdFile = File(sdPath, FILE_NAME_SD)
            var bufferedWriter: BufferedWriter? = null
            try {
                bufferedWriter = BufferedWriter(FileWriter(sdFile))
                bufferedWriter?.write("Some file content on SDcard")
                Log.d(LOG_TAG, "File recorded: ${sdFile.absolutePath}")
                toastL075(this, "File recorded: ${sdFile.absolutePath}")
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                toastL075(this, "Write to SDcard: " + e::class.java.canonicalName)
            } catch (e: IOException) {
                toastL075(this, "Write to SDcard: " + e::class.java.canonicalName)
            } finally {
                bufferedWriter?.close()
            }
        }
        findViewById<Button>(R.id.button4).setOnClickListener {
            //read file from SDcard
            if (Environment.getExternalStorageState() != Environment.MEDIA_MOUNTED) {
                Log.d(LOG_TAG, "SDcard is not mounted")
                toastL075(this, "SDcard is not mounted: ${Environment.getExternalStorageState()}")
                return@setOnClickListener
            }
            var sdPath = Environment.getExternalStorageDirectory()
            sdPath = File(sdPath.absolutePath + "/" + DIR_SD)
            val sdFile = File(sdPath, FILE_NAME_SD)
            var bufferedReader: BufferedReader? = null
            try {
                bufferedReader = BufferedReader(FileReader(sdFile))
                bufferedReader?.forEachLine {
                    Log.d(LOG_TAG, it)
                    toastL075(this, it)
                }
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
                toastL075(this, "Read from SDcard: " + e::class.java.canonicalName)
            } catch (e: IOException) {
                toastL075(this, "Read from SDcard: " + e::class.java.canonicalName)
            } finally {
                bufferedReader?.close()
            }
        }
    }

    private fun toastL075(context: Context, message: String) =
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}
