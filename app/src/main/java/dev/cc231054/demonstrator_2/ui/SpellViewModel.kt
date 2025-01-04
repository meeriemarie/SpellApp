package dev.cc231054.demonstrator_2.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.SpellRepository
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpellViewModel(val repository: SpellRepository) : ViewModel() {
    private val _spellUiState = MutableStateFlow(SpellUiState(emptyList(), null))
    val spellsUiState = _spellUiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.spells.collect { data ->
                _spellUiState.update { oldState ->
                    oldState.copy(
                        spells = data
                    )
                }
            }
        }
    }

    fun onCardClick(index: Int) {
        Log.i("State:", index.toString())
        _spellUiState.update {
            it.copy(selectedCardIndex = index)
        }
    }

    fun addSpell(name: String,
                 level: String,
                 duration: String,
                 range: String,
                 description: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpell(
                SpellEntity(
                    name = name,
                    level = level,
                    duration = duration,
                    range = range,
                    description = description
                )
            )

        }
    }

    fun deleteSpell(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSpell(id)
        }
    }

    fun updateSpell(
        id: Int,
        name: String,
        level: String,
        duration: String,
        range: String,
        description: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSpell(
                SpellEntity(
                    id = id,
                    name = name,
                    level = level,
                    duration = duration,
                    range = range,
                    description = description
                )
            )
        }
    }
}