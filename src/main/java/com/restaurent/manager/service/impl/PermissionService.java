package com.restaurent.manager.service.impl;

import com.restaurent.manager.dto.request.PermissionRequest;
import com.restaurent.manager.dto.response.PermissionResponse;
import com.restaurent.manager.mapper.PermissionMapper;
import com.restaurent.manager.repository.PermissionRepository;
import com.restaurent.manager.service.IPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(makeFinal = true)
@RequiredArgsConstructor
public class PermissionService implements IPermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;
    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        return permissionMapper.toPermissionResponse(
                permissionRepository.save(
                        permissionMapper.toPermission(request)));
    }

    @Override
    public List<PermissionResponse> getPermissions() {
        return permissionRepository.findAll().stream().map(permissionMapper::toPermissionResponse).toList();
    }
}
