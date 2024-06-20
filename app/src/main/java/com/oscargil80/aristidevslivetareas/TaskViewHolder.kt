package com.oscargil80.aristidevslivetareas

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder (view: View):RecyclerView.ViewHolder(view) {

    private val tvtask: TextView = view.findViewById(R.id.tvTask)
    private val cbtask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task){
    tvtask.text = task.name
    }



}