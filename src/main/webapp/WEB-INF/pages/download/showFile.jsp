<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <title>上传成功</title>
</head>
<body>
<br />
<br />
<center>
  <h2>上传成功!</h2>
  <h3>
    文件名 : "<strong> ${message}</strong>" 上传成功了!
  </h3>
  <br />
  <h3>
    <a href="${ctx}/fileUploadForm">返回</a>

  </h3>
</center>
</body>
</html>
