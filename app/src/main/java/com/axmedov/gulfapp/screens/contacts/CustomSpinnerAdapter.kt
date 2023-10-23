package com.axmedov.gulfapp.screens.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.axmedov.gulfapp.R

class CustomSpinnerAdapter(
    private var context: Context,
    private var countries: List<String>
) : BaseAdapter() {
    private var inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return countries.size
    }

    override fun getItem(i: Int): Any {
        return countries[i]
    }

    override fun getItemId(i: Int): Long {
        return 0
    }

    override fun getView(i: Int, view: View, viewGroup: ViewGroup): View {
        val view: View = inflater.inflate(R.layout.item_spinner, null)
        val names = view.findViewById<TextView>(R.id.txtItem) as TextView
        names.text = countries[i]
        return view
    }
}