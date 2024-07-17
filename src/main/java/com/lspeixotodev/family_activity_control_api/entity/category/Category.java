package com.lspeixotodev.family_activity_control_api.entity.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lspeixotodev.family_activity_control_api.entity.bill.Bill;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.*;

@Entity
@Table(name = "category", uniqueConstraints = {@UniqueConstraint(columnNames = "title")})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String title;

    @Column(nullable = false, length = 100)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Bill> bills;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public Category() {
    }

    public Category(UUID id, String title, String description, List<Bill> bills, Date createdAt, Date updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.bills = bills;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Category(String title, String description, List<Bill> bills, Date createdAt, Date updatedAt) {
        this.title = title;
        this.description = description;
        this.bills = bills;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }


    @Override
    public String toString() {
        return "Category{" +
                "id: " + id +
                ", title: '" + title + '\'' +
                ", description: '" + description + '\'' +
                ", createdAt: " + createdAt +
                ", updatedAt: " + updatedAt +
                '}';
    }
}
