package com.test.hex.draftapp.numbered

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l015_context_menu.*

private const val MENU_COLOUR_RED = 1
private const val MENU_COLOUR_GREEN = 2
private const val MENU_COLOUR_BLUE = 3

private const val MENU_SIZE_22 = 4
private const val MENU_SIZE_26 = 5
private const val MENU_SIZE_30 = 6

class L015ContextMenu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l015_context_menu)

        registerForContextMenu(textView1)
        registerForContextMenu(textView2)
    }

    override fun onCreateContextMenu(menu: ContextMenu?,
                                     v: View?,
                                     menuInfo: ContextMenu.ContextMenuInfo?) {
        when (v?.id) {
            textView1.id -> {
                menu?.add(0, MENU_COLOUR_RED, 0, "Red")
                menu?.add(0, MENU_COLOUR_GREEN, 0, "Green")
                menu?.add(0, MENU_COLOUR_BLUE, 0, "Blue")
            }
            textView2.id -> {
                menu?.add(0, MENU_SIZE_22, 0, "22")
                menu?.add(0, MENU_SIZE_26, 0, "26")
                menu?.add(0, MENU_SIZE_30, 0, "30")
            }
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            MENU_COLOUR_RED -> {
                textView1.setTextColor(Color.RED)
                textView1.text = "Text colour = red"
            }
            MENU_COLOUR_GREEN -> {
                textView1.setTextColor(Color.GREEN)
                textView1.text = "Text colour = green"
            }
            MENU_COLOUR_BLUE -> {
                textView1.setTextColor(Color.BLUE)
                textView1.text = "Text colour = blue"
            }
            MENU_SIZE_22 -> {
                textView2.textSize = 22F
                textView2.text = "Text size = 22"
            }
            MENU_SIZE_26 -> {
                textView2.textSize = 26F
                textView2.text = "Text size = 26"
            }
            MENU_SIZE_30 -> {
                textView2.textSize = 30F
                textView2.text = "Text size = 30"
            }
        }

        return super.onContextItemSelected(item)
    }
}
