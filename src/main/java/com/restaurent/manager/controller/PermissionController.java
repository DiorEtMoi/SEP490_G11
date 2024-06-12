package com.restaurent.manager.controller;

import com.restaurent.manager.dto.request.PermissionRequest;
import com.restaurent.manager.dto.response.ApiResponse;
import com.restaurent.manager.dto.response.PermissionResponse;
import com.restaurent.manager.service.IPermissionService;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/permission")
@AllArgsConstructor
@FieldDefaults(makeFinal = true)
public class PermissionController {
    IPermissionService permissionService;
    @PostMapping("/create")
    public ApiResponse<PermissionResponse> createPermission(@RequestBody PermissionRequest request){
        return ApiResponse.<PermissionResponse>builder()
                .result(permissionService.createPermission(request))
                .build();
    }
    @GetMapping
    public ApiResponse<List<PermissionResponse>> getPermission(){
        return ApiResponse.<List<PermissionResponse>>builder()
                .result(permissionService.getPermissions())
                .build();
    }
}
