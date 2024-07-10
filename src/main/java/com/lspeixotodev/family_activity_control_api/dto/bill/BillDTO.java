package com.lspeixotodev.family_activity_control_api.dto.bill;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lspeixotodev.family_activity_control_api.entity.bill.BillType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class BillDTO {

    private String id;

    private String title;

    private String owner;

    private BigDecimal amount = new BigDecimal(0);

    private String category;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    private Date finishAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    @JsonIgnore
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "America/Sao_Paulo")
    @JsonIgnore
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    private BillType type = BillType.VARIABLE;

    public BillDTO() {
    }

    public BillDTO(String id, String title, String owner, BigDecimal amount, String category, String description, Date finishAt, Date createdAt, Date updatedAt, BillType type) {
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

    public BillDTO(String title, String owner, BigDecimal amount, String category, String description, Date finishAt, Date createdAt, Date updatedAt, BillType type) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
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
        BillDTO CreateBillDTO = (BillDTO) o;
        return Objects.equals(id, CreateBillDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
