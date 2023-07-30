package com.example.roomwordsample.fragments

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.roomwordsample.R
import com.example.roomwordsample.WordViewModel
import com.example.roomwordsample.WordViewModelFactory
import com.example.roomwordsample.WordsApplication
import com.example.roomwordsample.entities.Word

class AddWordFragment : Fragment() {

    private lateinit var editWordView: EditText
    private val wordViewModel: WordViewModel by viewModels {
        WordViewModelFactory((activity?.application as WordsApplication).repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_word, container, false)
        editWordView = view.findViewById(R.id.edit_word)
        val button = view.findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            if (TextUtils.isEmpty(editWordView.text)) {
                Toast.makeText(
                    context,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
                ).show()
            } else {
                val strWord = editWordView.text
                val word = Word(strWord.toString())
                wordViewModel.insert(word)
                Toast.makeText(
                    context,
                    "word inserted successfully",
                    Toast.LENGTH_LONG
                ).show()
                findNavController().navigate(R.id.action_addWordFragment_to_homeFragment)
            }
        }
        return view
    }
}