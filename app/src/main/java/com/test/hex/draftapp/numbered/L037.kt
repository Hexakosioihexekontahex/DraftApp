package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.test.hex.draftapp.R

private const val LOG_TAG = "L037Logs"
private val positionId = arrayOf(1, 2, 3, 4)
private val positionName = arrayOf("Директор", "Программист", "Бухгалтер", "Охранник")
private val positionSalary = arrayOf(80000, 60000, 40000, 20000)

private val people_name = arrayOf("Максим", "Сергей", "Руслан", "Наталья", "Иван", "Мария",
        "Светлана", "Григорий")
private val people_posId = arrayOf(2, 3, 2, 2, 3, 1, 2, 4)

class L037 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l037)

        val dbHelper = DBHelper(this)
        val db = dbHelper.writableDatabase
        var c: Cursor

        Log.d(LOG_TAG, "~~~ Table position ~~~")
        c = db.query("position", null, null, null,
                null, null, null)
        logCursor(c)
        c.close()
        Log.d(LOG_TAG, "~~~  ~~~")

        Log.d(LOG_TAG, "~~~ Table people ~~~")
        c = db.query("people", null, null, null,
                null, null, null)
        logCursor(c)
        c.close()
        Log.d(LOG_TAG, "~~~  ~~~")

        Log.d(LOG_TAG, "~~~ INNER JOIN with rawQuery ~~~")
        val sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary" +
                " from people as PL" +
                " inner join position as PS" +
                " on PL.posid = PS.id" +
                " where salary > ?"
        c = db.rawQuery(sqlQuery, arrayOf("40000"))
        logCursor(c)
        c.close()
        Log.d(LOG_TAG, "~~~  ~~~")

        Log.d(LOG_TAG, "~~~ INNER JOIN with query ~~~")
        val table = "people as PL inner join position as PS on PL.posid = PS.id"
        val columns = arrayOf("PL.name as Name", "PS.name as Position", "salary as Salary")
        val selection = "salary < ?"
        val selectionArgs = arrayOf("40000")
        c = db.query(table, columns, selection, selectionArgs,
                null, null, null)
        logCursor(c)
        c.close()
        Log.d(LOG_TAG, "~~~  ~~~")

        dbHelper.close()
    }

    fun logCursor(cursor: Cursor) {
        if (cursor.moveToFirst()) {
            var sb: StringBuilder
            do {
                sb = StringBuilder("")
                for (cn in cursor.columnNames) {
                    sb.append("$cn = ${cursor.getString(cursor.getColumnIndex(cn))}; ")
                }
                Log.d(LOG_TAG, sb.toString())
            } while (cursor.moveToNext())
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }
    }

    class DBHelper(context: Context) : SQLiteOpenHelper(context,
            "myBD", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(LOG_TAG, "~~~ onCreate database ~~~")

            val cv = ContentValues()

            db?.execSQL(
                    "create table position (id integer primary key, name text, salary integer);"
            )
            for (i in 0 until positionId.size) {
                cv.clear()
                cv.put("id", positionId[i])
                cv.put("name", positionName[i])
                cv.put("salary", positionSalary[i])
                db?.insert("position", null, cv)
            }

            db?.execSQL(
                    "create table people (id integer primary key autoincrement, name text, posid integer)"
            )
            for (i in 0 until people_name.size) {
                cv.clear()
                cv.put("name", people_name[i])
                cv.put("posid", people_posId[i])
                db?.insert("people", null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }
}
