<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.servletContext.contextPath}/user/upload2" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="name"><br />
    文件：<input type="file" name="uploadFile"><br />
    文件：<input type="file" name="uploadFile2"><br />
    <button type="submit">提交</button>
</form>
</body>
</html>
