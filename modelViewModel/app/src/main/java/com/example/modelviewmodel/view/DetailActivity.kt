package com.example.modelviewmodel.view

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.example.modelviewmodel.R
import com.example.modelviewmodel.databinding.DetailActivityBinding
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.viewmodel.DetailPeliculaViewModel
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity(){

    private var detailPeliculaViewModel: DetailPeliculaViewModel? = null
    var pelicula: Pelicula?= null
    var detailTitle: TextView? = null
    var detailDescription: TextView? = null
    var detailNumVotos: TextView? = null
    var detailImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)

        initComponents()
        cargarPelicula()
       // setupBindings(savedInstanceState)
    }

    private fun initComponents(){
        detailTitle = findViewById(R.id.detalleTitulo)
        detailDescription = findViewById(R.id.detalleDescripcion)
        detailNumVotos = findViewById(R.id.detalleNumVotos)
        detailImage = findViewById(R.id.detalleImagen)
    }

    private fun cargarPelicula() {
        var miPantalla: Intent?= getIntent()
        pelicula = miPantalla?.getExtras()?.get("pelicula") as Pelicula?
        detailTitle?.setText(pelicula?.titulo)
        detailDescription?.setText(pelicula?.descripcion)
        Picasso.get().load("http://192.168.1.248:8080/images/" + pelicula?.imagen + ".jpg").into(detailImage)
        detailNumVotos?.setText(pelicula?.numvotos?.toString())
    }
}