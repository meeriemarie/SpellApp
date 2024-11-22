package dev.cc231054.demonstrator_2.ui

import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import dev.cc231054.demonstrator_2.SpellApplication

object AppViewModelProviderFactory {
    val Factory = viewModelFactory {
        initializer {
            val application = this[APPLICATION_KEY] as SpellApplication
            SpellViewModel(application.spellRepository)
        }
        initializer {
            val application = this[APPLICATION_KEY] as SpellApplication
            SpellDetailViewModel(this.createSavedStateHandle(), application.spellRepository)
        }
    }
}