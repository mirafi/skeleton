package org.mirafi.sample.skeleton.dao;

import org.hibernate.Session;
import org.mirafi.sample.skeleton.entity.AuthCredential;
import org.springframework.stereotype.Repository;

@Repository
public class AuthCredentialDao extends BaseDao {
    public AuthCredential getById(int id){
        Session session = this.getCurrentSession();
        return (AuthCredential)session.createQuery("FROM AuthCredential  WHERE id=:id")
                .setParameter("id",id)
                .setMaxResults(1)
                .uniqueResult();
    }
    public AuthCredential getByEmail(String email){
        Session session = this.getCurrentSession();
        return (AuthCredential)session.createQuery("FROM AuthCredential  WHERE email=:email")
                .setParameter("email",email)
                .setMaxResults(1)
                .uniqueResult();
    }

}