package controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author MayerFang
 * @file LoginController
 * @Description
 * @date 2018/9/29
 */
public class LoginController extends AbstractController {
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse
    ) throws Exception {
        String userName=httpServletRequest.getParameter("username");
        ModelAndView mav=new ModelAndView("WEB-INF/welcome");
        mav.addObject("username",userName);
        return mav;
    }
}
