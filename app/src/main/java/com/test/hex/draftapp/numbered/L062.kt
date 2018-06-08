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
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListAdapter
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"
private const val DIALOG_ITEMS = 1
private const val DIALOG_ADAPTER = 2
private const val DIALOG_CURSOR = 3

class L062 : AppCompatActivity() {
    private var cnt = 0
    private lateinit var db: L062DB
    lateinit var cursor: Cursor
    var data = arrayOf("one", "two", "three", "four")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l062)

        db = L062DB(this)
        db.open()
        cursor = db.getAllData()
        startManagingCursor(cursor)

        findViewById<Button>(R.id.button1).setOnClickListener {
            incCount()
            showDialog(DIALOG_ITEMS)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            incCount()
            showDialog(DIALOG_ADAPTER)
        }
        findViewById<Button>(R.id.button3).setOnClickListener {
            incCount()
            showDialog(DIALOG_CURSOR)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        when (id) {
            DIALOG_ITEMS -> {
                adb.setTitle(R.string.items)
                adb.setItems(data, myClickListener)
            }
            DIALOG_ADAPTER -> {
                adb.setTitle(R.string.adapter)
                val aAdapter = ArrayAdapter<String>(this,
                        android.R.layout.select_dialog_item, data)
                adb.setAdapter(aAdapter, myClickListener)
            }
            DIALOG_CURSOR -> {
                adb.setTitle(R.string.cursor)
                adb.setCursor(cursor, myClickListener, COLUMN_TXT)
            }
        }
        return adb.create()
    }

    private val myClickListener = DialogInterface.OnClickListener {
        _, which -> Log.d(LOG_TAG, "which = $which")

    }

    override fun onPrepareDialog(id: Int, dialog: Dialog?) {
        val aDialog: AlertDialog = dialog as AlertDialog
        val lAdapter: ListAdapter = aDialog.listView.adapter
        when (id) {
            DIALOG_ITEMS, DIALOG_ADAPTER -> {
                if (lAdapter is BaseAdapter) {
                    lAdapter.notifyDataSetChanged()
                }
            }
            DIALOG_CURSOR -> {

            }
        }

        super.onPrepareDialog(id, dialog)
    }

    private fun incCount() {
        cnt++
        data[3] = "$cnt"
        db.changeRec(4, "$cnt")
        cursor.requery()
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}

private const val DB_NAME = "mydb62"
private const val DB_VERSION = 1
private const val DB_TABLE = "mytab"

private const val COLUMN_ID = "_id"
private const val COLUMN_TXT = "txt"

private const val DB_CREATE = "create table $DB_TABLE(" +
        "$COLUMN_ID integer primary key autoincrement," +
        " $COLUMN_TXT text);"

class L062DB(context: Context) {
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

    fun changeRec(id: Int, txt: String) {
        val cv = ContentValues()
        cv.put(COLUMN_TXT, txt)
        db.update(DB_TABLE, cv, "$COLUMN_ID = $id", null)
    }

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
