package com.example.softdownloaderapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ChildCategory {
    @Id
    private int id;
    @Column
    private String name;
    @ManyToOne
    @JoinColumn(name = "parentcategoryid")
    private ParentCategory parentCategory;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
