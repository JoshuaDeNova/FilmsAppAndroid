package com.example.modelviewmodel.network

import com.example.modelviewmodel.model.Pelicula
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.*
import kotlin.collections.ArrayList

interface ApiService {
    @GET("peliculas")
    fun getPeliculas(): Call<ArrayList<Pelicula>>

    @GET("peliculas/genero/{genero}")
    fun getPeliculasByGenero(@Path("genero") genero: String): Call<ArrayList<Pelicula>>

    @GET("peliculas/edad")
    fun getPeliculasMas18(): Call<ArrayList<Pelicula>>

    @GET("peliculas/orden")
    fun getPeliculasOrderVotos(): Call<ArrayList<Pelicula>>
}