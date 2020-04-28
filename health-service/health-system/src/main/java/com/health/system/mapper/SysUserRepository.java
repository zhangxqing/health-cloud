package com.health.system.mapper;

import com.health.system.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author zq
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Long>, JpaSpecificationExecutor<SysUser> {

    /**
     * 根据用户名查询用户信息
     * @param userName 用户名
     * @return SysUser
     */
    Optional<SysUser> findByLoginName(String userName);
}
