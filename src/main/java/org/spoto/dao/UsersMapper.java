package org.spoto.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.spoto.model.Users;

import java.util.List;

public interface UsersMapper {
    List<Users> getList(RowBounds rb, @Param("username") String username, @Param("password") String password);

    Integer listCount(@Param("username") String username, @Param("password") String password);

    Integer updateUser(@Param("user") Users user);

    Integer addUser(@Param("user") Users user);

    //批量删除，参数为数组，需要在xml里面for循环遍历数组
    Integer deleteUsers(@Param("ids") List<Integer> ids);
}
