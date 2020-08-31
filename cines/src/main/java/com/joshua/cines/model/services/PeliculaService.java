package com.joshua.cines.model.services;

import com.joshua.cines.model.domain.Pelicula;
import com.joshua.cines.model.repositories.IPeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaService implements IService<Pelicula, Long>{

    @Autowired
    private IPeliculaRepository peliculaRepository;
    @Override
    public List<Pelicula> findAll() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula findById(Long aLong) {
        return peliculaRepository.findById(aLong).orElse(null);
    }

    @Override
    public Pelicula save(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public void delete(Long aLong) { peliculaRepository.deleteById(aLong); }

    @Override
    public List<Pelicula> findByColumn(String genero) { return peliculaRepository.findByColumn(genero); }

    @Override
    public List<Pelicula> findOrderVotos() { return peliculaRepository.findOrderVotos(); }

    @Override
    public List<Pelicula> findByEdad() {
        return peliculaRepository.findByEdad();
    }
}
