package com.example.habitstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity(), Adapter.OnItemClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        val layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = layoutManager
        val adapter = Adapter(habits, this)
        recyclerView.adapter = adapter

        intentHandler(intent, adapter)
    }

    private fun intentHandler(intent: Intent, adapter: Adapter) {
        val message = intent.getStringExtra("message")

        if (message == "newHabit") {
            val habit = intent.getSerializableExtra("habit") as Habit
            habits.add(habit)
            adapter.notifyItemInserted(habits.size - 1)
        } else if (message == "edit") {
            val position = intent.getIntExtra("position", 0)
            habits[position] = intent.getSerializableExtra("habit") as Habit
        }
    }

    fun onCreateButtonClicked(view: View) {
        val intent = Intent(this, EditListElementActivity::class.java)
        intent.putExtra("message", "create")

        startActivity(intent)
    }

    override fun onItemClick(position: Int) {
        val clickItem = habits[position]
        val intent = Intent(this, EditListElementActivity::class.java)
        intent.putExtra("message", "edit")
        intent.putExtra("position", position)
        intent.putExtra("habit", clickItem)

        startActivity(intent)
    }
}