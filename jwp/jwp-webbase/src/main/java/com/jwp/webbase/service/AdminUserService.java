/**
 * 
 */
package com.jwp.webbase.service;

import javax.transaction.Transactional;

import com.jwp.core.persistence.IService;
import com.jwp.webbase.entitys.AdminUser;

/**
 * @author fengmengyue
 *
 */
@Transactional
public interface AdminUserService extends IService<AdminUser> {

}
