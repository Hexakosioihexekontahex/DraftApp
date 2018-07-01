package com.test.hex.draftapp.numbered

import android.app.Fragment
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.hex.draftapp.R

private const val LOG_TAG = "myLogs"

class L104 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l104)
        Log.d(LOG_TAG, "L104 onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "L104 onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "L104 onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "L104 onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "L104 onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "L104 onDestroy")
    }
}

open class L104FirstFragment : Fragment() {
    open val n = 1

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.d(LOG_TAG, "Fragment$n onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "Fragment$n onCreate")
    }

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Fragment$n onCreateView")
        return inflater?.inflate(R.layout.l104_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(LOG_TAG, "Fragment$n onActivityCreated")
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "Fragment$n onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "Fragment$n onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "Fragment$n onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "Fragment$n onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LOG_TAG, "Fragment$n onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "Fragment$n onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(LOG_TAG, "Fragment$n onDetach")
    }
}

class L104SecondFragment : L104FirstFragment() {
    override val n = 2

    override fun onCreateView(inflater: LayoutInflater?,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "Fragment$n onCreateView")
        return inflater?.inflate(R.layout.l104_second, container, false)
    }
}
