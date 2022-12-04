package com.example.softdownloaderapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity(name = "comments")
public class Comment {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "commenterId")
    private User commenter;
    @ManyToOne
    @JoinColumn(name = "softwareId")
    private Soft soft;
    @Column
    private Date createDate;
    @Transient
    private List<Comment> replyComments;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getCommenter() {
        return commenter;
    }
    public void setCommenter(User commenter) {
        this.commenter = commenter;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Soft getSoft() {
        return soft;
    }
    public void setSoft(Soft soft) {
        this.soft = soft;
    }
    public List<Comment> getReplyComments() {
        return replyComments;
    }
    public void setReplyComments(List<Comment> replyComments) {
        this.replyComments = replyComments;
    }
}
