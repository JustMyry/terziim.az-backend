package com.terziim.backend.star.service.impl;


import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.star.dto.IcpStarRequest;
import com.terziim.backend.star.model.Star;
import com.terziim.backend.star.repository.StarRepository;
import com.terziim.backend.star.service.StarService;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.UserExternalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StarServiceImpl implements StarService {

    private final StarRepository repository;
    private final UserExternalService userService;
    private final JwtProvider jwtProvider;
    public StarServiceImpl(StarRepository repository, UserExternalService userService, JwtProvider jwtProvider){
        this.repository = repository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }



    @Override
    public IcpResponseModel<Float> getUsersStar(String username) {
        return null;
    }



    @Override
    public IcpResponseModel<Float> getUsersStarDetails(IcpStarRequest payload, String username) {
        return null;
    }



    @Override
    public IcpResponseModel<String> ulduzVer(IcpStarRequest paylaod, String username) {
        AppUser seller = userService.findUserByUsername(username);
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(paylaod.getJwt()));
        Star test = repository.findStarBySellerIdAndBuyerIdWithActive(seller.getUserId(), buyer.getUserId());
        if(seller==null || buyer==null || !(test==null) || !seller.isNotLocked() || !buyer.isNotLocked() || verifyStarRequest(paylaod) || buyer.getUserId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response("Unknown Usage")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        Star star = Star.builder().star(paylaod.getStar()).buyerId(buyer.getUserId()).sellerId(seller.getUserId()).isActive(true).build();
        repository.save(star);
        List<Star> userStars = repository.findStarsBySellerIdWithActive(seller.getUserId());
        int staredUsers = userStars.size();
        Float stars = (float) 0;
        for (Star userStar : userStars) {
            stars += userStar.getStar();
        }
        seller.setStar((stars/staredUsers));
        seller.setHowManyUserStared(staredUsers);
        userService.saveUser(seller);
        return IcpResponseModel.<String>builder()
                .response("You stared Seller: " + paylaod.getStar())
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    private boolean verifyStarRequest(IcpStarRequest paylaod) {
        return paylaod.getStar() > 5 || paylaod.getStar() < 0;
    }


    @Override
    public IcpResponseModel<String> ulduzuGeriAl(IcpJustJwt paylaod, String username) {
        AppUser seller = userService.findUserByUsername(username);
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(paylaod.getJwt()));
        Star star = repository.findStarBySellerIdAndBuyerIdWithActive(seller.getUserId(), buyer.getUserId());
        if(seller==null || buyer==null || star==null || !seller.isNotLocked() || !buyer.isNotLocked() || buyer.getUserId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response("Unknown Usage")
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        star.setActive(false);
        repository.save(star);
        List<Star> userStars = repository.findStarsBySellerIdWithActive(seller.getUserId());
        int staredUsers = userStars.size();
        Float stars = (float) 0;
        for (Star userStar : userStars) {
            stars += userStar.getStar();
        }
        seller.setStar((stars/staredUsers));
        seller.setHowManyUserStared(staredUsers);
        System.out.println("==================================");
        System.out.println(seller);
        System.out.println("==================================");
        userService.saveUser(seller);
        return IcpResponseModel.<String>builder()
                .response("You deleted your star")
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




}
