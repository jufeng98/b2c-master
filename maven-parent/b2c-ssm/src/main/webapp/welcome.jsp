<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="cn">
<head>
    <meta charset="UTF-8">
    <title>欢迎</title>
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.14/dist/vue.js"></script>
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="layout" style="min-width: 1000px;">
    <h1>欢迎来到我的世界1</h1>
    <%
    javax.el.ELContext c=pageContext.getELContext();
    System.out.println(c.getClass());
    System.out.println(c.getClass().getClassLoader());
    System.out.println(c.getClass().getResource(""));
    System.out.println(javax.el.ELContext.class);
    %>

    <el-table :data="tableData" style="width: 100%"
              :height="300" size="mini"
              v-loading="loadings.length>0" element-loading-text="请稍后..."
              header-cell-class-name="table-header-row-style"
              cell-class-name="table-header-row-style">
        <el-table-column type="selection" width="55"></el-table-column>
        <el-table-column type="index" width="55" label="序号"></el-table-column>
        <el-table-column prop="examCode" label="考试编号" width="100"></el-table-column>
        <el-table-column prop="examName" label="考试名称" width="230"></el-table-column>
        <el-table-column prop="examExplain" label="考试说明"></el-table-column>
    </el-table>
</div>
<script type="text/javascript">
    setData()

    function setData(data){
        initVue(data)
    }

    function initVue(data = {}) {
        new Vue({
            el: '#layout',
            data() {
                return {
                    loadings: [],
                    tableData: [],
                }
            },
            methods: {
                getSuites() {
                    that.loadings.push("")
                    axios.post('/b2c-ssm/exam/getExam', {examCode:'KS0001',delFlag:0})
                        .then(resJson => {
                            that.loadings.pop()
                            that.tableData = resJson.data.data
                        })
                },
            },
            mounted() {
                window.that = this
                this.getSuites()
            }
        })
    }
</script>
</body>
</html>
