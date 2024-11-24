package dev.cc231054.demonstrator_2.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "spells")
data class SpellEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val level: String,
    val duration: String,
    val range: String,
    val description: String
)
