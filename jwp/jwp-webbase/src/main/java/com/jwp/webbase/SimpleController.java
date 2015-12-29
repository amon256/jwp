/**
 * 
 */
package com.jwp.webbase;

import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jwp.core.entitys.CoreEntity;
import com.jwp.core.persistence.IService;
import com.jwp.core.persistence.SimplePrepareQueryhandler;
import com.jwp.core.utils.Pagination;
import com.jwp.core.utils.ResponseObject;

/**
 * 普通数据controller
 * @author fengmengyue
 *
 */
public abstract class SimpleController<T extends CoreEntity> extends BaseController {

	/**
	 * 主数据iservice
	 * @return
	 */
	protected abstract IService<T> getIService();
	
	
	/**
	 * 列表页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response,ModelMap model){
		return getListView(request, response, model);
	}
	
	/**
	 * 列表view
	 * @return
	 */
	protected abstract ModelAndView getListView(HttpServletRequest request,HttpServletResponse response,ModelMap model);
	
	/**
	 * 列表数据查询
	 * @param pagination
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="listData")
	@ResponseBody
	public Pagination<T> listData(Pagination<T> pagination,final T param,final HttpServletRequest request,final HttpServletResponse response){
		IService<T> service = getIService();
		service.findPagination(pagination, new SimplePrepareQueryhandler<T>(param){
			@Override
			protected Predicate[] getWhereCondition(CriteriaBuilder cb,T entity, Root<T> root) {
				return getListDataConditions(cb,root,param, request, response);
			}
		});
		return pagination;
	}
	
	/**
	 * 获取列表数据过滤条件
	 * @param cb 
	 * @param root 
	 * @param param
	 * @param request
	 * @param response
	 * @return
	 */
	protected abstract Predicate[] getListDataConditions(CriteriaBuilder cb, Root<T> root, T param,HttpServletRequest request,HttpServletResponse response);
	
	
	@RequestMapping(value="add")
	public ModelAndView add(ModelMap model,final HttpServletRequest request,final HttpServletResponse response){
		return getAddView(request, response, model);
	}
	
	/**
	 * 新增view
	 * @return
	 */
	protected abstract ModelAndView getAddView(HttpServletRequest request,HttpServletResponse response,ModelMap model);
	
	@RequestMapping(value="saveAdd")
	@ResponseBody
	public ResponseObject saveAdd(T entity,final HttpServletRequest request,final HttpServletResponse response){
		ResponseObject rb = ResponseObject.newInstance().fail();
		if(validateAdd(entity, rb, request, response)){
			getIService().insert(entity);
			rb.success();
		}else{
			rb.fail();
		}
		return rb;
	}
	
	/**
	 * 新增校验
	 * @param entity
	 * @param rb
	 * @param request
	 * @param response
	 * @return
	 */
	protected abstract boolean validateAdd(T entity,ResponseObject rb,HttpServletRequest request,HttpServletResponse response);
	
	@RequestMapping(value="edit")
	public ModelAndView edit(@RequestParam(value="id",required=true)String id,ModelMap model,final HttpServletRequest request,final HttpServletResponse response){
		T entity = getIService().findEntity(id);
		return getEditView(entity,request, response, model);
	}
	
	/**
	 * 新增view
	 * @param entity 
	 * @return
	 */
	protected abstract ModelAndView getEditView(T entity, HttpServletRequest request,HttpServletResponse response,ModelMap model);
	
	@RequestMapping(value="saveEdit")
	@ResponseBody
	public ResponseObject saveEdit(T entity,final HttpServletRequest request,final HttpServletResponse response){
		ResponseObject rb = ResponseObject.newInstance().fail();
		if(validateEdit(entity, rb, request, response)){
			getIService().update(entity, getSaveEditFields());
			rb.success();
		}else{
			rb.fail();
		}
		return rb;
	}
	
	/**
	 * 编辑保存校验
	 * @param entity
	 * @param rb
	 * @param request
	 * @param response
	 * @return
	 */
	protected abstract boolean validateEdit(T entity,ResponseObject rb,HttpServletRequest request,HttpServletResponse response);
	
	/**
	 * 获取编辑保存的字段
	 * @return
	 */
	protected abstract Collection<String> getSaveEditFields();
	
	@RequestMapping(value="delete")
	@ResponseBody
	public ResponseObject delete(@RequestParam(value="id",required=true)String id,final HttpServletRequest request,final HttpServletResponse response){
		ResponseObject rb = ResponseObject.newInstance().fail();
		T entity = getIService().findEntity(id);
		if(entity == null){
			rb.fail();
			rb.setMsg("数据不存在");
		}else{
			if(validateDelete(entity, rb, request, response)){
				getIService().delete(entity);
				rb.success();
			}
		}
		return rb;
	}
	
	/**
	 * 校验是否删除
	 * @param entity
	 * @param rb
	 * @param request
	 * @param response
	 * @return
	 */
	protected abstract boolean validateDelete(T entity,ResponseObject rb,final HttpServletRequest request,final HttpServletResponse response);
}
