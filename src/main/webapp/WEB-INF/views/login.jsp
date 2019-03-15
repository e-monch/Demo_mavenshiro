<%--
  Created by IntelliJ IDEA.
  User: lmq
  Date: 2019/3/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form method="post" action="/login">
        username:<input name="username" type="text"/> <br/>
        password:<input name="password" type="text"/> <br/>
        <button name="登陆" type="submit"/>
    </form>
    <h1>${errorMsg}</h1>
</body>
</html>
