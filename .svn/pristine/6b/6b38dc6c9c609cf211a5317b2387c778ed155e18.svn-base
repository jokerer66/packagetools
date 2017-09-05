<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>tempdown page</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">

  <style>
    .x{text-decoration:none;color:#862e32}
    p{text-align:center}
    .footer{ width:100%;position:fixed; bottom: 49px; }
    .p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 21.0px Zapfino}
    .v{text-decoration:none;color:#862e32;font-size:70px}
    .v1{text-decoration:none;color:#862e32;font-size:50px}
  </style>

  <script>

    function downfile(name){


      alert(name);


    }

    function downtest(){

      $.post("${ctx}/download",function(data) {

        alert(data);
      });

    }

  </script>


</head>
<body>
<br><br>
<center>
  <h1 class="v">临时文件下载页面</h1>
</center>
<br>
<table width="90%" style="padding-left: 3%;padding-right: 3%">
  <tr>
<td width="50%" style="text-align: left"> <a href="${ctx}/pc" style="font-size:30pt;">First Page</a> </td>
    <td width="50%" style="text-align: right"><a href="${ctx}/fileUploadForm" style="font-size:30pt;">Upload Page</a></td>

    </tr>
  </table>
<br><br>


<table style='width:100%' align="center">

  <c:forEach var="item" items="${filenames}" varStatus="id">

    <tr>
      <td align="center" height="100px"><a class='v1' id="${item.key}" href="/download?file_url=${temp_path}/${item.key}" >${item.key}</a>
      </td>
    </tr>

  </c:forEach>
</table>
</body>
</html>
