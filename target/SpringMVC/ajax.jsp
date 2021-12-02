<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script>
    var userList = new Array();
    userList.push({
        username: '张三',
        age: 18
    }, {
        username: '李四',
        age: 11
    })

    $.ajax({
        method: 'POST',
        url: "${pageContext.request.contextPath}/user/quick14",
        data: JSON.stringify(userList),
        contentType: "application/json;charset=utf-8"
    })
</script>
</body>
</html>
