package com.terziim.backend.activity.mapper;

import com.terziim.backend.activity.dto.response.IcpActivityResponse;
import com.terziim.backend.activity.model.Activity;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "spring")
public interface ActivityMapper {

    ActivityMapper INSTANCE = getMapper(ActivityMapper.class);

    IcpActivityResponse getResponseFromAction(Activity activity);

}
