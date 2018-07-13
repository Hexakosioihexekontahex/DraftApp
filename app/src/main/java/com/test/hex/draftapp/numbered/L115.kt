package com.test.hex.draftapp.numbered

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.ListFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import android.widget.ListView
import android.widget.TextView
import com.test.hex.draftapp.R

class L115 : AppCompatActivity(), L115TitlesFragment.OnItemClickListener {
    var position = 0
    var withDetails = true

    override fun itemClick(position: Int) {
        this.position = position
        showDetails(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l115)

        if (savedInstanceState != null) {
            position = savedInstanceState.getInt("position")
        }
        withDetails = (findViewById<FrameLayout>(R.id.cont) != null)
        if (withDetails) {
            showDetails(position)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("position", position)
    }

    private fun showDetails(position: Int) {
        var detailsFragment = supportFragmentManager
                .findFragmentById(R.id.cont) as L115DetailsFragment?
        if (detailsFragment?.getPosition() != position) {
            detailsFragment = newInstance(position)
            supportFragmentManager.beginTransaction().replace(R.id.cont, detailsFragment).commit()
        } else {
            startActivity(Intent(this, L115Details::class.java)
                    .putExtra("position", position))
        }
    }
}

class L115TitlesFragment : ListFragment() {
    interface OnItemClickListener {
        fun itemClick(position: Int)
    }

    private var listener: OnItemClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val adapter : ArrayAdapter<String> = ArrayAdapter(activity,
                android.R.layout.simple_list_item_1, resources.getStringArray(R.array.headers))
        listAdapter = adapter
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? OnItemClickListener?
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        listener = activity as? OnItemClickListener?
    }

    override fun onListItemClick(l: ListView?, v: View?, position: Int, id: Long) {
        super.onListItemClick(l, v, position, id)
        listener?.itemClick(position)
    }
}

fun newInstance(pos: Int) : L115DetailsFragment {
    val detailsFragment = L115DetailsFragment()
    val args = Bundle()
    args.putInt("position", pos)
    detailsFragment.arguments = args
    return detailsFragment
}

class L115DetailsFragment : Fragment() {
    fun getPosition() : Int = arguments?.getInt("position", 0) ?: 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.l115_details, container, false)
        v.findViewById<TextView>(R.id.tvText).text =
                resources.getStringArray(R.array.content)[getPosition()]
        return v
    }
}
