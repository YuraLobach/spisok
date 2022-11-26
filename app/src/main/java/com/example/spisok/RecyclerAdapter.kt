package com.example.spisok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(
    private val list: List<Task>,
    val clickHandler: (index: Int) -> Unit
): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }


        override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
            holder.textView.text = list[position].title
            holder.buttonDelete.setOnClickListener{
                clickHandler(holder.adapterPosition)
            }
        }

        override fun getItemCount(): Int = list.size

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textView = itemView.findViewById<TextView>(R.id.textView)
            val buttonDelete = itemView.findViewById<Button>(R.id.buttonDelete)
            val buttonInf = itemView.findViewById<Button>(R.id.buttonInf)

        }
    }

