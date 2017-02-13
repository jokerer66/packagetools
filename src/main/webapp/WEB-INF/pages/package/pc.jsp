
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script src="${ctx}/static/plugins/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/css/mystyle.css">
<html>
<head>

  <title>pc package</title>

  <style>
    .x{text-decoration:none;color:#862e32}
    p{text-align:center}
    .footer{ width:100%;position:fixed; bottom: 49px; }
    .p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 21.0px Zapfino}
    .v{text-decoration:none;color:#218868;font-size:40px;text-align: left}
    .pack_2{text-decoration:none;color:#218868;font-size:65px;}
  </style>


  <script language='javascript'>

    function pack2(inpara,svnversion){
      document.getElementById(inpara).innerText="打包进行中。。。";
      document.getElementById(inpara).style.color='#000000';
      $.post("${ctx}/pc/runPack?inPara="+inpara+"&svnversion="+svnversion+"",function(data){
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
      }else if(data=="200"){
        alert("ios打包成功.");
        location.reload();
      }else if(data=="201"){
        alert("ios svn 更新代码出现问题.");
        location.reload();
      }else if(data=="202"){
        alert("ios svn 地址连接出现问题");
        location.reload();
      }else if(data=="203"){
        alert("ios ios编译失败，请检查源代码");
        location.reload();
      }else if(data=="204"){
        alert("ios源代码checkout成功，请使用xcode打开一次源代码，然后关闭xcode后重新点击打包。");
        location.reload();
      }else if(data=="205"){
        alert("ios已有新的证书出现，请及时更新");
        location.reload();
      }else{
        alert("返回数据为:"+data);
        location.reload();
      }
      });
    }
    function pack(inpara){
      var svnversion=prompt("是否需要指定svn号打包,请填写指定打包的svn号,不填写,则默认采用最新svn号","");//将输入的内容赋给变量 svnversion ，
      //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值
      if(svnversion)//如果返回的有内容
      {
        alert("指定版本打包 项目:"+inpara+" svn:"+svnversion);
        pack2(inpara,svnversion);
      }else{
        alert("最新svn号打包 项目:"+inpara);
        pack2(inpara,"lastversion");
      }
    }
  </script>

</head>
<body bgcolor="white" background="static/images/ios_snow_yizi.png" style="background-size:100% 100%;">

<!--title-->
<div align="center" style="font-size:30pt;">
  <br>Package In PC</div>
<br><br><br>

<!--jump to other page link-->
<table width="95%" style="margin-left: 5%;margin-bottom: 30px" cellpadding="10px" cellspacing="15px">
  <tr>
    <td width="50%" class="v" ><a href="${ctx}/addinfo" >Configuration Page</a></td>
    <td width="50%" class="v"><a href="${ctx}/dsymDown" >Down dSYM Page</a></td>
  </tr>
  <tr>
    <td class="v"><a href="${ctx}/iosLog" >Down ipa Page</a></td>
    <td class="v"><a href="${ctx}/androidLog">Down apk Page</a></td>
  </tr>
  <tr>
    <td class="v"><a href="${ctx}/fileUploadForm" >Upload ipa/apk Page</a></td>
    <td class="v"><a href="${ctx}/logfiledown" >Down Log File</a></td>
</tr>
<!--Package apk link-->
<c:forEach var="m" items="${message}" varStatus="id">
  <table style='width:100%'>
    <tr>
      <td>

        <p class="align_center"><a class='pack_2'  id="${m}" href="javascript:pack('${m}')" >${m}</a></p>
        <br><br><br><br><br><br><br><br>
      </td>


    </tr>
  </table>
</c:forEach>


</body>
</html>