package com.oscargil80.aristidevslivetareas

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.oscargil80.aristidevslivetareas.databinding.ActivityMainBinding
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    //OSCARRRRRRRRRRRRRRRRR

    private val categories = listOf(
        TaskCategory.Business,
        TaskCategory.Personal,
        TaskCategory.Other
    )

    private  val tasks = mutableListOf(
        Task("PruebaBusiness", TaskCategory.Business),
        Task("PruebaPersonal", TaskCategory.Personal),
        Task("PruebaOtro", TaskCategory.Other)
    )

    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var taskAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
        initListeners()
    }

    private fun initListeners() {
        binding.fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        val btnAddTask:Button = dialog.findViewById(R.id.btnAddTask)
        val etTask:EditText = dialog.findViewById(R.id.etTask)
        val rgCategory:RadioGroup = dialog.findViewById(R.id.rgCategory)

        btnAddTask.setOnClickListener {
           val currentTask = etTask.text.toString()
            if(currentTask.isNotEmpty()){
                val seletedId = rgCategory.checkedRadioButtonId
                val selectedRadioButton:RadioButton = rgCategory.findViewById(seletedId)
                val currentCategory:TaskCategory = when(selectedRadioButton.text){
                    getString(R.string.todo_dialog_category_business) -> TaskCategory.Business
                    getString(R.string.todo_dialog_category_personal) -> TaskCategory.Personal
                    else ->TaskCategory.Other
                }
                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }

        }
        dialog.show()
    }

    private fun updateTasks() {
        val selectedCategories:List<TaskCategory> = categories.filter { it.isSelected }
        val newtask = tasks.filter { selectedCategories.contains(it.category) }
        taskAdapter.task = newtask

        taskAdapter.notifyDataSetChanged()
    }


    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(categories) {position -> updateCategories(position)}
        binding.rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCategories.adapter = categoriesAdapter

        taskAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
        binding.rvTasks.layoutManager =
            LinearLayoutManager(this) // No hay que poner vertical porque  viene por defecto
        binding.rvTasks.adapter = taskAdapter
    }

    private fun updateCategories(position: Int){
        categories[position].isSelected =!categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    private fun onItemSelected(position: Int){
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

}


/*
    private fun initComponet() {
        rvCategories = findViewById(R.id.rvCategories)
    }
*/