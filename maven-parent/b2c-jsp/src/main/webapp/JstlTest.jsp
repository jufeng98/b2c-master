<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/taglib.jsp" %>
<%! String name = "<span>liangyudong</span>";%>
<%
    request.setAttribute("name1", "<span>liangyudong</span>");
    List<String> list = Arrays.asList("hello", "world", "!");
    request.setAttribute("list", list);
    Date now = new Date();
    request.setAttribute("now", now);
    String[] strings = list.toArray(new String[list.size()]);
    request.setAttribute("strings", strings);
%>
<html>
<head>
    <meta charset="utf-8">
    <title>springmvc 标签库练习</title>
</head>
<body>

<h1>jstl 标签库练习</h1>


<form:form id="form" action="${ctx}/jstl/save" method="post" cssClass="form-cls" cssStyle="color: burlywood">

</form:form>



<p><c:out value="${name1}" escapeXml="true"/>|<%=name%>|${requestScope.name1}</p>

<c:set var="salary" scope="session" value="${2000*2}"/>
<p>${sessionScope.salary}</p>

<c:remove var="salary"/>
<p>${sessionScope.salary}</p>
<c:set var="salary" scope="session" value="${2000*2}"/>

<c:catch var="catchException">
    <% int x = 5 / 0;%>
</c:catch>

<c:if test="${catchException != null}">
    异常为 : ${catchException}, 发生了异常: ${catchException.message}</p>
</c:if>

<p>你的工资为 : ${salary}</p>
<c:choose>
    <c:when test="${salary <= 0}">
        太惨了。<br/>
    </c:when>
    <c:when test="${salary > 1000}">
        不错的薪水，还能生活。<br/>
    </c:when>
    <c:otherwise>
        什么都没有。<br/>
    </c:otherwise>
</c:choose>

<c:import var="data" url="https://www.baidu.com"/>
<c:out value="${data}"/>

<c:forEach var="i" begin="1" end="5">
    Item ${i}<br/>
</c:forEach>

<c:forEach items="${list}" var="str" varStatus="status">
    ${str}-${status.index}<br/>
</c:forEach>

<c:forTokens items="google,runoob,taobao" delims="," var="name">
    <c:out value="${name}"/><br/>
</c:forTokens>

<c:url value="http://localhost">
    <c:param name="name" value="梁煜东"/>
    <c:param name="url" value="www.runoob.com"/>
</c:url>
<br/>
<a href="
<c:url value="http://localhost">
    <c:param name="name" value="梁煜东"/>
    <c:param name="url" value="www.runoob.com"/>
</c:url>
">超链接</a>
<br/>
<%--
<c:redirect url="http://www.runoob.com"/>
--%>

<fmt:formatNumber value="12" type="number"/><br/>
<fmt:formatNumber value="12" type="number" minIntegerDigits="3"/><br/>
<fmt:formatNumber value="12" type="number" minFractionDigits="2"/><br/>
<fmt:formatNumber value="123456.78" pattern=".000"/><br/>
<fmt:formatNumber value="123456.78" pattern="#,#00.0#"/><br/>
<fmt:formatNumber value="12" type="currency"/><br/>
<fmt:formatNumber value="12" type="currency" currencyCode="GBP"/><br/>
<fmt:formatNumber value="0.12" type="percent"/><br/>
<fmt:formatNumber value="0.12" type="percent" minFractionDigits="2"/><br/>

<fmt:parseNumber pattern=".00" value="34.67" var="money" scope="session"/>
${money}<br/>

<fmt:formatDate value="${now}" pattern="yyyy-MM-dd HH:mm:ss"/><br/>
<fmt:formatDate type="time" value="${now}" timeStyle="Full"/><br/>
<fmt:formatDate value="${now}" dateStyle="Full"/><br/>

<c:set var="date" value="20-10-2015"/>
<fmt:parseDate value="${date}" var="parsedEmpDate" pattern="dd-MM-yyyy"/>
<p>解析后的日期为: <c:out value="${parsedEmpDate}"/></p>

<c:set var="myString" value="Hello World"/>
${fn:contains(myString,"Hello")}<br/>
${fn:contains("Stella Cadente","Cadente")}<br/>
${fn:join(strings, ",")}<br/>

<sql:setDataSource var="mysqlDataSource" driver="com.mysql.jdbc.Driver"
                   url="jdbc:mysql://127.0.0.1:3306/sakila?useUnicode=true&characterEncoding=utf8&rewriteBatchedStatements=true&serverTimezone=GMT%2B8"
                   user="root"  password="root"/>
<sql:query dataSource="${mysqlDataSource}" sql="select * from actor limit 5" var="result" />
<c:forEach var="row" items="${result.columnNames}">
    ${row}&nbsp;&nbsp;&nbsp;
</c:forEach>
<c:forEach var="row" items="${result.rows}">
    ${row}<br/>
</c:forEach>


</body>
</html>
