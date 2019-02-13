package com.roypan.learnshiro.service;

import com.roypan.learnshiro.entity.SysRole;
import com.roypan.learnshiro.exception.ResourceNotFoundException;
import com.roypan.learnshiro.repository.SysRoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author by Roy Pan
 */
@Service
public class SysRoleService {
    private SysRoleRepository sysRoleRepository;

    public void addSysRole(SysRole sysRole){
        sysRoleRepository.save(sysRole);
    }

    public void updateSysRole(Long sysRoleId,SysRole sysRole){
        SysRole r = sysRoleRepository.findById(sysRoleId).orElseThrow(() -> new ResourceNotFoundException("sysRoleId:" + sysRoleId + "not found!"));
        r.setRole(sysRole.getRole());
        r.setUserList(sysRole.getUserList());
        r.setPermissionList(sysRole.getPermissionList());
        sysRoleRepository.save(r);
    }

    public SysRole querySysRole(Long sysRoleId){
        return sysRoleRepository.findById(sysRoleId).orElseThrow(() -> new ResourceNotFoundException("sysRoleId:" + sysRoleId + "not found!"));
    }

    public List<SysRole> queryAll(){
        return sysRoleRepository.findAll();
    }

    public void deleteSysRole(Long sysRoleId){
        sysRoleRepository.deleteById(sysRoleId);
    }
}
