package com.itam.itam.repository.inventory;

import com.itam.itam.entity.inventory.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {
    Optional<Asset> findBySerialNumber(String serialNumber);

    List<Asset> findByStatus(String status);

    List<Asset> findByLocation(String location);

    @Query("SELECT DISTINCT a.assetType FROM Asset a WHERE a.assetType IS NOT NULL ORDER BY a.assetType")
    List<String> findDistinctAssetTypes();

    long countByStatus(String status);
}