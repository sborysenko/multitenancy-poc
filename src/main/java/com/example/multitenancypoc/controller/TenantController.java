package com.example.multitenancypoc.controller;

import com.example.multitenancypoc.multitenancy.model.Tenant;
import com.example.multitenancypoc.multitenancy.service.TenantService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {
    private TenantService tenantService;

    @GetMapping("/all")
    public List<Tenant> getAllTenants() {

        return tenantService.getTenants();

    }
}
