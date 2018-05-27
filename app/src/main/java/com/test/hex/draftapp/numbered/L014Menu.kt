package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.CheckBox
import android.widget.Toast
import com.test.hex.draftapp.R

class L014Menu : AppCompatActivity() {

    lateinit var checkBox1: CheckBox
    lateinit var checkBox2: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l014_menu)

        checkBox1 = findViewById(R.id.checkBox1)
        checkBox2 = findViewById(R.id.checkBox2)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        checkBox2.setOnCheckedChangeListener { _, _ ->
            invalidateOptionsMenu() }
        menu?.add(2, 4, 4, "item4")?.isCheckable = true
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId

        when (id) {
            R.id.action_settings -> {
                Toast.makeText(this,
                        getString(R.string.action_settings),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item1 -> {
                Toast.makeText(this,
                        getString(R.string.action_item1),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item2 -> {
                Toast.makeText(this,
                        getString(R.string.action_item2),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            R.id.action_item3 -> {
                Toast.makeText(this,
                        getString(R.string.action_item3),
                        Toast.LENGTH_SHORT)
                        .show()
            }
            4 -> {
                item.isChecked = !item.isChecked
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.action_mail)?.isVisible = checkBox2.isChecked

        menu?.setGroupVisible(R.id.group1, checkBox1.isChecked)

        return super.onPrepareOptionsMenu(menu)
    }
}
