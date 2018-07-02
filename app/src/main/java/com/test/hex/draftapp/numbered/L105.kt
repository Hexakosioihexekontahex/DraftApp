package com.test.hex.draftapp.numbered

import android.app.Fragment
import android.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.hex.draftapp.R
import kotlinx.android.synthetic.main.l105.*

class L105 : AppCompatActivity() {
    private lateinit var frag1: L105Fragment1
    private lateinit var frag2: L105Fragment2
    private lateinit var fTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l105)

        frag1 = L105Fragment1()
        frag2 = L105Fragment2()

        button1.setOnClickListener { wrap { fTransaction.add(R.id.frCont, frag1) } }
        button2.setOnClickListener { wrap { fTransaction.remove(frag1) } }
        button3.setOnClickListener { wrap { fTransaction.replace(R.id.frCont, frag2) } }
    }

    private fun wrap(function: () -> FragmentTransaction) {
        fTransaction = fragmentManager.beginTransaction()
        function()
        if (cbBackStack.isChecked) fTransaction.addToBackStack(null)
        fTransaction.commit()
    }
}

class L105Fragment1 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l105_frag1, container, false)
    }
}

class L105Fragment2 : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.l105_frag2, container, false)
    }
}
