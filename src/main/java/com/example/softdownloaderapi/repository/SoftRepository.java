package com.example.softdownloaderapi.repository;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.Soft;

@Repository
public class SoftRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public Soft getSoft(int id){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM software WHERE id = " + id;
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        try {
            Soft result = query.getSingleResult();

            return result;
        } catch (NoResultException e) {
            return null;
        }
        finally{
            session.close();
        }
    }
}
