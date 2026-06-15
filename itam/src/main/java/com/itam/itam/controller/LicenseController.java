package com.itam.itam.controller;

import com.itam.itam.dto.inventory.LicenseRequest;
import com.itam.itam.dto.inventory.LicenseResponse;
import com.itam.itam.service.LicenseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/licencias")
public class LicenseController {

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    public List<LicenseResponse> findAll(
            @RequestParam(value = "porVencer", required = false) Integer porVencer
    ) {
        return licenseService.findAll(porVencer);
    }

    @GetMapping("/{id}")
    public LicenseResponse findById(@PathVariable Long id) {
        return licenseService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LicenseResponse create(@Valid @RequestBody LicenseRequest request) {
        return licenseService.create(request);
    }

    @PutMapping("/{id}")
    public LicenseResponse update(@PathVariable Long id, @Valid @RequestBody LicenseRequest request) {
        return licenseService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        licenseService.delete(id);
    }
}