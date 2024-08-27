package com.devteria.identity_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.devteria.identity_service.dto.reponse.RoleResponse;
import com.devteria.identity_service.dto.request.RoleRequest;
import com.devteria.identity_service.entity.Role;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    @Mapping(target = "permissionSet", ignore = true)
    Role toRole(RoleRequest roleRequest);

    RoleResponse toRoleResponse(Role role);
}
