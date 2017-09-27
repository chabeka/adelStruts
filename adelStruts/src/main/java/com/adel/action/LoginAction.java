package com.adel.action;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.adel.model.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	
	final Logger log = LogManager.getLogger(LoginAction.class);
	User userBean;
	
    @Override
    public void validate() {
        if (userBean != null && userBean.getLogin() != null && userBean.getLogin().length() == 0) {
            this.addFieldError("userBean.login", "Name is required");
        }
        if (userBean != null && userBean.getLogin() != null && userBean.getPassword().length() == 0) {
            this.addFieldError("userBean.password", "Password is required");
        }
    }
 
    @Override
    public String execute() {
        if (userBean != null && userBean.getPassword() != null && userBean.getPassword().length() != 0 && userBean.getLogin() != null &&  userBean.getLogin().length() != 0) {
            return SUCCESS;
        } else {
            this.addActionError("Invalid username and password");
        }
        return INPUT;
    }

	public User getUserBean() {
		return userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}
    
}
