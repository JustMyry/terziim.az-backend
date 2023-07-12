package com.terziim.backend.product.repository;

import com.terziim.backend.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductById(Long id);


    @Query(value = "SELECT * FROM product u WHERE u.id=:id AND u.is_active = true ", nativeQuery = true)
    Product findActiveProductById(@Param("id") Long id);


    List<Product> findProductsByCategoryId(Long id);

    List<Product> findProductsBySubCategoryId(Long id);

    List<Product> findProductsBySellerId(String sellerId);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.checked = true ORDER BY u.created_at DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> findNewProducts(@Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.is_vip = true AND u.checked = true ORDER BY u.created_at DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> findNewVIPProducts(int offset, int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.seller_id=:id ORDER BY u.created_at AND u.checked = true DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> findProductBySellerIdWithOffset(@Param("id") String id, @Param("offset") int offset, @Param("limit") int limit);



    // SEARCH SERVICE QUERIES
    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.product_brand LIKE CONCAT('%',:brand,'%') AND u.checked = true  ORDER BY u.created_at DESC limit :limit offset :offset  ", nativeQuery = true)
    List<Product> searchOnBrand(@Param("brand") String brand, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.seller_id =:sellerId AND u.checked = true ORDER BY u.created_at DESC  limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnSeller(@Param("sellerId") String sellerId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.category_id =:categoryId AND u.checked = true ORDER BY u.created_at DESC  limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnCategory(@Param("categoryId") Long categoryId, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.sub_category_id =:subCategoryId AND u.checked = true ORDER BY u.created_at DESC limit :limit offset :offset ", nativeQuery = true)
    List<Product> searchOnSubCategory(@Param("subCategoryId") Long subCategoryId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.gender =:gender AND u.checked = true ORDER BY u.created_at DESC  limit :limit offset :offset  ", nativeQuery = true)
    List<Product> searchOnGender(@Param("gender") String gender, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.product_name LIKE CONCAT('%',:productName,'%') AND u.checked = true ORDER BY u.created_at DESC  limit :limit offset :offset  ", nativeQuery = true)
    List<Product> searchOnName(@Param("productName") String productName, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.product_name LIKE CONCAT('%',:productName,'%') AND u.category_id=:categoryId AND u.checked = true ORDER BY u.created_at DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnNameAndCategory(@Param("productName") String productName, @Param("categoryId") Long categoryId, @Param("offset") int offset, @Param("limit") int limit);

    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.product_name LIKE CONCAT('%',:productName,'%') AND u.gender=:gender AND u.checked = true ORDER BY u.created_at DESC  limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnNameAndGender(@Param("productName") String productName, @Param("gender") String gender, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.is_active = true AND u.category_id=:categoryId AND u.sub_category_id=:subCategoryId AND u.gender=:gender AND u.checked = true ORDER BY u.created_at DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnCategoryAndSubCategoryAndGender(@Param("categoryId") Long categoryId, @Param("subCategoryId") Long subCategoryId, @Param("gender") String gender, @Param("offset") int offset, @Param("limit") int limit);


    @Query(value = "SELECT * FROM product u WHERE u.checked = false ORDER BY u.created_at DESC limit :limit offset :offset", nativeQuery = true)
    List<Product> searchOnUnCheckedProducts(@Param("offset") int offset, @Param("limit") int limit);


}
