package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import com.test.hex.draftapp.R

const val LOG_TAG = "L036Logs"
val name = arrayOf("China", "USA", "Brazil", "Russia", "Japan", "Germany",
        "Egypt", "Italy", "France", "Canada")
val people = arrayOf(1400, 311, 195, 142, 128, 82, 80, 60, 66, 35)
val region = arrayOf("Asia", "America", "America", "Europe", "Asia",
        "Europe", "Africa", "Europe", "Europe", "America")


class L036 : AppCompatActivity(), View.OnClickListener {

    lateinit var btnAll: Button
    lateinit var btnFunc: Button
    lateinit var btnPeople: Button
    lateinit var btnSort: Button
    lateinit var btnGroup: Button
    lateinit var btnHaving: Button

    lateinit var etFunc: EditText
    lateinit var etPeople: EditText
    lateinit var etRegionPeople: EditText

    lateinit var rgSort: RadioGroup

    lateinit var dbHelper: DBHelper
    lateinit var db: SQLiteDatabase

    lateinit var c: Cursor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l036)

        btnAll = findViewById(R.id.btnAll)
        btnAll.setOnClickListener(this)

        btnFunc = findViewById(R.id.btnFunc)
        btnFunc.setOnClickListener(this)

        btnPeople = findViewById(R.id.btnPeople)
        btnPeople.setOnClickListener(this)

        btnSort = findViewById(R.id.btnSort)
        btnSort.setOnClickListener(this)

        btnGroup = findViewById(R.id.btnGroup)
        btnGroup.setOnClickListener(this)

        btnHaving = findViewById(R.id.btnHaving)
        btnHaving.setOnClickListener(this)

        etFunc = findViewById(R.id.etFunc)
        etPeople = findViewById(R.id.etPeople)
        etRegionPeople = findViewById(R.id.etRegionPeople)

        rgSort = findViewById(R.id.rgSort)

        dbHelper = DBHelper(this)
        db = dbHelper.writableDatabase

        c = db.query("myTable", null, null,
                null, null, null, null)
        if (c.count == 0) {
            val cv = ContentValues()

            for (i in 0 until 10) {
                cv.put("name", name[i])
                cv.put("people", people[i])
                cv.put("region", region[i])
                Log.d(LOG_TAG, "id = ${db.insert("myTable", null, cv)}")
            }
        }
        c.close()
        dbHelper.close()
        onClick(btnAll)
    }

    override fun onClick(v: View?) {
        db = dbHelper.writableDatabase

        val sFunc = etFunc.text.toString()
        val sPeople = etPeople.text.toString()
        val sRegionPeople = etRegionPeople.text.toString()

        val columns: Array<String>
        val selection: String
        val selectionArgs: Array<String>
        val groupBy: String
        val having:String

        when (v?.id) {
            R.id.btnAll -> {
                Log.d(LOG_TAG, "~~~ All ~~~")
                c = db.query("myTable", null, null, null,
                        null, null, null)
            }
            R.id.btnFunc -> {
                Log.d(LOG_TAG, "~~~ Function $sFunc ~~~")
                columns = arrayOf(sFunc)
                c = db.query("myTable", columns, null, null,
                        null, null, null)
            }
            R.id.btnPeople -> {
                Log.d(LOG_TAG, "~~~ Population more than $sPeople ~~~")
                selection = "people > ?"
                selectionArgs = arrayOf(sPeople)
                c = db.query("myTable", null, selection, selectionArgs,
                        null, null, null)
            }
            R.id.btnGroup -> {
                Log.d(LOG_TAG, "~~~ Population by regions ~~~")
                columns = arrayOf("region", "sum(people) as people")
                groupBy = "region"
                c = db.query("myTable", columns, null, null,
                        groupBy, null, null)
            }
            R.id.btnHaving -> {
                Log.d(LOG_TAG, "~~~ Regions with population more than $sRegionPeople ~~~")
                columns = arrayOf("region", "sum(people) as people")
                groupBy = "region"
                having = "sum(people) > $sRegionPeople"
                c = db.query("myTable", columns, null, null,
                        groupBy, having, null)
            }
            R.id.btnSort -> {
                var orderBy = ""
                when (rgSort.checkedRadioButtonId) {
                    R.id.rName -> {
                        Log.d(LOG_TAG, "~~~ Sort by name ~~~")
                        orderBy = "name"
                    }
                    R.id.rPeople -> {
                        Log.d(LOG_TAG, "~~~ Sort by population ~~~")
                        orderBy = "people"
                    }
                    R.id.rRegion -> {
                        orderBy = "region"
                    }
                }
                c = db.query("myTable", null, null, null,
                        null, null, orderBy)
            }
        }
        if (c.moveToFirst()) {
            var sb: StringBuilder
            do {
                sb = StringBuilder("")
                for (cn in c.columnNames) {
                    sb.append("$cn = ${c.getString(c.getColumnIndex(cn))};")
                }
                Log.d(LOG_TAG, sb.toString())
            } while (c.moveToNext())
        } else {
            Log.d(LOG_TAG, "Cursor is null")
        }
        dbHelper.close()
    }

    class DBHelper(context: Context) : SQLiteOpenHelper(context, "myDB", null, 1) {
        override fun onCreate(db: SQLiteDatabase?) {
            Log.d(LOG_TAG, "~~~ onCreate database ~~~")

            db?.execSQL("create table myTable (id integer primary key autoincrement, name text, people integer, region text);")
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        }

    }
}
