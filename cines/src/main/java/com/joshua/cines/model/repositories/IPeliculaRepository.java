package com.joshua.cines.model.repositories;

import com.joshua.cines.model.domain.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPeliculaRepository extends JpaRepository<Pelicula, Long> {

    @Query("SELECT p FROM Pelicula p WHERE p.genero LIKE %:genero%")
    public List<Pelicula> findByColumn(String genero);

    @Query(value = "SELECT * FROM Pelicula p ORDER BY p.numvotos DESC LIMIT 10", nativeQuery = true)
    public List<Pelicula> findOrderVotos();

    @Query(value = "SELECT * FROM Pelicula p WHERE p.edad >= 18", nativeQuery = true)
    public List<Pelicula> findByEdad();
}
