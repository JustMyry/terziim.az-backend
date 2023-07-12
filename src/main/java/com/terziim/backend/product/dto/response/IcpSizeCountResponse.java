package com.terziim.backend.product.dto.response;


import lombok.Builder;

@Builder
public class IcpSizeCountResponse {

    private Long id;
    private String size;
    private Integer count;



    //Constructors
    public IcpSizeCountResponse() {}

    public IcpSizeCountResponse(Long id, String size, Integer count) {
        this.id = id;
        this.size = size;
        this.count = count;
    }


    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
