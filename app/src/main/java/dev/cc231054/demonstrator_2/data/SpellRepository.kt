package dev.cc231054.demonstrator_2.data

import android.util.Log
import dev.cc231054.demonstrator_2.data.db.SpellDao
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import dev.cc231054.demonstrator_2.data.db.remote.ApiSpell
import dev.cc231054.demonstrator_2.data.db.remote.SpellRemoteService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class SpellRepository (
    private val spellDao: SpellDao) {

    val spells = spellDao.getAllSpells().map { list ->
        list.map{ entity ->
            Spell(entity.id, entity.name, entity.level, entity.range, entity.duration, entity.description, entity.isFavorite)
        }
    }

    val favoriteSpells = spellDao.getFavoriteSpells().map { list ->
        list.map { entity ->
            Spell(entity.id, entity.name, entity.level, entity.range, entity.duration, entity.description, entity.isFavorite)
        }
    }

    suspend fun findSpellById(spellId: Int) : Spell {
        val entity = spellDao.findSpellById(spellId)
        return Spell(entity.id, entity.name, entity.level, entity.duration, entity.range, entity.description, entity.isFavorite)
    }

    suspend fun addSpell(spellEntity: SpellEntity) {
        val newSpell = spellDao.addSpell(spellEntity);
        return newSpell;
    }

    suspend fun deleteSpell(spellId: Int) {
        val deletedId = spellDao.deleteSpell(spellId);
        return deletedId;
    }

    suspend fun updateSpell(spellEntity: SpellEntity) {
        val editedSpell = spellDao.updateSpell(spellEntity);
        return editedSpell;
    }

    suspend fun toggleFavorite(spell: Spell) {
        spellDao.setFavoriteSpell(!spell.isFavorite, spell.id)
    }

}