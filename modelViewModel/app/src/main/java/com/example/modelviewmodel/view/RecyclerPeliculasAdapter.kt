package com.example.modelviewmodel.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.modelviewmodel.BR
import androidx.recyclerview.widget.RecyclerView
import com.example.modelviewmodel.model.Pelicula
import com.example.modelviewmodel.viewmodel.PeliculaViewModel

class RecyclerPeliculasAdapter(var peliculaViewModel: PeliculaViewModel, var resource: Int) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerPeliculasAdapter.CardPeliculaHolder>(){

    var peliculas: List<Pelicula>? = null

    fun setPeliculasList(peliculas: List<Pelicula>?){
        this.peliculas = peliculas
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardPeliculaHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding:  ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardPeliculaHolder(binding)
    }

    override fun getItemCount(): Int {
        return peliculas?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardPeliculaHolder, p1: Int) {
        p0.setDataCard(peliculaViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    class CardPeliculaHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var binding: ViewDataBinding? = null

        init {
            this.binding = binding
        }

        fun setDataCard(peliculaViewModel: PeliculaViewModel, position: Int){
            binding?.setVariable(BR.model, peliculaViewModel)
            binding?.setVariable(BR.position, position)
            binding?.executePendingBindings()
        }
    }
}