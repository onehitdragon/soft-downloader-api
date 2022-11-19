package com.example.softdownloaderapi.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ParentCategory {
    @Id
    private int id;
    @Column
    private String name;
    @OneToMany(mappedBy = "parentCategory")
    private Set<ChildCategory> childCategories;

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
    public Set<ChildCategory> getChildCategories() {
        return childCategories;
    }
    public void setChildCategories(Set<ChildCategory> childCategories) {
        this.childCategories = childCategories;
    }
    
}
