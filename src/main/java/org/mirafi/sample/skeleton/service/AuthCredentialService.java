package org.mirafi.sample.skeleton.service;


import org.mirafi.sample.skeleton.dao.AuthCredentialDao;
import org.mirafi.sample.skeleton.entity.AuthCredential;
import org.mirafi.sample.skeleton.exception.EntityNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class AuthCredentialService {
    private AuthCredentialDao authCredentialDao;

    @Autowired
    public void setAuthCredentialDao(AuthCredentialDao authCredentialDao) {
        this.authCredentialDao = authCredentialDao;
    }


    @Transactional
    public AuthCredential getAuthCredential(int id) throws EntityNotFound {
        AuthCredential authCredential =  this.getById(id);

        if(authCredential == null)throw new EntityNotFound("AuthCredential not found by id :"+id);
        return authCredential;
    }
    @Transactional
    public AuthCredential getById(int id){
        return this.authCredentialDao.getById(id);
    }
    @Transactional
    public AuthCredential getByEmail(String email){
        return this.authCredentialDao.getByEmail(email);
    }




}