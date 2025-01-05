package dev.cc231054.demonstrator_2.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.db.SpellEntity

@Composable
fun FavoriteSpellScreen(
    modifier: Modifier = Modifier,
    spellViewModel: SpellViewModel = viewModel(factory = AppViewModelProviderFactory.Factory)
) {
    val state by spellViewModel.spellsUiState.collectAsStateWithLifecycle();
    FavoriteSpell(
        fetchedSpells = state.spells,
      onSpellClick = {
      },
        onFavoriteSpellClick = {
            spell: Spell ->  spellViewModel.toggleFavorite(spell)
        }
    )
}

@Composable
fun FavoriteSpell(
    modifier: Modifier = Modifier,
    fetchedSpells: List<Spell>,
    onSpellClick: (Int) -> Unit,
    onFavoriteSpellClick: (Spell) -> Unit
) {
    val favoriteSpells = fetchedSpells.filter { spell: Spell -> spell.isFavorite }
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        LazyColumn { itemsIndexed(favoriteSpells) {
            idx: Int, spell: Spell ->
            SpellListItem(
                spell = spell,
                onCardClick = { onSpellClick(spell.id) },
                onFavoriteClick = { onFavoriteSpellClick(spell) }
            )
        }
        }
    }
}

