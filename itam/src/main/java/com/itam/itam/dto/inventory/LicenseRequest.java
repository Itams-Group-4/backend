package com.itam.itam.dto.inventory;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LicenseRequest {
    @NotBlank(message = "El nombre del software es obligatorio")
    private String softwareName;

    private String licenseKey;
    private LocalDate expirationDate;
    private String status;
    private Long assetId;
    private Long responsibleId;
}