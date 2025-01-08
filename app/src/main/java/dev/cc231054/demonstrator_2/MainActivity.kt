package dev.cc231054.demonstrator_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.cc231054.demonstrator_2.ui.AddSpell
import dev.cc231054.demonstrator_2.ui.BottomNavigationBar
import dev.cc231054.demonstrator_2.ui.FavoriteSpellScreen
import dev.cc231054.demonstrator_2.ui.RemoteSpellList
import dev.cc231054.demonstrator_2.ui.Screens
import dev.cc231054.demonstrator_2.ui.SpellApp
import dev.cc231054.demonstrator_2.ui.theme.Demonstrator2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demonstrator2Theme {
                val bottomNav = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = { BottomNavigationBar(bottomNav) }
                )
                { innerPadding ->
                    NavHost(bottomNav, Screens.MySpells.name) {
                        composable (Screens.MySpells.name) {
                            SpellApp(Modifier.padding(innerPadding))
                        }
                        composable(Screens.AddSpell.name) {
                            AddSpell(Modifier.padding(innerPadding))
                        }
                        composable(Screens.SavedSpells.name) {
                            FavoriteSpellScreen(Modifier.padding(innerPadding))
                        }
                        composable(Screens.SpellList.name) {
                            RemoteSpellList(Modifier.padding(innerPadding))
                        }
                    }
                }
            }
        }
    }
}