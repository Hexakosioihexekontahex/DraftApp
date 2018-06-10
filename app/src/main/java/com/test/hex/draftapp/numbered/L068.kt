package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.util.Log
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L068 : AppCompatActivity() {

    private lateinit var parcel: Parcel
    private var dataSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l068)

        writeParcel()
        readParcel()

        Log.d(LOG_TAG, "${parcel.marshall()}")
        val sb = StringBuilder("ByteArray: ")
        parcel.marshall().forEach {
            sb.append("[$it]")
        }
        Log.d(LOG_TAG, sb.toString())
    }

    private fun writeParcel() {
        parcel = Parcel.obtain()

        val b: Byte = 1
        val i = 2
        val l = 3L
        val f = 4F
        val d = 5.0
        val s = "String"

        logWriteInfo("Before writing")
        parcel.apply {
            writeByte(b)
            logWriteInfo("Byte")
            writeInt(i)
            logWriteInfo("Int")
            writeLong(l)
            logWriteInfo("Long")
            writeFloat(f)
            logWriteInfo("Float")
            writeDouble(d)
            logWriteInfo("Double")
            writeString(s)
            logWriteInfo("String")
        }
    }

    private fun logWriteInfo(text: String) {
        Log.d(LOG_TAG, "$text: size = ${parcel.dataSize() - dataSize}, datasize = ${parcel.dataSize()}")
        dataSize = parcel.dataSize()
    }

    private fun readParcel() {
        logWriteInfo("Before reading")
        parcel.setDataPosition(0)
        logReadInfo("Byte = ${parcel.readByte()}")
        logReadInfo("Int = ${parcel.readInt()}")
        logReadInfo("Long = ${parcel.readLong()}")
        logReadInfo("Float = ${parcel.readFloat()}")
        logReadInfo("Double = ${parcel.readDouble()}")
        logReadInfo("String = ${parcel.readString()}")
    }

    private fun logReadInfo(text: String) {
        Log.d(LOG_TAG, "$text: dataPosition = ${parcel.dataPosition()}")
    }
}
