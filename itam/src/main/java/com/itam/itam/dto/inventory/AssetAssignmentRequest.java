package com.itam.itam.dto.inventory;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssetAssignmentRequest {
    @NotNull(message = "El ID del activo es obligatorio")
    private Long assetId;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long userId;

    private LocalDate assignedDate;
    private LocalDate returnedDate;
    private String notes;
}
