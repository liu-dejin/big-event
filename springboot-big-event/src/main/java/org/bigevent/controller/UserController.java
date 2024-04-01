package org.bigevent.controller;

import jakarta.validation.constraints.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.bigevent.model.User;
import org.bigevent.service.UserService;
import org.bigevent.utils.JwtUtil;
import org.bigevent.utils.Md5Util;
import org.bigevent.utils.Result;
import org.bigevent.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.selectUserName(username);
        if (user != null) return Result.error("用户名已存在");
        userService.register(username, password);
        return Result.success();
    }

    /**
     * 用户登录
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.selectUserName(username);

        if (user == null) return Result.error("用户名不存在");
        if (!user.getPassword().equals(Md5Util.getMD5String(password))) return Result.error("密码错误");

        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        map.put("username", user.getUsername());
        String token = JwtUtil.genToken(map);

        return Result.success("登录成功", token);
    }

    /**
     * 获取用户详细信息
     *
     * @param token
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(@RequestHeader(name = "Authorization") String token) {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.selectUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户基本信息
     *
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result<User> update(@RequestBody User user) {
        if (user.getPassword() != null && user.getUserPic() != null) return Result.error("密码和头像不能修改");
        userService.updateUser(user);
        User user1 = userService.selectUserById(user.getId());
        return Result.success(user1);
    }

    /**
     * 更新用户头像
     *
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 更新用户密码
     * @param params
     * @return
     */
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        if (!StringUtils.hasLength(params.get("old_pwd")) || !StringUtils.hasLength(params.get("new_pwd")) || !StringUtils.hasLength(params.get("re_pwd")))
            return Result.error("缺少必要参数");
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.selectUserName(username);
        if (!Md5Util.getMD5String(params.get("old_pwd")).equals(user.getPassword())) return Result.error("原密码错误");
        if (Md5Util.getMD5String(params.get("old_pwd")).equals(user.getPassword())) return Result.error("新旧密码不能相同");
        if (!params.get("new_pwd").equals(params.get("re_pwd"))) return Result.error("两次输入密码不一致");
        userService.updatePwd(params.get("new_pwd"));
        return Result.success();
    }
}
