package com.example.multitenancypoc.controller;

import com.example.multitenancypoc.multitenancy.model.Tenant;
import com.example.multitenancypoc.multitenancy.service.MultitenancyService;
import com.example.multitenancypoc.multitenancy.service.TenantService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
@Slf4j
public class TenantController {
    private TenantService tenantService;
    private MultitenancyService multitenancyService;

    @GetMapping("/all")
    public List<Tenant> getAllTenants() {

        return tenantService.getTenants();

    }

    @PostMapping("/add")
    public void addTenant(@RequestBody Tenant tenant) {
        log.debug("Adding new tenant. {}", tenant);

        tenantService.saveTenant(tenant);
        multitenancyService.initDatabase(tenant.getSchemaName());
    }
}
