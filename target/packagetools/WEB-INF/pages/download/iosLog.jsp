<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.2/themes/smoothness/jquery-ui.css">

<html>
<head>
  <title>Download ipa Page</title>
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

  <script>

    function go(clicktype){

      if(clicktype == "1"){

        location= "${ctx}/iosLog?jump_type=0&file_name_like=" + document.getElementById('search_context').value;
      }else if(clicktype == "2") {

        location= "${ctx}/iosLog?jump_type=2&cur_page=" + ${cur_page_num} + "&file_name_like=" + document.getElementById('search_context').value;
      }else if(clicktype == "3") {

        location= "${ctx}/iosLog?jump_type=3&cur_page=" + ${cur_page_num} + "&file_name_like=" + document.getElementById('search_context').value;

      }else if(clicktype == "4") {

        location= "${ctx}/iosLog?jump_type=1&file_name_like=" + document.getElementById('search_context').value;
      }else{

        var jump_page = document.getElementById('div_jump_page').value;
        if(jump_page == ""){

          alert("Which page do you want to go?");
        }else{

          location= "${ctx}/iosLog?jump_type=4&cur_page=" + ${cur_page_num} + "&change_page="+document.getElementById('div_jump_page').value+"&file_name_like="+document.getElementById('search_context').value;
        }
      }
    }
    function search(){

      location= "${ctx}/iosLog?file_name_like="+document.getElementById('search_context').value;
    }

  </script>
</head>

<body>
<br><center><h1 class="v">Install ipa Page</h1></center>

<table style="margin-left: 50px" width="98%">
  <tr>
    <td width="35%">
<br><a href="${ctx}/devicechose" style="font-size:30pt;">Pack Page</a><br><br><br><br>
      <a href="http://${systemip}:8080/down/iostools.html" style="font-size:30pt;">Ios Tools Page</a><br><br><br><br>
      <a href="${ctx}/iosreadydown" style="font-size:30pt;">ready_package</a><br><br><br><br>
    </td>
    <td  class="v3" width="65%" style="text-align: left">
  文件名:
  <input type="text" style="font-size: 30px;height: 50px;width: 250px" id="search_context" width="10px" value="${context_search}">
  <input type="button" style="font-size: 30px;height: 50px;width: 150px" id="search_button" onclick="search()" value="查询">
  <br><br>
  <%--<a href="${ctx}/androidLog?jump_type=0">首页</a>
  <a href="${ctx}/androidLog?jump_type=2&cur_page=${cur_page_num}">上一页</a>
  <a href="${ctx}/androidLog?jump_type=3&cur_page=${cur_page_num}">下一页</a>
  <a href="${ctx}/androidLog?jump_type=1">尾页</a>
--%>
  <a id="div_cur_page">当前页:${cur_page_num}</a>
  <a href="javascript:go(5)">跳转到:</a>
  <input type="text" style="font-size: 30px;height: 50px;width: 250px" id="div_jump_page" width="10px" value="${cur_page_num}">页<br><br>
  总页数为:${page_num}  总数据量为:${all_nums}
      </td>
    </tr>
  </table>

<p>
<a href="javascript:go(1)" style="margin-left: 10%;font-size: 40px;">首页</a>
<a href="javascript:go(2)" style="margin-left: 10%;font-size: 40px;">上一页</a>
<a href="javascript:go(3)" style="margin-left: 10%;font-size: 40px;">下一页</a>
<a href="javascript:go(4)" style="margin-left: 10%;font-size: 40px;">尾页</a>
</p>

<table style='width:95%' align="center">
  <br>
  <tr><td width="95%" height="200px" align="center"><a class="v" href="http://${systemip}:8080/downipa/ca/app.pem" style="font-size:30pt;">Click to Trust Me First!<br>(From the Root Authority)</a></td></tr>
  <br>

  <c:forEach var="item" items="${iosLogs}">

    <tr><td width="85%" height="200px"><p class='v'><a href="itms-services://?action=download-manifest&url=https://192.168.2.203:8443/downipa/${downipa_filename}/${item.file_name}/online/manifest.plist" >${item.file_name}</a><br></p></td>
    <td width="10%" height="200px"><p class='v1'><a href="/download?file_url=${item.store_root_path}/${item.store_path}/${item.file_name}/online/${item.product_name}.ipa" >Down</a></p></td>
    </tr>


  </c:forEach>
</table>
<p>
  <a href="javascript:go(1)" style="margin-left: 10%;font-size: 40px;">首页</a>
  <a href="javascript:go(2)" style="margin-left: 10%;font-size: 40px;">上一页</a>
  <a href="javascript:go(3)" style="margin-left: 10%;font-size: 40px;">下一页</a>
  <a href="javascript:go(4)" style="margin-left: 10%;font-size: 40px;">尾页</a>
</p>
</body>
</html>