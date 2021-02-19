<%--
  Created by IntelliJ IDEA.
  User: yu
  Date: 2019/6/4
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>hello world</title>
</head>
<body>
<span>名称:${toDoItem.name},时间:${toDoItem.remindTime}</span>
<a href="${pageContext.request.contextPath}/java">to java</a>
<a href="${pageContext.request.contextPath}/kotlin">to kotlin</a>
<a href="${pageContext.request.contextPath}/groovy">to groovy</a>
</body>
</html>
