<%--
  Created by IntelliJ IDEA.
  User: lmq
  Date: 2019/3/15
  Time: 17:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%--
        控制 jsp 元素的 shiro 标签, 需要引入 shiro 标签库 url:http://shiro.apache.org/tags"
        <shior:authenticated>                               登录之后
        <shiro:notAuthenticated>                            没登录时
        <shiro:user>                                        用户RememberMe时
        <shiro:guest>                                       用户没有RememberMe时
        <shiro:hasAnyRoles name="admin,abc">                有 admin,abc 角色中的一个时
        <shiro:hasRole name="abc">                          有 abc 角色时
        <shiro:lacksRole name="abc">                        没有 abc 角色时
        <shiro:hasPerssion name="department:create">        有 create 权限时
        <shiro:lacksPermission name="department:create">    没有 create 权限时
        <shiro:principal>                                   显示用户名称
        <shiro:principal property="username"/>              显示用户身份中的属性值
    --%>
    <shiro:principal></shiro:principal><a href="/logout">注销</a>
</body>
</html>
