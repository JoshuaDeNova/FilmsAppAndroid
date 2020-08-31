package com.example.modelviewmodel.model

import com.google.gson.JsonObject
import java.io.Serializable
import java.lang.Exception

class Pelicula (peliculaJson: JsonObject?) : Serializable {
    /*private String titulo, descripcion, trailer, imagen, genero;
    private int numVotos, idPelicula, edad;*/
    var titulo: String? = null
    var descripcion: String? = null
    var trailer: String? = null
    var imagen: String? = null
    var genero: String? = null
    var numvotos = 0
    var idpelicula = 0
    var edad = 0

    init {
        try {
            idpelicula = peliculaJson!!.get("idpelicula").asInt
            titulo = peliculaJson.get("titulo").asString
            descripcion = peliculaJson.get("descripcion").asString
            trailer = peliculaJson.get("trailer").asString
            imagen = peliculaJson.get("imagen").asString
            genero = peliculaJson.get("genero").asString
            numvotos = peliculaJson.get("numvotos").asInt
            edad = peliculaJson.get("edad").asInt
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}