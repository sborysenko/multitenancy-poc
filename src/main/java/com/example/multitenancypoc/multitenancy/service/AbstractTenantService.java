package com.example.multitenancypoc.multitenancy.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional("tenantTransactionManager")
public abstract class AbstractTenantService {
    private SessionFactory sessionFactory;

    public AbstractTenantService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
