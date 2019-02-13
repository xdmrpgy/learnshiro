package com.roypan.learnshiro.service;

import com.roypan.learnshiro.entity.SysPermission;
import com.roypan.learnshiro.exception.ResourceNotFoundException;
import com.roypan.learnshiro.repository.SysPermissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Roy Pan
 */
@Service
public class SysPermissionService {
    private SysPermissionRepository sysPermissionRepository;

    public void addSysPermission(SysPermission sysPermission){
        sysPermissionRepository.save(sysPermission);
    }

    public void updateSysPermission(Long sysPermissionId,SysPermission sysPermission){
        SysPermission p = sysPermissionRepository.findById(sysPermissionId).orElseThrow(() -> new ResourceNotFoundException("sysPermissionId:" + sysPermissionId + "not found!"));
        p.setName(sysPermission.getName());
        p.setRoleList(sysPermission.getRoleList());
        sysPermissionRepository.save(p);
    }

    public SysPermission querySysPermission(Long sysPermissionId){
        return sysPermissionRepository.findById(sysPermissionId).orElseThrow(() -> new ResourceNotFoundException("sysPermissionId:" + sysPermissionId + "not found!"));
    }

    public List<SysPermission> queryAll(){
        return sysPermissionRepository.findAll();
    }

    public void deleteSysPermission(Long sysPermissionId){
        sysPermissionRepository.deleteById(sysPermissionId);
    }
}
