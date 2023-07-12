package com.terziim.backend.product.service;

import com.terziim.backend.product.dto.response.IcpProductDetailedResponse;
import com.terziim.backend.category.model.ProductCategory;
import com.terziim.backend.category.model.ProductSubCategory;
import com.terziim.backend.product.dto.response.IcpProductMiniResponse;
import com.terziim.backend.product.model.Product;

import java.util.List;

public interface ProductExternalService {


    void changeProductStatusToPayload(Long productId, String payload);

    void deActivateProduct(Long productId);

    void activateProduct(Long productId);

    void addProductCategory(ProductCategory payload);

    void addProductSubCategory(ProductSubCategory payload);

    List<IcpProductDetailedResponse> getProductResponsesWithCategory(Long id);

    List<IcpProductDetailedResponse> getProductResponsesWithSubCategory(Long id);

    List<IcpProductMiniResponse> getProductMiniRespsWithSubCatFromProducts(List<Product> product);

    IcpProductDetailedResponse getProductResponseWithId(Long id);

    Integer getUsersProductCount(String userId);

    Product getProductById(Long id);

    void saveProduct(Product product);

    List<Product> getProductFromSubCatWithLimit(Long subCatId, int limit);

    boolean isThereProductWithThisId(Long id);

    boolean doesSellerAcceptOfferForProduct(Long id);

    void setProductToLocked(Long id);

    void setProductToUnLocked(Long id);

    List<Product> getUncheckedProducts(int offset, int limit);

    List<IcpProductMiniResponse> getUncheckedProductResponse(int offset, int limit);

    void setProductChecked(Long id);
}
