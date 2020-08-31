package com.joshua.cines.model.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "pelicula")
@Entity
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpelicula;
    private String titulo;
    private String descripcion;
    private Integer numvotos;
    private String trailer;
    private String imagen;
    private String genero;
    private Integer edad;
}
