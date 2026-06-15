package com.itam.itam.service;

import com.itam.itam.repository.inventory.AssetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final AssetRepository assetRepository;

    public CategoryService(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<String> findAll() {
        return assetRepository.findDistinctAssetTypes();
    }
}