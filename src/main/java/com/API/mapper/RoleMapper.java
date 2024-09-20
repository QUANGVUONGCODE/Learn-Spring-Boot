package com.API.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.API.dto.request.RoleRequest;
import com.API.dto.response.RoleResponse;
import com.API.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissions", ignore = true)
    Role MapToRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
