package com.example.modelviewmodel.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.modelviewmodel.R
import com.example.modelviewmodel.databinding.ActivityMainBinding
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.viewmodel.PeliculaViewModel

class MainActivity : AppCompatActivity() {

    private var peliculaViewModel: PeliculaViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: ActivityMainBinding
                = DataBindingUtil.
            setContentView(this, R.layout.activity_main)

        peliculaViewModel = ViewModelProviders.
            of(this).get(PeliculaViewModel::class.java)

        activityMainBinding.setModel(peliculaViewModel)
        setupListUpdate()
    }

    fun setupListUpdate(){
        //CallCoupons
        peliculaViewModel?.callPeliculas()
        //getCoupons - Lista de cupones
        peliculaViewModel?.getPeliculas()?.observe(this, Observer {
                peliculas: List<Pelicula> ->
            Log.w("Pelicula", peliculas.get(0).titulo)
            peliculaViewModel?.setPeliculasInRecyclerAdapter(peliculas)
        })
    }

}
