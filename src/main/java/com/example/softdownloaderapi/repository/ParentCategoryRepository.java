package com.example.softdownloaderapi.repository;


import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.ParentCategory;
import com.example.softdownloaderapi.model.ChildCategory;

@Repository
public class ParentCategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<ParentCategory> getAll(){
        Session session = sessionFactory.openSession();

        String queryStr = "SELECT * FROM parentcategory";
        Query<ParentCategory> query = session.createNativeQuery(queryStr, ParentCategory.class);
        List<ParentCategory> result = query.getResultList();

        return result;
    }
}
