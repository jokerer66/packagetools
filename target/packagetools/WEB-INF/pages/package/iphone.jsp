
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/plugins/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/css/mystyle.css">

<html>
<head>

  <title>iphone package</title>

  <style>
    .x{text-decoration:none;color:#862e32}
    p{text-align:center}
    .footer{ width:100%;position:fixed; bottom: 49px; }
    .p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 21.0px Zapfino}
    .v{text-decoration:none;color:#000;font-size:70px}
  </style>

  <script language='javascript'>

    function pack(inpara){

      document.getElementById(inpara).innerText="打包进行中。。。";
      document.getElementById(inpara).style.color='#000000';

      if(confirm('点击确定开始打包，请等待8-10mins。')){


        $.post("${ctx}/android/runPack?inPara="+inpara+"",function(data){

          if(data=="200"){
            alert("ios打包成功.");
            location.reload();

          }else if(data=="201"){
            alert("svn 地址连接出现问题.");
            location.reload();

          }else if(data=="202"){
            alert("svn 地址连接出现问题");
            location.reload();

          }else if(data=="203"){
            alert("ios编译失败，请检查源代码");
            location.reload();

          }else if(data=="204"){
            alert("ios源代码checkout成功，请使用xcode打开一次源代码，然后关闭xcode后重新点击打包。");
            location.reload();

          }else if(data=="205"){
            alert("已有新的证书出现，请及时更新");
            location.reload();

          }else{

            alert("返回数据为："+data);
            location.reload();

          }

        });


      }else{
        location.reload();
      }

    }

  </script>

</head>
<body  bgcolor="white" background="static/images/ios_snow_yizi.png" style="background-size:100% 100%;">

<!--title-->
<div align="center">
  <br>
  <p class="title_1">Package In Iphone</p>
  <br>
</div>

<!--jump to other page link-->
<br><br><br>
<a href="${ctx}/iosLog" style="font-size:30pt;">Down ipa Page</a><br><br><br><br>

<!--Package apk link-->
<c:forEach var="m" items="${message}" varStatus="id">
  <table style='width:100%'>
    <tr>
      <td>

        <p class="align_center"><a class='pack_2' id="${m}" href="javascript:pack('${m}')" >${m}</a></p>
        <br><br><br><br><br><br><br><br>
      </td>


    </tr>
  </table>
</c:forEach>

</body>
</html>