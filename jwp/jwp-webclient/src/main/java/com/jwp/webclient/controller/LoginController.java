/**
 * 
 */
package com.jwp.webclient.controller;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jwp.core.persistence.SimplePrepareQueryhandler;
import com.jwp.core.utils.SecurityUtil;
import com.jwp.webbase.context.WebContext;
import com.jwp.webbase.entitys.AdminUser;
import com.jwp.webbase.entitys.AdminUser_;
import com.jwp.webbase.service.AdminUserService;

/**
 * @author fengmengyue
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private AdminUserService service;

	@RequestMapping(value="login")
	public ModelAndView login(String uname,String upassword,boolean remember,ModelMap model,HttpServletRequest request,HttpServletResponse response){
		if(StringUtils.isNotEmpty(uname)){
			model.put("uname", uname);
			if(loginProcess(uname, upassword, model)){
				writeCookie(uname, remember, response);
				return new ModelAndView("redirect:index");
			}
		}else{
			
		}
		return new ModelAndView("login",model);
	}
	
	private void writeCookie(String uname,boolean remember,HttpServletResponse response){
		Cookie cookie = new Cookie("uname", uname);
		cookie.setDomain("/");
		if(remember){
			cookie.setMaxAge(7*24*60*60);
		}else{
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
	}
	
	private boolean loginProcess(final String uname,final String upassword,ModelMap model){
		if(StringUtils.isEmpty(upassword)){
			model.put("errorMsg", "密码不能为空");
			return false;
		}
		List<AdminUser> adminUsers = service.find(new SimplePrepareQueryhandler<AdminUser>(new AdminUser()){
			@Override
			protected Predicate[] getWhereCondition(CriteriaBuilder cb, AdminUser entity, Root<AdminUser> root) {
				List<Predicate> predicates = new LinkedList<Predicate>();
				predicates.add(cb.or(
						cb.equal(root.get(AdminUser_.account), uname),
						cb.equal(root.get(AdminUser_.mobile), uname),
						cb.equal(root.get(AdminUser_.email), uname)
				));
				predicates.add(cb.equal(root.get(AdminUser_.password), SecurityUtil.encryptSHA(upassword)));
				return predicates.toArray(new Predicate[]{});
			}
		});
		if(adminUsers != null && !adminUsers.isEmpty()){
			WebContext.login(adminUsers.get(0));
			return true;
		}else{
			model.put("errorMsg", "用户名或密码错误");
		}
		return false;
	}
}
