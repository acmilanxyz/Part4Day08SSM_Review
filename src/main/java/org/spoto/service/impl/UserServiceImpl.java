package org.spoto.service.impl;

import org.spoto.dao.UsersMapper;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.utils.PageUtils;
import org.spoto.utils.TableData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Value("${pageSize}")
    private int pageSize;

    @Resource
    private UsersMapper mapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TableData<Users> getList(Integer index, String username, String password) {
        //构造结果数据
        TableData<Users> td = new TableData<>();
        td.setPageIndex(index);
        td.setPageSize(pageSize);

        //查询数据条数
        Integer count = mapper.listCount(username, password);
        td.setDataCount(count);

        if (count <= 0) {
            return td;
        }

        //查询数据列表
        List<Users> list = mapper.getList(PageUtils.getRowBounds(index, pageSize), username, password);
        td.setDataList(list);
        return td;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveUserService(Users user) {
        Integer save = 0;
        if (user.getId() > 0) {
            save = mapper.updateUser(user);
        } else {
            save = mapper.addUser(user);
        }
        if (save > 0) {
            return true;
        } else {
            return false;
        }
    }

    //批量删除
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteUsersService(List<Integer> ids) {
        Integer delete = mapper.deleteUsers(ids);
        if (delete > 0) {
            return true;
        } else {
            return false;
        }
    }
}
