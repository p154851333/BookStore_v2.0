<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书列表</title>
    
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
	.icon {
		margin:10px;
		border: solid 2px gray;
		width: 160px;
		height: 195px;
		text-align: center;
		float: left;
	}
</style>
  </head>
  
  <body>
  <table width="100%" height="100%">
  	<tr height="100%" >
  		<td valign="top">
		<c:forEach items="${pageBean.beanList }" var="book">
		  <div class="icon">
		    <a href="<c:url value='/bookAction_findBookById?bid=${book.bid }'/>"><img src="<c:url value='/${book.image }'/>" border="0"/></a>
		      <br/>
		   	<a href="<c:url value='/bookAction_findBookById?bid=${book.bid }'/>">${book.bname }</a>
		  </div>
		</c:forEach>
  		</td>
	</tr>
	<tr>
		<td>
		<center>
			第${pageBean.currentPage}页/共${pageBean.totalPage}页 <a
				href="${pageBean.url}&currentPage=1">首页</a>
			<%--<!--判断页面，是否显示上一页。当前页是第一页时，不显示上一页  -->--%>
			<c:if test="${pageBean.currentPage > 1}">
				<a href="${pageBean.url}&currentPage=${pageBean.currentPage-1 }">上一页</a>
			</c:if>

			<%--页码列表 --%>
			<%-- --%>
			<c:choose>
				<%--当总页数小于分页列表的定长的时候（<10） --%>
				<c:when test="${pageBean.totalPage<10 }">
					<%--分页列表的开始和结尾就是1~总页数 --%>
					<c:set var="begin" value="1" />
					<c:set var="end" value="${pageBean.totalPage }" />
				</c:when>
				<%--当总页数大于分页列表的定长的时候（>10） --%>
				<c:otherwise>
					<%--分页列表的开始就是当前页码-5，结束就是当前页码+4（列表长度为10） --%>
					<c:set var="begin" value="${pageBean.currentPage-5}" />
					<c:set var="end" value="${pageBean.currentPage+4}" />
					<%--当分页列表的开始项为当前页码-5之后 < 1 则表示开始项需从1开始，给其赋值1
	 			并给结束项赋值10（定长是10） --%>
					<c:if test="${begin<1}">
						<c:set var="begin" value="1" />
						<c:set var="end" value="10" />
					</c:if>
					<%--当结束项是当前页码+4之后 > 总页码数，则表示结束项要超出总页码数，需给其赋值总页码数。 --%>
					<c:if test="${end>pageBean.totalPage}">
						<c:set var="begin" value="${pageBean.totalPage-9}" />
						<c:set var="end" value="${pageBean.totalPage}" />
					</c:if>
				</c:otherwise>
			</c:choose>
			<%--循环遍历分页列表，开始项~结束项 --%>
			<c:forEach var="i" begin="${begin}" end="${end}">
				<c:choose>
					<%--当前页码数不显示超链接 --%>
					<c:when test="${i eq pageBean.currentPage}">
	 			[${i}]
	 		</c:when>
					<c:otherwise>
						<%--非当前页码显示超链接 --%>
						<a href="${pageBean.url}&currentPage=${i}">[${i}]</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<%--<!--判断页面，是否显示上一页。当前页是最后页时，不显示下一页  -->--%>
			<c:if test="${pageBean.currentPage < pageBean.totalPage}">
				<a href="${pageBean.url}&currentPage=${pageBean.currentPage+1 }">下一页</a>
			</c:if>
			<a href="${pageBean.url}&currentPage=${pageBean.totalPage }">尾页</a>

		</center>
		</td>
  	</tr>
  </table>


</body>
 
</html>

