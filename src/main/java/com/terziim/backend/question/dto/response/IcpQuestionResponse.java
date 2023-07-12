package com.terziim.backend.question.dto.response;


import com.terziim.backend.question.dto.request.IcpAnswerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpQuestionResponse {

    private Long id;
    private LocalDate createdAt;

    private Long productId;

    private String buyerId;
    private String buyerName;
    private String question;

    private IcpAnswerResponse answer;


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public IcpAnswerResponse getAnswer() {
        return answer;
    }

    public void setAnswer(IcpAnswerResponse answer) {
        this.answer = answer;
    }
}
