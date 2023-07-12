package com.terziim.backend.product.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;

import java.util.Date;


/**
 * @author: Incide Inc.
 * @target: Bu class (entity) her mehsulun hansi olculere sahib oldugu ve olcuden nece eded oldugunu bildirir.
 *          <ManyToOne> olaraq '<Product>' Class'i daxilindeki Set<SizeCount> sizeCounts'a baglidir.
 *          Yalniz 'Product' Class'dan bura baglanmaq olur. (OneToMany mappedBy:Product).
 *          Her olcu yaradilir, sayi elave edilir ve Product daxilindeki sizeCounts'a elave edilir.
 * **/
@Entity
@Builder
public class SizeCount extends BaseModel {

    //Mehsulun olculeri
    private String size;

    //Her olcuden hansi miqdarda oldugu
    private Integer count;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;



    //Constructors
    public SizeCount() {}

    public SizeCount(Long id, Date createdAt, Date updatedAt, String size, Integer count) {
        super(id, createdAt, updatedAt);
        this.size = size;
        this.count = count;
//        this.product = product;
    }

    public SizeCount(String size, Integer count) {
        this.size = size;
        this.count = count;
//        this.product = product;
    }

    //Getters and Setters
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

//    public Product getProduct() {
//        return product;
//    }
//
//    public void setProduct(Product product) {
//        this.product = product;
//    }
}
