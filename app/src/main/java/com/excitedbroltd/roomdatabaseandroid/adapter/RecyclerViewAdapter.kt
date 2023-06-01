package com.excitedbroltd.roomdatabaseandroid.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.excitedbroltd.roomdatabaseandroid.Person
import com.excitedbroltd.roomdatabaseandroid.R
import com.excitedbroltd.roomdatabaseandroid.RecyclerListener

class RecyclerViewAdapter(private val listener: RecyclerListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    private var listItem = emptyList<Person>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Log.d("DEMU", "onBindViewHolder: ${listItem.size}")
        val person = listItem[position]
        holder.id.text = person.id.toString()
        holder.name.text = person.name
        holder.age.text = person.age.toString()
        holder.country.text = person.country
        holder.linearLayout.setOnClickListener {
            listener.onClick(position)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id = itemView.findViewById<TextView>(R.id.rv_id)
        val name = itemView.findViewById<TextView>(R.id.rv_name)
        val age = itemView.findViewById<TextView>(R.id.rv_age)
        val country = itemView.findViewById<TextView>(R.id.rv_country)
        val linearLayout = itemView.findViewById<LinearLayout>(R.id.ll_listItem)
    }

    fun setData(listItem: List<Person>) {
        this.listItem = listItem
    }
}

