package com.example.noteapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding : ActivityMainBinding
    var notes = mutableListOf(
        Note("This is the first note", "This is the content"),
        Note("This is the second note", "This is the second content"),
        Note("This is the third note", "This is the third content"),
        Note("This is the fifth note", "This is the fifth content"),
    )
    private lateinit var adapter: NoteAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = NoteAdapter(notes)

        binding.rvNotes.adapter = adapter
        binding.rvNotes.layoutManager = LinearLayoutManager(this@MainActivity)

        adapter.setOnClickListener (
            object : NoteAdapter.OnClickListener {

                override fun onClick(position: Int, model: Note) {
                    Intent(this@MainActivity, NoteActivity::class.java).apply {

                        this.putExtra("note_details", model)
                        startActivity(this)
                    }

                }
            }
        )




        binding.fabNewTodo.setOnClickListener {

            enableDialog()
        }



    }


    private fun enableDialog() {

        val createNoteDialog = Dialog(this)
        createNoteDialog.setContentView(R.layout.create_note_dialog)
        val btnCreateTodo = createNoteDialog.findViewById<Button>(R.id.btnCreateTodo)

        btnCreateTodo.setOnClickListener {

            val title = createNoteDialog.findViewById<EditText>(R.id.etTitle).text.toString()
            val content = createNoteDialog.findViewById<EditText>(R.id.etContent).text.toString()
            val note = Note(title, content)
            notes.add(note)

            adapter.notifyItemInserted(notes.lastIndex)
            Log.d("notes", "$notes")
            Toast.makeText(this, "New note has been created", Toast.LENGTH_LONG).show()
            createNoteDialog.dismiss()

        }

        createNoteDialog.show()
    }



}