package com.oscargil80.aristidevslivetareas

sealed class  TaskCategory {
    object  Personal : TaskCategory()
    object  Business : TaskCategory()
    object  Other : TaskCategory()
}