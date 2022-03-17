package com.example.habitstracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class EditListElementActivity : AppCompatActivity() {
    private var intentType: String = "create"
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_list_element)

        intentHandler(intent)
    }

    private fun intentHandler(intent: Intent) {
        val createOrEdit = intent.getStringExtra("message")
        if (createOrEdit == "edit") {
            intentType = "edit"
            position = intent.getIntExtra("position", 0)
            val habit = intent.getSerializableExtra("habit") as Habit

            val nameActivity: TextView = findViewById(R.id.textViewNameActivity)
            nameActivity.text = resources.getString(R.string.edit_habit)

            val nameView: EditText = findViewById(R.id.editTextName)
            val descriptionView: EditText = findViewById(R.id.editTextDescription)
            val priorityView: Spinner = findViewById(R.id.spinnerPriority)
            val quantityView: EditText = findViewById(R.id.editTextQuantity)
            val periodView: EditText = findViewById(R.id.editTextPeriod)

            nameView.setText(habit.name)
            descriptionView.setText(habit.description)
            quantityView.setText(habit.quantity)
            periodView.setText(habit.period)

            val arr = resources.getStringArray(R.array.habit_priority)
            val index = arr.indexOf(habit.priority)
            priorityView.setSelection(index)

            val typeView: RadioButton = when (habit.type) {
                resources.getString(R.string.good_habit) -> findViewById(R.id.radioButtonGoodType)
                else -> findViewById(R.id.radioButtonBadType)
            }

            typeView.isChecked = true
        }
    }

    fun onSaveButtonClicked(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        val nameView: EditText = findViewById(R.id.editTextName)
        val descriptionView: EditText = findViewById(R.id.editTextDescription)
        val priorityView: Spinner = findViewById(R.id.spinnerPriority)
        val quantityView: EditText = findViewById(R.id.editTextQuantity)
        val periodView: EditText = findViewById(R.id.editTextPeriod)
        val radioGroup: RadioGroup = findViewById(R.id.radioGroupType)
        val typeView: RadioButton = findViewById(radioGroup.checkedRadioButtonId)

        val habit = Habit(
            nameView.text.toString(),
            descriptionView.text.toString(),
            priorityView.selectedItem.toString(),
            quantityView.text.toString(),
            periodView.text.toString(),
            typeView.text.toString()
        )
        intent.putExtra("message", if (intentType == "create") "newHabit" else "edit")
        intent.putExtra("position", position)
        intent.putExtra("habit", habit)
        startActivity(intent)
    }
}