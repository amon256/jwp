/**
 * 
 */
package com.jwp.webclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author fengmengyue
 *
 */
@Controller
public class IndexController {

	@RequestMapping(value="index")
	public ModelAndView index(ModelMap model){
		return new ModelAndView("index");
	}
}
