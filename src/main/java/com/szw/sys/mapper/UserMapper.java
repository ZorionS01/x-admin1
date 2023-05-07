package com.szw.sys.mapper;

import com.szw.sys.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author szw
 * @since 2023-04-29
 */
public interface UserMapper extends BaseMapper<User> {


    List<String> selectRoleNameByUserId(Integer userId);
}
