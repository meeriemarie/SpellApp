package dev.cc231054.demonstrator_2.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.cc231054.demonstrator_2.data.SpellRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpellViewModel(val repository: SpellRepository) : ViewModel() {
    private val _spellUiState = MutableStateFlow(SpellUiState(emptyList(), null))
    val spellsUiState = _spellUiState

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
        _spellUiState.update {
            it.copy(selectedCardIndex = index)
        }
    }
}