package com.health.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.health.system.domain.SysPost;
import com.health.system.domain.dto.SysPostDto;

import java.util.List;

/**
 * 岗位信息 数据层
 *
 * @author ruoyi
 */
public interface SysPostMapper extends BaseMapper<SysPost> {
    /**
     * 查询岗位数据集合
     *
     * @param post 岗位信息
     * @return 岗位数据集合
     */
    List<SysPostDto> selectPostList(SysPostDto post);

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    List<SysPostDto> selectPostAll();

    /**
     * 根据用户ID查询岗位
     *
     * @param userId 用户ID
     * @return 岗位列表
     */
    List<SysPostDto> selectPostsByUserId(Long userId);

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    SysPostDto selectPostById(Long postId);

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deletePostByIds(Long[] ids);

    /**
     * 修改岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int updatePost(SysPostDto post);

    /**
     * 新增岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    int insertPost(SysPostDto post);

    /**
     * 校验岗位名称
     *
     * @param postName 岗位名称
     * @return 结果
     */
    SysPost checkPostNameUnique(String postName);

    /**
     * 校验岗位编码
     *
     * @param postCode 岗位编码
     * @return 结果
     */
    SysPost checkPostCodeUnique(String postCode);
}
