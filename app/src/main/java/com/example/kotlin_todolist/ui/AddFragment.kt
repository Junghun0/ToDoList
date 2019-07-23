package com.example.kotlin_todolist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.kotlin_todolist.R
import com.example.kotlin_todolist.database.Todo
import com.example.kotlin_todolist.model.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_edit.*
import java.util.*

class AddFragment : Fragment() {
    private var date: Long = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProviders.of(requireActivity())
            .get(TodoListViewModel::class.java)
        date = calendarView.date
        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year,month,day)
            val timeInMillis = calendar.timeInMillis
            date = timeInMillis
        }

        done_button.setOnClickListener{
            val title = todo_editText.text.toString()

            viewModel.insert(Todo(title, date))
            it.findNavController().popBackStack()
        }

    }


}
