package com.javen.controller;

import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.javen.service.IAdminService;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Controller
@RequestMapping("/Admin")
public class AdminController {
	private static Logger log=LoggerFactory.getLogger(AdminController.class);
	@Resource
	private IAdminService adminService;

	//后台登录
	@RequestMapping(value = "/Login")
  	public ModelAndView login() {
  		ModelAndView mv = new ModelAndView();
  		mv.setViewName("Admin/NewFile");
  		System.out.println("--login--");
  		return mv;
  	}
    
    //Validate登录验证
    @RequestMapping(value = "Validate", method = RequestMethod.POST)
    public String LoginUser(String userName, String passWord, Model model) {
    	System.out.println("--"+userName+"--"+passWord+"--");
        Subject subject = SecurityUtils.getSubject();
        //SecurityUtils.getSubject()是每个请求创建一个Subject, 
    	 //并保存到ThreadContext的resources（ThreadLocal<Map<Object, Object>>）变量中，
    	 //也就是一个http请求一个subject,并绑定到当前线程。 
        
        UsernamePasswordToken token = new UsernamePasswordToken(userName, passWord);
        //根据userName与passWord获取token
        
        try {
            subject.login(token);
            return "redirect:/Admin/Panel";//登录验证成功，跳转用户管理页面
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            model.addAttribute("message", "用户名错误！");
            return "Admin/NewFile";
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            model.addAttribute("message", "密码错误！");
            return "Admin/NewFile";
        }
    }
    
	//后台首页 - Panel（需要权限）
  	@RequestMapping(value = "/Panel")
  	public ModelAndView Panel() {		
  		ModelAndView mv = new ModelAndView();
  		mv.setViewName("Admin/Panel");
  		return mv;
  	}

    @RequiresPermissions("user:query")
    @RequestMapping(value = {"/Permissions"})
    public ModelAndView TestPermissions(ModelMap model) {
        ModelAndView mav = new ModelAndView("Admin/testPermissions");
        mav.addObject("message", "testPermissions OK!");
        return mav;
    }

    @RequiresRoles("admin")
    @RequestMapping("/AdminRoles")
    public ModelAndView AdminRoles(ModelMap model) {
        ModelAndView mav = new ModelAndView("Admin/testAdminRoles");
        mav.addObject("message", "testAdminRoles OK!");
        return mav;
    }

}