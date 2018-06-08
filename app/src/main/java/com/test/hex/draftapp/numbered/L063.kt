package com.test.hex.draftapp.numbered

import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"
private const val DIALOG_ITEMS = 1
private const val DIALOG_ADAPTER = 2
private const val DIALOG_CURSOR = 3

class L063 : AppCompatActivity() {

    private lateinit var db: L063DB
    lateinit var cursor: Cursor
    var data = arrayOf("one", "two", "three", "four")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l063)

        db = L063DB(this)
        db.open()
        cursor = db.getAllData()
        startManagingCursor(cursor)

        findViewById<Button>(R.id.button1).setOnClickListener {
            showDialog(DIALOG_ITEMS)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            showDialog(DIALOG_ADAPTER)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            showDialog(DIALOG_CURSOR)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        when (id) {
            DIALOG_ITEMS -> {
                adb.setTitle(R.string.items)
                adb.setSingleChoiceItems(data, -1, myClickListener)
            }
            DIALOG_ADAPTER -> {
                adb.setTitle(R.string.adapter)
                val aAdapter = ArrayAdapter(this,
                        android.R.layout.select_dialog_singlechoice, data)
                adb.setSingleChoiceItems(aAdapter, -1, myClickListener)
            }
            DIALOG_CURSOR -> {
                adb.setTitle(R.string.cursor)
                adb.setSingleChoiceItems(cursor, -1, COLUMN_TXT, myClickListener)
            }
        }
        adb.setPositiveButton(R.string.ok, myClickListener)
        return adb.create()
    }

    private val myClickListener = DialogInterface.OnClickListener{
        dialog, which ->
        val lv = (dialog as AlertDialog).listView
        if (which == Dialog.BUTTON_POSITIVE) {
            Log.d(LOG_TAG, "pos = ${lv.checkedItemPosition}")
        } else {
            Log.d(LOG_TAG, "which = $which")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}


private const val DB_NAME = "mydb63"
private const val DB_VERSION = 1
private const val DB_TABLE = "mytab"

private const val COLUMN_ID = "_id"
private const val COLUMN_TXT = "txt"

private const val DB_CREATE = "create table $DB_TABLE(" +
        "$COLUMN_ID integer primary key autoincrement," +
        " $COLUMN_TXT text);"

class L063DB(context: Context) {
    private var ctx: Context = context
    private lateinit var dbHelper: DBHelper
    private lateinit var db: SQLiteDatabase

    fun open() {
        dbHelper = DBHelper(ctx, DB_NAME, null, DB_VERSION)
        db = dbHelper.writableDatabase
    }

    fun close() {
        dbHelper.close()
    }

    fun getAllData(): Cursor = db.query(DB_TABLE, null, null, null,
            null, null, null)

    inner class DBHelper(context: Context?,
                         name: String?,
                         factory: SQLiteDatabase.CursorFactory?,
                         version: Int
    ) : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(DB_CREATE)
            val cv = ContentValues()
            for (i in 1..5) {
                cv.clear()
                cv.put(COLUMN_ID, i)
                cv.put(COLUMN_TXT, "sometext $i")
                db?.insert(DB_TABLE, null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }
}
