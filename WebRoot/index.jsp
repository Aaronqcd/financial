<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%
  String path = request.getContextPath();
  String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<base href="<%=basePath%>">
</head>
<body>
<script>
top.window.location= 'Login!start.action';
</script>
</body>
</html>