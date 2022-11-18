package com.example.softdownloaderapi.repository;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.ParentCategory;

@Repository
public class ParentCategoryRepository{
    @Autowired
    private SessionFactory sessionFactory;

    public List<ParentCategory> getAll(){
        Session session = sessionFactory.openSession();
        Query<ParentCategory> query = session.createNativeQuery("SELECT * FROM parentcategory", ParentCategory.class);
        List<ParentCategory> result = query.list();
        session.close();

        return result;
    }
}
