package com.itam.itam.dto.inventory;

import com.itam.itam.dto.user.UserResponse;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssetResponse {
    private Long id;
    private String assetName;
    private String assetType;
    private String model;
    private String serialNumber;
    private String location;
    private String status;
    private LocalDate acquisitionDate;
    private BigDecimal acquisitionValue;
    private String observations;
    private UserResponse responsible;
}