package dev.cc231054.demonstrator_2.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

enum class Screens {
    SpellList,
    SavedSpells,
    MySpells,
    AddSpell
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val activeRoute = navController.currentBackStackEntryAsState().value?.destination?.route?:Screens.MySpells.name
    val activeSreen = Screens.valueOf(activeRoute)

    NavigationBar {
        NavigationBarItem(
            selected = activeSreen == Screens.SpellList,
            onClick = {navController.navigate(Screens.SpellList.name)},
            icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Spell List") },
            label = { Text("Spell List") }
        )
        NavigationBarItem(
            selected = activeSreen == Screens.MySpells,
            onClick = {navController.navigate(Screens.MySpells.name)},
            icon = { Icon(imageVector = Icons.Filled.Build, contentDescription = "My Spells") },
            label = { Text("My Spells") }
        )
        NavigationBarItem(
            selected = activeSreen == Screens.SavedSpells,
            onClick = {navController.navigate(Screens.SavedSpells.name)},
            icon = { Icon(imageVector = Icons.Filled.Favorite, contentDescription = "Favorites") },
            label = { Text("Favorites") }
        )
        NavigationBarItem(
            selected = activeSreen == Screens.AddSpell,
            onClick = {navController.navigate(Screens.AddSpell.name)},
            icon = { Icon(imageVector = Icons.Filled.AddCircle, contentDescription = "Add Spell") },
            label = { Text("Add Spell") }
        )

    }
}