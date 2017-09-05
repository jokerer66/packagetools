<!doctype html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>responsive demo</title>
  <script src="${ctx}/static/plugins/js/device.js"></script>
</head>

<body style="margin: auto; position: absolute; width:100%; height: 100%">

<script>
  var isAndroid = device.android();

  var isIPhone = device.iphone(),
          isIPad = device.ipad();

  var isIOS = isIPhone  || isIPad;

  if(isAndroid){
    window.open("androidLog","_self");
  }
  else if(isIOS){
    window.open("iosLog","_self");
  }
  else{
    window.open("iosLog","_self");
  }
</script>
</body>
</html>