package controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MayerFang
 * @file
 * @Description
 * @date 2018/9/29
 */

@Controller
public class RestController {

    public RestController(){}

    @RequestMapping(value = "/login/{userName}",method = RequestMethod.GET)
    public ModelAndView myMethod(HttpServletRequest request, HttpServletResponse response
    , @PathVariable("userName")String username, ModelMap modelMap){

        modelMap.put("userName",username);
        return new ModelAndView("/login/hello",modelMap);
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String registPost(){
        return "WEB-INF/welcome";
    }
}
