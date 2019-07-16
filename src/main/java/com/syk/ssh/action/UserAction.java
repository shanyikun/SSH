package com.syk.ssh.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.util.ValueStack;
import com.syk.ssh.entity.UserEntity;
import com.syk.ssh.formBean.UserInfo;
import com.syk.ssh.service.UserService;

@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements ModelDriven<UserInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "userService")
	private UserService userService;
	private UserInfo userInfo = new UserInfo(){};
	private Map<String, Object> result;
	
	public String execute() {
		return SUCCESS;
	}
	
	public String getLoginPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String method = request.getMethod();
		if(method.equals("GET")) {
			return "loginPage";
		}
		else {
			String name = userInfo.getName();
			String password = userInfo.getPassword();
			result = userService.login(name, password);
			if(result.get("statCode").equals("0")) {
				HttpSession session = request.getSession();
				session.setAttribute("userEntity", result.get("userEntity"));
				return "loginStat";
			}
			else {
				return "loginStat";
			}
		}
	}
	
	public String getRegisterPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String method = request.getMethod();
		if(method.equals("GET")) {
			return "registerPage";
		}
		else {
			String name = userInfo.getName();
			String password = userInfo.getPassword();
			result = userService.register(name, password);
			if(result.get("statCode").equals("0")) {
				HttpSession session = request.getSession();
				session.setAttribute("userEntity", result.get("userEntity"));
			}
			return "registerStat";
		}
	}
	
	public String getUserPage() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if(session!=null) {
			ValueStack valueStack = (ValueStack) request.getAttribute("struts.valueStack");
			valueStack.set("userInfo", session.getAttribute("userEntity"));
			return "userPage";
		}
		else {
			return "loginPage";
		}
	}
	
	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
	
	public Map<String, Object> getResult(){
		return this.result;
	}
	
	public UserInfo getModel() {
		// TODO Auto-generated method stub
		return userInfo;
	}
}
