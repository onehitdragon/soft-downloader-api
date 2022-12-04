package com.example.softdownloaderapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ReplyComment {
    @Column
    private int commentId;
    @Id
    @Column
    private int replyCommentId;

    public int getCommentId() {
        return commentId;
    }
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }
    public int getReplyCommentId() {
        return replyCommentId;
    }
    public void setReplyCommentId(int replyCommentId) {
        this.replyCommentId = replyCommentId;
    }
}
