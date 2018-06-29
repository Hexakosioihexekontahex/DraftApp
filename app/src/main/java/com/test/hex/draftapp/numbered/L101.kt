package com.test.hex.draftapp.numbered

import android.content.*
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SimpleCursorAdapter
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l101.*
import java.lang.IllegalArgumentException

//log
private const val LOG_TAG = "myLogs"
//db
private const val DB_NAME = "l101db"
private const val DB_VERSION = 1
//table
private const val CONTACT_TABLE = "contacts"
private const val CONTACT_ID = "_id"
private const val CONTACT_NAME = "name"
private const val CONTACT_EMAIL = "email"
//create table
private const val DB_CREATE =
        "create table $CONTACT_TABLE($CONTACT_ID integer primary key autoincrement, $CONTACT_NAME text, $CONTACT_EMAIL text);"
//authority
private const val AUTHORITY = "com.test.hex.draftapp.AddressBook"
//path
private const val CONTACT_PATH = "contacts"
//uri
private val CONTACT_CONTENT_URI = Uri.parse("content://$AUTHORITY/$CONTACT_PATH")
//type
private val CONTACT_CONTENT_TYPE = "vnd.android.cursor.div/vnd.$AUTHORITY.$CONTACT_PATH"
//one string
private val CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.$AUTHORITY.$CONTACT_PATH"
//uri matcher
private const val URI_CONTACTS = 1
private const val URI_CONTACTS_ID = 2

class L101 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l101)

        val cursor = contentResolver.query(CONTACT_CONTENT_URI, null,
                null, null, null)
        startManagingCursor(cursor)

        val from = arrayOf(CONTACT_NAME, CONTACT_EMAIL)
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val sAdapter = SimpleCursorAdapter(this, android.R.layout.simple_list_item_2,
                cursor, from, to)
        lvList.adapter = sAdapter
        //insert
        button1.setOnClickListener {
            val newUri = contentResolver.insert(CONTACT_CONTENT_URI,
                    ContentValues().apply {
                        put(CONTACT_NAME, "name 4")
                        put(CONTACT_EMAIL, "email 4")
                    })
            Log.d(LOG_TAG, "insert, result Uri: $newUri")
        }
        //update
        button2.setOnClickListener {
            val uri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, 2)
            val cnt = contentResolver.update(uri, ContentValues().apply {
                put(CONTACT_NAME, "name 5")
                put(CONTACT_EMAIL, "email 5")
            }, null, null)
            Log.d(LOG_TAG, "update, count = $cnt")
        }
        //delete
        button3.setOnClickListener {
            val uri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, 2)
            val cnt = contentResolver.delete(uri, null, null)
            Log.d(LOG_TAG, "delete, count = $cnt")
        }
        //error
        button4.setOnClickListener {
            val uri = Uri.parse("content://$AUTHORITY/phones")
            var cr: Cursor? = null
            try {
                cr = contentResolver.query(uri, null, null,
                        null, null)
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Error: ${e.javaClass}, ${e.message}")
            } finally {
                cr?.close()
            }
        }
    }
}

