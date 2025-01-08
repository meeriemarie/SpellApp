package dev.cc231054.demonstrator_2.data.db.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

data class ApiSpell (
        val index: String,
        val url: String,
        val name: String,
        val level: Int
    )