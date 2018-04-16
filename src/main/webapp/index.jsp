<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<h1>这里是首页</h1>
<h2>欢迎：${userName}</h2>
<shiro:hasRole name="r1">
    用户[<shiro:principal/>]拥有角色r1<br/>
</shiro:hasRole>
<shiro:hasRole name="r3">
    用户[<shiro:principal/>]拥有角色r3<br/>
</shiro:hasRole>
</body>
</html>