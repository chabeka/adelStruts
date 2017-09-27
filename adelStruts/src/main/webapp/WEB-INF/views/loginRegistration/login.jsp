<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
    <div align="center">
    	<s:form action="/login" >
	        <s:textfield key="userBean.login"/>
	        <s:textfield key="userBean.password"/>
	        <s:submit value="%{getText('button.label.submit')}"/>
        </s:form>
    </div>
</body>
</html>