class L101ContentProvider : ContentProvider() {
    private var uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS)
        addURI(AUTHORITY, "$CONTACT_PATH/#", URI_CONTACTS_ID)
    }
    private lateinit var dbHelper: L101DBHelper
    lateinit var db: SQLiteDatabase

    override fun insert(uri: Uri?, values: ContentValues?): Uri {
        Log.d(LOG_TAG, "insert, $uri")
        if (uriMatcher.match(uri) != URI_CONTACTS) {
            throw IllegalArgumentException("Wrong uri: $uri")
        }
        db = dbHelper.writableDatabase
        val rowId = db.insert(CONTACT_TABLE, null, values)
        val resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowId)
        context.contentResolver.notifyChange(resultUri, null)
        return resultUri
    }

    override fun query(uri: Uri?, projection: Array<out String>?, selection: String?, selectionArgs: Array<out String>?, sortOrder: String?): Cursor {
        Log.d(LOG_TAG, "query: $uri")
        var mSortOrder : String? = null
        var mSelection : String? = null
        when (uriMatcher.match(uri)) {
            URI_CONTACTS -> {
                Log.d(LOG_TAG, "URI_CONTACTS")
                mSortOrder = if (sortOrder.isNullOrEmpty()) "$CONTACT_NAME ASC" else sortOrder
            }
            URI_CONTACTS_ID -> {
                val id = uri?.lastPathSegment
                Log.d(LOG_TAG, "URI_CONTACTS_ID, $id")
                mSelection = if (selection.isNullOrEmpty()) "$CONTACT_ID = $id"
                    else "$selection AND $CONTACT_ID = $id"
            }
            else -> {
                throw IllegalArgumentException("Wrong URI: $uri")
            }
        }
        db = dbHelper.writableDatabase
        val cursor = db.query(
                CONTACT_TABLE,
                projection,
                mSelection,
                selectionArgs,
                null,
                null,
                mSortOrder
        )
        cursor.setNotificationUri(context.contentResolver, CONTACT_CONTENT_URI)
        return cursor
    }

    override fun onCreate(): Boolean {
        Log.d(LOG_TAG, "onCreate")
        dbHelper = L101DBHelper(context)
        return true
    }

    override fun update(uri: Uri?, values: ContentValues?, selection: String?, selectionArgs: Array<out String>?): Int {
        Log.d(LOG_TAG, "update, $uri")
        var mSelection : String? = null
        when (uriMatcher.match(uri)) {
            URI_CONTACTS -> {
                Log.d(LOG_TAG, "URI_CONTACTS")
            }
            URI_CONTACTS_ID -> {
                val id = uri?.lastPathSegment
                Log.d(LOG_TAG, "URI_CONTACTS_ID, $id")
                mSelection = if (selection.isNullOrEmpty()) "$CONTACT_ID = $id"
                    else "$selection AND $CONTACT_ID = $id"
            }
            else -> {
                throw IllegalArgumentException("Wrong URI: $uri")
            }
        }
        db = dbHelper.writableDatabase
        val cnt = db.update(CONTACT_TABLE, values, mSelection, selectionArgs)
        context.contentResolver.notifyChange(uri, null)
        return cnt
    }

    override fun delete(uri: Uri?, selection: String?, selectionArgs: Array<out String>?): Int {
        var mSelection : String? = null
        Log.d(LOG_TAG, "delete, $uri")
        when (uriMatcher.match(uri)) {
            URI_CONTACTS -> {
                Log.d(LOG_TAG, "URI_CONTACTS")
            }
            URI_CONTACTS_ID -> {
                val id = uri?.lastPathSegment
                Log.d(LOG_TAG, "URI_CONTACTS_ID, $id")
                mSelection = if (selection.isNullOrEmpty()) "$CONTACT_ID = $id"
                    else "$selection AND $CONTACT_ID = $id"
            }
            else -> {
                throw IllegalArgumentException("Wrong URI: $uri")
            }
        }
        db = dbHelper.writableDatabase
        val cnt = db.delete(CONTACT_TABLE, mSelection, selectionArgs)
        context.contentResolver.notifyChange(uri, null)
        return cnt
    }

    override fun getType(uri: Uri?): String? {
        Log.d(LOG_TAG, "getType, $uri")
        when (uriMatcher.match(uri)) {
            URI_CONTACTS -> {
                return CONTACT_CONTENT_TYPE
            }
            URI_CONTACTS_ID -> {
                return CONTACT_CONTENT_ITEM_TYPE
            }
        }
        return null
    }

    private inner class L101DBHelper(context: Context) : SQLiteOpenHelper(
            context, DB_NAME, null, DB_VERSION
    ){
        override fun onCreate(db: SQLiteDatabase?) {
            db?.execSQL(DB_CREATE)
            for (i in 1..3) {
                db?.insert(CONTACT_TABLE, null, ContentValues().apply {
                    put(CONTACT_NAME, "name $i")
                    put(CONTACT_EMAIL, "email $i")
                })
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }
    }
}
