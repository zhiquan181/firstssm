<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>新增用户</title>
</head>
<body>
	<form action="/firstssm/User/UserInsert" method="post">
		<p>账号：<input type="text" value="" name="account" required="required"/></p>
		<p>密码：<input type="text" value="" name="passwd" required="required"/></p>
		<p>年龄：<input type="text" value="" name="age" required="required"/></p>
		<p><input type="submit" value="提交"/></p>
	</form>
</body>
</html>