
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/plugins/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/css/mystyle.css">


<html>
<head>

  <title>android package</title>
  <script language='javascript'>

    function pack(inpara){

      document.getElementById(inpara).innerText="打包进行中...";
      document.getElementById(inpara).style.color='color_purple';


      if(confirm('点击确定开始打包，请等待3-4mins。')){


        $.post("${ctx}/android/runPack?inPara="+inpara+"",function(data){

          alert("1");

          alert(data);

          if (data == "0") {

            alert("打包成功，请检查。");
            location.reload();
          } else if (data == "001") {

            alert("数据库或者svn连不上.");
            location.reload();
          } else if(data=="002"){

            alert("后台正在打包，请稍等");
            location.reload();
          }else if(data=="100"){
            alert("Android 打包成功");
            location.reload();
          }else if(data=="101"){
            alert("svn checkout 异常");
            location.reload();
          }else if(data=="102"){
            alert("修改文件出现异常");
            location.reload();

          }else if(data=="103"){
            alert("ready ant环境准备过程中出现异常")
            location.reload();

          }else if(data=="104"){
            alert("ant 编译文件出现异常")
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
<body bgcolor="white" background="static/images/ios_snow_yizi.png" style="background-size:100% 100%;">

<!--title-->
<div align="center">

  <br><p class="title_1">Package In Android</p><br>

</div>

<!--jump to other page link-->
<br><br><br>
<a href="${ctx}/androidLog" style="font-size:30pt;">Down apk Page</a><br><br><br><br>

<!--Package apk link-->
<c:forEach var="m" items="${message}" varStatus="id">
  <table style='width:95%'>
    <tr>
      <td>
        <p class="align_center"><a class='pack_1' id="${m}" href="javascript:pack('${m}')" >${m}</a></p>
        <br><br><br><br>
      </td>
    </tr>
  </table>
</c:forEach>

</body>
</html>