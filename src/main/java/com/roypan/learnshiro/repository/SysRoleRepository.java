package com.roypan.learnshiro.repository;

import com.roypan.learnshiro.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by Roy Pan
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole,Long> {
}
