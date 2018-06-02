package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.test.hex.draftapp.R

private const val DB_NAME = "MyDB"
private const val TABLE_NAME = "MyTable"

class L038 : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        database.delete(TABLE_NAME, null, null)
        val startTime = System.currentTimeMillis()

        when (v?.id) {
            R.id.btnInsert -> {
                insertRecords()
            }
            R.id.btnInsertByTransaction -> {
                insertRecordsByTransaction()
            }
            R.id.btnInsertByTransactionWithStatement -> {
                insertRecordsByTransactionWithStatement()
            }
        }

        val diff = System.currentTimeMillis() - startTime
        tvTime.text = "Time: $diff ms"
    }

    lateinit var tvTime: TextView
    lateinit var btnInsert: Button
    lateinit var btnInsertByTransaction: Button
    lateinit var btnInsertByTransactionWithStatement: Button
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l038)

        initDB()

        tvTime = findViewById(R.id.tvTime)

        btnInsert = findViewById(R.id.btnInsert)
        btnInsert.setOnClickListener(this)

        btnInsertByTransaction = findViewById(R.id.btnInsertByTransaction)
        btnInsertByTransaction.setOnClickListener(this)

        btnInsertByTransactionWithStatement = findViewById(R.id.btnInsertByTransactionWithStatement)
        btnInsertByTransactionWithStatement.setOnClickListener(this)
    }

    private fun initDB() {
        database = this.openOrCreateDatabase(DB_NAME, MODE_PRIVATE, null)
        database.execSQL(
                "CREATE TABLE IF NOT EXISTS $TABLE_NAME(FirstNumber INT, SecondNumber INT, Result INT);"
        )
        database.delete(TABLE_NAME, null, null)
    }

    private fun insertRecords() {
        for (i in 0 until 1000) {
            val cv = ContentValues()
            cv.put("FirstNumber", i)
            cv.put("SecondNumber", i)
            cv.put("Result", i*i)
            database.insert(TABLE_NAME, null, cv)
        }
    }

    private fun insertRecordsByTransaction() {
        database.beginTransaction()
        try {
            for (i in 0 until 1000) {
                val cv = ContentValues()
                cv.put("FirstNumber", i)
                cv.put("SecondNumber", i)
                cv.put("Result", i*i)
                database.insert(TABLE_NAME, null, cv)
            }
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
    }

    private fun insertRecordsByTransactionWithStatement() {
        val sql = "INSERT INTO $TABLE_NAME VALUES(?,?,?);"
        val statement = database.compileStatement(sql)
        database.beginTransaction()
        try {
            for (i in 0 until 1000) {
                statement.apply {
                    clearBindings()
                    bindLong(1, i.toLong())
                    bindLong(2, i.toLong())
                    bindLong(3, (i*i).toLong())
                    execute()
                }
            }
            database.setTransactionSuccessful()
        } finally {
            database.endTransaction()
        }
    }

    override fun onDestroy() {
        database.close()
        super.onDestroy()
    }
}
