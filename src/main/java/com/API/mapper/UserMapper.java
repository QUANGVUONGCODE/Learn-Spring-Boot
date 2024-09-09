package com.API.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;

import com.API.dto.request.UserCreationRequest;
import com.API.dto.request.UserUpdateRequest;
import com.API.dto.response.UserResponse;
import com.API.entity.User;


@Mapper(componentModel = "spring")
public interface UserMapper{
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "Password", ignore = true)
    })
    User mapToUser(UserCreationRequest request);
    UserResponse toUserResponse(User user);
    @Mapping(target = "id", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
