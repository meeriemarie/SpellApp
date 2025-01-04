package dev.cc231054.demonstrator_2.data

import dev.cc231054.demonstrator_2.data.db.SpellDao
import dev.cc231054.demonstrator_2.data.db.SpellEntity
import kotlinx.coroutines.flow.map

class SpellRepository (private val spellDao: SpellDao) {
    val spells = spellDao.getAllSpells().map { list ->
        list.map{ entity ->
            Spell(entity.id, entity.name, entity.level, entity.range, entity.duration, entity.description)
        }
    }

    suspend fun findSpellById(spellId: Int) : Spell {
        val entity = spellDao.findSpellById(spellId)
        return Spell(entity.id, entity.name, entity.level, entity.duration, entity.range, entity.description)
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
}