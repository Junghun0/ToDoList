package com.example.kotlin_todolist.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_todolist.R
import com.example.kotlin_todolist.database.Todo
import com.example.kotlin_todolist.databinding.ItemTodoBinding
import com.example.kotlin_todolist.model.TodoListViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProviders.of(requireActivity())
            .get(TodoListViewModel::class.java)

        val adapter = TodoAdapter{
            //수정화면
            val action = MainFragmentDirections.actionMainFragmentToEditFragment(it)
            findNavController().navigate(action)
        }

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        viewModel.getAll().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
        })

        add_button.setOnClickListener {
            //추가화면
            it.findNavController().navigate(R.id.action_mainFragment_to_addFragment2)
        }
    }
}

class TodoAdapter(private val clickListener: (todo: Todo) -> Unit) :
        RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){
    var items = listOf<Todo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_todo, parent, false)
        val viewHolder = TodoViewHolder(ItemTodoBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.todo = items[position]
    }

    class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root)

}
