<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
<form id="form1" name="form1" method="post" action="/firstssm/Admin/Validate">
    <p align="center">用户登录123456</p>
    <table width="296" border="1" align="center">
        <tr>
            <td width="98" height="34">用户名：</td>
            <td width="182"><label><input name="userName" type="text" id="userName"/></label></td>
        </tr>

        <tr>
            <td height="36">密码：</td>
            <td><label> <input name="passWord" type="password" id="passwd"/></label></td>
        </tr>
        <tr>
            <td height="35" colspan="2"><label>
                <input type="submit" name="Submit" value="提交"/>
            </label> <label> <input type="reset" name="Submit2" value="重置"/>
            </label>${message}</td>
        </tr>
    </table>
</form>
</body>
</html>