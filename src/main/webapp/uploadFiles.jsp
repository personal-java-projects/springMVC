<%--
  Created by IntelliJ IDEA.
  User: 2948
  Date: 2021/12/3
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user/uploadFiles" method="post" enctype="multipart/form-data">
  名称：<input type="text" name="name"><br />
  文件1：<input type="file" name="uploadFiles"><br />
  文件2：<input type="file" name="uploadFiles"><br />
  文件3：<input type="file" name="uploadFiles"><br />
  <button type="submit">提交</button>
</form>
</body>
</html>
