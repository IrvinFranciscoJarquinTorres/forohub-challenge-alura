package com.forohub.controller;

import com.forohub.dto.DatosRegistroUsuario;
import com.forohub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistroUsuario datos) {
        var usuario = usuarioService.registrar(datos);
        return ResponseEntity.ok("Usuario registrado exitosamente: " + usuario.getNombre());
    }
}