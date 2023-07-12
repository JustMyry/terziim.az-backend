package com.terziim.backend.activity.service;

import com.terziim.backend.activity.dto.response.IcpActivityResponse;
import com.terziim.backend.icpcommication.response.IcpResponseModel;

import java.util.List;

public interface ActivityService {

    IcpResponseModel<List<IcpActivityResponse>> showAllActions(int offset);

    IcpResponseModel<List<IcpActivityResponse>> showAllActiveActions(int offset);

    IcpResponseModel<IcpActivityResponse> showSpecificActionWithId(Long id);

    IcpResponseModel<IcpActivityResponse> showSpecificActionWithUsername(String name);

    IcpResponseModel<String> setActionDeactive(Long id);

    IcpResponseModel<String> setActionActive(Long id);

    IcpResponseModel<String> deleteActionById(Long id);
}
