package com.itam.itam.dto.inventory;

import com.itam.itam.dto.user.UserResponse;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MaintenanceResponse {
    private Long id;
    private Long assetId;
    private String assetName;
    private LocalDate scheduledDate;
    private String type;
    private String status;
    private String description;
    private UserResponse technician;
}
