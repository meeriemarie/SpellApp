package dev.cc231054.demonstrator_2.data.db.mapper

import dev.cc231054.demonstrator_2.data.db.SpellEntity
import dev.cc231054.demonstrator_2.data.db.remote.ApiSpell

fun ApiSpell.toEntity(): SpellEntity {
    return SpellEntity(
        id = 0,
        name = this.name,
        level = this.level.toString(),
        duration = "Unknown",
        range = "Unknown",
        description = "No description available.",
        isFavorite = false
    )
}

fun SpellEntity.toApiModel(): ApiSpell {
    return ApiSpell(
        index = this.id.toString(),
        url = "",
        name = this.name,
        level = this.level.toIntOrNull() ?: 0
    )
}