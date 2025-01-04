package dev.cc231054.demonstrator_2.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dev.cc231054.demonstrator_2.data.Spell
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import dev.cc231054.demonstrator_2.ui.theme.Typography

enum class Routes(val route: String) {
    MySpells("my spells"),
    SpellDetail("details/{spellId}"),
    EditSpell("edit/{spellId}")
}

@Composable
fun SpellApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController, Routes.MySpells.route, modifier = Modifier) {
        composable(Routes.MySpells.route) {
            SpellListScreen(
                onDeleteClick = {
                    navController.navigate(Screens.MySpells.name)
                },
                onEditClick = {
                    // navController.navigate(Screens.MySpells.name)
                    navController.navigate("edit")
                })
            {
                spellId -> navController.navigate("details/$spellId")
            }
        }
        composable(Routes.SpellDetail.route, arguments = listOf(navArgument("spellId") {
            type = NavType.IntType
        })) {
            SpellsDetailScreen(
                onDeleteClick = {
                    navController.navigate(Screens.MySpells.name)
            },
                onEditClick = {
                    spellId -> navController.navigate("edit/$spellId")
                })
        }
        composable (Routes.EditSpell.route, arguments = listOf(navArgument("spellId") {
            type = NavType.IntType
        })){
            EditSpellScreen()
        }
    }
}

@Composable
fun SpellsDetailScreen(
    modifier: Modifier = Modifier
        .padding(20.dp),
    spellDetailViewModel: SpellDetailViewModel = viewModel(factory = AppViewModelProviderFactory.Factory),
    onDeleteClick: () -> Unit,
    onEditClick:(spellId: Int) -> Unit
) {
    val state by spellDetailViewModel.spellDetailUIState.collectAsStateWithLifecycle()
    SpellDetails(
        state.spell,
        modifier,
        onDeleteButton = {
            spellDetailViewModel.deleteSpell()
            onDeleteClick()
        },
        onEditButton = {
            onEditClick(state.spell.id)
        }
    )
}

@JvmOverloads
@Composable
fun SpellListScreen(
    modifier: Modifier = Modifier,
    spellViewModel: SpellViewModel = viewModel(factory = AppViewModelProviderFactory.Factory),
    onDeleteClick: () -> Unit,
    onEditClick: () -> Unit,
    onSpellClick: (Int) -> Unit
) {
    val state by spellViewModel.spellsUiState.collectAsStateWithLifecycle();
    Log.i("Spell App", "Selected: ${state.selectedCardIndex}")
    Column (modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            itemsIndexed(state.spells) {
                index: Int, spell: Spell ->
                Log.i("Spell App", "Selected: ${state.selectedCardIndex}")
                Log.i("Spell App: Index", "Selected: ${index}")
                if (index == state.selectedCardIndex) {
                    SpellDetails(spell,
                        onDeleteButton = {
                            spellViewModel.deleteSpell(index)
                            onDeleteClick()
                        },
                        onEditButton = {
                            spellViewModel.onCardClick(index)
                            onEditClick()
                        })
                } else {
                    Log.i("Spell App: Index", "Selected: ${index}")
                    SpellListItem(spell,
                        onCardClick = {
                            onSpellClick(spell.id)
                            spellViewModel.onCardClick(index)
                    })
                }
            }
        }
    }
}

@Composable
fun SpellListItem(spell: Spell,
                  onCardClick: () -> Unit,
                  modifier: Modifier = Modifier
) {
    OutlinedCard(onClick = onCardClick, modifier = modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(Modifier.padding(16.dp)) {
            Text(spell.name, style = Typography.headlineMedium)
        }
    }
}

@Composable
fun SpellDetails(spell: Spell,
                 modifier: Modifier = Modifier,
                 onDeleteButton: () -> Unit,
                 onEditButton: () -> Unit){
    OutlinedCard(
        modifier
            .fillMaxWidth()
            .padding(8.dp)) {
        Column (Modifier.padding(16.dp)) {
            Text(spell.name, style = Typography.headlineMedium)
            Column {
                Text("Level ${spell.level} spell", style = Typography.bodySmall)
                Spacer(Modifier.width(16.dp))
                Text("Range: ${spell.range} feet", style = Typography.bodySmall)
                Spacer(Modifier.width(16.dp))
                Text("Duration: ${spell.duration} minutes", style = Typography.bodySmall)
                Spacer(Modifier.width(16.dp))
                Text(spell.description, style = Typography.bodySmall)
                Spacer(Modifier.width(16.dp))
                Button(onClick = onDeleteButton) {
                    Icon( imageVector = Icons.Filled.Delete, contentDescription = "Delete")
                }
                Button(onClick = onEditButton) {
                    Icon( imageVector = Icons.Filled.Edit, contentDescription = "Edit")
                }
            }
        }
    }
}