package com.example.modelviewmodel.model.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.network.ApiAdapter
import com.example.modelviewmodel.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeliculaRepositoryImpl: PeliculaRepository {

    private var peliculas = MutableLiveData<List<Pelicula>>()

    override fun getPeliculas(): MutableLiveData<List<Pelicula>> {
        return peliculas;
    }

    override fun callPeliculasAPI() {
        val apiAdapter = ApiAdapter()
        val apiService = apiAdapter.getClientService()
        val call = apiService.getPeliculas()

        call.enqueue(object : Callback<ArrayList<Pelicula>> {
            override fun onFailure(call: Call<ArrayList<Pelicula>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t?.stackTrace
            }

            override fun onResponse(call: Call<ArrayList<Pelicula>>, response: Response<ArrayList<Pelicula>>) {
                val responseBody = response.body()
                peliculas.value = responseBody
            }
        })
    }

    override fun callPeliculasMas18API() {
        val apiAdapter = ApiAdapter()
        val apiService: ApiService = apiAdapter.getClientService()
        val call : Call<ArrayList<Pelicula>> = apiService.getPeliculasMas18()

        call.enqueue(object : Callback<ArrayList<Pelicula>>{
            override fun onFailure(call: Call<ArrayList<Pelicula>>, t: Throwable){
                Log.e("ERROR: ", t.message)
                t?.stackTrace
            }

            override fun onResponse(call: Call<ArrayList<Pelicula>>, response: Response<ArrayList<Pelicula>>){
                val responseBody: ArrayList<Pelicula>? = response.body()
                peliculas.value = responseBody
            }
        })
    }

    override fun callPeliculasOrderVotos() {
        val apiAdapter = ApiAdapter()
        val apiService: ApiService = apiAdapter.getClientService()
        val call : Call<ArrayList<Pelicula>> = apiService.getPeliculasOrderVotos()

        call.enqueue(object : Callback<ArrayList<Pelicula>>{
            override fun onFailure(call: Call<ArrayList<Pelicula>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t?.stackTrace
            }

            override fun onResponse(call: Call<ArrayList<Pelicula>>, response: Response<ArrayList<Pelicula>>) {
                val responseBody: ArrayList<Pelicula>? = response.body()
                peliculas.value = responseBody
            }
        })
    }

    override fun callPeliculasByGeneroAPI(genero: String) {
        val apiAdapter = ApiAdapter()
        val apiService: ApiService = apiAdapter.getClientService()
        val call: Call<ArrayList<Pelicula>> = apiService.getPeliculasByGenero(genero)

        call.enqueue(object : Callback<ArrayList<Pelicula>>{
            override fun onFailure(call: Call<ArrayList<Pelicula>>, t: Throwable) {
                Log.e("ERROR: ", t.message)
                t?.stackTrace
            }

            override fun onResponse(call: Call<ArrayList<Pelicula>>, response: Response<ArrayList<Pelicula>>) {
                val responseBody = response.body()
                peliculas.value = responseBody
            }
        })
    }
}

