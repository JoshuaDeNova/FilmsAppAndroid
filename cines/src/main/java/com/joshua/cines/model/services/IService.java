package com.joshua.cines.model.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IService<Entity, Id> {
    List<Entity> findAll();
    List<Entity> findByColumn(String genero);
    List<Entity> findOrderVotos();
    List<Entity> findByEdad();
    Entity findById(Id id);
    Entity save(Entity entity);
    void delete(Id id);
}