package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleCursorAdapter
import com.test.hex.draftapp.R

private const val CM_DELETE_ID = 1

class L052 : AppCompatActivity() {

    lateinit var lvData: ListView
    private lateinit var db: L052DB
    private lateinit var scAdapter: SimpleCursorAdapter
    private lateinit var cursor: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l052)

        db = L052DB(this)
        db.open()

        cursor = db.getAllData()
        startManagingCursor(cursor)

        val from = arrayOf(COLUMN_IMG, COLUMN_TXT)
        val to = intArrayOf(R.id.ivImg, R.id.tvText)

        scAdapter = SimpleCursorAdapter(this, R.layout.l052_item, cursor, from, to)
        lvData = findViewById(R.id.lvData)
        lvData.adapter = scAdapter
        registerForContextMenu(lvData)
    }

    fun onClickButton(v: View?){
        db.addRec("sometext + ${cursor.count +1}", android.R.drawable.ic_media_play)
        cursor.requery()
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(0, CM_DELETE_ID, 0, R.string.delete_entry)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == CM_DELETE_ID) {
            val acmi = item.menuInfo as AdapterView.AdapterContextMenuInfo
            db.delRec(acmi.id)
            cursor.requery()
            return true
        }
        return super.onContextItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}

private const val DB_NAME = "myDB"
private const val DB_VERSION = 1
private const val DB_TABLE = "mytab"
private const val COLUMN_ID = "_id"
private const val COLUMN_IMG = "img"
private const val COLUMN_TXT = "txt"
private const val DB_CREATE =
        "create table $DB_TABLE($COLUMN_ID integer primary key autoincrement," +
                " $COLUMN_IMG integer, $COLUMN_TXT text);"


class L052DB(ctx: Context) {

    private var mCtx: Context = ctx
    private lateinit var mDBHelper: DBHelper
    private lateinit var mDB: SQLiteDatabase

    fun open() {
        mDBHelper = DBHelper(mCtx, DB_NAME, null, DB_VERSION)
        mDB = mDBHelper.writableDatabase
    }

    fun close() {
        mDBHelper.close()
    }

    fun getAllData(): Cursor = mDB.query(DB_TABLE, null, null, null,
            null, null, null)

    fun delRec(id: Long) {
        mDB.delete(DB_TABLE, "$COLUMN_ID = $id", null)
    }

    fun addRec(text: String, img: Int) {
        val cv = ContentValues()
        cv.put(COLUMN_TXT, text)
        cv.put(COLUMN_IMG, img)
        mDB.insert(DB_TABLE, null, cv)
    }

    private inner class DBHelper(context: Context?,
                                 name: String?,
                                 factory: SQLiteDatabase.CursorFactory?,
                                 version: Int) : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(DB_CREATE)

            val cv = ContentValues()
            for (i in 1..5) {
                cv.put(COLUMN_TXT, "some text #$i")
                cv.put(COLUMN_IMG, android.R.drawable.ic_media_play)
                db?.insert(DB_TABLE, null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}
