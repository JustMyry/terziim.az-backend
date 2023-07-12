package com.terziim.backend.icpcommication.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


@Getter
@Setter
public class IcpSingleData {

    @NonNull
    private String jwt;
    private String data;


}
