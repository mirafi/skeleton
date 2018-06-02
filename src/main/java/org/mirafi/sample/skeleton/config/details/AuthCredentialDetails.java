package org.mirafi.sample.skeleton.config.details;


import org.mirafi.sample.skeleton.constant.ACCESS_ACCOUNT_STATUS;
import org.mirafi.sample.skeleton.constant.security.SecurityRole;
import org.mirafi.sample.skeleton.entity.AccessRole;
import org.mirafi.sample.skeleton.entity.AuthCredential;
import org.mirafi.sample.skeleton.entity.PersonalInformation;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public class AuthCredentialDetails extends AuthCredential implements UserDetails{

    public AuthCredentialDetails(AuthCredential authCredential) {
        super();
        super.setId(authCredential.getId());
        super.setEmail(authCredential.getEmail());
        super.setPassword(authCredential.getPassword());
        super.setStatus(authCredential.getStatus());
        super.setCreatedAt(authCredential.getCreatedAt());

        super.setPersonalInformation(authCredential.getPersonalInformation());
        super.setAccessRole(authCredential.getAccessRole());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();

        Collection<AccessRole> accessRoles =  super.getAccessRole();
        System.out.println(accessRoles);
        if(accessRoles==null || accessRoles.size()==0)return authorities;

        for(AccessRole accessRole : accessRoles) {
            authorities.add(new SimpleGrantedAuthority(SecurityRole.prefix_ + accessRole.getAccessRole().name()));
        }
        return authorities;
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return super.getStatus().equals(ACCESS_ACCOUNT_STATUS.ACTIVE);
    }

    @Override
    public boolean isAccountNonLocked() {
        return super.getStatus().equals(ACCESS_ACCOUNT_STATUS.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return super.getStatus().equals(ACCESS_ACCOUNT_STATUS.ACTIVE);
    }

    @Override
    public boolean isEnabled() {
        return super.getStatus().equals(ACCESS_ACCOUNT_STATUS.ACTIVE);
    }

    @Override
    public String getName() {
        PersonalInformation personalInformation = super.getPersonalInformation();
        return (personalInformation!=null)?super.getPersonalInformation().getFullName():"";
    }
}
