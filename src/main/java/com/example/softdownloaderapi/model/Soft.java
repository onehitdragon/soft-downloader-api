package com.example.softdownloaderapi.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "software")
public class Soft {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String title;
    @Column
    private String content;
    @ManyToOne
    @JoinColumn(name = "authorId")
    private User author;
    @Column
    private int amountView;
    @Column
    private Date createDate;
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "softwarecategory", 
        joinColumns = { @JoinColumn(name = "softwareId") }, 
        inverseJoinColumns = {@JoinColumn(name = "childCategoryId") })
    private List<ChildCategory> childCategories;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public User getAuthor() {
        return author;
    }
    public void setAuthor(User author) {
        this.author = author;
    }
    public int getAmountView() {
        return amountView;
    }
    public void setAmountView(int amountView) {
        this.amountView = amountView;
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public List<ChildCategory> getChildCategories() {
        return childCategories;
    }
    public void setChildCategories(List<ChildCategory> childCategories) {
        this.childCategories = childCategories;
    }
    
}
