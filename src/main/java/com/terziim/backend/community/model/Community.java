package com.terziim.backend.community.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Builder;

import java.util.Date;


@Entity
@Builder
public class Community extends BaseModel {


    private String authorId;

    @Column(length = 400)
    private String community;

    private Boolean isActive;


    //Constractors
    public Community(Long id, Date createdAt, Date updatedAt, String authorId, String community, Boolean isActive) {
        super(id, createdAt, updatedAt);
        this.authorId = authorId;
        this.community = community;
        this.isActive = isActive;
    }

    public Community(String authorId, String community, Boolean isActive) {
        this.authorId = authorId;
        this.community = community;
        this.isActive = isActive;
    }

    public Community() {}



    //Getters and Setters
    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
