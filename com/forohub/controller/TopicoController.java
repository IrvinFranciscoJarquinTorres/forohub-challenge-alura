package com.forohub.controller;

import com.forohub.dto.DatosActualizarTopico;
import com.forohub.dto.DatosRespuestaTopico;
import com.forohub.dto.DatosTopico;
import com.forohub.model.Usuario;
import com.forohub.service.TopicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crear(
            @RequestBody @Valid DatosTopico datos,
            @AuthenticationPrincipal Usuario usuario) {
        var topico = topicoService.crear(datos, usuario);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<List<DatosRespuestaTopico>> listar() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos) {
        return ResponseEntity.ok(topicoService.actualizar(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminar(@PathVariable Long id) {
        topicoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}