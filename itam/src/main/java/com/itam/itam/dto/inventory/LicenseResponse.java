package com.itam.itam.dto.inventory;

import com.itam.itam.dto.user.UserResponse;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LicenseResponse {
    private Long id;
    private String softwareName;
    private String licenseKey;
    private LocalDate expirationDate;
    private String status;
    private Long assetId;
    private UserResponse responsible;
}