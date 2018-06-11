package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.StringReader

class L079 : AppCompatActivity() {
    private val LOG_TAG = "myLogs"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l079)
        try {
            val xpp = prepareXpp()
            var tmpSb: StringBuilder
            while (xpp.eventType != XmlPullParser.END_DOCUMENT) {
                when (xpp.eventType) {
                    XmlPullParser.START_DOCUMENT -> Log.d(LOG_TAG, "START_DOCUMENT")
                    XmlPullParser.START_TAG -> {
                        Log.d(LOG_TAG, "START_TAG: name = ${xpp.name}," +
                                " depth = ${xpp.depth}, attrCount = ${xpp.attributeCount}")
                        tmpSb = StringBuilder("")
                        for (i in 0 until xpp.attributeCount) {
                            tmpSb.append(xpp.getAttributeName(i))
                                    .append(" = ")
                                    .append(xpp.getAttributeValue(i))
                                    .append(", ")
                        }
                        if(!tmpSb.isEmpty()) {
                            Log.d(LOG_TAG, "Attributes: " + tmpSb.toString())
                        }
                    }
                    XmlPullParser.END_TAG -> Log.d(LOG_TAG, "END_TAG: name = ${xpp.name}")
                    XmlPullParser.TEXT -> Log.d(LOG_TAG, "text = ${xpp.text}")

                }
                xpp.next()
            }
            Log.d(LOG_TAG, "END_DOCUMENT")
        } catch (e: XmlPullParserException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

//    private fun prepareXpp() = resources.getXml(R.xml.data79)

    private fun prepareXpp(): XmlPullParser {
        val xppFactory = XmlPullParserFactory.newInstance()
        xppFactory.isNamespaceAware = true
        val xpp = xppFactory.newPullParser()
        xpp.setInput(StringReader(
                "<data><phone><company>Samsung</company></phone></data>"
        ))
        return xpp
    }
}
