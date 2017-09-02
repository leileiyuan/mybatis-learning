package com.belongtou.mybatis.mapper;

import com.belongtou.mybatis.model.User;

public interface UserMapper {
    User selectUserById(int userId);
}
