package com.syk.ssh.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("indexAction")
@Scope("prototype")
@Result(name="index", location="/vm/login.vm", type="velocity")
public class IndexAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Action(value="/")
	public String execute() {
		return "index";
	}
}
