/**
 * 
 */
package com.jwp.core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jwp.core.entitys.User;
import com.jwp.core.entitys.User_;
import com.jwp.core.persistence.SimplePrepareQueryhandler;
import com.jwp.core.service.UserService;
import com.jwp.core.utils.Pagination;

/**
 * @author fengmengyue
 *
 */
public class TestPersistence extends TestBase {

	@Autowired
	private UserService service;
	
	@Test
	public void insert(){
		Pagination<User> pagination = new Pagination<User>(10, 1);
		User user = new User();
		user.setNickName("admin");
		user.setUserName("admin");
		user.setSort_("nickName");
		user.setOrder_("desc");
		service.findPagination(pagination, new SimplePrepareQueryhandler<User>(user){
			@Override
			protected Predicate[] getWhereCondition(CriteriaBuilder cb,User entity,Root<User> root) {
				List<Predicate> predicates = new LinkedList<Predicate>();
				if(StringUtils.isNotEmpty(entity.getNickName())){
					predicates.add(cb.like(root.get(User_.nickName), "%"+entity.getNickName()+"%"));
				}
				if(StringUtils.isNotEmpty(entity.getUserName())){
					predicates.add(cb.like(root.get(User_.userName), "%" + entity.getUserName() + "%"));
				}
				return predicates.toArray(new Predicate[]{});
			}
		});
		Collection<User> users = pagination.getDatas(); 
		for(User u : users){
			System.out.println(u.getUserName());
		}
	}
	
}
