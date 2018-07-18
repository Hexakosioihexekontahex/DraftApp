package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.test.hex.draftapp.R
import com.test.hex.draftapp.numbered.L034DBHelper.Companion.instance
import kotlinx.android.synthetic.main.l034.*
import org.jetbrains.anko.db.*

class L034 : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l034)

        addBtn.setOnClickListener(this)
        readBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)
        updBtn.setOnClickListener(this)
        delBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val id = etId.text.toString()

        val database = instance(this)

        when (v?.id) {
            addBtn.id -> {
                database.use {
                    insert(TABLE_CONTACTS,KEY_NAME to name, KEY_MAIL to email)
                }
            }
            readBtn.id -> {
                database.use {
                    select(TABLE_CONTACTS).exec {
                        if (this.moveToFirst()) {
                            val idIndex = this.getColumnIndex(KEY_ID)
                            val nameIndex = this.getColumnIndex(KEY_NAME)
                            val mailIndex = this.getColumnIndex(KEY_MAIL)
                            do {
                                Log.d("dbLog", "ID = ${this.getInt(idIndex)}, name = ${
                                this.getString(nameIndex)}, email = ${this.getString(mailIndex)}")
                            } while (this.moveToNext())
                        } else {
                            Log.d("dbLog", "0 rows")
                        }
                        this.close()
                    }
                }
            }
            clearBtn.id -> {
                database.use {
                    delete(TABLE_CONTACTS)
                }
            }
            updBtn.id -> {
                if (id.isEmpty()){
                    return
                }
                database.use {
                    update(TABLE_CONTACTS, KEY_NAME to name, KEY_MAIL to email)
                            .whereArgs( "$KEY_ID = $id").exec()
                }
                Log.d("dbLog", "updated")
            }
            delBtn.id -> {
                if (id.isEmpty()){
                    return
                }
                database.use {
                    delete(TABLE_CONTACTS, "$KEY_ID=?", arrayOf(id))
                }
                Log.d("dbLog", "deleted")
            }
        }
    }
}
