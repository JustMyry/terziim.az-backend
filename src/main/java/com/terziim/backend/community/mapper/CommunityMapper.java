package com.terziim.backend.community.mapper;



import com.terziim.backend.community.dto.request.IcpCommunityRequest;
import com.terziim.backend.community.dto.response.IcpCommunityResponse;
import com.terziim.backend.community.model.Community;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper(componentModel = "spring")
public interface CommunityMapper {

    CommunityMapper INSTANCE = getMapper(CommunityMapper.class);


    @Mapping(source = "id", target = "id")
    //@Mapping(source = "authorId", target = "authorId")
    @Mapping(source = "community", target = "community")
    @Mapping(source = "createdAt", target = "createdAt")
    IcpCommunityResponse getCommunityResponse(Community community);


    @Mapping(source = "community", target = "community")
    Community getCommunityEntity(IcpCommunityRequest request);

}
