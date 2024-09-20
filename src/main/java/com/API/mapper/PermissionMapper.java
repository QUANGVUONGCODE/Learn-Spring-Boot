package com.API.mapper;

import org.mapstruct.Mapper;

import com.API.dto.request.PermissionRequest;
import com.API.dto.response.PermissionResponse;
import com.API.entity.Permission;

@Mapper(componentModel = "spring")
public interface PermissionMapper {

    Permission MapToPermission(PermissionRequest request);

    PermissionResponse toPermissionResponse(Permission permission);
}
