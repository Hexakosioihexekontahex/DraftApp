package com.test.hex.draftapp.numbered

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.hex.draftapp.R
import org.jetbrains.anko.find
import java.util.*

private const val LOG_TAG = "myLogs"
private const val PAGE_COUNT = 10
private const val ARGUMENT_PAGE_NUMBER = "arg_page_number"

class L125 : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l125)

        find<ViewPager>(R.id.vpPager).apply {
            adapter = object : FragmentStatePagerAdapter(supportFragmentManager) { // -FragmentPagerAdapter
                override fun getItem(position: Int): Fragment = newInstance(position)
                override fun getCount(): Int = PAGE_COUNT
                override fun getPageTitle(position: Int): CharSequence? {
                    return "Title #$position"
                }
            }
            setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrollStateChanged(p0: Int) {}
                override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {}

                override fun onPageSelected(position: Int) {
                    Log.d(LOG_TAG, "onPageSelected : $position")
                }

            })
        }
    }
}

class L125Fragment : Fragment() {
    private var pageNumber: Int? = null
    private var backColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageNumber = arguments?.getInt(ARGUMENT_PAGE_NUMBER)
        with(Random()) {
            backColor = Color.argb(40,
                    nextInt(256), nextInt(256), nextInt(256))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.l125_fragment, null).apply {
                find<TextView>(R.id.tvPage).apply {
                    text = "Page $pageNumber"
                    setBackgroundColor(backColor)
                }
    }
}

private fun newInstance(page: Int) = L125Fragment().apply {
        arguments = Bundle().apply{
            putInt(ARGUMENT_PAGE_NUMBER, page)
        }
    }
