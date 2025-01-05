package dev.cc231054.demonstrator_2.ui

import android.widget.Button
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AddSpell(modifier: Modifier = Modifier,
             spellViewModel: SpellViewModel = viewModel(factory = AppViewModelProviderFactory.Factory)) {
    var name by rememberSaveable { mutableStateOf("") }
    var level by rememberSaveable { mutableStateOf("") }
    var duration by rememberSaveable { mutableStateOf("") }
    var range by rememberSaveable { mutableStateOf("") }
    var description by rememberSaveable { mutableStateOf("") }
    var isFavorite by rememberSaveable { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(5.dp))
        inputName(name, onNameChange = {name = it})
        Spacer(modifier = Modifier.padding(5.dp))
        inputLevel(level, onLevelChange = {level = it})
        Spacer(modifier = Modifier.padding(5.dp))
        inputDuration(duration, onDurationChange = {duration = it})
        Spacer(modifier = Modifier.padding(5.dp))
        inputRange(range, onRangeChange = {range = it})
        Spacer(modifier = Modifier.padding(5.dp))
        inputDescription(description, onDescriptionChange = {description = it})
        addButton(onAddButtonClicked = {
            spellViewModel.addSpell(
                name,
                level,
                duration,
                range,
                description,
                isFavorite
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
fun inputName(name: String, onNameChange: (String) -> Unit) {
    OutlinedTextField(
        value = name,
        onValueChange = onNameChange,
        label = { Text(text = "Enter spell name") }
    )
}

@Composable
fun inputLevel(level: String, onLevelChange: (String) -> Unit) {
    OutlinedTextField(
        value = level,
        onValueChange = onLevelChange,
        label = { Text(text = "Enter level") }
    )
}

@Composable
fun inputDuration(duration: String, onDurationChange: (String) -> Unit) {
    OutlinedTextField(
        value = duration,
        onValueChange = onDurationChange,
        label = { Text(text = "Enter duration in minutes") }
    )
}

@Composable
fun inputRange(range: String, onRangeChange: (String) -> Unit) {
    OutlinedTextField(
        value = range,
        onValueChange = onRangeChange,
        label = { Text(text = "Enter range in feet") }
    )
}

@Composable
fun inputDescription(description: String, onDescriptionChange: (String) -> Unit) {
    OutlinedTextField(
        value = description,
        onValueChange = onDescriptionChange,
        label = { Text(text = "Enter description") }
    )
}

@Composable
fun addButton(onAddButtonClicked: () -> Unit) {
    Button (onClick = onAddButtonClicked) {
        Text(text = "Add Spell")
    }
}