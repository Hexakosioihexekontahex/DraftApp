package com.test.hex.draftapp.numbered

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.test.hex.draftapp.R

private const val DIALOG_EXIT = 1

class L060 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l060)

        findViewById<Button>(R.id.bExit).setOnClickListener {
            showDialog(DIALOG_EXIT)
        }
    }

    override fun onCreateDialog(id: Int): Dialog {
        if (id == DIALOG_EXIT) {
            val adb = AlertDialog.Builder(this)
            adb.apply {
                setTitle(R.string.exit)
                setMessage(R.string.save_data)
                setIcon(android.R.drawable.ic_dialog_info)
                setPositiveButton(R.string.yes, myClickListener)
                setNegativeButton(R.string.no, myClickListener)
                setNeutralButton(R.string.cancel, myClickListener)
                setCancelable(false)
            }
            return adb.create()
        }
        return super.onCreateDialog(id)
    }

    private val myClickListener = DialogInterface.OnClickListener {
        _, which ->
        when (which) {
            Dialog.BUTTON_POSITIVE -> {
                saveData()
                finish()
            }
            Dialog.BUTTON_NEGATIVE -> {
                finish()
            }
            Dialog.BUTTON_NEUTRAL -> {

            }
        }
    }

    private fun saveData(){
        Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT)
                .show()
    }

    override fun onBackPressed() {
        showDialog(DIALOG_EXIT)
        super.onBackPressed()
    }
}
