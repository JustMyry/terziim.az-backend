package com.terziim.backend.order.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Date;


@Entity
@Builder
public class Orders extends BaseModel {

    // Eger sifaris 'TEKLIF KARTI' ile verilibse bu "field" doldurulur.
    // Birbasa Satin Alma olubsa bu "field" bos (null) qalir.
    private Long offerMessageId;

    @NonNull
    @Column(nullable = false)
    private Long productId;
    private String sellerId;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private String  size;

    @Column(nullable = false)
    private Float price;

    private Float cargoPrice;

    @Column(nullable = false)
    private String buyerId;
    // Alicinin xususi isteyi ve ya notu varsa saticiya bildirir.
    // Satici bunu diqqete almaq mecburiyyetinde deyil.
    private String buyerComment;

    // Satici "Sifarislerim" bolmesinde sifarisi gorur ve Tesdiq/Legv edir.
    // Satici cavab verib bu "field"i doldurmadan sifaris 'kargo'ya verilmir.
    // Satici 3 gun icinde cavab vermezse aktivsizliye gore hesabi incelemeye alinir.
    private Boolean seenBySeller;
    private Boolean acceptedBySeller;

    // { wait | exsam | cargo | finished | cfb (canceled by buyer) | cfs (canceled by seller) }
    private String status;
    // Legv edilerse ve ya finish olarsa sifaris 'deActive' edilir.
    private Boolean isActive;



    //Constructors
    public Orders() {}

    public Orders(Long id, Date createdAt, Date updatedAt, Long offerMessageId, @NonNull Long productId, String sellerId,
                  Integer count, String size, Float price, Float cargoPrice,
                  String buyerId, String buyerComment, Boolean seenBySeller, Boolean acceptedBySeller, String status,
                  Boolean isActive) {
        super(id, createdAt, updatedAt);
        this.offerMessageId = offerMessageId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.count = count;
        this.size = size;
        this.price = price;
        this.cargoPrice = cargoPrice;
        this.buyerId = buyerId;
        this.buyerComment = buyerComment;
        this.seenBySeller = seenBySeller;
        this.acceptedBySeller = acceptedBySeller;
        this.status = status;
        this.isActive = isActive;
    }

    public Orders(Long offerMessageId, @NonNull Long productId, String sellerId, Integer count,
                  String size, Float price, Float cargoPrice, String buyerId,
                  String buyerComment, Boolean seenBySeller, Boolean acceptedBySeller, String status, Boolean isActive) {
        this.offerMessageId = offerMessageId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.count = count;
        this.size = size;
        this.price = price;
        this.cargoPrice = cargoPrice;
        this.buyerId = buyerId;
        this.buyerComment = buyerComment;
        this.seenBySeller = seenBySeller;
        this.acceptedBySeller = acceptedBySeller;
        this.status = status;
        this.isActive = isActive;
    }

    //Getters and Setters
    public Long getOfferMessageId() {
        return offerMessageId;
    }

    public void setOfferMessageId(Long offerMessageId) {
        this.offerMessageId = offerMessageId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getCargoPrice() {
        return cargoPrice;
    }

    public void setCargoPrice(Float cargoPrice) {
        this.cargoPrice = cargoPrice;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getBuyerComment() {
        return buyerComment;
    }

    public void setBuyerComment(String buyerComment) {
        this.buyerComment = buyerComment;
    }

    public Boolean getSeenBySeller() {
        return seenBySeller;
    }

    public void setSeenBySeller(Boolean seenBySeller) {
        this.seenBySeller = seenBySeller;
    }

    public Boolean getAcceptedBySeller() {
        return acceptedBySeller;
    }

    public void setAcceptedBySeller(Boolean acceptedBySeller) {
        this.acceptedBySeller = acceptedBySeller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }


    //toString
    @Override
    public String toString() {
        return "Orders{" +
                "offerMessageId=" + offerMessageId +
                ", productId=" + productId +
                ", sellerId='" + sellerId + '\'' +
                ", count=" + count +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", cargoPrice=" + cargoPrice +
                ", buyerId='" + buyerId + '\'' +
                ", buyerComment='" + buyerComment + '\'' +
                ", seenBySeller=" + seenBySeller +
                ", acceptedBySeller=" + acceptedBySeller +
                ", status='" + status + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
