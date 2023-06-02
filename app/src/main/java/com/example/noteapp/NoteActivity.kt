package com.example.noteapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import com.example.noteapp.databinding.ActivityNoteBinding
import java.io.Serializable

class NoteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val note = intent.serializable<Note>("note_details")
        val title = note?.title
        val content = note?.content
        binding.etTitle.setText(title)
        binding.etContent.setText(content)
        binding.btnEditTodo.setOnClickListener {

            onEdit()

        }

    }




    inline fun <reified T : Serializable> Intent.serializable(key: String): T? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(key, T::class.java)
            else -> @Suppress("DEPRECATION") getSerializableExtra(key) as? T
        }



    private fun onEdit() {

        Intent(this, MainActivity::class.java).apply{

            startActivity(this)
        }


    }
}