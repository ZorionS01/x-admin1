package com.szw.sys.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.szw.common.utils.JwtUtil;
import com.szw.sys.entity.User;
import com.szw.sys.mapper.UserMapper;
import com.szw.sys.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author szw
 * @since 2023-04-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    /*@Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());
        wrapper.eq(User::getPassword,user.getPassword());
        User user1 = this.baseMapper.selectOne(wrapper);
        if (user1 != null){
            String key = "keys:" + UUID.randomUUID();
            user1.setPassword(null);
            redisTemplate.opsForValue().set(key,user1,30, TimeUnit.MINUTES);
            Map<String,Object> map = new HashMap<>();
            map.put("token",key);
            return map;
        }
        return null;
    }*/
    @Override
    public Map<String, Object> login(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername,user.getUsername());

        User user1 = this.baseMapper.selectOne(wrapper);
        if (user1 != null && passwordEncoder.matches(user.getPassword(),user1.getPassword())){
//            String key = "keys:" + UUID.randomUUID();
            user1.setPassword(null);
            String token = jwtUtil.createToken(user1);
//            redisTemplate.opsForValue().set(key,user1,30, TimeUnit.MINUTES);
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);
            return map;
        }
        return null;
    }

    @Override
    public Map<String, Object> getUserInfo(String token) {
        //获取token 返回map
//        Object object = redisTemplate.opsForValue().get(token);//Object object 此时获取的不是json对象 是key-value键值
        //JSON.toJSONString(object) 转化为json对象
//        User user = JSON.parseObject(JSON.toJSONString(object), User.class);
        User user = null;
        try {
            user = jwtUtil.parseToken(token, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> list = this.baseMapper.selectRoleNameByUserId(user.getId());
        Map<String,Object> data = new HashMap<>();
        data.put("roles",list);
        data.put("avatar",user.getAvatar());
        return data;
    }

    @Override
    public void logout(String token) {
//        redisTemplate.delete(token);
    }
}
