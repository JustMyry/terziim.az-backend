package com.terziim.backend.product.service.impl;

import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.dto.response.IcpSizeCountResponse;
import com.terziim.backend.product.model.Product;
import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.product.mapper.ProductMapper;
import com.terziim.backend.category.model.ProductCategory;
import com.terziim.backend.category.model.ProductSubCategory;
import com.terziim.backend.category.repository.CategoryRepository;
import com.terziim.backend.product.repository.ProductRepository;
import com.terziim.backend.category.repository.SubCategoryRepository;
import com.terziim.backend.product.service.ProductExternalService;
import com.terziim.backend.user.model.AppUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class ProductExternalServiceImpl implements ProductExternalService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    public ProductExternalServiceImpl(ProductRepository repository, CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository){
        this.repository = repository;
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }


    @Override
    public void changeProductStatusToPayload(Long productId, String payload) {
        Product product = repository.findProductById(productId);
        product.setStatus(payload);
        repository.save(product);
    }

    @Override
    public void deActivateProduct(Long productId) {
        Product product = repository.findProductById(productId);
        product.setActive(false);
        repository.save(product);
    }

    @Override
    public void activateProduct(Long productId) {
        Product product = repository.findProductById(productId);
        product.setActive(true);
        repository.save(product);
    }

    @Override
    public void addProductCategory(ProductCategory payload) {

    }

    @Override
    public void addProductSubCategory(ProductSubCategory payload) {

    }

    @Override
    public List<IcpProductDetailedResponse> getProductResponsesWithCategory(Long id) {
        List<IcpProductDetailedResponse> responses = repository.findProductsByCategoryId(id).stream().map(s->{
            return getProductResponseWithoutSeller(s);
        }).collect(Collectors.toList());
        return responses;
    }

    @Override
    public List<IcpProductDetailedResponse> getProductResponsesWithSubCategory(Long id) {
        List<IcpProductDetailedResponse> responses = repository.findProductsBySubCategoryId(id).stream().map(s->{
            return getProductResponseWithoutSeller(s);
        }).collect(Collectors.toList());
        return responses;
    }

    @Override
    public List<IcpProductMiniResponse> getProductMiniRespsWithSubCatFromProducts(List<Product> product) {
        return product.stream().map(s->getProductMiniResponse(s)).collect(Collectors.toList());
    }

    @Override
    public IcpProductDetailedResponse getProductResponseWithId(Long id) {
        return getProductResponseWithoutSeller(repository.findProductById(id ));
    }

    @Override
    public Integer getUsersProductCount(String userId) {
        return repository.findProductsBySellerId(userId).size();
    }

    @Override
    public Product getProductById(Long id) {
        return repository.findProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> getProductFromSubCatWithLimit(Long subCatId, int limit) {
        return repository.searchOnSubCategory(subCatId, 0, limit);
    }

    @Override
    public boolean isThereProductWithThisId(Long id) {
        return repository.findProductById(id)==null;
    }

    @Override
    public boolean doesSellerAcceptOfferForProduct(Long id) {
        Product product = repository.findProductById(id);
        if(product == null)
            return false;
        return product.getOfferedPrice();
    }

    @Override
    public void setProductToLocked(Long id) {
        Product product = repository.findProductById(id);
        product.setNotLocked(false);
        repository.save(product);
    }

    @Override
    public void setProductToUnLocked(Long id) {
        Product product = repository.findProductById(id);
        product.setNotLocked(true);
        repository.save(product);
    }

    @Override
    public List<Product> getUncheckedProducts(int offset, int limit) {
        return repository.searchOnUnCheckedProducts(offset, limit);
    }

    @Override
    public List<IcpProductMiniResponse> getUncheckedProductResponse(int offset, int limit) {
        return repository.searchOnUnCheckedProducts(offset, limit).stream().map(s->{
            return getProductMiniResponse(s);
        }).collect(Collectors.toList());
    }

    @Override
    public void setProductChecked(Long id) {
        Product product = repository.findProductById(id);
        product.setChecked(true);
        repository.save(product);
    }


    // Get Detailed Response from Entity =============================================================================
    private IcpProductDetailedResponse getProductResponse(Product product, AppUser seller){
        IcpProductDetailedResponse response = ProductMapper.INSTANCE.getProductDetailedResponse(product);
        setSizesToProduct(product, response);
        response.setSellerType(seller.getUserType());
        response.setProductId(product.getId());
        return response;
    }

    private IcpProductDetailedResponse getProductResponseWithoutSeller(Product product){
        IcpProductDetailedResponse response = ProductMapper.INSTANCE.getProductDetailedResponse(product);
        setSizesToProduct(product, response);
        response.setSellerType(null);
        response.setProductId(product.getId());
        return response;
    }
    // #################################################################################################################


    // Get Mini Response from Entity ===================================================================================
    private IcpProductMiniResponse getProductMiniResponse(Product product){
        IcpProductMiniResponse response = ProductMapper.INSTANCE.getProductMiniResponse(product);
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

}
