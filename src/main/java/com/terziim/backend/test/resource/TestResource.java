package com.terziim.backend.test.resource;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.terziim.backend.guard.constants.GuardConstants.getUnusalWords;

@RestController
@RequestMapping("test")
public class TestResource {

    @GetMapping("/unusualwords")
    public List<String> testUnusualwords(){
        return getUnusalWords();
    }

}
