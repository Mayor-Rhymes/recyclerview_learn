package com.example.noteapp

import java.io.Serializable

data class Note (
    val title: String,
    val content: String,
): Serializable
