package com.sustly.mycat.controller;

import com.sustly.mycat.entry.User;
import com.sustly.mycat.mapper.UserMapper;
import com.sustly.mycat.snowflake.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author liyue
 * @date 19-9-30 下午4:29
 */
@RestController
public class UserController {

    private final UserMapper userMapper;

    private SnowFlake snowFlake = new SnowFlake(3, 5);

    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/insert")
    public void insert(@RequestParam("username")String username,
                      @RequestParam("password")String password){
        User user = new User();
        user.setId(snowFlake.createId());
        user.setUsername(username);
        user.setPassword(password);
        user.setCreateTime(new Date().toString());
        user.setLastLoginTime(new Date().toString());
        userMapper.insert(user);
    }

    @GetMapping("/get")
    public User get(@RequestParam("username")String username){
        return userMapper.get(username);
    }
}
