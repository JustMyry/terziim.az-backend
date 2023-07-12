package com.terziim.backend.follow.mapper;

import com.terziim.backend.follow.dto.IcpFollowResponse;
import com.terziim.backend.user.model.AppUser;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;


@Mapper(componentModel = "spring")
public interface FollowMapper {


    FollowMapper INSTANCE = getMapper(FollowMapper.class);

    //@Mapping(target = "relation", expression = "java(null)")
    IcpFollowResponse getFollowResponseFromUser(AppUser appUser);
}
