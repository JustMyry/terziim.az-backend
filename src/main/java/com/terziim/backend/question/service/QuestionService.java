package com.terziim.backend.question.service;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.question.dto.request.IcpAnswerRequest;
import com.terziim.backend.question.dto.request.IcpQuestionRequest;
import com.terziim.backend.question.dto.response.IcpQuestionResponse;

import java.util.List;

public interface QuestionService {
    
    IcpResponseModel<String> askQuestion(IcpQuestionRequest payload);

    IcpResponseModel<String> deleteQuestion(IcpJustJwt payload, Long id);

    IcpResponseModel<String> answerQuestion(IcpAnswerRequest payload, Long id);

    IcpResponseModel<String> deleteAnswer(IcpJustJwt payload, Long id);

    IcpResponseModel<List<IcpQuestionResponse>> showQuestionsToSeller(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpQuestionResponse>> showAnswersToBuyer(IcpJustJwt payload, int offset);

    IcpResponseModel<List<IcpQuestionResponse>> showQuestionsOfProduct(Long productId, int offset);
}
