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
import android.util.SparseBooleanArray
import android.widget.Button
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"
private const val DIALOG_ITEMS = 1
private const val DIALOG_CURSOR = 2

class L064 : AppCompatActivity() {

    private lateinit var db: L064DB
    lateinit var cursor: Cursor
    var data = arrayOf("one", "two", "three", "four")
    private var chkd = booleanArrayOf(false, true, true, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l064)

        db = L064DB(this)
        db.open()
        cursor = db.getAllData()
        startManagingCursor(cursor)

        findViewById<Button>(R.id.button1).setOnClickListener {
            showDialog(DIALOG_ITEMS)
        }
        findViewById<Button>(R.id.button2).setOnClickListener {
            showDialog(DIALOG_CURSOR)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        val adb = AlertDialog.Builder(this)
        when (id) {
            DIALOG_ITEMS -> {
                adb.setTitle(R.string.items)
                adb.setMultiChoiceItems(data, chkd,
                        myItemsMultiClickListener)
            }
            DIALOG_CURSOR -> {
                adb.setTitle(R.string.cursor)
                adb.setMultiChoiceItems(cursor, COLUMN_CHK, COLUMN_TXT,
                        myCursorMultiClickListener)
            }
        }
        adb.setPositiveButton(R.string.ok, myBtnClickListener)
        return adb.create()
    }
    private val myItemsMultiClickListener = DialogInterface.OnMultiChoiceClickListener{
        _, which, isChecked ->
        Log.d(LOG_TAG, "which = $which, isChecked = $isChecked")
    }
    private val myCursorMultiClickListener = DialogInterface.OnMultiChoiceClickListener{
        _, which, isChecked ->
        Log.d(LOG_TAG, "which = $which, isChecked = $isChecked")
        db.changeRec(which, isChecked)
        cursor.requery()
    }
    private val myBtnClickListener = DialogInterface.OnClickListener{
        dialog, _ ->
        val sbArray: SparseBooleanArray =
                (dialog as AlertDialog)
                        .listView
                        .checkedItemPositions
        for(i in 0 until sbArray.size()) {
            val key = sbArray.keyAt(i)
            if (sbArray.get(key)) {
                Log.d(LOG_TAG, "checked: $key")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}


private const val DB_NAME = "mydb64"
private const val DB_VERSION = 1
private const val DB_TABLE = "mytab"

private const val COLUMN_ID = "_id"
private const val COLUMN_CHK = "checked"
private const val COLUMN_TXT = "txt"

private const val DB_CREATE = "create table $DB_TABLE(" +
        "$COLUMN_ID integer primary key, $COLUMN_CHK integer," +
        " $COLUMN_TXT text);"

class L064DB(context: Context) {
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

    fun changeRec(pos: Int, isChecked: Boolean) {
        val cv = ContentValues()
        cv.put(COLUMN_CHK, if (isChecked) 1 else 0)
        db.update(DB_TABLE, cv, "$COLUMN_ID = ${pos +1 }", null)
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
            for (i in 1..4) {
                cv.clear()
                cv.put(COLUMN_ID, i)
                cv.put(COLUMN_TXT, "sometext $i")
                cv.put(COLUMN_CHK, 0)
                db?.insert(DB_TABLE, null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }
}
