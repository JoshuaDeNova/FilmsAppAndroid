package com.joshua.cines.model.domain;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Table(name = "usuario")
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idusuario;
    @NonNull
    @Size(max = 50)
    private String nombre;
    @NonNull
    private String apellidos;
    @NonNull
    private String email;
    @NonNull
    private String password;
}
