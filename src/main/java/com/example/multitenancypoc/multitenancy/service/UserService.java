package com.example.multitenancypoc.multitenancy.service;

import com.example.multitenancypoc.multitenancy.model.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class UserService extends AbstractTenantService implements UserDetailsService {
    public UserService(@Autowired @Qualifier("tenantSessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User getUserByLogin(String login) throws UsernameNotFoundException {
        List<User> users = getSession()
                .createQuery("from User u where u.login = :login")
                .setParameter("login", login)
                .getResultList();

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User " + login + " not found");
        }

        return users.get(0);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.debug("Load user details for user {}", username);
        return new UserDetailsImpl(getUserByLogin(username));
    }

    private class UserDetailsImpl implements UserDetails {
        User user;

        public UserDetailsImpl(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return null;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getLogin();
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
    }
}
