<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2016/12/15
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${ctx}/static/plugins/js/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/static/plugins/js/plugins/jquery/jquery-ui.min.js"></script>
<script src="${ctx}/static/plugins/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/css/mystyle.css">
<html>
<head>
    <title>addproject</title>
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

        .text_stat {
            color: #862e32;
        }

        .text2 {
            width: 99%;
            height: 100%;
            border: 0;
            padding-left: 5px;
            padding-right: 5px;
            margin: 0;
            background: #ffffff;
        }
        .text3 {
            width: 99%;
            height: 120px;
            border: 0;
            padding-left: 5px;
            padding-right: 5px;
            margin: 0;
            background: #ffffff;
        }

        .title_2 {
            text-decoration: none;
            color: #862e32;
            font-size: 30px;
        }

        .text_yellow {
            width: 100%;
            height: 100%;
            border: 0;
            padding: 0;
            margin: 0;
            background: #FFF8DC;
        }

        .but_style {
            width: 100%;
            height: 100%;
            border: 0;
            padding: 0;
            margin: 0;
            background: #EEE8AA;
            color: #08575B
        }
    </style>

    <script language="JavaScript">

        //判断输入的各项内容是否合法的正则表达式,pattern的值前后需要//包起来
        function isTrueName(patrn, s) {
            if (!patrn.exec(s)) return false
            return true
        }

        function resetit(){
            if (confirm('确定重置?')) {
                document.getElementById('text_proid').innerText="do not need to write";
                document.getElementById('text_projectname').value="xxx-ios-online";
                document.getElementById('text_productname').value="xxx";
                document.getElementById('text_iosbundleid').value="com.xxx";
                document.getElementById('text_onoff').value="1";
                document.getElementById('text_platform').value="ios";
                document.getElementById('text_before_context').value="";
                document.getElementById('text_after_context').value="";
                document.getElementById('text_iosbuildtype').value="Automatic";
                document.getElementById('text_proversion_profile').value="";
                document.getElementById('text_iosapp_path').value="/build/XXX/Build/Products/Release-iphoneos/XXX.app";
            }
        }



        //检查用户名是否是否在数据库中已存在，如果存在则更新，如果不存在则新增

        function addprojectinfo(){

            var js_proid = document.getElementById('text_proid').innerText;
            var js_projectname = document.getElementById('text_projectname').value;
            var js_productname = document.getElementById('text_productname').value;
            var js_iosbundleid = document.getElementById('text_iosbundleid').value;
            var js_onoff = document.getElementById('text_onoff').value;
            var js_platform = document.getElementById('text_platform').value;
            var js_before_context = document.getElementById('text_before_context').value;
//            var js_on_context = document.getElementById('text_on_context').value;
            var js_on_context = "null";
            var js_after_context = document.getElementById('text_after_context').value;
            var js_iosbuildtype = document.getElementById('text_iosbuildtype').value;
            var js_proversion_profile = document.getElementById('text_proversion_profile').value;
            var js_iosapp_path = document.getElementById('text_iosapp_path').value;


            js_before_context =  js_before_context.replace(/\n|\r\n/g,"<br>");
            js_after_context =  js_after_context.replace(/\n|\r\n/g,"<br>");
            $.post("${ctx}/addproject/saveproject?js_proid=" + js_proid + "&js_projectname=" + js_projectname +"&js_productname="+js_productname+"&js_iosbundleid="+js_iosbundleid+
                    "&js_onoff=" + js_onoff + "&js_platform=" + js_platform
            + "&js_before_context=" + js_before_context + "&js_on_context=" + js_on_context + "&js_after_context=" + js_after_context
            + "&js_iosbuildtype=" + js_iosbuildtype + "&js_proversion_profile=" + js_proversion_profile +"&js_iosapp_path="+js_iosapp_path
            , function (data) {
                if (data == "0") {
                alert("新增失败");
                } else if (data == "1") {
                alert("新增成功");
                } else if (data == "2") {
                    alert("编辑失败");
                } else if (data == "3") {
                    alert("编辑成功");
                } else if (data == "4") {
                    alert("新名字数据库中已存在");
                } else {
                    alert("保存数据失败！");
                    location.reload();
                }
            });
        }


        //检查输入的各项是否合法
        function button_checklogic() {
            var flag = 0;
            //判断packname取值是否正确
            if (!isTrueName(/^[a-zA-Z0-9\.-]{10,40}$/, document.getElementById('text_packname').value)) {

                alert("打包项目名称输入不合法");
                flag = 1;

            } else if (document.getElementById('text_sdkinfo').value == "") {

                alert("sdk不能为空");
                flag = 1;

            } else if (!isTrueName(/[0-2]/, document.getElementById('text_onoff').value)) {

                alert("服务器类型输入不合法");
                flag = 1;

            } else if (!(document.getElementById('text_platform').value == "android" || document.getElementById('text_platform').value == "ios")) {

                alert("平台类型输入不合法");
                flag = 1;

            } else if (document.getElementById('text_platform').value == "ios" && document.getElementById('text_iosbuildtype').value == "manual" &&
                    (!isTrueName(/^([a-zA-Z0-9]{8}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{12})$/, document.getElementById('text_certificate').value)
                    || document.getElementById('text_certificate').value == "")){

                alert("证书名称输入不合法");
                flag = 1;

            }

            return flag;

        }
        //查询当前packname的数据
        function search_project() {

            var projectname = document.getElementById('text_projectname').value;

            if (projectname == "") {

                alert("请输入打包项目名称");

            } else {

                $.post("${ctx}/addproject/searchproject?projectname=" + projectname, function (data) {

                    if (data == "0") {
                        alert("打包项目名称在数据库中不存在");
                    } else {

                        var infos = data.split("|");

                        var reg=new RegExp("<br>","g"); //创建正则RegExp对象
                        var js_before_context =infos[6];
                        var js_on_context =infos[7];
                        var js_after_context =infos[8];
                        document.getElementById('text_proid').innerHTML = infos[0];
                        document.getElementById('text_projectname').value = infos[1];
                        document.getElementById('text_productname').value = infos[2];
                        document.getElementById('text_iosbundleid').value = infos[3];
                        document.getElementById('text_onoff').value = infos[4];
                        document.getElementById('text_platform').value = infos[5];
                        document.getElementById('text_before_context').value = js_before_context.replace(reg,"\r\n");
//                        document.getElementById('text_on_context').value = js_on_context.replace(reg,"\r\n");
                        document.getElementById('text_after_context').value = js_after_context.replace(reg,"\r\n");
                        document.getElementById('text_iosbuildtype').value = infos[9];
                        document.getElementById('text_proversion_profile').value = infos[10];
                        document.getElementById('text_iosapp_path').value = infos[11];


                    }
                })
            }
        }
        //保存数据到数据库
        function save_data_project() {


            if (confirm('确定保存?')) {
                var projectname = document.getElementById('text_projectname').value;
                if (projectname == "" || projectname == null) {

                    alert("请输入打包项目名称");

                } else {
                    $.post("${ctx}/addproject/isprojectexist?projectname=" + projectname, function (data) {
                        if(data == "0"){

                            addprojectinfo();

                        }else if(data == "1"){
                            if(document.getElementById('text_proid').innerText == "do not need to write"){
                                alert("数据库中已经存在相同的projectname");
                            }else{
                                addprojectinfo();

                            }

                        }
                    })
                }
            }
        }

        function delete_project() {

            var projectid = document.getElementById('text_proid').innerText;
            var projectname = document.getElementById('text_projectname').value;
            if (projectname == "" || projectname == null) {
                alert("请输入打包项目名称");
            } else if(projectid == "do not need to write") {
                alert("请先查询后,再进行删除")
            }else{
                if (confirm('确定删除当前工程信息?')) {
                    $.post("${ctx}/addproject/deleteproject?projectid=" + projectid, function (data) {

                        if (data == "0") {
                            alert("删除失败");
                        } else {
                            alert("删除成功");
                            location.reload();
                        }
                    });
                }

            }
        }

    </script>
