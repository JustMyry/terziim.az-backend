package com.terziim.backend.question.service.impl;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.notification.service.NotificationExternalService;
import com.terziim.backend.question.dto.request.IcpAnswerRequest;
import com.terziim.backend.question.dto.request.IcpQuestionRequest;
import com.terziim.backend.question.dto.response.IcpQuestionResponse;
import com.terziim.backend.question.mapper.QuestionMapper;
import com.terziim.backend.question.model.Answer;
import com.terziim.backend.question.model.Question;
import com.terziim.backend.question.repository.AnswerRepository;
import com.terziim.backend.question.repository.QuestionRepository;
import com.terziim.backend.question.service.QuestionExternalService;
import com.terziim.backend.question.service.QuestionService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository qRepository;
    private final AnswerRepository aRepository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    private final NotificationExternalService notificationService;
    public QuestionServiceImpl(QuestionRepository qRepository, AnswerRepository aRepository,
                               UserExternalService userService, JwtProvider jwtProvider,
                               NotificationExternalService notificationService) {
        this.qRepository = qRepository;
        this.aRepository = aRepository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.notificationService = notificationService;
    }




    @Override
    public IcpResponseModel<List<IcpQuestionResponse>> showQuestionsOfProduct(Long productId, int offset) {
        List<IcpQuestionResponse> responses = getQuestionResponseList(qRepository.getByProductIdWithLimits(productId, offset, 20));
        return IcpResponseModel.<List<IcpQuestionResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getRequestIsInvalid())
                .build();
    }




    // Buyers
    @Override
    public IcpResponseModel<String> askQuestion(IcpQuestionRequest payload) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Question question = QuestionMapper.INSTANCE.getQuestionModel(payload);
        question.setBuyerId(buyer.getUserId());
        question.setBuyerName(buyer.getUsername());
        qRepository.save(question);
        return IcpResponseModel.<String>builder()
                .response("Sual uğurla qeydə alındı. Satıcı cavabladığı zaman sizə bildiriş gələcəkdir!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> deleteQuestion(IcpJustJwt payload, Long id) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Question question = qRepository.getById(id);
        if(buyer==null || !buyer.isNotLocked() || question==null)
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        qRepository.delete(question);
        return IcpResponseModel.<String>builder()
                .response("Sual uğurla silindi!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpQuestionResponse>> showAnswersToBuyer(IcpJustJwt payload, int offset) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpQuestionResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpQuestionResponse>>builder()
                .response(getQuestionResponseList(qRepository.getByBuyerIdForBuyer(buyer.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }





    // Sellers
    @Override
    public IcpResponseModel<String> answerQuestion(IcpAnswerRequest payload, Long id) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Question question = qRepository.getById(id);
        if(seller==null || !seller.isNotLocked() || question == null || !question.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Answer answer = QuestionMapper.INSTANCE.getAnswerModel(payload);
        answer.setSellerId(seller.getUserId());
        question.setAnswer(answer);
        qRepository.save(question);

        //Notification Service
        notificationService.createQuestionNotification(question.getBuyerId(), seller.getUserId(),
                seller.getUsername(), question.getQuestion(), question.getId(), question.getProductId());

        return IcpResponseModel.<String>builder()
                .response("Sual uğurla cavablandırıldı!")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> deleteAnswer(IcpJustJwt payload, Long id) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Question question = qRepository.getById(id);
        if(seller==null || !seller.isNotLocked() || question == null || !question.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        aRepository.delete(question.getAnswer());
        question.setAnswer(null);
        question.setAnswered(false);
        qRepository.save(question);
        return IcpResponseModel.<String>builder()
                .response("Cavab uğurla silindi! Sualı yenidən cavablandıra bilərsiniz.")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpQuestionResponse>> showQuestionsToSeller(IcpJustJwt payload, int offset) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpQuestionResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        return IcpResponseModel.<List<IcpQuestionResponse>>builder()
                .response(getQuestionResponseList(qRepository.getBySellerIdForSellerWithLimits(seller.getUserId(), offset, 20)))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    // Utility Methods
    List<IcpQuestionResponse> getQuestionResponseList(List<Question> questions){
        return questions.stream().map(s-> {
            IcpQuestionResponse response = QuestionMapper.INSTANCE.getQuestionResponse(s);
            response.setId(s.getId());
            response.setAnswer(QuestionMapper.INSTANCE.getAnswerResponse(s.getAnswer()));
            response.setCreatedAt( LocalDateTime.ofInstant( s.getCreatedAt().toInstant(), ZoneOffset.UTC ).toLocalDate());
            return response;
        }).collect(Collectors.toList());
    }
}
