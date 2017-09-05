<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">

<html>
<head>
    <title>Android ready package download</title>
    <style>
        .x{text-decoration:none;color:#862e32}
        p{text-align:center}
        .footer{ width:100%;position:fixed; bottom: 49px; }
        .p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 21.0px Zapfino}
        .v{text-decoration:none;color:#862e32;font-size:70px}
        .v1{text-decoration:none;color:#862e32;font-size:35px}
        .v2{text-decoration:none;color:#862e32;font-size:30px;width: 50px;height: 50px;text-align: center;padding-right: 10px}
        .v3{text-decoration:none;color:#862e32;font-size:40px;width: 600px;height: 10px;text-align: center;padding-right: 10px}

    </style>

</head>

<body>
<br><center><h1 class="v">Down Ready apk Page</h1></center>


<br><br>

<table style='width:100%' align="center">
    <c:forEach  items="${androidreadylist}" var="items">
        <tr><th height="200px"><p class='v'><a href="/download?file_url=${items.store_root_path}/${items.store_path}/${items.file_name}/online/${items.product_name}.apk" >${items.file_name}</a><br></p></th></tr>
    </c:forEach>
</table>


</body>
</html>
