package com.example.multitenancypoc.multitenancy.service;

import com.example.multitenancypoc.multitenancy.model.Tenant;
import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TenantService extends AbstractTenantService {

    public TenantService(@Autowired @Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<Tenant> getTenants() {
        return getSession().createQuery("from Tenant").getResultList();
    }

}
