package com.test.hex.draftapp.numbered

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l020_component_animation.*

private const val MENU_ALPHA_ID = 1
private const val MENU_SCALE_ID = 2
private const val MENU_TRANSLATE_ID = 3
private const val MENU_ROTATE_ID = 4
private const val MENU_COMBO_ID = 5

class L020ComponentAnimation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l020_component_animation)

        registerForContextMenu(tv)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        when (v?.id) {
            tv.id -> {
                menu?.add(0, MENU_ALPHA_ID, 0, "alpha")
                menu?.add(0, MENU_SCALE_ID, 0, "scale")
                menu?.add(0, MENU_TRANSLATE_ID, 0, "translate")
                menu?.add(0, MENU_ROTATE_ID, 0, "rotate")
                menu?.add(0, MENU_COMBO_ID, 0, "combo")
            }
        }
        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        lateinit var anim: Animation
        when (item?.itemId) {
            MENU_ALPHA_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myalpha)
            MENU_SCALE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myscale)
            MENU_TRANSLATE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.mytrans)
            MENU_ROTATE_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.myrotate)
            MENU_COMBO_ID -> anim =
                    AnimationUtils.loadAnimation(this, R.anim.mycombo)
        }
        tv.startAnimation(anim)
        return super.onContextItemSelected(item)
    }
}
