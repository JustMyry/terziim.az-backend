package com.terziim.backend.icpcommication.response;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IcpResponseModel<T> {

    private T response;
    private IcpResponseStatus status;

}
