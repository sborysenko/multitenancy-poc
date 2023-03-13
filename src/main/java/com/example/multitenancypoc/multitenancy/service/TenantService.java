package com.example.multitenancypoc.multitenancy.service;

import com.example.multitenancypoc.multitenancy.model.UserTenant;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantService extends AbstractTenantService {

    public TenantService(@Autowired @Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<UserTenant> getTenants() {
        return getSession().createQuery("from UserTenant").getResultList();
    }

    public void saveTenant(UserTenant userTenant) {
        getSession().persist(userTenant);
    }

}
