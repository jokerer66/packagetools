<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="${ctx}/static/plugins/js/plugins/jquery/jquery-2.1.0.min.js"></script>
<script src="${ctx}/static/plugins/js/plugins/jquery/jquery-ui.min.js"></script>
<script src="${ctx}/static/plugins/js/jquery-1.11.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="${ctx}/static/plugins/css/mystyle.css">
<html>
<head>
    <title>Add Package Info Page</title>

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
        //检查用户名是否是否在数据库中已存在，如果存在则更新，如果不存在则新增
        function button_checkrepeat() {
            var packname = document.getElementById('text_packname').value;
            if (packname == "") {
                alert("请输入打包项目名称");
            } else {
                $.post("${ctx}/addinfo/check_repeat?packname=" + packname, function (data) {
                    if (data == "0") {
                        alert(packname + "不存在数据库中");
                    } else {
                        alert(packname + "已经存在数据库中");
                    }
                })
            }
        }
        //检查输入的各项是否合法
        function button_checklogic() {
            var flag = 0;
            //判断packname取值是否正确
            if (!isTrueName(/^[a-zA-Z0-9\.-]{10,40}$/, document.getElementById('text_packname').value)) {
                alert("打包项目名称输入不合法");
                flag = 1;
            } else if (!isTrueName(/^https./, document.getElementById('text_svnurl').value)) {
                alert("svn地址不合法，请以https://开头");
                flag = 1;
            } else if (!isTrueName(/[0-9][.][0-9]{1,3}[.][0-9]{1,3}/, document.getElementById('text_main_version').value)) {
                alert("主版本号不合法");
                flag = 1;
            }
            return flag;
        }
        //查询当前packname的数据
        function button_search() {
            var packname = document.getElementById('text_packname').value;
            if (packname == "") {
                alert("请输入打包项目名称");
            } else {
                $.post("${ctx}/addinfo/search_svninfo?packname=" + packname, function (data) {
                    if (data == "0") {
                        alert("打包项目名称在数据库svninfo表或config表中不存在");
                    } else {
                        var infos = data.split("|");
                        document.getElementById('text_pid').innerHTML = infos[0];
                        document.getElementById('text_packname').value = infos[1];
                        document.getElementById('text_svnurl').value = infos[2];
                        document.getElementById('text_main_version').value = infos[3];
                        document.getElementById('select_projectinfo').value = infos[4];
                        if (infos[5] == "1") {
                            document.getElementById('text_isautopack').checked = true;
                        } else if (infos[5] == "0") {
                            document.getElementById('text_isautopack').checked = false;
                        }
                    }
                })
            }
        }
        //保存数据到数据库
        function save_data() {
            var js_projectname = document.getElementById('select_projectinfo').value;
            var js_pid = document.getElementById('text_pid').innerText;
            var js_packname = document.getElementById('text_packname').value;
            var js_svnurl = document.getElementById('text_svnurl').value;
            var js_main_version = document.getElementById('text_main_version').value;
            var js_isautopack = 0;
            if (document.getElementById('text_isautopack').checked) {
                js_isautopack = 1;
            } else {
                js_isautopack = 0;
            }
            if (confirm('确定保存?')) {
                var flag = button_checklogic();
                if (flag == 0) {
                    if (js_packname != null || js_svnurl != null) {
                        $.post("${ctx}/addinfo/saveInfoquick?ctr_pid=" + js_pid + "&ctr_projectname=" + js_projectname + "&ctr_packname=" + js_packname + "&ctr_svnurl=" + js_svnurl + "&ctr_main_version=" + js_main_version + "&ctr_isautopack=" + js_isautopack
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
                    } else {
                        alert('打包项目名称、svn路径为必填项');
                        location.reload();
                    }
                }//校验失败什么都不做
            }
        }
        function delete_database() {
            var packname = document.getElementById('text_packname').value;
            if (packname == "") {
                alert("请输入打包项目名称");
            } else {
                if (confirm('确定删除当前打包工程信息?')) {
                    $.post("${ctx}/addinfo/delete_svninfo_config?packname=" + packname, function (data) {
                        if (data == "0") {
                            alert("删除成功");
                            location.reload();
                        } else {
                            alert("删除失败");
                            location.reload();
                        }
                    });
                } else {
                    location.reload();
                }
            }
        }
        function delete_dsym() {
            if (confirm('确定删除七天前的sdym文件?')) {
                $.post("${ctx}/addinfo/delete_dsym", function (data) {
                    if (data == "0") {
                        alert("删除成功");
                        location.reload();
                    } else {
                        alert("删除失败");
                        location.reload();
                    }
                });
            } else {
                location.reload();
            }
        }
        function changeproject(projectname) {
            if (projectname.indexOf("ios") != -1) {
                button_ios();
            } else if (projectname.indexOf("android") != -1) {
                button_android();
            }
        }
    </script>

</head>
<body>
<center>

    <p class="title_2">新增打包数据页面</p>

    <a href="${ctx}/pc" class="font_20 color_blue">返回</a>
    <a href="${ctx}/addproject" class="font_20 color_blue">项目信息的配置</a>
    <a href="${ctx}/globalset" class="font_20 color_blue">环境全局配置</a>
    <br><br>


    <div class="table-b">
        <table id="mytable" class="gridtable" width="80%">

            <tr>
                <th width="25%">数据库字段</th>
                <th width="55%">取值</th>
                <th width="20%">备注</th>

            </tr>
            <tr>
                <td><p class="text_yellow">项目配置:</p></td>

                <td>
                    <select class="text2" onChange="changeproject(this.value);" id="select_projectinfo">
                        <c:forEach var="projectmodel" items="${projectlist}" varStatus="id">
                            <option value="${projectmodel.projectname}">${projectmodel.projectname}</option>
                        </c:forEach>
                    </select>
                </td>

                <td><p class="text_yellow">打包链接对应内的项目</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">packname id:</p></td>
                <td><p class="text_yellow" id="text_pid">do not need to write</p></td>
                <td><p class="text_yellow">数据库定位符</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">打包项目名称:<br>(XXX-ios/android-1.x.x-debug-online)</p></td>
                <td><input class="text2" type="text" id="text_packname" value="XXX-ios-1.1.3-debug-online"></td>
                <td><p class="text_yellow">*产品-平台-版本-模式-服务器类型</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">svn地址:<br></p></td>
                <td><input class="text2" type="text" id="text_svnurl"></td>
                <td><p class="text_yellow">*https协议或svn协议都可以</p></td>
            </tr>


            <tr>
                <td><p class="text_yellow">版本号(1.x.x):</p></td>
                <td><input class="text2" type="text" id="text_main_version" value="1.1.3"></td>
                <td><p class="text_yellow">主版本号</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">isautopack<br></p></td>
                <td><input class="text2" type="checkbox" id="text_isautopack" value="0"></td>
                <td><p class="text_yellow"></p></td>
            </tr>
            <%--<tr>--%>
            <%--<td><p class="text_yellow">ipa包最终存储路径:</p></td>--%>
            <%--<td><input class="text2" type="text" id="text_store_root_path" value="/Users/${system_log_user_name}/Desktop/ios_build">--%>
            <%--</td>--%>
            <%--<td><p class="text_yellow">保持默认即可</p></td>--%>
            <%--</tr>--%>


            <!--ios企业版的地址以及需要执行的脚本的处理,可以置空-->
            <%--<tr>--%>
            <%--<td><p class="text_yellow">企业版上传脚本路径:</p></td>--%>
            <%--<td><input class="text2" type="text" id="text_enterprise_path" value="">--%>
            <%--</td>--%>
            <%--<td><p class="text_yellow">企业版上传的根路径,如果mode=debug或不需要传到其他服务器此处可置空处理。如果有其他存放路径请输入位置，如:/Users/${system_log_user_name}/Desktop/ios_build</p></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
            <%--<td><p class="text_yellow">企业版上传脚本名称:</p></td>--%>
            <%--<td><input class="text2" type="text" id="text_enterprise_name" value="">--%>
            <%--</td>--%>
            <%--<td><p class="text_yellow">企业版上传的脚本名称,如果mode=debug或不需要上传到初136以外的其他服务器，此处可置空处理.如果需要启用脚本请以sh结尾，如:upload_enterprise_ios.sh</p></td>--%>
            <%--</tr>--%>

            <tr>
                <td><input class="but_style" type="button" onclick="button_search()" id="button_search"
                           value="通过packname查询"></td>
                <td><input class="but_style" type="button" onclick="save_data()" id="button_submit" value="保存"></td>

            </tr>

            <tr>
                <td><input class="but_style" type="button" onclick="delete_database()" id="button_delete_database"
                           value="删除该条打包项目"></td>
                <td><input class="but_style" type="button" onclick="delete_dsym()" id="button_delete_dsym"
                           value="删除七天前的dSYM文件"></td>
            </tr>

        </table>
    </div>

    <br><br>

    2017
</center>
</body>
</html>