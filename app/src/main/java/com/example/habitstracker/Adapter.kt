package com.example.habitstracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(
    private val habits: List<Habit>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.list_element, parent, false))
    }

    override fun getItemCount(): Int = habits.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(habits[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val nameText: TextView = itemView.findViewById(R.id.name)
        private val descriptionText: TextView = itemView.findViewById(R.id.description)
        private val priorityText: TextView = itemView.findViewById(R.id.priority)
        private val periodText: TextView = itemView.findViewById(R.id.period)
        private val typeText: TextView = itemView.findViewById(R.id.type)
        private val image: ImageView = itemView.findViewById(R.id.circle)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(habit: Habit) {
            nameText.text = habit.name
            descriptionText.text = " — ${habit.description}"
            priorityText.text = "${habit.priority} приоритет"
            periodText.text = "Периодичность: ${habit.period}"
            typeText.text = habit.type[0].toString()

            image.setBackgroundResource(R.drawable.green_shape_oval)
            if (habit.type == itemView.context.resources.getString(R.string.good_habit)) {
                image.setBackgroundResource(R.drawable.green_shape_oval)
            } else {
                image.setBackgroundResource(R.drawable.red_shape_oval)
            }
        }

        override fun onClick(view: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}