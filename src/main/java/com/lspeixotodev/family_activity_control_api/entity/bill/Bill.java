package com.lspeixotodev.family_activity_control_api.entity.bill;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "bill")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, columnDefinition = "TEXT", length = 50)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT", length = 50)
    private String owner;

    private BigDecimal amount;

    @Column(nullable = false, columnDefinition = "TEXT", length = 50)
    private String category;

    @Column(nullable = false, columnDefinition = "TEXT", length = 100)
    private String description;

    @Column(name = "finish_at", nullable = false, updatable = false)
    private Date finishAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "TEXT", length = 50)
    private BillType type;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Date updatedAt;

    public Bill() {
    }

    public Bill(UUID id, String title, String owner, BigDecimal amount, String category, String description, Date finishAt, Date createdAt, Date updatedAt, BillType type) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.finishAt = finishAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
    }

    public Bill(String title, String owner, BigDecimal amount, String category, String description, Date finishAt, Date createdAt, Date updatedAt, BillType type) {
        this.title = title;
        this.owner = owner;
        this.amount = amount;
        this.category = category;
        this.description = description;
        this.finishAt = finishAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getValue() {
        return amount;
    }

    public void setValue(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public BillType getType() {
        return type;
    }

    public void setType(BillType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return Objects.equals(id, bill.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
