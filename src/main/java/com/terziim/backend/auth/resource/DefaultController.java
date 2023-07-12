package com.terziim.backend.auth.resource;

import com.terziim.backend.auth.dto.IcpSignIn;
import com.terziim.backend.auth.dto.IcpSignUpAsAdmin;
import com.terziim.backend.auth.dto.IcpSignUpAsBuyer;
import com.terziim.backend.auth.dto.IcpSignUpAsSeller;
import com.terziim.backend.auth.service.AuthService;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.product.service.impl.ProductServiceImpl;
import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/")
public class DefaultController {

    private AuthService service;
    public DefaultController(AuthService service){
        this.service=service;
    }

    @PostMapping("/signin")
    IcpResponseModel<String> signIn(@RequestBody IcpSignIn request){
        return service.signInUser(request);
    }


    @PostMapping("/signup/istifadeci")
    IcpResponseModel<String> signUp(@RequestBody IcpSignUpAsBuyer request) throws MessagingException {
        return service.signUp(request);
    }

//    @Deprecated
//    @PostMapping("/signup/satici")
//    IcpResponseModel<String> signUpSeller(@RequestBody IcpSignUpAsSeller request) throws MessagingException {
//        return service.signUpSeller(request);
//    }

    @PostMapping("/signup/admin")
    IcpResponseModel<String> signUpAdmin(@RequestBody IcpSignUpAsAdmin request){
        return service.signUpAdmin(request);
    }


    @GetMapping("/activate/{userid}")
    IcpResponseModel<String> activateUser(@PathVariable String userid, @RequestParam String code){
        return service.activateUser(userid, code);
    }

}