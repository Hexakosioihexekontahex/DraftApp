package com.test.hex.draftapp.numbered

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

const val DATABASE_VERSION = 1
const val DATABASE_NAME = "contactDb"
const val TABLE_CONTACTS = "contacts"

const val KEY_ID = "_id"
const val KEY_NAME = "name"
const val KEY_MAIL = "mail"

class L034DBHelper(context: Context)
    : ManagedSQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private var instance: L034DBHelper? = null

        @Synchronized
        fun instance(context: Context): L034DBHelper {
            if (instance == null) {
                instance = L034DBHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    val Context.database: L034DBHelper
        get() = L034DBHelper.instance(applicationContext)

    override fun onCreate(database: SQLiteDatabase?) {
        database?.createTable(
                TABLE_CONTACTS, true, KEY_ID to INTEGER + PRIMARY_KEY,
                KEY_NAME to TEXT, KEY_MAIL to TEXT)
    }

    override fun onUpgrade(database: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        database?.dropTable(TABLE_CONTACTS, true)
        onCreate(database)
    }

}