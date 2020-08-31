package com.joshua.cines.model.services;

import com.joshua.cines.model.domain.Usuario;
import com.joshua.cines.model.repositories.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IService<Usuario, Long>{
    @Autowired
    private IUsuarioRepository usuarioRepository;
    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public List<Usuario> findByColumn(String genero) {
        return null;
    }

    @Override
    public List<Usuario> findOrderVotos() {
        return null;
    }

    @Override
    public List<Usuario> findByEdad() {
        return null;
    }

    @Override
    public Usuario findById(Long aLong) {
        return usuarioRepository.findById(aLong).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long aLong) {
        usuarioRepository.deleteById(aLong);
    }
}
