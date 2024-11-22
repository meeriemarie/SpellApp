package dev.cc231054.demonstrator_2.ui

import dev.cc231054.demonstrator_2.data.Spell

data class SpellUiState(
    val spells : List<Spell>,
    val selectedCardIndex: Int?
)