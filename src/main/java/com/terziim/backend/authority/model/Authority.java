package com.terziim.backend.authority.model;

import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Authority extends BaseModel {

    //@Column(unique = true)
    private String authority;
    private boolean isActive;

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public String getAuthority() { return authority; }

    public void setAuthority(String authority) { this.authority = authority; }
}
