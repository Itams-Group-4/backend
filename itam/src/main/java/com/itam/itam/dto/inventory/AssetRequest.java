package com.itam.itam.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssetRequest {
    @NotBlank(message = "El nombre del activo es obligatorio")
    private String assetName;

    @NotBlank(message = "El tipo de activo es obligatorio")
    private String assetType;

    @NotBlank(message = "El modelo es obligatorio")
    private String model;

    @NotBlank(message = "El número de serie es obligatorio")
    private String serialNumber;

    @NotBlank(message = "La ubicación es obligatoria")
    private String location;

    @NotBlank(message = "El estado del activo es obligatorio")
    private String status;

    private LocalDate acquisitionDate;
    private BigDecimal acquisitionValue;
    private String observations;
    private Long responsibleId;
}