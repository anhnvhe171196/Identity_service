package com.devteria.identity_service.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.devteria.identity_service.dto.reponse.ApiResponse;
import com.devteria.identity_service.dto.reponse.RoleResponse;
import com.devteria.identity_service.dto.request.RoleRequest;
import com.devteria.identity_service.service.RoleService;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleResponse> create(@RequestBody RoleRequest permissionRequest) {
        return ApiResponse.<RoleResponse>builder()
                .result(roleService.create(permissionRequest))
                .build();
    }

    @GetMapping
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .result(roleService.getAllRoles())
                .build();
    }

    @DeleteMapping("/{role}")
    ApiResponse<Void> delete(@PathVariable("role") String permission) {
        roleService.deleteRole(permission);
        return ApiResponse.<Void>builder().build();
    }
}
