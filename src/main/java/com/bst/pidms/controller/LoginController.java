package com.bst.pidms.controller;

import com.bst.pidms.entity.User;
import com.bst.pidms.service.CatalogService;
import com.bst.pidms.service.UserService;
import com.bst.pidms.utils.MD5;
import com.bst.pidms.utils.SessionUtil;
import org.apache.catalina.manager.util.SessionUtils;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: BST
 * @Date: 2019/4/26 16:12
 */
@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    CatalogService catalogService;

    @RequestMapping("validation")
    public Map<String, Object> validUser(@RequestParam("username") String name, @RequestParam("password") String pwd, HttpSession httpSession, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        User userByName = userService.getUserByName(name);
        if (userByName == null) {
            map.put("success", false);
            map.put("errMsg", "用户不存在!");
        }
        if (!userByName.getPassword().equals(MD5.md5(pwd))) {
            map.put("success", false);
            map.put("errMsg", "密码错误!");
        } else {
            map.put("success", true);
            map.put("root", catalogService.getRootId(userByName.getId()));
        }
        SessionUtil.getInstance().setSessionMap(userByName);
        //设置Session过期时间
        SessionUtil.getInstance().setSessionTimeout();

        return map;
    }

}
