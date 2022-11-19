package com.example.softdownloaderapi.repository;



import org.hibernate.query.Query;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.CheckingUserResult;
import com.example.softdownloaderapi.model.User;

@Repository
public class UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public CheckingUserResult checkUser(User user){
        Session session = sessionFactory.openSession();

        String queryStr = "SELECT * FROM users WHERE username = '"
         + user.getUsername() + "' AND password = '"+ user.getPassword() +"'";
        Query<User> query = session.createNativeQuery(queryStr, User.class);
        try {
            User result = query.getSingleResult();
            
            return new CheckingUserResult(result, true);
        } catch (NoResultException e) {
            return new CheckingUserResult(false);
        }
        finally {
            session.close();
        }
    }
}
