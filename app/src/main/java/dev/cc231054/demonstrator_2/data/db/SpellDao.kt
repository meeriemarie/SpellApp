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

    @Query("DELETE FROM spells WHERE id = :id")
    suspend fun deleteSpell(id: Int)

    @Query("SELECT * FROM spells")
    fun getAllSpells(): Flow<List<SpellEntity>>

    @Query("SELECT * FROM spells WHERE id = :spellId")
    suspend fun findSpellById(spellId : Int) : SpellEntity

    @Query("SELECT * FROM spells WHERE isFavorite = 1")
    fun getFavoriteSpells(): Flow<List<SpellEntity>>

    @Query("UPDATE spells SET isFavorite = :isFav WHERE id = :spellId")
    fun setFavoriteSpell(isFav: Boolean, spellId: Int)
}