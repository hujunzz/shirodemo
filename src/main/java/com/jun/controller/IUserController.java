package com.jun.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by jun on 2018/4/17.
 */
public class IUserController {

    @Controller
    @RequestMapping("/main")
    public class IUserController {


        private static final String main_page="main";

        private static final String index_page="main";

        @Resource
        private IUserService iUserService;

        @Autowired
        private HttpSession session;

        @Autowired
        private SecurityManager securityManager;


        @RequestMapping("/toMainPage.do")
        public ModelAndView toMainPage(String loginUser, String password, HttpServletRequest request){

            ModelAndView mv = new ModelAndView();
            mv.setViewName("index");
            UsernamePasswordToken token = new UsernamePasswordToken(loginUser,password);
            //记住我
//      token.setRememberMe(true);
            Subject subject = SecurityUtils.getSubject();

            String userName = (String)subject.getSession().getAttribute("userName");
            if(!StringUtils.isBlank(userName)){
                mv.setViewName(main_page);
                return mv;
            }

            try {
                subject.login(token);
            } catch (IncorrectCredentialsException ice) {
                // 捕获密码错误异常
                mv.addObject("message", "密码错误!");
                return mv;
            } catch (UnknownAccountException uae) {
                // 捕获未知用户名异常
                mv.addObject("message", "登录名错误!");
                return mv;
            } catch (ExcessiveAttemptsException eae) {
                // 捕获错误登录过多的异常
                mv.addObject("message", "登录失败超过五次,暂时无法登录!");
                return mv;
            }

            IUser user = iUserService.findByUsername(loginUser);
            request.getSession().setAttribute("userName", user.getUserName());

            logger.debug("进入跳转的方法");
            mv.setViewName(main_page);
            return mv;
        }

        /**
         * 登出
         */
        @RequestMapping("/logout.do")
        public ModelAndView logout(){
            Subject subject = SecurityUtils.getSubject();
            if(subject.isAuthenticated()){
                subject.logout();
            }
            ModelAndView mv = new ModelAndView();
            mv.setViewName("index");
            return mv;
        }

    }

}
