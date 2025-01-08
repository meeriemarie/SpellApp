package dev.cc231054.demonstrator_2.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.cc231054.demonstrator_2.data.db.remote.ApiSpell

@Database(entities = [SpellEntity::class, ApiSpell::class], version = 4, exportSchema = false)
abstract class SpellDatabase: RoomDatabase() {
    abstract fun spellDao(): SpellDao

    companion object {
        @Volatile
        private var Instance: SpellDatabase? = null

        fun getDatabase(context: Context): SpellDatabase {
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, SpellDatabase::class.java, "spell_database")
                    .fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                return instance
            }
        }
    }
}