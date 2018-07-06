package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l113.*

private const val LOG_TAG = "myLogs"

class L113 : AppCompatActivity() {
    private var actionMode: ActionMode? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l113)

        lvActionMode.apply {
            adapter = ArrayAdapter<String>(this@L113,
                    android.R.layout.simple_list_item_activated_1,
                    /*data*/ arrayOf("one", "two", "three", "four", "five"))
            choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
            setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
                override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                    mode?.finish()
                    return false
                }

                override fun onItemCheckedStateChanged(mode: ActionMode?, position: Int, id: Long, checked: Boolean) {
                    Log.d(LOG_TAG, "position = $position, checked = $checked")
                }

                override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    mode?.menuInflater?.inflate(R.menu.l113_context, menu)
                    return true
                }

                override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                    return false
                }

                override fun onDestroyActionMode(mode: ActionMode?) {

                }

            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.l113_main, menu)
        return true
    }

    private val callback = object : ActionMode.Callback {
        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
            Log.d(LOG_TAG, "item: $item")
            return false
        }

        override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            mode?.menuInflater?.inflate(R.menu.l113_context, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            Log.d(LOG_TAG, "onDestroyActionMode")
            actionMode = null
        }

    }
}
