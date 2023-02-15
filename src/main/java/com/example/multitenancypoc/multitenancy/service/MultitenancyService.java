package com.example.multitenancypoc.multitenancy.service;

import com.example.multitenancypoc.config.FlywayConfig;
import org.flywaydb.core.Flyway;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class MultitenancyService  {
    private DataSource dataSource;

    @Autowired
    public MultitenancyService(DataSource dataSource) {
            this.dataSource = dataSource;
    }

    public void initDatabase(String schema) {
        Flyway flyway = Flyway.configure()
                .locations("migration/main")
                .dataSource(dataSource)
                .schemas(schema)
                .table(FlywayConfig.HISTORY_TABLE)
                .load();
        flyway.migrate();
    }
}
