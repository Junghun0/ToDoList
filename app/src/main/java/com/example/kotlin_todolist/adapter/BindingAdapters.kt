package com.example.kotlin_todolist.adapter

import android.widget.CalendarView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("date")
fun longDateToString(view: TextView, date: Long){
    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    view.text = sdf.format(Date(date))
}

@BindingAdapter("setDate")
fun setCalendarDate(view: CalendarView, date: Long){
    view.date = date
}