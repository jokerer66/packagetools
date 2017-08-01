<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Backend Link Page</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">
    <style>
        p {
            text-align: center
        }
    </style>
</head>

<body>
<body bgcolor="white" background="static/images/ios_snow_yizi.png" style="background-size:100% 100%;">
<br>
<center><h1 class="save">Backend Link Page</h1></center>
<center>
    <a href="${ctx}/pc" class="font_20 color_blue">返回打包页面</a>
    <!--SavelinkController方法2，命名为addsavelinkinfo-->
    <a href="${ctx}/addsavelinkinfo" class="font_20 color_blue">添加链接页面</a>
</center>
<br><br>

<table style='width:100%' align="center">
    <c:forEach items="${savelink}" var="items">
        <table style='width:100%'>
            <p>
            <p class="align_center">
            <td>
                <span class="save">${items.projectname}&nbsp</span>
                <span class="save"><a href="${items.link}" target="_blank">${items.linkname}&nbsp</a></span>
                <span class="save">username:${items.username}&nbsp</span>
                <span class="save">passwd:${items.passwd}&nbsp</span>
            </td>
            </p>
            </tr>
        </table>
    </c:forEach>
</table>
</html>