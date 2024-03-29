package com.example.softdownloaderapi.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.softdownloaderapi.model.Comment;
import com.example.softdownloaderapi.model.ReplyComment;

@Repository
public class CommentRepository {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Comment> getCommentsBySoft(int softwareId){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT * FROM comments WHERE softwareId = " + softwareId;
        Query<Comment> query = session.createNativeQuery(queryStr, Comment.class);
        List<Comment> comments = query.list();

        queryStr = "SELECT replycomment.* FROM replycomment JOIN comments ON replycomment.replyCommentId = comments.id WHERE comments.softwareId = " + softwareId;
        Query<ReplyComment> query2 = session.createNativeQuery(queryStr, ReplyComment.class);
        List<ReplyComment> replyComments = query2.list();

        HashMap<Integer, Comment> hash = new HashMap<Integer, Comment>();
        for (Comment comment : comments) {
            hash.put(comment.getId(), comment);
        }

        List<Comment> result = new ArrayList<Comment>();
        for (Comment comment : comments) {
            boolean isCommentReply = false;
            for (ReplyComment replyComment : replyComments) {
                if(replyComment.getReplyCommentId() == comment.getId()){
                    Comment commentReceiveReply = hash.get(replyComment.getCommentId());
                    if(commentReceiveReply.getReplyComments() == null){
                        commentReceiveReply.setReplyComments(new ArrayList<Comment>());
                    }
                    commentReceiveReply.getReplyComments().add(hash.get(comment.getId()));
                    isCommentReply = true;
                }
            }
            if(!isCommentReply){
                result.add(comment);
            }
        }

        session.close();

        return result;
    }

    public Comment insert(int sortId, int commenterId, Comment comment){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "INSERT INTO Comments(content, commenterId, softwareId, createDate) VALUES ('"+ comment.getContent() +"', "+ commenterId +", "+ sortId +", CURRENT_TIMESTAMP)";
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        queryStr = "SELECT * FROM comments WHERE id = (SELECT MAX(id) FROM comments)";
        Comment insertedComment = session.createNativeQuery(queryStr, Comment.class).getSingleResult();
        session.close();

        return insertedComment;
    }

    public Comment insertReply(int sortId, int commenterId, Comment comment, int receivedCommentId){
        Comment result = insert(sortId, commenterId, comment);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String queryStr = "INSERT INTO ReplyComment(commentId, replyCommentId) VALUES ("+ receivedCommentId +", "+ result.getId() +")";
        session.createNativeQuery(queryStr).executeUpdate();
        transaction.commit();
        session.close();

        return result;
    }

    public long getTotalComment(){
        Session session = sessionFactory.openSession();
        String queryStr = "SELECT COUNT(id) FROM comments";
        long result = ((Number)session.createNativeQuery(queryStr).uniqueResult()).longValue();
        session.close();

        return result;
    }
}
