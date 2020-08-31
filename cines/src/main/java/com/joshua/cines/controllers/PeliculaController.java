package com.joshua.cines.controllers;

import com.joshua.cines.model.domain.Pelicula;
import com.joshua.cines.model.domain.Usuario;
import com.joshua.cines.model.services.PeliculaService;
import com.joshua.cines.model.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/peliculas")
public class PeliculaController {
    @Autowired
    private PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> findAll(){
        return peliculaService.findAll();
    }

    @GetMapping(value = "/orden")
    public List<Pelicula> findOrderVotos(){ return peliculaService.findOrderVotos(); }

    @GetMapping
    @RequestMapping(value = "/genero/{genero}")
    public List<Pelicula> findByColumn(@PathVariable("genero") String genero){ return peliculaService.findByColumn(genero); }

    @GetMapping
    @RequestMapping(value = "/edad")
    public List<Pelicula> findByEdad(){ return peliculaService.findByEdad(); }
}
