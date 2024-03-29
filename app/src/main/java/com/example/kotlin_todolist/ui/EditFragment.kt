package com.example.kotlin_todolist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.kotlin_todolist.R
import com.example.kotlin_todolist.databinding.FragmentEditBinding
import com.example.kotlin_todolist.model.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_edit.*
import java.util.*

class EditFragment : Fragment() {
    private val viewModel: TodoListViewModel by lazy{
        ViewModelProviders.of(requireActivity()).get(TodoListViewModel::class.java)
    }
    private var date: Long = 0
    val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentEditBinding.bind(view)
        val todo = args.todo

        binding.todo = args.todo

        date = calendarView.date
        calendarView.setOnDateChangeListener { calendarView, year, month, day ->
            val calendar = Calendar.getInstance()
            calendar.set(year,month,day)
            val timeInMillis = calendar.timeInMillis
            date = timeInMillis
        }

        done_button.setOnClickListener{
            val title = todo_editText.text.toString()

            todo.title = title
            todo.date = date

            viewModel.update(todo)
            findNavController().popBackStack()
        }

        delete_button.setOnClickListener{
            viewModel.delete(todo)
            findNavController().popBackStack()
        }
    }

}
