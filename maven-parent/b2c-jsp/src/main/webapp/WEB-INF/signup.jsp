<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="sp" uri="/WEB-INF/tlds/tag.tld" %>
<%@ taglib prefix="spjsp" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>用户注册</title>
        <link type="text/css" rel="stylesheet" href="/static.css"/>
    </head>
    <body>

        <h1>${applicationScope.companyName}:注册新账户</h1>
        <h2><sp:boot/></h2>
        <spjsp:table align="center" size="32">title</spjsp:table>
        <h2><sp:i18n key="login.name"/></h2>

        <table align="center">
            <thead>
            <th>Account列表</th>
            </thead>
            <tbody>
                <sp:ite items="${accounts}" item="account">
                   <tr>
                       <td>${index}:</td>
                       <td>${account.id}</td>
                       <td>${account.email}</td>
                       <td>${account.showName}</td>
                       <td>${account.pwd}</td>
                       <td>${account.activated}</td>
                   </tr>
                </sp:ite>
            </tbody>
        </table>

        <sp:escape>
            <h1>原始html元素</h1>
        </sp:escape>

        <h2>jstl标签库</h2>
        <c:out value="${companyName}" default="javamaster"/>
        <br/>
        <table align="center">
            <thead>
            <th>Account列表</th>
            </thead>
            <tbody>
            <c:forEach items="${accounts}" var="account" varStatus="status">
                <tr>
                    <td>${status.index}:</td>
                    <td>${account.id}</td>
                    <td>${account.email}</td>
                    <td>${account.showName}</td>
                    <td>${account.pwd}</td>
                    <td>${account.activated}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <form method="post" action="/user/signup">
            <label>账户ID:</label><input name="id" type="text"><img src="/ui.png" title="不能含有特殊字符"/><br/>
            <label>Email:</label><input name="email" type="text"><img src="/ui.png" title="中国大陆的email"/><br/>
            <label>显示名称:</label><input name="showName" type="text"><img src="/ui.png" title="只能为中文"/><br/>
            <label>密码:</label><input name="pwd" type="text"><img src="/ui.png"/><br/>
            <label>确认密码:</label><input name="dblPwd" type="text"><img src="/ui.png"/><br/>
            <label>验证码:</label><input name="captcha" type="text"><img src="/ui.png"/>
            <img id="kaptchaImage" src="/kaptcha/getImage"/><a id="changeImage" href="#">看不清?换一张</a><br/>
            <input type="submit" value="确认并提交"/>
        </form>

        <script src="/user.js" type="text/javascript"></script>
    </body>
</html>