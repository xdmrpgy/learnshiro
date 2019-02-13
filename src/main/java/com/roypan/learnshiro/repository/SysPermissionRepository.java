package com.roypan.learnshiro.repository;

import com.roypan.learnshiro.entity.SysPermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by Roy Pan
 */
@Repository
public interface SysPermissionRepository extends JpaRepository<SysPermission,Long> {

}
