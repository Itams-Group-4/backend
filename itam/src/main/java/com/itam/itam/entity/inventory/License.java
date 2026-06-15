package com.itam.itam.entity.inventory;

import com.itam.itam.entity.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "license")
public class License {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "software_name", nullable = false)
    private String softwareName;

    @Column(name = "license_key")
    private String licenseKey;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'active'")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asset_id")
    private Asset asset;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "responsible_id")
    private User responsible;

    @PrePersist
    protected void onCreate() {
        if (this.status == null) {
            this.status = "active";
        }
    }
}
