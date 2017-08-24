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
        //保存数据到数据库
        function save_data_globalset() {
            var js_sdkinfo = document.getElementById('text_sdkinfo').value;
            var js_code_path = document.getElementById('text_code_path').value;
            var js_android_pack_path = document.getElementById('text_android_pack_path').value;
            var js_ios_pack_path = document.getElementById('text_ios_pack_path').value;
            var js_tomcat_path = document.getElementById('text_tomcat_path').value;
            var js_downipa_filename = document.getElementById('text_downipa_filename').value;
            var js_svnusername = document.getElementById('text_svnusername').value;
            var js_svnpassword = document.getElementById('text_svnpassword').value;
            var js_hostip = document.getElementById('text_hostip').value;
            var js_autopackstarthour = document.getElementById('text_autopackstarthour').value;
            var js_autopackstartminute = document.getElementById('text_autopackstartminute').value;
            var js_autopackperiod = document.getElementById('text_autopackperiod').value;
            var js_httprequest = document.getElementById('text_httprequest').value;
            js_httprequest = js_httprequest.replace("&","\&");
            if (confirm('确定保存?')) {
                $.post("${ctx}/globalset/saveglobalset?js_sdkinfo=" + js_sdkinfo
<<<<<<< HEAD
                        + "&js_code_path=" + js_code_path +"&js_android_pack_path=" + js_android_pack_path+"&js_ios_pack_path=" + js_ios_pack_path
                        + "&js_tomcat_path=" + js_tomcat_path + "&js_downipa_filename=" + js_downipa_filename + "&js_svnusername=" + js_svnusername + "&js_svnpassword="+ js_svnpassword+"&js_hostip="+js_hostip
                        + "&js_autopackstarthour=" +js_autopackstarthour +"&js_autopackstartminute=" + js_autopackstartminute+ "&js_autopackperiod=" + js_autopackperiod +"&js_httprequest="+js_httprequest
                        , function (data) {
                            if(data == 1){
                                alert("修改成功");location.reload();
                            }else{
                                alert("修改失败");
                            }

                        })

=======
                    + "&js_code_path=" + js_code_path +"&js_android_pack_path=" + js_android_pack_path+"&js_ios_pack_path=" + js_ios_pack_path
                    + "&js_tomcat_path=" + js_tomcat_path + "&js_downipa_filename=" + js_downipa_filename + "&js_svnusername=" + js_svnusername + "&js_svnpassword="+ js_svnpassword+"&js_hostip="+js_hostip
                    + "&js_autopackstarthour=" +js_autopackstarthour +"&js_autopackstartminute=" + js_autopackstartminute+ "&js_autopackperiod=" + js_autopackperiod
                    , function (data) {
                        if(data == 1){
                            alert("修改成功");location.reload();
                        }else{
                            alert("修改失败");
                        }
                    })
>>>>>>> origin/master
            }
        }
        function reset_autopacktimer(){
            var js_autopackstarthour = document.getElementById('text_autopackstarthour').value;
            var js_autopackstartminute = document.getElementById('text_autopackstartminute').value;
            var js_autopackperiod = document.getElementById('text_autopackperiod').value;
            if (confirm('确定重置?')) {
                $.post("${ctx}/globalset/resettimer?js_autopackstarthour=" +js_autopackstarthour +"&js_autopackstartminute=" + js_autopackstartminute+ "&js_autopackperiod=" + js_autopackperiod
                    , function (data) {
                        if(data == 1){
                            alert("重置成功");
                        }else{
                            alert("重置失败");
                        }
                    })
            }
        }

        function sendHttpRequest(){
            var js_httprequest = document.getElementById('text_httprequest').value;
            if (confirm('确定发送?')) {
                $.post("${ctx}/globalset/sendhttprequest?js_httprequest=" +js_httprequest
                        , function (data) {
                            if(data == 1){
                                alert("发送成功");
                            }else{
                                alert("发送失败");
                            }

                        })
            }
        }
    </script>
</head>
<body>
<center>

    <p class="title_2">全局变量数据页面</p>

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
                <td><p class="text_yellow">Android的sdk路径</p></td>
                <td><input class="text2" type="text" id="text_sdkinfo" value="${globalset.sdkinfo}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">源代码下载路径</p></td>
                <td><input class="text2" type="text" id="text_code_path" value="${globalset.codepath}"></td>
                <td><p class="text_yellow">源代码下载后的存放路径</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">生成android包的存放路径</p></td>
                <td><input class="text2" type="text" id="text_android_pack_path" value="${globalset.android_packpath}"></td>
                <td><p class="text_yellow">生成包的存放路径</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">生成ios包的存放路径</p></td>
                <td><input class="text2" type="text" id="text_ios_pack_path" value="${globalset.ios_packpath}"></td>
                <td><p class="text_yellow">生成包的存放路径</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">tomcat路径</p></td>
                <td><input class="text2" type="text" id="text_tomcat_path" value="${globalset.tomcat_path}"></td>
                <td><p class="text_yellow">tomcat路径</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">tomcat路径下放置ipa包文件夹名<br>downipa</p></td>
                <td><input class="text2" type="text" id="text_downipa_filename" value="${globalset.downipa_filename}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">svn用户名</p></td>
                <td><input class="text2" type="text" id="text_svnusername" value="${globalset.svnusername}"></td>
                <td><p class="text_yellow">svn用户名</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">svn password</p></td>
                <td><input class="text2" type="password" id="text_svnpassword" value="${globalset.svnpassword}"></td>
                <td><p class="text_yellow">svn password</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">部署服务器的访问域名:<br>xxx.com</p></td>
                <td><input class="text2" type="text" id="text_hostip" value="${globalset.hostip}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">自动打包开始时间:小时<br></p></td>
                <td><input class="text2" type="text" id="text_autopackstarthour" value="${globalset.autopackstarthour}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">自动打包开始时间:分钟<br></p></td>
                <td><input class="text2" type="text" id="text_autopackstartminute" value="${globalset.autopackstartminute}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">自动打包间隔时间(分钟)<br></p></td>
                <td><input class="text2" type="text" id="text_autopackperiod" value="${globalset.autopackperiod}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">http请求<br></p></td>
                <td><input class="text2" type="text" id="text_httprequest" value="${globalset.httprequest}"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <tr>
                <%--<td><input class="but_style" type="button" onclick="search_project()"  value="通过projectname查询"></td>--%>
                <td><input class="but_style" type="button" onclick="save_data_globalset()"  value="保存"></td>
                <td><input class="but_style" type="button" onclick="reset_autopacktimer()" value="重置自动打包定时器"></td>
                    <td><input class="but_style" type="button" onclick="sendHttpRequest()" value="立即发送http请求"></td>
            </tr>

        </table>
    </div>

    <br><br>

    2017
</center>
</body>
</html>