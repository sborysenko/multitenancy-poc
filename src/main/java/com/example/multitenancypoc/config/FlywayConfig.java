package com.example.multitenancypoc.config;

import com.example.multitenancypoc.multitenancy.service.TenantService;
import org.flywaydb.core.Flyway;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class FlywayConfig {
    public static String HISTORY_TABLE = "schema_version";
    @Bean
    public Flyway flyway(DataSource dataSource) {
        Flyway flyway = Flyway.configure()
                .locations("migration/multitenancy")
                .dataSource(dataSource)
                .schemas(SchemaBasedMultiTenantConnectionProvider.DEFAULT_SCHEMA)
                .table(HISTORY_TABLE)
                .load();
        flyway.migrate();
        return flyway;
    }

    @Bean
    CommandLineRunner commandLineRunner(TenantService tenantService, DataSource dataSource) {
        return args -> {
            tenantService.getTenants().forEach(tenant -> {
                String schema = tenant.getSchemaName();
                Flyway flyway = Flyway.configure()
                        .locations("migration/main")
                        .dataSource(dataSource)
                        .schemas(schema)
                        .table(HISTORY_TABLE)
                        .load();
                flyway.migrate();

            });
        };
    }
}
