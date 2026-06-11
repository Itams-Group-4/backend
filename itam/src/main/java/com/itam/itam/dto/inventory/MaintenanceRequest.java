package com.itam.itam.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MaintenanceRequest {
    @NotNull(message = "El ID del activo es requerido")
    private Long assetId;

    @NotNull(message = "La fecha programada es obligatoria")
    private LocalDate scheduledDate;

    @NotBlank(message = "El tipo de mantenimiento es obligatorio")
    private String type;

    private String status;
    private String description;
    private Long technicianId;
}