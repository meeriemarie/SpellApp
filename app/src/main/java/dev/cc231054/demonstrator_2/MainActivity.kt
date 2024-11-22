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
import dev.cc231054.demonstrator_2.ui.SpellApp
import dev.cc231054.demonstrator_2.ui.theme.Demonstrator2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Demonstrator2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   SpellApp(Modifier.padding(innerPadding))
                }
            }
        }
    }
}