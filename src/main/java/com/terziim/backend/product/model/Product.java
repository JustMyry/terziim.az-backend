package com.terziim.backend.product.model;


import com.terziim.backend.icpmodel.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

/**
 * @author: Indice Inc.
 * @target: Bu class SATICIlarin mehsullarini 'Terziim DB' da saxlamaq ucundur.
 *          <OneToMany> olaraq '<SizeCount>'i saxlayir.
 * **/
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {

    private String sellerId;

    private Long categoryId;
    private Long subCategoryId;

    private String productName;
    private String productBrand;
    private String productInfo;
    private String productPictureUrl;

    //Mehsula 'Terziim Inc' terefinden verilmis mukafatlari temsil edir (edecek).
    // < BILINMIR >
    private String productTicket;

    //< kisi | qadin | umumi >
    private String gender;

    private Float productPrice;
    private Float priceBeforeDiscount;

    //Detalli melumat ucun -> model/'SizeCount'
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "product_id")
    private Set<SizeCount> sizeCounts;

    //Mehsulun anliq veziyyetini gosterir.
    //< OnSale | OutOfStock | Waiting >
    private String status;

    private Boolean isVip;

    //Mehsulu alici aldiqdan sonra geri qaytara bilim bilmeyeciyini gosterir.
    //< true | false >
    private Boolean returnable;

    //Mehsulu alici aldiqdan sonra deyisdire bilib bilmeyeciyini gosterir.
    //< true | false >
    private Boolean changeable;

    //Mehsul ucun alicinin qiymet teklif ede bilib bilmeyeciyini gosterir.
    //< true | false >
    private Boolean offeredPrice;

    private Boolean isNew;

    private Boolean terziimPay;
    private Boolean cash;

    //Mehsul ilk yuklendiyinde 'FALSE' olur. Administrasiya yoxlayib tesdiqleyerde 'TRUE'
    //< true | false >
    private Boolean checked;


    //Delete emeliyyatlarinda istifade edilir. False oldugu teqdirde Front'da gorunmur ve statusu geri dondurele bilmir.
    //< true | false >
    private Boolean isActive;

    //Mehsulu incelemeye almaq ucun istifade edilir. 'isActive'den ferqi bunun statusunu geri dondurub yeniden
    //"TRUE" (gorunur) etmek olur.
    //< true | false >
    private Boolean isNotLocked;


//    //Utility Methods
//    public void addSizeCounts(SizeCount sizes) {
//        this.sizeCounts.add(sizes);
//        sizes.setProduct(this);
//    }



    //Getters and Setters
    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(Long subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getProductTicket() {
        return productTicket;
    }

    public void setProductTicket(String productTicket) {
        this.productTicket = productTicket;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public Float getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Float priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Set<SizeCount> getSizeCounts() {
        return sizeCounts;
    }

    public void setSizeCounts(Set<SizeCount> sizeCounts) {
        this.sizeCounts = sizeCounts;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getVip() {
        return isVip;
    }

    public void setVip(Boolean vip) {
        isVip = vip;
    }

    public Boolean getReturnable() {
        return returnable;
    }

    public void setReturnable(Boolean returnable) {
        this.returnable = returnable;
    }

    public Boolean getChangeable() {
        return changeable;
    }

    public void setChangeable(Boolean changeable) {
        this.changeable = changeable;
    }

    public Boolean getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(Boolean offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Boolean getTerziimPay() {
        return terziimPay;
    }

    public void setTerziimPay(Boolean terziimPay) {
        this.terziimPay = terziimPay;
    }

    public Boolean getCash() {
        return cash;
    }

    public void setCash(Boolean cash) {
        this.cash = cash;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getNotLocked() {
        return isNotLocked;
    }

    public void setNotLocked(Boolean notLocked) {
        isNotLocked = notLocked;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public String getProductPictureUrl() {
        return productPictureUrl;
    }

    public void setProductPictureUrl(String productPictureUrl) {
        this.productPictureUrl = productPictureUrl;
    }
}
