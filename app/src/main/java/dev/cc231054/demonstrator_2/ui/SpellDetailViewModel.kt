package dev.cc231054.demonstrator_2.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.SpellRepository
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class SpellDetailUIState(
    val spell: Spell
)

class SpellDetailViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: SpellRepository
) : ViewModel() {
    private val spellId: Int = checkNotNull(savedStateHandle["spellId"])

    private val _spellDetailUIState = MutableStateFlow(SpellDetailUIState(
        spell = Spell(0,"","","","","", false)
    ))
    val spellDetailUIState = _spellDetailUIState.asStateFlow()

    init {
        viewModelScope.launch {
            val spell = repository.findSpellById(spellId)
            _spellDetailUIState.update {
                it.copy(spell = spell)
            }
        }
    }

    fun deleteSpell() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSpell(spellId)
        }
    }

    fun toggleFavorite(spell: Spell) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavorite(spell)
        }
    }
}