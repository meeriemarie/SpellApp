package dev.cc231054.demonstrator_2.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface SpellDao {
    @Insert
    suspend fun addSpell(spellEntity: SpellEntity)

    @Update
    suspend fun updateSpell(spellEntity: SpellEntity)

    @Delete
    suspend fun deleteSpell(spellEntity: SpellEntity)

    @Query("SELECT * FROM spells")
    fun getAllSpells(): Flow<List<SpellEntity>>
}