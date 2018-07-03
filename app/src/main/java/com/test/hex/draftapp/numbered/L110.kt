package com.test.hex.draftapp.numbered

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.test.hex.draftapp.R
import org.jetbrains.anko.find

private const val LOG_TAG = "myLogs"

class L110 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l110)

        find<Button>(R.id.button1).setOnClickListener {
            L110Dialog1().show(fragmentManager, "dlg1")
        }
        find<Button>(R.id.button2).setOnClickListener {
            L110Dialog2().show(fragmentManager, "dlg2")
        }
    }
}

class L110Dialog1 : DialogFragment(), View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.setTitle("Title!")
        return inflater?.inflate(R.layout.l110_dialog_1, container, false)?.apply {
            find<Button>(R.id.button1).setOnClickListener(this@L110Dialog1)
            find<Button>(R.id.button2).setOnClickListener(this@L110Dialog1)
            find<Button>(R.id.button3).setOnClickListener(this@L110Dialog1)
        }
    }

    override fun onClick(v: View?) {
        Log.d(LOG_TAG, "Dialog1: ${(v as? Button)?.text ?: "!ERROR!"}")
        dismiss()
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        Log.d(LOG_TAG, "Dialog1: onDismiss")
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        Log.d(LOG_TAG, "Dialog1: onCancel")
    }
}

class L110Dialog2 : DialogFragment(), DialogInterface.OnClickListener {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(activity).apply {
            setTitle("Title!")
            setPositiveButton(R.string.yes, this@L110Dialog2)
            setNegativeButton(R.string.no, this@L110Dialog2)
            setNeutralButton(R.string.maybe, this@L110Dialog2)
            setMessage(R.string.text_of_your_message)
        }.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        when (which) {
            Dialog.BUTTON_POSITIVE -> {
                logAnswer(R.string.yes)
            }
            Dialog.BUTTON_NEGATIVE -> {
                logAnswer(R.string.no)
            }
            Dialog.BUTTON_NEUTRAL -> {
                logAnswer(R.string.maybe)
            }
        }
    }

    private fun logAnswer(answer: Int) {
        Log.d(LOG_TAG, "Dialog2: ${resources.getString(answer)}")
    }

    override fun onDismiss(dialog: DialogInterface?) {
        super.onDismiss(dialog)
        Log.d(LOG_TAG, "Dialog2: onDismiss")
    }

    override fun onCancel(dialog: DialogInterface?) {
        super.onCancel(dialog)
        Log.d(LOG_TAG, "Dialog2: onCancel")
    }
}
