package com.itam.itam.service;

import com.itam.itam.dto.inventory.AssetRequest;
import com.itam.itam.dto.inventory.AssetResponse;
import com.itam.itam.dto.user.UserResponse;
import com.itam.itam.entity.inventory.Asset;
import com.itam.itam.entity.users.User;
import com.itam.itam.repository.inventory.AssetRepository;
import com.itam.itam.repository.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    public AssetService(AssetRepository assetRepository, UserRepository userRepository) {
        this.assetRepository = assetRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<AssetResponse> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public AssetResponse findById(Long id) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo no encontrado"));

        return toResponse(asset);
    }

    @Transactional
    public AssetResponse create(AssetRequest request) {
        if (assetRepository.findBySerialNumber(request.getSerialNumber()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe un activo con ese número de serie");
        }

        User responsible = null;

        if (request.getResponsibleId() != null) {
            responsible = userRepository.findById(request.getResponsibleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado"));
        }

        Asset asset = Asset.builder()
                .assetName(request.getAssetName())
                .assetType(request.getAssetType())
                .model(request.getModel())
                .serialNumber(request.getSerialNumber())
                .location(request.getLocation())
                .status(request.getStatus())
                .acquisitionDate(request.getAcquisitionDate())
                .acquisitionValue(request.getAcquisitionValue())
                .observations(request.getObservations())
                .responsible(responsible)
                .build();

        return toResponse(assetRepository.save(asset));
    }

    @Transactional
    public AssetResponse update(Long id, AssetRequest request) {
        Asset asset = assetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo no encontrado"));

        assetRepository.findBySerialNumber(request.getSerialNumber())
                .ifPresent(existingAsset -> {
                    if (!existingAsset.getId().equals(id)) {
                        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ya existe otro activo con ese número de serie");
                    }
                });

        User responsible = null;

        if (request.getResponsibleId() != null) {
            responsible = userRepository.findById(request.getResponsibleId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Responsable no encontrado"));
        }

        asset.setAssetName(request.getAssetName());
        asset.setAssetType(request.getAssetType());
        asset.setModel(request.getModel());
        asset.setSerialNumber(request.getSerialNumber());
        asset.setLocation(request.getLocation());
        asset.setStatus(request.getStatus());
        asset.setAcquisitionDate(request.getAcquisitionDate());
        asset.setAcquisitionValue(request.getAcquisitionValue());
        asset.setObservations(request.getObservations());
        asset.setResponsible(responsible);

        return toResponse(assetRepository.save(asset));
    }

    @Transactional
    public void delete(Long id) {
        if (!assetRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Activo no encontrado");
        }

        assetRepository.deleteById(id);
    }

    private AssetResponse toResponse(Asset asset) {
        return AssetResponse.builder()
                .id(asset.getId())
                .assetName(asset.getAssetName())
                .assetType(asset.getAssetType())
                .model(asset.getModel())
                .serialNumber(asset.getSerialNumber())
                .location(asset.getLocation())
                .status(asset.getStatus())
                .acquisitionDate(asset.getAcquisitionDate())
                .acquisitionValue(asset.getAcquisitionValue())
                .observations(asset.getObservations())
                .responsible(toUserResponse(asset.getResponsible()))
                .build();
    }

    private UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .build();
    }
}