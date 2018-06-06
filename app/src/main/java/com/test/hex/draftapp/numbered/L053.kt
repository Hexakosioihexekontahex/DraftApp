package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.SimpleCursorTreeAdapter
import com.test.hex.draftapp.R

class L053 : AppCompatActivity() {

    lateinit var elvMain: ExpandableListView
    lateinit var db: L053DB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l053)

        db = L053DB(this)
        db.open()

        val cursor = db.getCompanyData()
        startManagingCursor(cursor)

        val groupFrom = arrayOf(COMPANY_COLUMN_NAME)
        val groupTo = intArrayOf(android.R.id.text1)
        val childFrom = arrayOf(PHONE_COLUMN_NAME)
        val childTo = intArrayOf(android.R.id.text1)

        val sctAdapter: SimpleCursorTreeAdapter = MyAdapter(this,
                cursor, android.R.layout.simple_expandable_list_item_1,
                groupFrom, groupTo, android.R.layout.simple_list_item_1,
                childFrom, childTo)

        elvMain = findViewById(R.id.elvMain)
        elvMain.setAdapter(sctAdapter)
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
    inner class MyAdapter(context: Context?,
                    cursor: Cursor?,
                    groupLayout: Int,
                    groupFrom: Array<out String>?,
                    groupTo: IntArray?,
                    childLayout: Int,
                    childFrom: Array<out String>?,
                    childTo: IntArray?
    ) : SimpleCursorTreeAdapter(context, cursor, groupLayout, groupFrom, groupTo,
            childLayout, childFrom, childTo) {
        override fun getChildrenCursor(groupCursor: Cursor?): Cursor {
            val idColumn: Int = groupCursor?.getColumnIndex(COMPANY_COLUMN_ID)!!
            return db.getPhoneData(groupCursor.getLong(idColumn))
        }
    }
}

private const val DB_NAME = "mydb"
private const val DB_VERSION = 1

private const val COMPANY_TABLE = "company"
const val COMPANY_COLUMN_ID = "_id"
const val COMPANY_COLUMN_NAME = "name"
private const val COMPANY_TABLE_CREATE = "create table $COMPANY_TABLE($COMPANY_COLUMN_ID integer" +
        " primary key autoincrement, $COMPANY_COLUMN_NAME text);"

private const val PHONE_TABLE = "phone"
const val PHONE_COLUMN_ID = "_id"
const val PHONE_COLUMN_NAME = "name"
const val PHONE_COLUMN_COMPANY = "company"
private const val PHONE_TABLE_CREATE = "create table $PHONE_TABLE($PHONE_COLUMN_ID integer" +
        " primary key autoincrement, $PHONE_COLUMN_NAME text, $PHONE_COLUMN_COMPANY integer);"

class L053DB(ctx: Context) {
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

    fun getCompanyData(): Cursor = mDB.query(COMPANY_TABLE, null, null, null,
            null, null, null)

    fun getPhoneData(companyID: Long?): Cursor = mDB.query(PHONE_TABLE, null,
            "$PHONE_COLUMN_COMPANY = $companyID", null, null,
            null, null)

    private class DBHelper(context: Context?,
                           name: String?,
                           factory: SQLiteDatabase.CursorFactory?,
                           version: Int) : SQLiteOpenHelper(context, name, factory, version) {
        override fun onCreate(db: SQLiteDatabase?) {
            val cv = ContentValues()
            val companies = arrayOf("HTC", "Samsung", "LG")

            db?.execSQL(COMPANY_TABLE_CREATE)
            for(i in 0 until companies.size) {
                cv.put(COMPANY_COLUMN_ID, i + 1)
                cv.put(COMPANY_COLUMN_NAME, companies[i])
                db?.insert(COMPANY_TABLE, null, cv)
            }

            val phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero", "One")
            val phonesSamsung = arrayOf("Galaxy S II", "Galaxy S3", "Galaxy Nexus", " Wave", "Galaxy Note")
            val phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

            db?.execSQL(PHONE_TABLE_CREATE)
            cv.clear()
            for(i in 0 until phonesHTC.size) {
                cv.put(PHONE_COLUMN_COMPANY, 1)
                cv.put(PHONE_COLUMN_NAME, phonesHTC[i])
                db?.insert(PHONE_TABLE, null, cv)
            }
            for(i in 0 until phonesSamsung.size) {
                cv.put(PHONE_COLUMN_COMPANY, 2)
                cv.put(PHONE_COLUMN_NAME, phonesSamsung[i])
                db?.insert(PHONE_TABLE, null, cv)
            }
            for(i in 0 until phonesLG.size) {
                cv.put(PHONE_COLUMN_COMPANY, 3)
                cv.put(PHONE_COLUMN_NAME, phonesLG[i])
                db?.insert(PHONE_TABLE, null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }
}
