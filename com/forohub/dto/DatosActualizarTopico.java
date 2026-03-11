package com.forohub.dto;

import com.forohub.model.StatusTopico;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        String curso,
        StatusTopico status
) {}