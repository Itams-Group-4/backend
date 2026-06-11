package com.itam.itam.repository.inventory;

import com.itam.itam.entity.inventory.AssetAssignment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetAssignmentRepository {
    List<AssetAssignment> findByUserId(Long userId);

    List<AssetAssignment> findByAssetId(Long assetId);

    List<AssetAssignment> findByReturnedDateIsNull();
}