</head>
<body>
<center>

    <p class="title_2">新增环境数据页面</p>

    <a href="${ctx}/addinfo" class="font_20 color_blue">返回</a>
    <br><br>


    <div class="table-b">
        <table id="mytable" class="gridtable" width="80%">

            <tr>
                <th width="25%">数据库字段</th>
                <th width="55%">取值</th>
                <th width="20%">备注</th>

            </tr>
            <tr>
                <td><p class="text_yellow">id:</p></td>
                <td><p class="text_yellow" id="text_proid">do not need to write</p></td>
                <td><p class="text_yellow">数据库定位符</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">打包环境名称:<br></p></td>
                <td><input class="text2" type="text" id="text_projectname" value="xxx-ios-online"></td>
                <td><p class="text_yellow">*产品名称-平台-环境</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">打包产品名称:<br></p></td>
                <td><input class="text2" type="text" id="text_productname" value="xxx"></td>
                <td><p class="text_yellow">*产品名称</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">ios的bundle bundle identifier:(android不填写)</p></td>
                <td><input class="text2" type="text" id="text_iosbundleid" value="com.xxx"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">服务器类型(线下0/线上1/预发2/...):</p></td>
                <td><input class="text2" type="text" id="text_onoff" value="1"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">平台类型(android/ios):</p></td>
                <td><input class="text2" type="text" id="text_platform" value="ios"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">打包前的修改环境脚本<br>目前功能未完善,代码中需要使用转义符号标示特殊符号</p></td>
                <td><textarea class="text3" type="text" id="text_before_context" value=""></textarea></td>
                <td><p class="text_yellow">修改项目的配置项<br>可使用参数$local_path表示下载下来的代码根目录<br>$svn_version表示当前svn路径下最新的svn号<br>$svn_date表示具体svn信息<br>$packinfo_onoff表示服务器类型(0/1/2)</p></td>
            </tr>

            <%--<tr>--%>
                <%--<td><p class="text_yellow">打包脚本<br>目前功能未完善,代码中需要使用转义符号标示特殊符号</p></td>--%>
                <%--<td><textarea class="text3" type="text" id="text_on_context" value=""></textarea></td>--%>
                <%--<td><p class="text_yellow">打包脚本<br>可使用参数$local_path表示下载下来的代码根目录<br>$svn_version表示当前svn路径下最新的svn号<br>$svn_date表示具体svn信息<br>$packinfo_onoff表示服务器类型(0/1/2)</p></td>--%>
            <%--</tr>--%>
            <tr>
                <td><p class="text_yellow">打包后处理的脚本<br>目前功能未完善,代码中需要使用转义符号标示特殊符号</p></td>
                <td><textarea class="text3" type="text" id="text_after_context" value=""></textarea></td>
                <td><p class="text_yellow">打出来的包的移动等操作<br>可使用参数$local_path表示下载下来的代码根目录<br>$svn_version表示当前svn路径下最新的svn号<br>$svn_date表示具体svn信息<br>$packinfo_onoff表示服务器类型(0/1/2)</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">ios所用的证书选择形式<br>(manual/Automatic)</p></td>
                <td><input class="text2" type="text" id="text_iosbuildtype" value="Automatic"></td>
                <td><p class="text_yellow">手动/自动(android不需要填写)</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">ios所用的证书<br>(若证书选择形式选择自动,无需填写)</p></td>
                <td><input class="text2" type="text" id="text_proversion_profile" value=""></td>
                <td><p class="text_yellow">(android不需要填写)</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">ios根目录下的生成的app文件所在路径<br></p></td>
                <td><input class="text2" type="text" id="text_iosapp_path" value="/build/XXX/Build/Products/Release-iphoneos/XXX.app"></td>
                <td><p class="text_yellow">(android不需要填写)</p></td>
            </tr>

            <tr>
                <td><input class="but_style" type="button" onclick="search_project()"  value="通过projectname查询"></td>
                <td><input class="but_style" type="button" onclick="save_data_project()"  value="保存"></td>
            </tr>
            <tr>
                <td><input class="but_style" type="button" onclick="resetit()"  value="重置"></td>
                <td><input class="but_style" type="button" onclick="delete_project()" value="删除该条项目"></td>
            </tr>

        </table>
    </div>

    <br><br>

    2017
</center>
</body>
</html>
