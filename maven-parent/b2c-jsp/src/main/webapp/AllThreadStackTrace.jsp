<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>显示所有线程栈</title>
</head>
<body>
<h1>线程栈</h1>
<pre>
    <%
        Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> threadEntry : allStackTraces.entrySet()) {
            Thread thread = threadEntry.getKey();
            out.println("线程：" + thread.getId() + " " + thread.getName());
            StackTraceElement[] stackTraceElements = threadEntry.getValue();
            for (StackTraceElement stackTraceElement : stackTraceElements) {
                out.println(stackTraceElement);
            }
        }
    %>
</pre>
</body>
</html>
