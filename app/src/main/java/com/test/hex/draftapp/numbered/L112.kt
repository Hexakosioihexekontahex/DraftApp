package com.test.hex.draftapp.numbered

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l112.*

class L112 : AppCompatActivity() {
    val MENU_ID = 1
    lateinit var fragment1: Fragment
    lateinit var fragment2: Fragment
    lateinit var fragment: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l112)

        fragment1 = L112Fragment1()
        fragment = fragment1
        fragment2 = L112Fragment2()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.l112_menu, menu)
        menu?.setGroupVisible(R.id.groupVsbl, cbShowHide.isChecked)
        if (cbAddDelete.isChecked) {
            menu?.add(0, MENU_ID, 0, "Item 1")?.apply {
                setIcon(android.R.drawable.ic_delete)
                setShowAsAction(
                        MenuItem.SHOW_AS_ACTION_ALWAYS or MenuItem.SHOW_AS_ACTION_WITH_TEXT
                )
            }
        } else {
            menu?.removeItem(MENU_ID)
        }
        return true
    }

    fun onClick(v: View?) {
        when (v?.id) {
            cbAddDelete.id, cbShowHide.id -> {
                invalidateOptionsMenu()
            }
            button1.id -> {
                fragment = if (fragment == fragment1) fragment2 else fragment1
                fragmentManager.beginTransaction()
                        .replace(R.id.flCont, fragment)
                        .commit()
            }
        }
    }
}

class L112Fragment1 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l112_frag1, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.l112_fragment1, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}

class L112Fragment2 : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l112_frag2, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.l112_fragment2, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}
