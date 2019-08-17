<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	您没有该权限！
	<p>
		使用@RequiresPermissions("user:query")，@RequiresRoles("admin")注解方式进行权限控制，<br/>
		该方式没有权限，或没有角色时不会自动跳转到没有权限的页面，而是直接把异常抛到页面了，所以我们要配置一个全局的异常处理。
	</p>
	<p>
		在springmvc配置文件中，进行如下配置，配置全局异常捕获，当shiro框架抛出权限不足异常时，跳转到权限不足提示页面。
	</p>
	<p style="color:red;">
		  &lt;!-- 全局异常处理 --&gt;<br/>
		  &lt;bean class=&quot;org.springframework.web.servlet.handler.SimpleMappingExceptionResolver&quot;&gt;<br/>
		  &nbsp;&nbsp;&lt;property name=&quot;exceptionMappings&quot;&gt;<br/>
		  &nbsp;&nbsp;&nbsp;&nbsp;&lt;props&gt;<br/>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;prop key=&quot;org.apache.shiro.authz.UnauthorizedException&quot;&gt;redirect:/noPrivilegeUI.jsp&lt;/prop&gt;<br/> 
		  &nbsp;&nbsp;&nbsp;&nbsp;&lt;/props&gt;<br/>
		  &nbsp;&nbsp;&lt;/property&gt;<br/>
		  &lt;/bean&gt;<br/>
	</p>
</body>
</html>