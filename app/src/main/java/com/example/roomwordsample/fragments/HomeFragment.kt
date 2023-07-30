package com.example.roomwordsample.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.WordListAdapter
import com.example.roomwordsample.WordViewModel
import com.example.roomwordsample.WordViewModelFactory
import com.example.roomwordsample.WordsApplication
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment() {

    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = WordListAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        activity?.let {
            wordViewModel.allWords.observe(it) { words ->
                // Update the cached copy of the words in the adapter.
                words?.let { adapter.submitList(it) }

                ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView,
                        viewHolder: RecyclerView.ViewHolder,
                        target: RecyclerView.ViewHolder
                    ): Boolean {
                        return false
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val position = viewHolder.adapterPosition
                        val word = words[position]
                        wordViewModel.delete(word.id)
                        adapter.notifyItemRemoved(viewHolder.adapterPosition)
                        Snackbar.make(recyclerView, "Word deleted", Snackbar.LENGTH_LONG)
                            .setAction(
                                "Undo"
                            ) {
                                adapter.notifyItemInserted(position)
                            }.show()
                    }
                }).attachToRecyclerView(recyclerView)
            }
        }
        val fab = view.findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addWordFragment)
            //findNavController().navigate(R.id.action_homeFragment_to_newWordFragment)
        }
        return view
    }
}