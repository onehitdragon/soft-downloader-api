package com.example.softdownloaderapi.repository;



import org.hibernate.query.Query;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

    public boolean checkUsername(String username){
        Session session = sessionFactory.openSession();

        String queryStr = "SELECT * FROM users WHERE username = '"+ username +"'";
        Query<User> query = session.createNativeQuery(queryStr, User.class);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
        finally {
            session.close();
        }
    } 

    public void insertUser(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "INSERT INTO Users(username, password, fullname, createDate, roleId) VALUES ('"+ user.getUsername() +"', '"+ user.getPassword() +"', '"+ user.getFullName() +"', CURRENT_TIMESTAMP, 1)";
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
    }
}
