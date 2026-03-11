package com.forohub.repository;

import com.forohub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    List<Topico> findByCurso(String curso);
    List<Topico> findByAutorId(Long autorId);
    boolean existsByTituloAndMensaje(String titulo, String mensaje);
}