<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书详细</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
	body {
		font-size: 10pt;
	}
	div {
		margin:20px;
		border: solid 2px gray;
		width: 150px;
		height: 150px;
		text-align: center;
	}
	li {
		margin: 10px;
	}
	a {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -70px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
	a:HOVER {
		background: url(<c:url value='/images/all.png'/>) no-repeat;
		display: inline-block;
		
		background-position: 0 -106px;
		margin-left: 30px;
		height: 36px;
		width: 146px;
	}
</style>


<script type="text/javascript">
	function change0() {
		var count = document.getElementById("countid").value;
		if (count=="NaN"||count=="") {
			count=0;
		}
		if (count>1) {
			document.getElementById("countid").value=count-1;
		}
	} 
	function change1() {
		var count = document.getElementById("countid").value;
		if (count=="NaN"||count=="") {
			count=0;
		}
		document.getElementById("countid").value=parseInt(count) + parseInt(1);
	} 
</script>
  </head>
  
  <body>
  
  <div>
    <img src="<c:url value='/${book.image }'/>" border="0"/>
  </div>
  <ul>
    <li>书名：${book.bname }</li>
    <li>作者：${book.author }</li>
    <li>单价：${book.price }元</li>
    <li>
    	<form id="form" action="<c:url value='/cartAction_addCartItme.action'/>" method="post">
    		<input type="hidden" name="bid" value="${book.bid }" />
  			数量：
  			<input type="button" onclick="javascript:change0();" value = "-"/>
  			<input type="text" size="3" name="count" 
  			onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}
  			else{this.value=this.value.replace(/\D/g,'')}" 
  			onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}
  			else{this.value=this.value.replace(/\D/g,'')}"  
  			value="1" id="countid"/>
  			<input type="button" onclick="javascript:change1();" value = "+"/>
  			
  		</form>
    </li>
  </ul>
  <a href="javascript:document.getElementById('form').submit();"></a>
  
  </body>
</html>
