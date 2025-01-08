package dev.cc231054.demonstrator_2

import android.app.Application
import dev.cc231054.demonstrator_2.data.SpellRepository
import dev.cc231054.demonstrator_2.data.db.SpellDatabase
import dev.cc231054.demonstrator_2.data.db.remote.SpellRemoteService
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory


class SpellApplication : Application() {
    val spellRepository by lazy {
        val database = SpellDatabase.getDatabase(this)
        SpellRepository(database.spellDao())
    }
}