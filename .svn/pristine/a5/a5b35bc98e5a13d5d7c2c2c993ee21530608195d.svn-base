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

<center>
  <h2>请选择文件进行上传</h2>
    <br>单独文件下载，文件名不要加-，直接上传，然后到“临时下载页面”进行下载<br>
  </h4>
  <h3>
    <a href="${ctx}/pc">返回</a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <a href="${ctx}/tempdown">临时下载页面</a>
  </h3>
  <br />
  <form:form method="post" enctype="multipart/form-data"
             modelAttribute="uploadedFile" action="fileUpload">
    <table height="200px" width="700px">
      <tr height="160px" >
        <td style="font-size: 20px;" width="150px">上传文件:&nbsp;</td>
        <td><input style="font-size: 20px;" type="file" name="file" />
        </td>
        <td style="font-size: 20px;height: 100px;width: 200px;" style="color: red; font-style: italic;"><form:errors
                path="file" />
        </td>
      </tr>
      <tr>
        <td>&nbsp;</td>
        <td><input style="width: 400px;height:100px;" type="submit" value="上传" />
        </td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </form:form>
  <form:form method="post" enctype="multipart/form-data"
             modelAttribute="uploadedFile" action="androidfileUpload">
    <table height="250px"  width="700px">
      <tr height="40px" >
        <td width="25%" style="font-size: 20px;" >产品选择:</td>
        <td width="25%" style="font-size: 20px;">
          <select id="andselect_productname" name ="select_productname">
            <c:forEach var="productname" items="${productnamelist_android}" varStatus="id">
              <option value="${productname}">${productname}</option>
            </c:forEach>
          </select>
        </td>
        <td width="25%" style="font-size: 20px;" >版本号:&nbsp;</td>
        <td width="25%" style="font-size: 20px;"><input  id="andversion" name="version" value=""></td>
      </tr>
      <tr height="200px" >

        <td style="font-size: 20px;text-align:left;">上传android googleplay apk文件:&nbsp;</td>
        <td><input style="font-size: 20px;" type="file" name="file" />
        </td>
        <td style="font-size: 20px;height: 100px;width: 200px;" style="color: red; font-style: italic;"><form:errors
                path="file" />
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td><input style="width: 400px;height:100px;text-align:left;" type="submit" value="上传apk包" />
        </td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </form:form>

  <form:form method="post" enctype="multipart/form-data"
             modelAttribute="uploadedFile" action="iosfileUpload">
    <table height="250px"  width="700px">
      <tr height="40px" >
        <td width="25%" style="font-size: 20px;" >产品选择:</td>
        <td width="25%" style="font-size: 20px;">
          <select id="iosselect_productname" name ="select_productname">
            <c:forEach var="productname" items="${productnamelist_ios}" varStatus="id">
              <option value="${productname}">${productname}</option>
            </c:forEach>
          </select>
        </td>
        <td width="25%" style="font-size: 20px;" >版本号:&nbsp;</td>
        <td width="25%" style="font-size: 20px;"><input  id="iosversion" name="version" value=""></td>
      </tr>
      <tr height="200px" >

        <td style="font-size: 20px;text-align:left;">上传ios appstore ipa文件:&nbsp;</td>
        <td><input style="font-size: 20px;" type="file" name="file" />
        </td>
        <td style="font-size: 20px;height: 100px;width: 200px;" style="color: red; font-style: italic;"><form:errors
                path="file" />
        </td>
      </tr>

      <tr>
        <td>&nbsp;</td>
        <td><input style="width: 400px;height:100px;text-align:left;" type="submit" value="上传ipa包" />
        </td>
        <td>&nbsp;</td>
      </tr>
    </table>
  </form:form>
<br>


</center>
</body>
</html>
