package com.terziim.backend.icpmodel;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseModel {


    //    @SequenceGenerator(name = "base_seq", sequenceName = "base_seq")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "base_seq")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;



    @PrePersist
    public void setCreationDate() {
        this.createdAt = new Date();
    }


    @PreUpdate
    public void setChangeDate() {
        this.updatedAt = new Date();
    }


    // Setter and Getters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


}
