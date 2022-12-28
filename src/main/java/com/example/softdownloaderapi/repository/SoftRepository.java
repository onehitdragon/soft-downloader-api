package com.example.softdownloaderapi.repository;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.Soft;

@Repository
public class SoftRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Soft> getHighestViewingSoft(int amount){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM software ORDER BY amountView DESC LIMIT " + amount;
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }

    public List<Soft> getNewestViewingSoft(int amount){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM software ORDER BY createDate DESC LIMIT " + amount;
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }

    public List<Soft> getByParentCategorySoft(int parentCategoryId){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM softwarecategory JOIN childcategory ON softwarecategory.childCategoryId = childcategory.id JOIN software ON software.id = softwarecategory.softwareId WHERE childcategory.parentCategoryId = " + parentCategoryId;
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }

    public List<Soft> getByChildCategorySoft(int childCategoryId){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM softwarecategory JOIN software ON softwarecategory.softwareId = software.id WHERE softwarecategory.childCategoryId = " + childCategoryId;
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }

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

    public Soft insert(Soft soft){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        int id = (int) session.save("software", soft);
        transaction.commit();
        session.close();

        return getSoft(id);
    }

    public void delete(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "DELETE FROM softwarecategory WHERE softwareId = " + id;
        session.createNativeQuery(queryStr).executeUpdate();
        queryStr = "DELETE FROM software WHERE id = " + id;
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
    }

    public List<Soft> search(String keyword){
        Session session = sessionFactory.openSession();

        String queryStr = "SELECT * FROM software WHERE title LIKE '%"+ keyword +"%'";
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }

    public void addViewing(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "UPDATE software SET amountView = amountView + 1 WHERE id = " + id;
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();
    }

    public long getTotalView(){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT SUM(amountView) FROM software";
        long result = ((Number)session.createNativeQuery(queryStr).uniqueResult()).longValue();
        session.close();

        return result;
    }

    public long getTotalSoft(){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT COUNT(id) FROM software";
        long result = ((Number)session.createNativeQuery(queryStr).uniqueResult()).longValue();
        session.close();

        return result;
    }

    public List<Soft> getAll(){
        Session session = sessionFactory.openSession();
        
        String queryStr = "SELECT * FROM software";
        Query<Soft> query = session.createNativeQuery(queryStr, Soft.class);
        List<Soft> result = query.list();
        session.close();

        return result;
    }
}
