package dev.cc231054.demonstrator_2.data.db.remote

data class DetailSpell (
    val index: String,
    val url: String,
    val name: String,
    val level: Int,
    val duration: String,
    val range: String,
    val desc: String,
    val higher_level: String,
    val isRitual: Boolean,
    val isConcentration: Boolean,
    val isFavorite: Boolean = false
)