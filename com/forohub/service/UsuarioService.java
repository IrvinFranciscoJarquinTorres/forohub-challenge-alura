package com.forohub.service;

import com.forohub.dto.DatosRegistroUsuario;
import com.forohub.model.Usuario;
import com.forohub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario registrar(DatosRegistroUsuario datos) {
        if (usuarioRepository.findByEmail(datos.email()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con ese email");
        }
        var usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setContrasena(passwordEncoder.encode(datos.contrasena()));
        return usuarioRepository.save(usuario);
    }
}