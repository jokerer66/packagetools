<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 16/5/20
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ios ready package download</title>

    <style>
        .x{text-decoration:none;color:#862e32}
        p{text-align:center}
        .footer{ width:100%;position:fixed; bottom: 49px; }
        .p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 21.0px Zapfino}
        .v{text-decoration:none;color:#862e32;font-size:70px}
        .v1{text-decoration:none;color:#862e32;font-size:40px}
        .v2{text-decoration:none;color:#862e32;font-size:30px;width: 50px;height: 50px;text-align: center;padding-right: 10px}
        .v3{text-decoration:none;color:#862e32;font-size:40px;width: 600px;height: 10px;text-align: center;padding-right: 10px}

    </style>
</head>
<body>
<table style='width:95%' align="center">
    <c:forEach var="item" items="${iosreadylist}">
        <tr><td width="85%" height="200px"><p class='v'><a href="itms-services://?action=download-manifest&url=https://${systemip}:8443/downipa/${downipa_filename}/${item.file_name}/online/manifest.plist" >${item.file_name}</a><br></p></td>
            <td width="10%" height="200px"><p class='v1'><a href="/download?file_url=${item.store_root_path}/${item.store_path}/${item.file_name}/online/${item.product_name}.ipa" >Down</a></p></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
