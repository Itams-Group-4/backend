package com.itam.itam.dto.inventory;

import com.itam.itam.dto.user.UserResponse;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class AssetAssignmentResponse {
    private Long id;
    private Long assetId;
    private String assetName;
    private UserResponse user;
    private LocalDate assignedDate;
    private LocalDate returnedDate;
    private String notes;
}