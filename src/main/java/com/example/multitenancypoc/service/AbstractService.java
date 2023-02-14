package com.example.multitenancypoc.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional("tenantTransactionManager")
public class AbstractService {
    private SessionFactory sessionFactory;

    public AbstractService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
