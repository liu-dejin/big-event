package org.bigevent.service;

import org.bigevent.model.User;

public interface UserService {
    User selectUserName(String username);

    void register(String username, String password);

    int updateUser(User user);

    User selectUserById(int userId);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newPwd);
}
