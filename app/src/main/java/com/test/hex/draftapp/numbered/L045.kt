package com.test.hex.draftapp.numbered

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ExpandableListView
import android.widget.SimpleExpandableListAdapter
import com.test.hex.draftapp.R

class L045 : AppCompatActivity() {
    //company names (groups)
    private val groups = arrayOf("HTC", "Samsung", "LG")
    //phone names (elements)
    private val phonesHTC = arrayOf("Sensation", "Desire", "Wildfire", "Hero", "One")
    private val phonesSamsung = arrayOf("Galaxy S II", "Galaxy S3", "Galaxy Nexus", " Wave", "Galaxy Note")
    private val phonesLG = arrayOf("Optimus", "Optimus Link", "Optimus Black", "Optimus One")

    //group collection
    private lateinit var groupData: MutableList<MutableMap<String, String>>
    //child collection
    private lateinit var childDataItem: MutableList<MutableMap<String, String>>
    //common collection
    private lateinit var childData: MutableList<MutableList<MutableMap<String, String>>>
    //attributes of group or element
    private lateinit var map: MutableMap<String, String>


    lateinit var expListView: ExpandableListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.l045)

        groupData = mutableListOf()
        childDataItem = mutableListOf()
        childData = mutableListOf()
        map = mutableMapOf()

        //fill group collection from array with group names
        for (group in groups) {
            map = mutableMapOf()
            map["groupName"] = group
            groupData.add(map)
        }

        //group attrs
        val groupFrom = arrayOf("groupName")
        //id collection
        val groupTo = intArrayOf(android.R.id.text1)

        for (phone in phonesHTC) {
            map = mutableMapOf()
            map["phoneName"] = phone
            childDataItem.add(map)
        }

        childData.add(childDataItem)

        childDataItem = mutableListOf()

        //second group
        for(phone in phonesSamsung) {
            map = mutableMapOf()
            map["phoneName"] = phone
            childDataItem.add(map)
        }
        childData.add(childDataItem)

        childDataItem = mutableListOf()

        //third group
        for(phone in phonesLG) {
            map = mutableMapOf()
            map["phoneName"] = phone
            childDataItem.add(map)
        }
        childData.add(childDataItem)

        val childFrom = arrayOf("phoneName")

        val childTo = intArrayOf(android.R.id.text1)
        val adapter = SimpleExpandableListAdapter(
                this, groupData,
                android.R.layout.simple_expandable_list_item_1, groupFrom,
                groupTo, childData, android.R.layout.simple_list_item_1,
                childFrom, childTo
        )
        expListView = findViewById(R.id.expListView)
        expListView.setAdapter(adapter)
    }
}
