package dev.cc231054.demonstrator_2.data.db.remote


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SpellRemoteService {
    @GET("spells")
    fun getSpells(): Response<List<ApiSpell>>

    @GET("spells/{name}")
    fun getSpell(@Path("name") index: String): ApiSpell
}