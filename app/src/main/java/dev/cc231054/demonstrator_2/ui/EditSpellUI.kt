package dev.cc231054.demonstrator_2.ui


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.db.SpellEntity

@JvmOverloads
@Composable
fun EditSpellScreen(modifier: Modifier = Modifier.padding(20.dp), spellViewModel: SpellDetailViewModel = viewModel(factory = AppViewModelProviderFactory.Factory)) {
    val state by spellViewModel.spellDetailUIState.collectAsStateWithLifecycle()
    EditSpell(
        modifier,
        spell = state.spell
    )
}

@Composable
fun EditSpell(modifier: Modifier = Modifier,
              spellViewModel: SpellViewModel = viewModel(factory = AppViewModelProviderFactory.Factory),
              spell: Spell) {


    var name by rememberSaveable { mutableStateOf(spell.name) }
    var level by rememberSaveable { mutableStateOf(spell.level) }
    var duration by rememberSaveable { mutableStateOf(spell.duration) }
    var range by rememberSaveable { mutableStateOf(spell.range) }
    var description by rememberSaveable { mutableStateOf(spell.description) }
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(spell) {
        name = spell.name
        level = spell.level
        duration = spell.duration
        range = spell.range
        description = spell.description
        isFavorite = spell.isFavorite
    }

    Log.i("Spell App", name)
    Log.i("Spell App 2", spell.name)
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        changeName(name, onNameChange = {name = it})
        Spacer(modifier = Modifier.padding(5.dp))
        changeLevel(level, onLevelChange = {level = it})
        Spacer(modifier = Modifier.padding(5.dp))
        changeDuration(duration, onDurationChange = {duration = it})
        Spacer(modifier = Modifier.padding(5.dp))
        changeRange(range, onRangeChange = {range = it})
        Spacer(modifier = Modifier.padding(5.dp))
        changeDescription(description, onDescriptionChange = {description = it})
        updateButton(onUpdateButtonClicked = {
            spellViewModel.updateSpell(
                id = spell.id,
                name = name,
                level = level,
                duration = duration,
                range  = range,
                description = description,
                isFavorite = isFavorite
            )
            name = ""
            level = ""
            duration = ""
            range = ""
            description = ""
            isFavorite = false
        })
    }
}

@Composable
fun changeName(name: String, onNameChange: (String) -> Unit) {
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Enter spell Name")}
        )
}

@Composable
fun changeLevel(level: String, onLevelChange: (String) -> Unit) {
    OutlinedTextField(
        value = level,
        onValueChange = onLevelChange,
        label = { Text(text = "Enter level") }
    )
}

@Composable
fun changeDuration(duration: String, onDurationChange: (String) -> Unit) {
    OutlinedTextField(
        value = duration,
        onValueChange = onDurationChange,
        label = { Text(text = "Enter duration in minutes") }
    )
}

@Composable
fun changeRange(range: String, onRangeChange: (String) -> Unit) {
    OutlinedTextField(
        value = range,
        onValueChange = onRangeChange,
        label = { Text(text = "Enter range in feet") }
    )
}

@Composable
fun changeDescription(description: String, onDescriptionChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text(text = "Enter description") }
    )
}

@Composable
fun updateButton(onUpdateButtonClicked: () -> Unit) {
    Button (onClick = onUpdateButtonClicked) {
        Text(text = "Save Changes")
    }
}