package com.terziim.backend.question.resource;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.question.dto.request.IcpAnswerRequest;
import com.terziim.backend.question.dto.request.IcpQuestionRequest;
import com.terziim.backend.question.dto.response.IcpQuestionResponse;
import com.terziim.backend.question.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sual")
public class QuestionResource {

    private final QuestionService service;
    public QuestionResource(QuestionService service){
        this.service = service;
    }



    @PostMapping("/ver")
    IcpResponseModel<String> askQuestion(@RequestBody IcpQuestionRequest payload){
        return service.askQuestion(payload);
    }


    @PostMapping("/sil")
    IcpResponseModel<String> deleteQuestion(@RequestBody IcpJustJwt payload, @RequestParam("sual") Long id){
        return service.deleteQuestion(payload, id);
    }


    @PostMapping("/cavabla")
    IcpResponseModel<String> answerQuestion(@RequestBody IcpAnswerRequest payload, @RequestParam("cavab") Long id){
        return service.answerQuestion(payload, id);
    }


    @PostMapping("/cavabisil")
    IcpResponseModel<String> deleteAnswer(@RequestBody IcpJustJwt payload, @RequestParam("cavab") Long id){
        return service.deleteAnswer(payload, id);
    }


    @PostMapping("/suallarim")
    IcpResponseModel<List<IcpQuestionResponse>> showQuestionsToSeller(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.showQuestionsToSeller(payload, offset);
    }


    @PostMapping("/cavablarim")
    IcpResponseModel<List<IcpQuestionResponse>> showAnswersToBuyer(@RequestBody IcpJustJwt payload, @RequestParam("offset") int offset){
        return service.showAnswersToBuyer(payload, offset);
    }


    @PostMapping("/mehsul/{productId}")
    IcpResponseModel<List<IcpQuestionResponse>> showQuestionsOfProduct(@PathVariable Long productId, @RequestParam("offset") int offset){
        return service.showQuestionsOfProduct(productId, offset);
    }

}
