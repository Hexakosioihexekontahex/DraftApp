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

private const val LOG_TAG = "L039Logs"
private const val DB_NAME = "staff"
private const val DB_VERSION = 2

class L039 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l039)

        val dbHelper = DBHelper(this)
        val db = dbHelper.writableDatabase
        Log.d(LOG_TAG, "~~~ Staff db v.${db.version} ~~~")
        writeStuff(db)
        dbHelper.close()
    }

    private fun writeStuff(db: SQLiteDatabase?) {
        var cursor = db?.rawQuery("SELECT * FROM PEOPLE", null)
        if (cursor != null) {
            logCursor(cursor, "Table people")
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }
        cursor?.close()

        cursor = db?.rawQuery("SELECT * FROM position", null)
        if (cursor != null) {
            logCursor(cursor, "Table position")
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }
        cursor?.close()

        val sqlQuery = "SELECT PL.name AS Name, PS.name AS Position, salary AS Salary " +
                "FROM people AS PL " +
                "INNER JOIN position AS PS " +
                "ON PL.posid = PL.id "
        cursor = db?.rawQuery(sqlQuery, null)
        if (cursor != null) {
            logCursor(cursor, "inner join")
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }
        cursor?.close()
    }

    private fun logCursor(cursor: Cursor, title: String) {
        if (cursor.moveToFirst()) {
            Log.d(LOG_TAG, "$title. ${cursor.count} rows")
            var sb: StringBuilder
            do {
                sb = StringBuilder("")
                for (cn in cursor.columnNames) {
                    sb.append("$cn = ${cursor.getString(cursor.getColumnIndex(cn))}; ")
                }
                Log.d(LOG_TAG, sb.toString())
            } while (cursor.moveToNext())
        }
    }

    private class DBHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(LOG_TAG, "~~~ onCreate database ~~~")

            val peopleName = arrayOf("Иван", "Мария", "Петр", "Антон", "Даша",
                    "Борис", "Костя", "Игорь")
            val peoplePosId = arrayOf(2, 3, 2, 2, 3, 1, 2, 4)

            val positionId = arrayOf(1, 2, 3, 4)
            val positionName = arrayOf("Директор", "Программер", "Бухгалтер", "Охранник")
            val positionSalary = arrayOf(15000, 13000, 10000, 8000)

            val cv = ContentValues()

            db?.execSQL(
                    "CREATE TABLE position (id integer primary key, name text, salary integer);"
            )
            for(i in 0 until positionId.size) {
                cv.clear()
                cv.put("id", positionId[i])
                cv.put("name", positionName[i])
                cv.put("salary", positionSalary[i])
                db?.insert("position", null, cv)
            }

            db?.execSQL(
                    "CREATE TABLE people (id integer primary key autoincrement, name text, posid integer);"
            )

            for(i in 0 until peopleName.size) {
                cv.clear()
                cv.put("name", peopleName[i])
                cv.put("posid", peoplePosId[i])
                db?.insert("people", null, cv)
            }
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
            Log.d(LOG_TAG, "~~~ onUpgrade database from $oldVersion to $newVersion version ~~~")

            if (oldVersion == 1 && newVersion == 2) {
                val cv = ContentValues()

                val positionId = arrayOf(1, 2, 3, 4)
                val positionName = arrayOf("Директор", "Программер", "Бухгалтер", "Охранник")
                val positionSalary = arrayOf(15000, 13000, 10000, 8000)

                db?.beginTransaction()
                try {
                    db?.execSQL(
                            "CREATE TABLE position (id integer primary key, name text, salary integer)"
                    )
                    for (i in 0 until positionId.size) {
                        cv.clear()
                        cv.put("id", positionId[i])
                        cv.put("name", positionName[i])
                        cv.put("salary", positionSalary[i])
                        db?.insert("position", null, cv)
                    }

                    db?.execSQL(
                            "ALTER TABLE people ADD COLUMN posid integer;"
                    )

                    for (i in 0 until positionId.size) {
                        cv.clear()
                        cv.put("posid", positionId[i])
                        db?.update("people", cv, "position = ?",
                                arrayOf(positionName[i]))
                    }

                    db?.execSQL(
                            "CREATE TEMPORARY TABLE people_tmp (id integer, name text, position text, posid integer);"
                    )
                    db?.execSQL(
                            "INSERT INTO people_tmp SELECT id, name, position, posid FROM people;"
                    )
                    db?.execSQL(
                            "DROP TABLE people;"
                    )
                    db?.execSQL(
                            "CREATE TABLE people (id integer primary key autoincrement, name text, posid integer);"
                    )
                    db?.execSQL(
                            "INSERT INTO people SELECT id, name, posid FROM people_tmp;"
                    )
                    db?.execSQL(
                            "DROP TABLE people_tmp;"
                    )
                    db?.setTransactionSuccessful()
                } finally {
                    db?.endTransaction()
                }
            }
        }

    }
}
