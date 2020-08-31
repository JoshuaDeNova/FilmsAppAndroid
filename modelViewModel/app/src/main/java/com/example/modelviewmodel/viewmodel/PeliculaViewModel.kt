package com.example.modelviewmodel.viewmodel

import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.modelviewmodel.R
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.model.observable.PeliculaObservable
import com.example.modelviewmodel.view.DetailActivity
import com.example.modelviewmodel.view.RecyclerPeliculasAdapter


class PeliculaViewModel: ViewModel() {

    private var peliculaObservable: PeliculaObservable = PeliculaObservable()
    private var recyclerPeliculasAdapter: RecyclerPeliculasAdapter? = null
    var genero = MutableLiveData<String>()
    var categoria : String? = null

    fun callPeliculas(){
        peliculaObservable.callPeliculas()
    }

    fun getPeliculas() : MutableLiveData<List<Pelicula>> {
        return peliculaObservable.getPeliculas()
    }

    fun callPeliculasOrderVotos(){
        return peliculaObservable.callPeliculasOrderVotos()
    }

    fun callPeliculasByGenero(genero: String){
        peliculaObservable.callPeliculasByGenero(genero)
    }

    fun callPeliculasMas18(){
        peliculaObservable.callPeliculasMas18()
    }

    fun setPeliculasInRecyclerAdapter(peliculas: List<Pelicula>){
        recyclerPeliculasAdapter?.setPeliculasList(peliculas)
        recyclerPeliculasAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerPeliculasAdapter(): RecyclerPeliculasAdapter?{
        recyclerPeliculasAdapter = RecyclerPeliculasAdapter(this, R.layout.card_pelicula)
        return recyclerPeliculasAdapter
    }

    fun getPeliculaAt(position: Int): Pelicula?{
        var peliculas: List<Pelicula>? = peliculaObservable.getPeliculas().value
        return peliculas?.get(position)
    }

    fun clickBusquedaGenero(v: View){
        if(genero?.value == null || genero?.value == ""){
            callPeliculas()
        } else {
            categoria = genero.value
            callPeliculasByGenero(categoria!!)
        }
    }

    fun clickFiltroEdad(v: View){
        callPeliculasMas18()
    }

    fun clickDetail(v: View, pelicula:Pelicula){
        var pelicula = pelicula
        val intent: Intent = Intent(v.context, DetailActivity::class.java).putExtra("pelicula", pelicula)
        v.context.startActivity(intent)
    }

    fun clickOrderVotos(v: View){
        callPeliculasOrderVotos()
    }
}