package com.forohub.service;

import com.forohub.dto.DatosActualizarTopico;
import com.forohub.dto.DatosRespuestaTopico;
import com.forohub.dto.DatosTopico;
import com.forohub.model.Topico;
import com.forohub.model.Usuario;
import com.forohub.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;

    public DatosRespuestaTopico crear(DatosTopico datos, Usuario autor) {
        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new RuntimeException("Ya existe un tópico con el mismo título y mensaje");
        }
        var topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setCurso(datos.curso());
        topico.setAutor(autor);
        topicoRepository.save(topico);
        return mapearRespuesta(topico);
    }

    public List<DatosRespuestaTopico> listarTodos() {
        return topicoRepository.findAll()
                .stream()
                .map(this::mapearRespuesta)
                .toList();
    }

    public DatosRespuestaTopico buscarPorId(Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        return mapearRespuesta(topico);
    }

    public DatosRespuestaTopico actualizar(Long id, DatosActualizarTopico datos) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));
        if (datos.titulo() != null) topico.setTitulo(datos.titulo());
        if (datos.mensaje() != null) topico.setMensaje(datos.mensaje());
        if (datos.curso() != null) topico.setCurso(datos.curso());
        if (datos.status() != null) topico.setStatus(datos.status());
        topicoRepository.save(topico);
        return mapearRespuesta(topico);
    }

    public void eliminar(Long id) {
        if (!topicoRepository.existsById(id)) {
            throw new RuntimeException("Tópico no encontrado");
        }
        topicoRepository.deleteById(id);
    }

    private DatosRespuestaTopico mapearRespuesta(Topico topico) {
        return new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                topico.getCurso()
        );
    }
}