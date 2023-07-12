package com.terziim.backend.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;




/**
 * @Author: Indice Incorporation.
 * @Target: Bu sinif istifadecinin sifresini deyismek istediyi zaman atacagi istekdir. CurrentPassword yoxlanilir, dogurlandigi teqdirde yeni 'newPassword' ile evez olunur.
 * @Comment: Hec bir serh yoxdur.
 * */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpEditUserPassword {

    private String jwt;

    private String currentPassword;

    private String newPassword;



    //Getters and Setters
    public String getJwt() { return jwt; }

    public void setJwt(String jwt) { this.jwt = jwt; }

    public String getCurrentPassword() { return currentPassword; }

    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public String getNewPassword() { return newPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }

}
