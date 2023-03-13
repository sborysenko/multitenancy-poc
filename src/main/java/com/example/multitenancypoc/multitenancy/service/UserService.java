package com.example.multitenancypoc.multitenancy.service;

import com.example.multitenancypoc.model.User;
import com.example.multitenancypoc.multitenancy.model.AuthUser;
import com.example.multitenancypoc.multitenancy.model.UserTenant;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
@Transactional("tenantTransactionManager")
public class UserService extends AbstractTenantService implements UserDetailsService {
    public UserService(@Autowired @Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public AuthUser getUserByLogin(String login) throws UsernameNotFoundException {
        List<AuthUser> authUsers = getSession()
                .createQuery("from AuthUser u where u.login = :login")
                .setParameter("login", login)
                .getResultList();

        if (authUsers.isEmpty()) {
            throw new UsernameNotFoundException("User " + login + " not found");
        }

        return authUsers.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user details for user {}", username);
        return new UserDetailsImpl(getUserByLogin(username));
    }

    public List<User> getUserAccounts() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetailsImpl) {
            AuthUser user = ((UserDetailsImpl) auth.getPrincipal()).getAuthUser();

            List<User> users = new ArrayList<>();
            user.getTenants().forEach(tenant -> {
                String sql = "select * from " + tenant.getSchemaName() + ".user";
//                getSession().

            });
            return users;
        }
        return null;
    }


    private class UserDetailsImpl implements UserDetails {
        AuthUser authUser;

        public UserDetailsImpl(AuthUser authUser) {
            this.authUser = authUser;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return authUser.getPassword();
        }

        @Override
        public String getUsername() {
            return authUser.getLogin();
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }

        public AuthUser getAuthUser() {
            return authUser;
        }
    }
}
