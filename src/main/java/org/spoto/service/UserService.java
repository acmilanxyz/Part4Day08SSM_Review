package org.spoto.service;

import org.spoto.model.Users;
import org.spoto.utils.TableData;

import java.util.List;

public interface UserService {
    public TableData<Users> getList(Integer index, String username, String password);

    public boolean saveUserService(Users user);

    //批量删除
    boolean deleteUsersService(List<Integer> ids);
}
