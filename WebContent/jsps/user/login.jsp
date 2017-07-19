<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>DarlingMonkey网上书城|登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	//验证码刷新的函数
	function _change(){
		/*
		1.得到img元素
		2.将img中的src内容替换新的请求
		*/
		var img=document.getElementById("img");
		img.src="<c:url value='/userAction_VerifyCodeChange.action'/>?a=" + new Date().getTime();
	}
	
	</script>
	    <style type="text/css">
		#divwai{
			width:600px;
			height:300px;
		}
   		</style>


  </head>
  
  <body>
  <h1>登录</h1>
   <div id="divwai">
<p style="color: red; font-weight: 900">${msg }</p>
<form action="<c:url value='/userAction_login.action'/>" method="post" target="_top">
<fieldset>
	<legend>登录</legend>
	用户名：<input type="text" name="username" value="${formUser.username }"/><font style="color: red">${errors.username }</font><br/>
	密　码：<input type="password" name="password"/><font style="color: red">${errors.password }</font><br/>
	验证码：<input type="text" name="verifycode" value="" size="3" />
					<img src="<c:url value='/userAction_VerifyCodeChange.action'/>" id="img"/>
					<a href="javascript:_change()">看不清，换一张</a>
					<font style="color: red">${errors.verifycode }</font><br/>
	<div align="center"><input type="submit" value="登录"/></div>
</fieldset>
</form>
</div>
  </body>
</html>
