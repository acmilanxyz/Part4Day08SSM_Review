package org.spoto.controller;

import com.alibaba.fastjson.JSONObject;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.utils.StringUtils;
import org.spoto.utils.TableData;
import org.spoto.utils.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("user-list.ajax")
    @ResponseBody
    public JSONObject getUserList(String index, String username, String password) {
        // 初始化当前页面索引
        Integer indexInt = 1;
        // 校验参数
        if (StringUtils.isNotEmpty(index)) {
            indexInt = Integer.parseInt(index);
        } else {
            System.out.println("非法操作！");
            return null;
        }
        // 调用Service
        TableData<Users> td = userService.getList(indexInt, username, password);
        // 结果处理
        return WebUtils.returnData(td);
    }

    //保存新建及更新
    @RequestMapping("user-save.ajax")
    @ResponseBody
    public JSONObject save(Users user) {
        boolean save = userService.saveUserService(user);
        JSONObject data = new JSONObject();
        data.put("save", save);
        return data;
    }

    //删除与批量删除的整合
    @RequestMapping("user-delete.ajax")
    @ResponseBody
    public JSONObject delete(String ids) {
        JSONObject data = new JSONObject();
        if (StringUtils.isEmpty(ids)) {
            data.put("delete", false);
            return data;
        }

        //将Str字符串转换为String数组，再转换为int数组
        String[] idsListStr = ids.split(",");
        List<Integer> idsList = new ArrayList<>();
        for (String idStr : idsListStr) {
            int id = Integer.parseInt(idStr);
            idsList.add(id);
        }
        boolean delete = userService.deleteUsersService(idsList);
        data.put("delete", delete);
        return data;
    }

}
