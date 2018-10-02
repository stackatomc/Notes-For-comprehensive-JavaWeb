package com.mvc;

import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author MayerFang
 * @file UserController
 * @Description
 * @date 2018/10/2
 */
@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/displayUsers";
    }

    @RequestMapping(name = "/displayUser")
    public String getAll(HttpServletRequest request, HttpServletResponse response){

        List<User> user =userService.getAll();
        request.setAttribute("users",user);
        return "/displayUsers";

    }

    @RequestMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable String id,HttpServletResponse response){
        userService.deleteUser(id);
        return "redirect:/displayUsers";
    }

    @RequestMapping("/getUser/{id}")
    public String getUser(@PathVariable String id,HttpServletRequest request){
        User user = userService.getUser(id);
        request.setAttribute("user",user);
        return "/editUser";
    }

    @RequestMapping("/getUser/{id}")
    public String modifyUser(User user){
        userService.modifyUser(user);
        return "redirect:/displayUsers";
    }
}
