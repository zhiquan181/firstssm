package com.javen.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import com.javen.model.User;
import com.javen.service.IUserService;
  
@Controller  
@RequestMapping("/User")
public class UserController {
    private static Logger log=LoggerFactory.getLogger(UserController.class);
    @Resource
    private IUserService userService;
     
    //��ȡ�����û��б�
    @RequiresPermissions("user:query")
	@RequestMapping(value = "/UserList")
	public ModelAndView userlist(HttpServletRequest request) {
 		ModelAndView mv = new ModelAndView();
 		List<User> userlist = this.userService.getUserList();
 		request.setAttribute("userlist",userlist);
 		mv.setViewName("User/UserList");
 		return mv;
 	}
	
	//��ȡ�û�������Ϣ
    @RequiresPermissions("user:query")
	@RequestMapping(value = "/UserDetail")
	public ModelAndView userdetail(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("id"));
		System.out.println(userid);
 		ModelAndView mv = new ModelAndView();
 		User user = this.userService.getUserDetailById(userid);
 		System.out.println(user);
 		request.setAttribute("user",user);
 		mv.setViewName("User/UserDetail");
 		return mv;
 	}
	
	//go to �����û�ҳ��
    @RequiresPermissions("user:insert")
	@RequestMapping(value = "/ToUserInsert")
	public ModelAndView touserinsert() {		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("User/UserInsert");
		return mv;
	}
	
	//�����û�
    @RequiresPermissions("user:insert")
	@RequestMapping(value = "/UserInsert")
	public ModelAndView userinsert(HttpServletRequest request) {
 		ModelAndView mv = new ModelAndView();
 		String account = String.valueOf(request.getParameter("account"));
		String password = String.valueOf(request.getParameter("passwd"));
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(account+"--"+password+"--"+age);
 		User user=null;
 		user = new User();
 		user.setUsername(account);
 		user.setPassword(password);
        user.setAge(age);
 		int status = this.userService.UserInsert(user);
 		System.out.println(status);
 		mv.setViewName("redirect:UserList");
 		return mv;
 	}
	
	//go to �޸��û�ҳ��
    @RequiresPermissions("user:update")
	@RequestMapping(value = "/ToUserUpdate")
	public ModelAndView touserupdate(HttpServletRequest request) {
		int userid = Integer.parseInt(request.getParameter("id"));
		System.out.println(userid);
 		ModelAndView mv = new ModelAndView();
 		User user = this.userService.getUserDetailById(userid);
 		System.out.println(user);
 		request.setAttribute("user",user);
 		mv.setViewName("User/UserUpdate");
 		return mv;
 	}
	
	//�޸��û�
    @RequiresPermissions("user:update")
	@RequestMapping(value = "/UserUpdate")
	public ModelAndView userupdate(HttpServletRequest request) {
 		ModelAndView mv = new ModelAndView();
 		int id = Integer.parseInt(request.getParameter("id"));
 		String account = String.valueOf(request.getParameter("account"));
		String password = String.valueOf(request.getParameter("passwd"));
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(id+"--"+account+"--"+password+"--"+age);
 		User user=null;
 		user = new User();
 		user.setId(id);
 		user.setUsername(account);
 		user.setPassword(password);
        user.setAge(age);
 		int status = this.userService.UserUpdate(user);
 		System.out.println(status);
 		mv.setViewName("redirect:UserList");
 		return mv;
 	}
	
	//ɾ���û�
    @RequiresPermissions("user:delete")
	@RequestMapping(value = "/UserDelete")
	public ModelAndView userdelete(HttpServletRequest request) {
 		ModelAndView mv = new ModelAndView();
 		int userid = Integer.parseInt(request.getParameter("id"));
 		int status = this.userService.UserDelete(userid);
 		System.out.println(status);
 		mv.setViewName("redirect:UserList");
 		return mv;
	}
	
	//��˷���ǰ��json - ���ݽӿ�
    //�˽ӿ���Ҫadmin��ɫ��¼
	//http://localhost:8081/firstssm/admin/tojsonobj
    @RequiresRoles("admin")
	@RequestMapping(value = "/ToJsonObj")
    public void tojsonobj(HttpServletResponse response) {
    	//��������Ϸ���MAP����
    	String msg = "ok";
    	int arr[] = new int[3];
    	
    	for(int i = 0; i < arr.length; i++) {
			arr[i] = i*10000;
		}
    	
        Map map = new HashMap();
        map.put("msg",msg);
        map.put("arr",arr);
        String data = JSON.toJSONString(map);
        try {
			response.getWriter().write(data);
			//{"arr":[0,10000,20000],"msg":"ok"} //ǰ�˽�data�ַ���ת��json��ʽ����ʹ��
		} catch (IOException e) {}
    }
    
}