package dev.cc231054.demonstrator_2.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class Screens {
    Spellbook,
    AddSpell
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val activeRoute = navController.currentBackStackEntryAsState().value?.destination?.route?:Screens.Spellbook.name
    val activeSreen = Screens.valueOf(activeRoute)

    NavigationBar {
        NavigationBarItem(
            selected = activeSreen == Screens.Spellbook,
            onClick = {navController.navigate(Screens.Spellbook.name)},
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Spellbook") },
            label = { Text("Spellbook") }
        )
        NavigationBarItem(
            selected = activeSreen == Screens.AddSpell,
            onClick = {navController.navigate(Screens.AddSpell.name)},
            icon = { Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add Spell") },
            label = { Text("Add Spells") }
        )
    }
}