package com.oscargil80.aristidevslivetareas

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TaskViewHolder (view: View):RecyclerView.ViewHolder(view) {

    private val tvtask: TextView = view.findViewById(R.id.tvTask)
    private val cbtask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task){
     if(task.isSelected){
         tvtask.paintFlags = tvtask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
     }else{
         tvtask.paintFlags = tvtask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
     }
        cbtask.isChecked = task.isSelected

        tvtask.text = task.name


        val color = when (task.category){
            TaskCategory.Business -> R.color.business_category
            TaskCategory.Other -> R.color.other_category
            TaskCategory.Personal -> R.color.personal_category
        }

        cbtask.buttonTintList = ColorStateList.valueOf(
            ContextCompat.getColor(cbtask.context, color)
        )

    }



}