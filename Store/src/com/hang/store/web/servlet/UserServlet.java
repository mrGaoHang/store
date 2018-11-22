package com.hang.store.web.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hang.store.domain.User;
import com.hang.store.service.impl.UserServiceImpl;
import com.hang.store.service.UserService;
import com.hang.store.utils.MailUtils;
import com.hang.store.utils.MyBeanUtils;
import com.hang.store.utils.UUIDUtils;
import com.hang.store.web.base.BaseServlet;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
		
	}
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
		
	}

	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		//	1.使用BeanUtils处理数据并封装到user对象里
		MyBeanUtils.populate(user, request.getParameterMap());
		//调用Service业务层方法调用注册功能
		UserService userService = new UserServiceImpl();
		user.setUid(UUIDUtils.getId());
		user.setCode(UUIDUtils.getCode());
		try {
			userService.userRegist(user);
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "注册成功，请前往邮箱激活！");
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "注册失败，请重新注册！");
		}
		return "/jsp/info.jsp";
		
	}
	public String active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//完成用户激活
		//获取点击连接后的code
		String code = request.getParameter("code");
		//调用service方法的激活功能
		UserService userService = new UserServiceImpl();
		boolean flag=false;
		try {
			flag = userService.userActive(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==true) {
			//用户激活成功，向requet放入提示信息，转发到登录页面
			request.setAttribute("msg", "用户激活成功，请登录！");
			return "/jsp/login.jsp";
		}else {
			//激活失败，向request放入提示消息，并转发到提示页面
			request.setAttribute("msg", "用户激活失败，请重新激活！");
			return "/jsp/info.jsp";
		}
		
	}

}
