<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/taglib.jsp" %>
<html>
<head>
    <meta charset="utf-8">
    <title>springmvc 标签库练习</title>
</head>
<body>

<h1>springmvc 标签库练习</h1>

<form:form id="form" modelAttribute="reqVo" action="${ctx}/jstl/save" method="post" cssClass="form-cls"
           cssStyle="color: burlywood">

    <form:hidden path="id"/>

    <label>姓名:</label><form:input path="name"/><form:errors path="name"/><br/>

    <label>密码:</label><form:password path="password"/><br/>

    <label>记住登录:</label><form:checkbox path="rememberLogin"/><br/>

    <label>APP类型:</label><form:checkboxes path="appType" items="${fnc:getDictList('app_terminal')}" itemLabel="label"
                                          itemValue="value"/><br/>

    <label>性别:</label><form:radiobutton path="gender" label="男" value="1"/>
    <form:radiobutton path="gender" label="女" value="2"/><br/>

    <label>审核状态:</label><form:radiobuttons path="applyStatus" items="${fnc:getDictList('hb_apply_status')}"
                                           itemLabel="label"
                                           itemValue="value"/><br/>

    <label>描述:</label><form:textarea path="desc"/><br/>

    <label>产品类型:</label>
    <form:select path="productType" items="${fnc:getDictList('product_type')}"
                 itemLabel="label"
                 itemValue="value"/><br/>

    <label>券类型:</label>
    <form:select path="couponType" style="width:177px;">
        <form:option value="" label="--请选择--"/>
        <form:options items="${fnc:getDictList('coupon_type')}" itemLabel="label" itemValue="value"/>
    </form:select><br/>

    <form:button>
        提交
    </form:button>

</form:form>


</body>
</html>
