package com.terziim.backend.user.model;

import com.terziim.backend.authority.model.Authority;
import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.*;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;



@Entity
@ToString
public class AppUser extends BaseModel implements Serializable {


    @Column(nullable = false, updatable = false)
    private String userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String password;

    private String verificationCode;

    private String userType;

    private String bio;
    private String profilePictureUrl;
    private String privacy;
    private String email;
    private String address;
    private String gender;

    private Float star;
    private Integer howManyUserStared;

    private Date lastLoginDate;
    private boolean isActive;
    private boolean isNotLocked;
    private boolean banned;

    private String role;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            joinColumns = @JoinColumn(name = "appuser_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Collection<Authority> authorities = new ArrayList<>();

    // Constructors
    public AppUser(){}

    public AppUser(Long id, Date createdAt, Date updatedAt, String userId, String username, String phone,
                   String password, String verificationCode, String userType, String bio, String profilePictureUrl,
                   String privacy, String email, String address, String gender, Float star, Integer howManyUserStared,
                   Date lastLoginDate, boolean isActive, boolean isNotLocked, String role,
                   Collection<Authority> authorities) {
        super(id, createdAt, updatedAt);
        this.userId = userId;
        this.username = username;
        this.phone = phone;
        this.password = password;
        this.verificationCode = verificationCode;
        this.userType = userType;
        this.bio = bio;
        this.profilePictureUrl = profilePictureUrl;
        this.privacy = privacy;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.star = star;
        this.howManyUserStared = howManyUserStared;
        this.lastLoginDate = lastLoginDate;
        this.isActive = isActive;
        this.isNotLocked = isNotLocked;
        this.role = role;
        this.authorities = authorities;
    }

    // Getters and Setterss
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return phone;
    }

    public void setEmail(String email) {
        this.phone = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserBio() {
        return bio;
    }

    public void setUserBio(String bio) { this.bio = bio; }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(boolean notLocked) {
        isNotLocked = notLocked;
    }

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getPrivacy() { return privacy; }

    public void setPrivacy(String privacy) { this.privacy = privacy; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Float getStar() {
        return star;
    }

    public void setStar(Float star) {
        this.star = star;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getHowManyUserStared() {
        return howManyUserStared;
    }

    public void setHowManyUserStared(Integer howManyUserStared) {
        this.howManyUserStared = howManyUserStared;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    //toString
    @Override
    public String toString() {
        return "AppUser{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", email='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", userType='" + userType + '\'' +
                ", bio='" + bio + '\'' +
                ", profilePictureUrl='" + profilePictureUrl + '\'' +
                ", privacy='" + privacy + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", star=" + star +
                ", howManyUserStared=" + howManyUserStared +
                ", lastLoginDate=" + lastLoginDate +
                ", isActive=" + isActive +
                ", isNotLocked=" + isNotLocked +
                ", role='" + role + '\'' +
                ", authorities=" + authorities +
                ", gender=" + gender +
                '}';
    }
}