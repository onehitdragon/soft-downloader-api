package com.example.softdownloaderapi.repository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.Comment;

@Repository
public class CommentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getCommentsBySoft(int softwareId){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT * FROM comments WHERE softwareId = "+ softwareId +" AND comments.id NOT IN ( SELECT replycomment.replyCommentId FROM replycomment )";
        Query<Comment> query = session.createNativeQuery(queryStr, Comment.class);
        List<Comment> result = query.list();
        for (Comment cm : result) {
            queryStr = "SELECT * FROM replycomment JOIN comments ON replycomment.replyCommentId = comments.id WHERE replycomment.commentId = " + cm.getId();
            query = session.createNativeQuery(queryStr, Comment.class);
            cm.setReplyComment(query.list());
        }

        return result;
    }
}
