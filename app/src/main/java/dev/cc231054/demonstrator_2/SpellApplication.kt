package dev.cc231054.demonstrator_2

import android.app.Application
import dev.cc231054.demonstrator_2.data.SpellRepository
import dev.cc231054.demonstrator_2.data.db.SpellDatabase

class SpellApplication : Application() {
    val spellRepository by lazy {
        val database = SpellDatabase.getDatabase(this)

        SpellRepository(database.spellDao())
    }
}