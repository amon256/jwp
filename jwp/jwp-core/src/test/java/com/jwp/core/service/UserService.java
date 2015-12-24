/**
 * 
 */
package com.jwp.core.service;

import javax.transaction.Transactional;

import com.jwp.core.entitys.User;
import com.jwp.core.persistence.IService;

/**
 * @author fengmengyue
 *
 */
@Transactional
public interface UserService extends IService<User> {

}
