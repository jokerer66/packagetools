<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <script type="text/javascript" src="jquery-1.2.6.min.js"></script>
  <title>文件上传</title>
</head>
<body>

<style>
  .table-b table {
    border: 1px solid #F00
  }
  table.gridtable {
    font-family: verdana, arial, sans-serif;
    font-size: 11px;
    border-width: 1px;
    border-color: #666666;
    border-collapse: collapse;
  }
  table.gridtable th {
    border-width: 1px;
    color: #FFFFFF;
    padding: 8px;
    border-style: solid;
    border-color: #08575B;
    background-color: #7F4614;
  }
  table.gridtable td {
    height: 40px;
    border-width: 1px;
    padding: 8px;
    color: #08575B;
    border-style: solid;
    border-color: #08575B;
    background-color: #FFF8DC;
  }
  .title_2 {
    text-decoration: none;
    color: #862e32;
    font-size: 30px;
  }
</style>

<center>
  <p class="title_2">文件上传页面</p>
  <br>单独文件下载，文件名不要加-，直接上传，然后到“临时下载页面”进行下载<br>
  </h4>
  <h3>
    <a href="${ctx}/pc">返回</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/tempdown">临时下载页面</a>
  </h3>
  <br />

  <form:form method="post" enctype="multipart/form-data"
             modelAttribute="uploadedFile" action="fileUpload">
  <div class="table-b">
    <table id="mytable" class="gridtable" width="80%">

      <tr>
        <td style="font-size: 20px;" width="150px">上传普通文件:&nbsp;</td>
        <td><input style="font-size: 20px;" type="file" name="file"/><form:errors path="file" /></td>
          <%--<td style="font-size: 20px;height: 100px;width: 200px;" style="color: red; font-style: italic;"><form:errors path="file" /></td>--%>
        <td><input style="width: 200px;height:150px;" type="submit" value="上传"/></td>
      </tr>


    </table>
    </form:form>
    <form:form method="post" enctype="multipart/form-data"
               modelAttribute="uploadedFile" action="androidfileUpload">
    <div class="table-b">
      <table id="mytable" class="gridtable" width="80%">

        <tr>
          <td width="25%" style="font-size: 20px;">Android产品选择:
            <select id="andselect_productname" name ="select_productname">
              <c:forEach var="productname" items="${productnamelist_android}" varStatus="id">
                <option value="${productname}">${productname}</option>
              </c:forEach>
            </select>
          </td>
          <td width="25%" style="font-size: 20px;">版本号(如1.8.0):&nbsp;<input id="andversion" name="version" value="">
          </td>
        </tr>
        <tr height="200px">

          <td style="font-size: 20px;text-align:left;">选择下载到本地googleplay apk文件:&nbsp;
            <input style="font-size: 20px;" type="file" name="file"/>
          </td>
          <td><input style="width:200px;height:150px;text-align:center;" type="submit" value="开始上传apk到服务器"
                     style="text-align:center"/></td>
        </tr>
      </table>
      </form:form>

      <form:form method="post" enctype="multipart/form-data"
                 modelAttribute="uploadedFile" action="iosfileUpload">
      <div class="table-b">
        <table id="mytable" class="gridtable" width="80%">
          <tr height="40px" >
            <td width="25%" style="font-size: 20px;">IOS产品选择:
              <select id="iosselect_productname" name ="select_productname">
                <c:forEach var="productname" items="${productnamelist_ios}" varStatus="id">
                  <option value="${productname}">${productname}</option>
                </c:forEach>
              </select>
            </td>
            <td width="25%" style="font-size: 20px;">版本号(如1.8.0):&nbsp;<input id="iosversion" name="version" value=""></td>
          </tr>
          <tr height="200px" >
            <td style="font-size: 20px;text-align:left;">选择下载到本地的appstore ipa文件:&nbsp;<input style="font-size: 20px;"
                                                                                             type="file" name="file"/></td>
            <td><input style="width:200px;height:150px;text-align:center;" type="submit" value="开始上传ipa到服务器"
                       style="text-align:center"/></td>
          </tr>
        </table>
        </form:form>
        <br>

      </div>
    </div>
  </div>
</center>
</body>
</html>