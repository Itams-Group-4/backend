package com.itam.itam.service;

import com.itam.itam.dto.inventory.LicenseRequest;
import com.itam.itam.dto.inventory.LicenseResponse;
import com.itam.itam.dto.user.UserResponse;
import com.itam.itam.entity.inventory.Asset;
import com.itam.itam.entity.inventory.License;
import com.itam.itam.entity.users.User;
import com.itam.itam.repository.inventory.AssetRepository;
import com.itam.itam.repository.inventory.LicenseRepository;
import com.itam.itam.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
public class LicenseService {

    private final LicenseRepository licenseRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public LicenseService(
            LicenseRepository licenseRepository,
            AssetRepository assetRepository,
            UserRepository userRepository
    ) {
        this.licenseRepository = licenseRepository;
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<LicenseResponse> findAll(Integer porVencer) {
        List<License> licenses;

        if (porVencer != null) {
            LocalDate today = LocalDate.now();
            LocalDate limitDate = today.plusDays(porVencer);
            licenses = licenseRepository.findByExpirationDateBetween(today, limitDate);
        } else {
            licenses = licenseRepository.findAll();
        }

        return licenses.stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public LicenseResponse findById(Long id) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Licencia no encontrada"
                ));

        return toResponse(license);
    }

    @Transactional
    public LicenseResponse create(LicenseRequest request) {
        Asset asset = null;
        User responsible = null;

        if (request.getAssetId() != null) {
            asset = assetRepository.findById(request.getAssetId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Activo no encontrado"
                    ));
        }

        if (request.getResponsibleId() != null) {
            responsible = userRepository.findById(request.getResponsibleId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Usuario responsable no encontrado"
                    ));
        }

        License license = License.builder()
                .softwareName(request.getSoftwareName())
                .licenseKey(request.getLicenseKey())
                .expirationDate(request.getExpirationDate())
                .status(request.getStatus())
                .asset(asset)
                .responsible(responsible)
                .build();

        return toResponse(licenseRepository.save(license));
    }

    @Transactional
    public LicenseResponse update(Long id, LicenseRequest request) {
        License license = licenseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Licencia no encontrada"
                ));

        Asset asset = null;
        User responsible = null;

        if (request.getAssetId() != null) {
            asset = assetRepository.findById(request.getAssetId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Activo no encontrado"
                    ));
        }

        if (request.getResponsibleId() != null) {
            responsible = userRepository.findById(request.getResponsibleId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Usuario responsable no encontrado"
                    ));
        }

        license.setSoftwareName(request.getSoftwareName());
        license.setLicenseKey(request.getLicenseKey());
        license.setExpirationDate(request.getExpirationDate());
        license.setStatus(request.getStatus());
        license.setAsset(asset);
        license.setResponsible(responsible);

        return toResponse(licenseRepository.save(license));
    }

    @Transactional
    public void delete(Long id) {
        if (!licenseRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Licencia no encontrada"
            );
        }

        licenseRepository.deleteById(id);
    }

    private LicenseResponse toResponse(License license) {
        return LicenseResponse.builder()
                .id(license.getId())
                .softwareName(license.getSoftwareName())
                .licenseKey(license.getLicenseKey())
                .expirationDate(license.getExpirationDate())
                .status(license.getStatus())
                .assetId(license.getAsset() != null ? license.getAsset().getId() : null)
                .responsible(license.getResponsible() != null ? toUserResponse(license.getResponsible()) : null)
                .build();
    }

    private UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}