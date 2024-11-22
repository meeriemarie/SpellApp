package dev.cc231054.demonstrator_2.data

import dev.cc231054.demonstrator_2.data.db.SpellDao
import kotlinx.coroutines.flow.map

class SpellRepository (private val spellDao: SpellDao){
    val spells = spellDao.getAllSpells().map { list ->
        list.map{ entity ->
            Spell(entity.id, entity.name, entity.level, entity.range, entity.duration, entity.description)
        }
    }

    suspend fun findSpellById(spellId: Int) : Spell {
        val entity = spellDao.findSpellById(spellId)
        return Spell(entity.id, entity.name, entity.level, entity.duration, entity.range, entity.description)
    }
}