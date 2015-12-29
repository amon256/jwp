/**
 * 
 */
package com.jwp.webbase.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jwp.core.persistence.IService;
import com.jwp.core.persistence.SimplePrepareQueryhandler;
import com.jwp.core.utils.CollectionUtils;
import com.jwp.core.utils.ResponseObject;
import com.jwp.core.validate.LengRangeValidationRule;
import com.jwp.core.validate.RegexpValidationRule;
import com.jwp.core.validate.RequiredValidationRule;
import com.jwp.core.validate.Validation;
import com.jwp.core.validate.ValidationResult;
import com.jwp.core.validate.ValidationRule;
import com.jwp.core.validate.ValidationUtil;
import com.jwp.webbase.SimpleController;
import com.jwp.webbase.entitys.AdminUser;
import com.jwp.webbase.entitys.AdminUser_;
import com.jwp.webbase.service.AdminUserService;

/**
 * @author fengmengyue
 *
 */
@Controller
@RequestMapping(value="adminuser/*")
public class AdminUserController extends SimpleController<AdminUser> {

	@Autowired
	private AdminUserService service;
	
	@Override
	protected IService<AdminUser> getIService() {
		return service;
	}
	
	@Override
	protected ModelAndView getListView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("adminuser/listAdminUser");
	}

	@Override
	protected Predicate[] getListDataConditions(CriteriaBuilder cb,Root<AdminUser> root, AdminUser param,
			HttpServletRequest request, HttpServletResponse response) {
		List<Predicate> predicates = new LinkedList<Predicate>();
		if(StringUtils.isNotEmpty(param.getName())){
			predicates.add(cb.like(root.get(AdminUser_.name), "%"+param.getName()+"%"));
		}
		if(StringUtils.isNotEmpty(param.getAccount())){
			predicates.add(cb.like(root.get(AdminUser_.account), "%"+param.getAccount()+"%"));
		}
		if(StringUtils.isNotEmpty(param.getMobile())){
			predicates.add(cb.like(root.get(AdminUser_.mobile), "%"+param.getMobile()+"%"));
		}
		if(StringUtils.isNotEmpty(param.getEmail())){
			predicates.add(cb.like(root.get(AdminUser_.email), "%"+param.getEmail()+"%"));
		}
		if(StringUtils.isNotEmpty(param.getKeyword())){
			predicates.add(cb.or(
					cb.like(root.get(AdminUser_.name)   , "%"+param.getKeyword()+"%"),
					cb.like(root.get(AdminUser_.account), "%"+param.getKeyword()+"%"),
					cb.like(root.get(AdminUser_.mobile) , "%"+param.getKeyword()+"%"),
					cb.like(root.get(AdminUser_.email)   , "%"+param.getKeyword()+"%"))
			);
		}
		return predicates.toArray(new Predicate[]{});
	}

	@Override
	protected ModelAndView getAddView(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return new ModelAndView("adminuser/addAdminUser");
	}

	@Override
	protected boolean validateAdd(AdminUser entity, ResponseObject rb,
			HttpServletRequest request, HttpServletResponse response) {
		//必填和格式校验
		List<ValidationResult> results = ValidationUtil.validate(entity,
				new Validation("name", "昵称", 
						new RequiredValidationRule(),
						new LengRangeValidationRule(2, 10)
				),
				new Validation("account", "账号", 
						new RequiredValidationRule(),
						new LengRangeValidationRule(4, 20),
						new RegexpValidationRule("[_@$.0-9a-zA-Z]+"),
						new ExistsUserValidationRule(entity)
				),
				new Validation("password", "密码", 
						new RequiredValidationRule(),
						new LengRangeValidationRule(4, 20)
				),
				new Validation("mobile", "手机", 
						new RegexpValidationRule("1[0-9]{10}"),
						new ExistsUserValidationRule(entity)
				),
				new Validation("email", "邮箱", 
						new RegexpValidationRule("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"),
						new ExistsUserValidationRule(entity)
				)
		);
		if(results != null && !results.isEmpty()){
			rb.put("validateErrors", results);
			return false;
		}
		return true;
	}
	
	@Override
	protected ModelAndView getEditView(AdminUser entity,
			HttpServletRequest request, HttpServletResponse response,
			ModelMap model) {
		return new ModelAndView("adminuser/editAdminUser");
	}

	@Override
	protected boolean validateEdit(AdminUser entity, ResponseObject rb,
			HttpServletRequest request, HttpServletResponse response) {
		List<ValidationResult> results = ValidationUtil.validate(entity,
				new Validation("name", "昵称", 
						new RequiredValidationRule(),
						new LengRangeValidationRule(2, 10)
				),
				new Validation("mobile", "手机", 
						new RegexpValidationRule("1[0-9]{10}"),
						new ExistsUserValidationRule(entity)
				),
				new Validation("email", "邮箱", 
						new RegexpValidationRule("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"),
						new ExistsUserValidationRule(entity)
				)
		);
		if(results != null && !results.isEmpty()){
			rb.put("validateErrors", results);
			return false;
		}
		return true;
	}

	@Override
	protected Collection<String> getSaveEditFields() {
		return CollectionUtils.createSet(String.class, "mobile","email","roles","headPhoto");
	}

	@Override
	protected boolean validateDelete(AdminUser entity, ResponseObject rb,
			HttpServletRequest request, HttpServletResponse response) {
		return true;
	}

	
	private class ExistsUserValidationRule implements ValidationRule{
		private AdminUser self;
		ExistsUserValidationRule(AdminUser self){
			this.self = self;
		}
		@Override
		public String type() {
			return "exists";
		}
		@Override
		public boolean validate(Object bean, final Object value) {
			if(value == null){
				return true;
			}
			if(value instanceof String){
				if(StringUtils.isEmpty(value.toString().trim())){
					return true;
				}
			}
			List<AdminUser> existsList = service.find(new SimplePrepareQueryhandler<AdminUser>((AdminUser) bean){
				@Override
				protected Predicate[] getWhereCondition(CriteriaBuilder cb,
						AdminUser entity, Root<AdminUser> root) {
					return new Predicate[]{cb.or(
								cb.equal(root.get(AdminUser_.account), value),
								cb.equal(root.get(AdminUser_.mobile), value),
								cb.equal(root.get(AdminUser_.email), value)
							)};
				}
			});
			if(self != null && StringUtils.isNotEmpty(self.getId())){
				if(existsList != null && !existsList.isEmpty()){
					for(AdminUser au : existsList){
						if(StringUtils.equals(self.getId(), au.getId())){
							return true;
						}
					}
				}
			}
			return existsList == null || existsList.isEmpty();
		}
		@Override
		public String getErrorMessage() {
			return "己被注册";
		}
	}
}
