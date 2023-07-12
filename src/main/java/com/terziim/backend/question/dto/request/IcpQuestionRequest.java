package com.terziim.backend.question.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpQuestionRequest {

    @NonNull
    private Long productId;
    private String sellerId;


    @NonNull
    private String jwt;
    private String question;



    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
