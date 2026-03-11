package com.forohub.dto;

import jakarta.validation.constraints.NotBlank;

public record DatosTopico(
        @NotBlank String titulo,
        @NotBlank String mensaje,
        @NotBlank String curso
) {}