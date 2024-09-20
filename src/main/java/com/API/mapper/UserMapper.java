package com.API.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.API.dto.request.UserCreationRequest;
import com.API.dto.request.UserUpdateRequest;
import com.API.dto.response.UserResponse;
import com.API.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User mapToUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
