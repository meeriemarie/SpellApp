package dev.cc231054.demonstrator_2.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.SpellRepository
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import dev.cc231054.demonstrator_2.data.db.remote.ApiSpell
import dev.cc231054.demonstrator_2.data.db.remote.SpellRemoteService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.Result

class SpellViewModel(private val repository: SpellRepository) : ViewModel() {
    private val BASE_URL = "https://www.dnd5eapi.co/api/"

    private val _apiSpells = MutableLiveData<List<ApiSpell>>()
    val apiSpells: LiveData<List<ApiSpell>> = _apiSpells

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val apiService: SpellRemoteService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(SpellRemoteService::class.java)

    fun getApiSpells() {
        viewModelScope.launch {
            try {
                val response = apiService.getSpells()
                if (response.isSuccessful) {
                    _apiSpells.postValue(response.body())
                } else {
                    _errorMessage.postValue("Error: ${response.message()}")
                }
            } catch (e: Exception) {
                _errorMessage.postValue("Failure: ${e.message}")
            }
        }
    }


    private val _spellUiState = MutableStateFlow(SpellUiState(emptyList(), null))
    val spellsUiState = _spellUiState.asStateFlow()

    private val _favoriteUiState = MutableStateFlow<List<Spell>>(emptyList())
    val favoriteUiState = _favoriteUiState.asStateFlow()


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


        viewModelScope.launch {
            repository.favoriteSpells.collect { favorites ->
                _favoriteUiState.value = favorites
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
                 description: String,
                 isFavorite: Boolean,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSpell(
                SpellEntity(
                    name = name,
                    level = level,
                    duration = duration,
                    range = range,
                    description = description,
                    isFavorite = isFavorite
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
        description: String,
        isFavorite: Boolean
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSpell(
                SpellEntity(
                    id = id,
                    name = name,
                    level = level,
                    duration = duration,
                    range = range,
                    description = description,
                    isFavorite = isFavorite
                )
            )
        }
    }

    fun toggleFavorite(spell: Spell) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.toggleFavorite(spell)
        }
    }

}