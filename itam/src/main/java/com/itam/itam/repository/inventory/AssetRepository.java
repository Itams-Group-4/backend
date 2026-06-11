package com.itam.itam.repository.inventory;

import com.itam.itam.entity.inventory.Asset;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository {
    Optional<Asset> findBySerialNumber(String serialNumber);

    List<Asset> findByStatus(String status);

    List<Asset> findByLocation(String location);
}