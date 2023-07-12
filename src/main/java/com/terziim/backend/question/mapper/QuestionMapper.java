package com.terziim.backend.question.mapper;


import com.terziim.backend.question.dto.request.IcpAnswerRequest;
import com.terziim.backend.question.dto.request.IcpQuestionRequest;
import com.terziim.backend.question.dto.response.IcpAnswerResponse;
import com.terziim.backend.question.dto.response.IcpQuestionResponse;
import com.terziim.backend.question.model.Answer;
import com.terziim.backend.question.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    QuestionMapper INSTANCE = getMapper(QuestionMapper.class);



    IcpQuestionResponse getQuestionResponse(Question question);

    IcpAnswerResponse getAnswerResponse(Answer answer);

    @Mapping(target = "answered", expression = "java(false)")
    Question getQuestionModel(IcpQuestionRequest request);

    Answer getAnswerModel(IcpAnswerRequest request);


}
