package com.test.hex.draftapp.numbered

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.test.hex.draftapp.R
import org.jetbrains.anko.find

//private const val LOG_TAG = "myLogs"

class L106 : AppCompatActivity(), L106Fragment2.OnSomeEventListener {
    override fun someEvent(s: String) {
        fragmentManager.findFragmentById(R.id.fragment2)
        val tmp = "Text from Fragment 2: $s"
        find<TextView>(R.id.tvText1).text = tmp
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l106)

        fragmentManager.beginTransaction().apply {
            add(R.id.fragment2, L106Fragment2())
            commit()
        }
        find<Button>(R.id.button).setOnClickListener {
            fragmentManager.findFragmentById(R.id.fragment1)
                    .find<TextView>(R.id.tvText1)
                    .text = getString(R.string.access_to_fragment_1_from_activity)
            fragmentManager.findFragmentById(R.id.fragment2)
                    .find<TextView>(R.id.tvText2)
                    .text = getString(R.string.access_to_fragment_2_from_activity)
        }
    }
}
class L106Fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l106_first, container, false)
                ?.apply {
                    find<Button>(R.id.button1).setOnClickListener {
                        activity.find<Button>(R.id.button).text =
                                context.getString(R.string.access_from_fragment_1)
                    }
        }
    }
}

class L106Fragment2 : Fragment() {
    interface OnSomeEventListener {
        fun someEvent(s: String)
    }

    private var someEventListener: OnSomeEventListener? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            someEventListener = context as OnSomeEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException(context.toString() + " must implement OnSomeEventListener")
        }
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        try {
            someEventListener = activity as OnSomeEventListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement OnSomeEventListener")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l106_second, container, false)
                ?.apply {
                    find<Button>(R.id.button2).setOnClickListener {
                        someEventListener?.someEvent("Test text to Fragment 1")
                    }

        }
    }
}
