package dev.cc231054.demonstrator_2.ui


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import dev.cc231054.demonstrator_2.data.db.remote.ApiSpell
import dev.cc231054.demonstrator_2.ui.theme.Typography


@Composable
fun RemoteSpellList(
    modifier: Modifier = Modifier,
    spellViewModel: SpellViewModel = viewModel(factory = AppViewModelProviderFactory.Factory)
) {
    val spells by spellViewModel.apiSpells.observeAsState(initial = emptyList())
    val errorMessage by spellViewModel.errorMessage.observeAsState()

    LaunchedEffect(Unit) {
        spellViewModel.getApiSpells()
    }

    Column(modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier.padding(16.dp))
        LazyColumn {
            if (!errorMessage.isNullOrEmpty()) {
                item {
                    Text(
                            text = errorMessage ?: "",
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                    )
                }
            } else {
                    items(spells) {
                        spell ->
                        RemoteListItem(spell)
                    }
                }
            }
        }
    }


    @Composable
    fun RemoteListItem(apiSpell: ApiSpell,
                       modifier: Modifier = Modifier) {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {  }
                Text(text = apiSpell.name, style = Typography.headlineMedium)
            }
        }
    }
