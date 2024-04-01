package org.bigevent.service.impl;

import org.bigevent.mapper.UserMapper;
import org.bigevent.model.User;
import org.bigevent.service.UserService;
import org.bigevent.utils.Md5Util;
import org.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectUserName(String username) {
        return userMapper.selectUserName(username);
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        String md5String = Md5Util.getMD5String(password);
        user.setUsername(username);
        user.setPassword(md5String);
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(User user) {
        user.setUpdateTime(LocalDateTime.now());
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public User selectUserById(int userId) {
        return userMapper.selectByPrimaryKey((long) userId);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = new User();
        user.setId((Integer) map.get("userId"));
        user.setUserPic(avatarUrl);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        User user = new User();
        user.setId((Integer) map.get("userId"));
        String md5String = Md5Util.getMD5String(newPwd);
        user.setPassword(md5String);
        user.setUpdateTime(LocalDateTime.now());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
