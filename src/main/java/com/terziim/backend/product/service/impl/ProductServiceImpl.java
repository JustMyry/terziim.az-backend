package com.terziim.backend.product.service.impl;

import com.terziim.backend.icpcommication.request.IcpJustJwt;
import com.terziim.backend.icpcommication.request.IcpSingleData;
import com.terziim.backend.icpcommication.response.IcpResponseModel;
import com.terziim.backend.icpcommication.response.IcpResponseStatus;
import com.terziim.backend.like.service.LikeExternalService;
import com.terziim.backend.product.dto.request.IcpClothesRequest;
import com.terziim.backend.product.dto.request.IcpProductRequest;
import com.terziim.backend.product.dto.request.IcpShoesRequest;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.dto.response.IcpSizeCountResponse;
import com.terziim.backend.product.mapper.ProductMapper;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.model.SizeCount;
import com.terziim.backend.product.repository.ProductRepository;
import com.terziim.backend.product.repository.SizeCountRepository;
import com.terziim.backend.product.service.ProductService;
import com.terziim.backend.security.jwt.JwtProvider;
import com.terziim.backend.user.model.AppUser;
import com.terziim.backend.user.service.impl.UserExternalServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final UserExternalServiceImpl userService;
    private final JwtProvider jwtProvider;
    private final LikeExternalService likeService;
    private final SizeCountRepository sizeRepository;
    public ProductServiceImpl(ProductRepository repository, UserExternalServiceImpl userService, JwtProvider jwtProvider,
                              LikeExternalService likeService, SizeCountRepository sizeRepository){
        this.repository = repository;
        this.userService = userService;
        this.jwtProvider = jwtProvider;
        this.likeService = likeService;
        this.sizeRepository = sizeRepository;
    }


    @Override
    public IcpResponseModel<IcpProductDetailedResponse> addClothesProduct(IcpClothesRequest payload) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked() || !verifyProductGender(payload.getGender()) || !seller.getUserType().equals("SELLER") || payload.getCategoryId()!=1)
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        Product product = getClothesProduct(payload);
        product.setSellerId(seller.getUserId());
        product.setStatus("OnSale");
        product.setNotLocked(true);
        product.setActive(true);
        repository.save(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(getProductResponse(product, seller))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpProductDetailedResponse> addShoesProduct(IcpShoesRequest payload) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked() || !verifyProductGender(payload.getGender()) || !seller.getUserType().equals("SELLER") || payload.getCategoryId()!=3)
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        Product product = getShoesProduct(payload);
        product.setSellerId(seller.getUserId());
        product.setStatus("OnSale");
        product.setNotLocked(true);
        product.setActive(true);
        repository.save(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(getProductResponse(product, seller))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<IcpProductDetailedResponse> addOtherProduct(IcpProductRequest payload) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || !seller.isNotLocked() || !verifyProductGender(payload.getGender()) || !seller.getUserType().equals("SELLER") || !verifyOtherCategory(payload.getCategoryId()))
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        Product product = getOtherProduct(payload);
        product.setSellerId(seller.getUserId());
        product.setStatus("OnSale");
        product.setNotLocked(true);
        product.setActive(true);
        repository.save(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(getProductResponse(product, seller))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> changeCountOfSizeCount(IcpJustJwt payload, Integer say, String size, Long productId) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Product product = repository.findActiveProductById(productId);
        if(seller==null || product==null || !seller.isNotLocked() || !product.getSellerId().equals(seller.getUserId()) || !product.getNotLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        System.out.println("#############################" + size);
        SizeCount sizeCount = product.getSizeCounts().stream().filter(s-> s.getSize().equals(size)).findFirst().get();
        sizeCount.setCount(say);
        sizeRepository.save(sizeCount);
        return IcpResponseModel.<String>builder()
                .response(sizeCount.getSize() + " olcusunun sayi dusuruldu -> " + say)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<String> deleteProduct(IcpJustJwt payload, Long id) {
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Product product = repository.findActiveProductById(id);
        if(seller==null || product==null || !seller.isNotLocked() || !product.getSellerId().equals(seller.getUserId()) || !product.getNotLocked())
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        product.setActive(false);
        repository.save(product);
        return IcpResponseModel.<String>builder()
                .response("Product deleted.")
                .status(IcpResponseStatus.get404())
                .build();
    }



    @Override
    public IcpResponseModel<IcpProductDetailedResponse> showProductForSpecificUser(Long productId, IcpJustJwt payload) {
        Product product = repository.findProductById(productId);
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(product==null || buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        AppUser user = userService.findAppUserByUserId(product.getSellerId());
        IcpProductDetailedResponse response = getProductResponse(product, user);
        response.setIsUserLiked(doesUserLikedProduct(product.getId(), buyer.getUserId()));
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(response)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<IcpProductDetailedResponse> showProductForGuest(Long productId) {
        Product product = repository.findProductById(productId);
        if(product==null)
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        AppUser user = userService.findAppUserByUserId(product.getSellerId());
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(getProductResponse(product, user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForGuest(int offset) {
        List<IcpProductMiniResponse> products = repository.findNewProducts(offset, 20).stream().map(s->{
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(seller.getStar());
            product.setSellerLikesCount(seller.getHowManyUserStared());
            return product;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(products)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }




    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForGuest(int offset) {
        List<IcpProductMiniResponse> products = repository.findNewVIPProducts(offset, 20).stream().map(s->{
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            product.setProductId(s.getId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(seller.getStar());
            product.setSellerLikesCount(seller.getHowManyUserStared());
            return product;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(products)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewProductsForUser(int offset, IcpJustJwt payload) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        List<IcpProductMiniResponse> products = repository.findNewProducts(offset, 20).stream().map(s->{
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            product.setProductId(s.getId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(seller.getStar());
            product.setSellerLikesCount(seller.getHowManyUserStared());
            product.setIsUserLiked(doesUserLikedProduct(product.getProductId(), buyer.getUserId()));
            product.setProductLikes(getProductLikes(product.getProductId()));
            return product;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(products)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }

    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showNewVIPProductsForUser(int offset, IcpJustJwt payload) {
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(buyer==null || !buyer.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        List<IcpProductMiniResponse> products = repository.findNewVIPProducts(offset, 20).stream().map(s->{
            AppUser seller = userService.findAppUserByUserId(s.getSellerId());
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            product.setProductId(s.getId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(seller.getStar());
            product.setSellerLikesCount(seller.getHowManyUserStared());
            product.setIsUserLiked(doesUserLikedProduct(product.getProductId(), buyer.getUserId()));
            product.setProductLikes(getProductLikes(product.getProductId()));
            return product;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(products)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<String> changeStatusOfProductToSold(IcpJustJwt payload, Long id) {
        Product product = repository.findProductById(id);
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(product==null || seller==null || !seller.isNotLocked() ||!product.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        product.setStatus("Sold");
        repository.save(product);
        return IcpResponseModel.<String>builder()
                .response("Product Status changed to Sold")
                .status(IcpResponseStatus.get404())
                .build();
    }



    @Override
    public IcpResponseModel<String> changeStatusOfProductToExists(IcpJustJwt payload, Long id) {
        Product product = repository.findProductById(id);
        AppUser seller = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(product==null || seller==null || !seller.isNotLocked() ||!product.getSellerId().equals(seller.getUserId()))
            return IcpResponseModel.<String>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        product.setStatus("OnSale");
        repository.save(product);
        return IcpResponseModel.<String>builder()
                .response("Product Status changed to OnSale")
                .status(IcpResponseStatus.get404())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToUser(String accId, IcpJustJwt payload, int offset) {
        AppUser seller = userService.findAppUserByUserId(accId);
        AppUser buyer = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        if(seller==null || buyer==null || !buyer.isNotLocked() || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        List<IcpProductMiniResponse> responses = repository.findProductBySellerIdWithOffset(accId, offset, 20).stream().map(s->{
            IcpProductMiniResponse product = ProductMapper.INSTANCE.getProductMiniResponse(s);
            product.setProductId(s.getId());
            product.setSellerType(seller.getUserType());
            product.setSellerName(seller.getUsername());
            product.setSellerLikes(seller.getStar());
            product.setSellerLikesCount(seller.getHowManyUserStared());
            product.setIsUserLiked(doesUserLikedProduct(product.getProductId(), buyer.getUserId()));
            product.setProductLikes(getProductLikes(product.getProductId()));
            return product;
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.get404())
                .build();
    }



    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showProductsOfUserToGuest(String username, int offset) {
        AppUser seller = userService.findUserByUsername(username);
        if(seller==null || !seller.isNotLocked())
            return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                    .response(null)
                    .status(IcpResponseStatus.get404())
                    .build();
        List<IcpProductMiniResponse> responses = repository.findProductBySellerIdWithOffset(seller.getUserId(), offset, 20).stream().map(s->{
            return getProductMiniResponse(s, seller);
        }).collect(Collectors.toList());
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(responses)
                .status(IcpResponseStatus.getSuccess())
                .build();
    }



    @Override
    public IcpResponseModel<IcpProductDetailedResponse> makeDiscount(Long productId, IcpSingleData payload) {
        AppUser user = userService.findAppUserByUserId(jwtProvider.getSubject(payload.getJwt()));
        Product product = repository.findProductById(productId);
        if(user==null || !user.isNotLocked() || product==null || !product.getNotLocked() || !product.getSellerId().equals(user.getUserId()))
            return IcpResponseModel.<IcpProductDetailedResponse>builder()
                    .response(null)
                    .status(IcpResponseStatus.getRequestIsInvalid())
                    .build();
        product.setPriceBeforeDiscount(product.getProductPrice());
        product.setProductPrice(Float.valueOf(payload.getData()));
        repository.save(product);
        return IcpResponseModel.<IcpProductDetailedResponse>builder()
                .response(getProductResponse(product, user))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    @Override
    public IcpResponseModel<List<IcpProductMiniResponse>> showSimilarProducts(Long subCatId, int offset) {
        return IcpResponseModel.<List<IcpProductMiniResponse>>builder()
                .response(repository.searchOnSubCategory(subCatId, offset, 10).stream().map(s->{
                    AppUser user = userService.findAppUserByUserId(s.getSellerId());
                    return getProductMiniResponse(s, user);
                }).collect(Collectors.toList()))
                .status(IcpResponseStatus.getSuccess())
                .build();
    }


    // Utility Methods

    // Get ENTITY from requests ========================================================================================
    private Product getClothesProduct(IcpClothesRequest payload) {
        Product product = ProductMapper.INSTANCE.getClothesProduct(payload);
        Set<SizeCount> sizeCounts = new HashSet<>();
        sizeCounts.add(SizeCount.builder().size("XS").count(payload.getCountOfXS()).build());
        sizeCounts.add(SizeCount.builder().size("S").count(payload.getCountOfS()).build());
        sizeCounts.add(SizeCount.builder().size("M").count(payload.getCountOfM()).build());
        sizeCounts.add(SizeCount.builder().size("L").count(payload.getCountOfL()).build());
        sizeCounts.add(SizeCount.builder().size("XL").count(payload.getCountOfXL()).build());
        sizeCounts.add(SizeCount.builder().size("XXL").count(payload.getCountOfXXL()).build());
        product.setSizeCounts(sizeCounts);
        return product;
    }

    private Product getShoesProduct(IcpShoesRequest payload) {
        Product product = ProductMapper.INSTANCE.getShoesProduct(payload);
        Set<SizeCount> sizeCounts = new HashSet<>();
        sizeCounts.add(SizeCount.builder().size("30").count(payload.getCountOf30()).build());
        sizeCounts.add(SizeCount.builder().size("31").count(payload.getCountOf31()).build());
        sizeCounts.add(SizeCount.builder().size("32").count(payload.getCountOf32()).build());
        sizeCounts.add(SizeCount.builder().size("33").count(payload.getCountOf33()).build());
        sizeCounts.add(SizeCount.builder().size("34").count(payload.getCountOf34()).build());
        sizeCounts.add(SizeCount.builder().size("35").count(payload.getCountOf35()).build());
        sizeCounts.add(SizeCount.builder().size("36").count(payload.getCountOf36()).build());
        sizeCounts.add(SizeCount.builder().size("37").count(payload.getCountOf37()).build());
        sizeCounts.add(SizeCount.builder().size("38").count(payload.getCountOf38()).build());
        sizeCounts.add(SizeCount.builder().size("39").count(payload.getCountOf39()).build());
        sizeCounts.add(SizeCount.builder().size("40").count(payload.getCountOf40()).build());
        sizeCounts.add(SizeCount.builder().size("41").count(payload.getCountOf41()).build());
        sizeCounts.add(SizeCount.builder().size("42").count(payload.getCountOf42()).build());
        sizeCounts.add(SizeCount.builder().size("43").count(payload.getCountOf43()).build());
        sizeCounts.add(SizeCount.builder().size("44").count(payload.getCountOf44()).build());
        sizeCounts.add(SizeCount.builder().size("45").count(payload.getCountOf45()).build());
        sizeCounts.add(SizeCount.builder().size("46").count(payload.getCountOf46()).build());
        product.setSizeCounts(sizeCounts);
        return product;
    }

    private Product getOtherProduct(IcpProductRequest payload) {
        Product product = ProductMapper.INSTANCE.getOtherProduct(payload);
        return product;
    }
    // #################################################################################################################


    // Get Detailed Response from Entity =============================================================================
    private IcpProductDetailedResponse getProductResponse(Product product, AppUser seller){
        IcpProductDetailedResponse response = ProductMapper.INSTANCE.getProductDetailedResponse(product);
        setSizesToProduct(product, response);
        response.setProductId(product.getId());
        response.setSellerType(seller.getUserType());
        response.setProductLikes(getProductLikes(response.getProductId()));
        return response;
    }
    // #################################################################################################################


    // Get Mini Response from Entity ===================================================================================
    private IcpProductMiniResponse getProductMiniResponse(Product product, AppUser seller){
        IcpProductMiniResponse response = ProductMapper.INSTANCE.getProductMiniResponse(product);
        response.setProductId(product.getId());
        response.setSellerType(seller.getUserType());
        response.setSellerName(seller.getUsername());
        response.setSellerLikes(seller.getStar());
        response.setSellerLikesCount(seller.getHowManyUserStared());
        response.setProductLikes(getProductLikes(response.getProductId()));
        return response;
    }
    // #################################################################################################################


    void setSizesToProduct (Product product, IcpProductDetailedResponse response) {
        Set<IcpSizeCountResponse> sizes = product.getSizeCounts().stream().map(s->{
            return IcpSizeCountResponse.builder()
                    .id(s.getId())
                    .size(s.getSize())
                    .count(s.getCount())
                    .build();
        }).collect(Collectors.toSet());
        response.setSizeCounts(sizes);
    }



    private boolean verifyProductGender(String payload) {
        return payload.equals("kisi") || payload.equals("qadin") || payload.equals("usaq") || payload.equals("umumi");
    }

    private boolean verifyClothesSizes(IcpClothesRequest payload) {
        return payload.equals("kisi") || payload.equals("qadin") || payload.equals("usaq") || payload.equals("umumi");
    }

    private boolean verifyShoesSizes(IcpShoesRequest payload) {
        return payload.equals("kisi") || payload.equals("qadin") || payload.equals("usaq") || payload.equals("umumi");
    }

    //////
    //////   LIKE SERVICE
    private Boolean doesUserLikedProduct(Long productId, String userId) {
        System.out.println("-doesUserLikedProduct -> " + likeService.doesUserLikedProduct(userId, productId));
        return likeService.doesUserLikedProduct(userId, productId);
    }


    private Integer getProductLikes(Long productId){
        return likeService.likesCountOfProduct(productId);
    }


    private boolean verifyOtherCategory(Long categoryId) {
        return categoryId==2 || categoryId==4;
    }


}
