package com.test.hex.draftapp.numbered

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.test.hex.draftapp.R

class L034 : AppCompatActivity(), View.OnClickListener {

    lateinit var etName: EditText
    lateinit var etEmail: EditText
    lateinit var etId: EditText
    lateinit var addBtn: Button
    lateinit var readBtn: Button
    lateinit var clearBtn: Button
    lateinit var updBtn: Button
    lateinit var delBtn: Button
    lateinit var dbHelper: L034DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l034)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etId = findViewById(R.id.etId)

        addBtn = findViewById(R.id.addBtn)
        readBtn = findViewById(R.id.readBtn)
        clearBtn = findViewById(R.id.clearBtn)
        updBtn = findViewById(R.id.updBtn)
        delBtn = findViewById(R.id.delBtn)

        addBtn.setOnClickListener(this)
        readBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)
        updBtn.setOnClickListener(this)
        delBtn.setOnClickListener(this)

        dbHelper = L034DBHelper(this)
    }

    override fun onClick(v: View?) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val id = etId.text.toString()

        val database = dbHelper.writableDatabase
        val contentValues = ContentValues()


        when (v?.id) {
            R.id.addBtn -> {
                contentValues.put(KEY_NAME, name)
                contentValues.put(KEY_MAIL, email)

                database.insert(TABLE_CONTACTS, null, contentValues)
            }
            R.id.readBtn -> {
                val cursor = database.query(TABLE_CONTACTS, null, null,
                        null, null, null, null)

                if (cursor.moveToFirst()) {
                    val idIndex = cursor.getColumnIndex(KEY_ID)
                    val nameIndex = cursor.getColumnIndex(KEY_NAME)
                    val mailIndex = cursor.getColumnIndex(KEY_MAIL)
                    do {
                        Log.d("dbLog", "ID = ${cursor.getInt(idIndex)}, name = ${
                        cursor.getString(nameIndex)}, email = ${cursor.getString(mailIndex)}")
                    } while (cursor.moveToNext())
                } else {
                    Log.d("dbLog", "0 rows")
                }
                cursor.close()
            }
            R.id.clearBtn -> {
                database.delete(TABLE_CONTACTS, null, null)
            }
            R.id.updBtn -> {
                if (id.equals("", ignoreCase = true)){
                    return
                }
                contentValues.put(KEY_MAIL, email)
                contentValues.put(KEY_NAME, name)
                val updateCount = database
                        .update(TABLE_CONTACTS,
                        contentValues,
                        "$KEY_ID= ?",
                        arrayOf(id))
                Log.d("dbLog", "updates rows count $updateCount")
            }
            R.id.delBtn -> {
                if (id.equals("", ignoreCase = true)){
                    return
                }
                val delCount = database
                        .delete(TABLE_CONTACTS,
                                "$KEY_ID= $id", null)
                Log.d("dbLog", "deleted rows count $delCount")
            }
        }
        dbHelper.close()
    }
}
