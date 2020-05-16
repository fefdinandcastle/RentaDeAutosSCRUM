package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.menu_item.view.*

class MenuAdapter(val items : ArrayList<String>, val context: Context?) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.menu_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvMenuItem?.text = items.get(position)
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) , View.OnClickListener{
    // Holds the TextView that will add each animal to
    val tvMenuItem = view.tvMenuItem
    init {
        view.setOnClickListener(this)
    }
    override fun onClick(view: View) {
        Toast.makeText(view.context, "Clicked Position = " + adapterPosition, Toast.LENGTH_SHORT).show()
    }

}