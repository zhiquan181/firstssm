<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>获取所有用户列表</title>
<style type="text/css">
	body div div{border:dashed 1px #e2e2e2;margin:10px auto;height:50px;border-radius:5px;clear: both;}
	body div div a{width:;height:50px;line-height:50px;font-size:15px;display: inline-block;float: left;}
	.user_detail{width:80%;text-indent: 20px;}
	.user_update{width:10%;}
	.user_delete{width:10%;}
</style>
</head>
<body>
	<div>
		<c:forEach var="v_userlist"  items="${userlist}">
			<div>
				<a class='user_detail' href="/firstssm/User/UserDetail?id=${v_userlist.id}">${v_userlist.username}</a>
				<a class='user_update' href="/firstssm/User/ToUserUpdate?id=${v_userlist.id}">修改</a>
				<a class='user_delete' href="/firstssm/User/UserDelete?id=${v_userlist.id}">删除</a>
			</div>
		</c:forEach>
		
		<p>
			<a href="/firstssm/User/ToUserInsert">新增用户</a>
			<br/><br/>
			<a href='/firstssm/Admin/Panel'>后台首页</a>
		</p>
	</div>
</body>
</html>