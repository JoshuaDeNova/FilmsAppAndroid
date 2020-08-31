package com.example.modelviewmodel.model.observable

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.model.repository.PeliculaRepository
import com.example.modelviewmodel.model.repository.PeliculaRepositoryImpl

class PeliculaObservable: BaseObservable(){

    private var peliculaRepository: PeliculaRepository =
        PeliculaRepositoryImpl()

    fun callPeliculas(){
        peliculaRepository.callPeliculasAPI()
    }

    fun getPeliculas() : MutableLiveData<List<Pelicula>>{
        return peliculaRepository.getPeliculas()
    }

    fun callPeliculasByGenero(genero : String){
        peliculaRepository.callPeliculasByGeneroAPI(genero)
    }

    fun callPeliculasMas18(){
        peliculaRepository.callPeliculasMas18API()
    }

    fun callPeliculasOrderVotos(){
        peliculaRepository.callPeliculasOrderVotos()
    }
}