package org.mirafi.sample.skeleton.config.service;


import org.mirafi.sample.skeleton.config.details.AuthCredentialDetails;
import org.mirafi.sample.skeleton.entity.AuthCredential;
import org.mirafi.sample.skeleton.service.AuthCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("authCredentialDetailsService")
public class AuthCredentialDetailsService implements UserDetailsService {

    private AuthCredentialService authCredentialService;

    @Autowired
    public void setAuthCredentialService(AuthCredentialService authCredentialService) {
        this.authCredentialService = authCredentialService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        AuthCredential authCredential = this.authCredentialService.getByEmail(s);
        if(authCredential==null){
            throw new UsernameNotFoundException("Email or password is wrong");
        }

        return new AuthCredentialDetails(authCredential);
    }
}