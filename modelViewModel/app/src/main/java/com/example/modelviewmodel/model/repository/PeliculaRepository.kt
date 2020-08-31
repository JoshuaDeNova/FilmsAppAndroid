package com.example.modelviewmodel.model.repository

import androidx.lifecycle.MutableLiveData
import com.example.modelviewmodel.model.Pelicula

interface PeliculaRepository {
    fun getPeliculas(): MutableLiveData<List<Pelicula>>
    fun callPeliculasAPI()
    fun callPeliculasMas18API()
    fun callPeliculasOrderVotos()
    fun callPeliculasByGeneroAPI(genero: String)
}