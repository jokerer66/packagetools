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
    <title>Add SaveLink Info Page</title>

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

        //检查输入的各项是否合法
        function button_checklogic() {
            var flag = 0;
            //判断packname取值是否正确
            if (!isTrueName(/^[a-zA-Z0-9\.-]{2,40}$/, document.getElementById('text_projectname').value)) {
                alert("项目名称输入不合法，请输入2个以上的英文或数字字符");
                flag = 1;
            } else if (!isTrueName(/^http/ || /^https/, document.getElementById('text_link').value)) {
                alert("链接地址不合法，请以https或http开头");
                flag = 1;
                ///^[\w\.-]{5,40}$/
            } else if (!isTrueName(/^[\u4e00-\u9fa5_a-zA-Z0-9\s\\.-]{5,40}$/, document.getElementById('text_linkname').value)) {
                alert("链接名称输入不合法，请输入5个以上的中文或英文字符可含数字中划线和空格");
                flag = 1;
            }
            return flag;
        }


        //方法1：新增savelink数据到数据库
        function save_data() {
            var js_projectname = document.getElementById('text_projectname').value;
            var js_linkname = document.getElementById('text_linkname').value;
            var js_link = document.getElementById('text_link').value;
            var js_username = document.getElementById('text_username').value;
            var js_passwd = document.getElementById('text_passwd').value;
            if (confirm('确定保存?')) {
                var flag = button_checklogic();
                if (flag == 0) {
                    if (js_linkname != null || js_link != null) {
                        $.post("${ctx}/savelinkinfopage/savelinkinfo?projectname=" + js_projectname + "&linkname=" + js_linkname + "&link=" + js_link + "&username=" + js_username + "&passwd=" + js_passwd
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
                                    alert("新链接数据库中已存在");
                                } else {
                                    alert("保存数据失败！");
                                    location.reload();
                                }
                            });
                    } else {
                        alert('工程名称、link为必填项');
                        location.reload();
                    }
                }//校验失败什么都不做
            }
        }

        <%--//检查数据库中是否存在该链接名称，存在则更新，不存在则新增--%>
        <%--function button_checkrepeat() {--%>
            <%--var linkname = document.getElementById('linkname').value;--%>
            <%--if (linkname == "") {--%>
                <%--alert("请输入链接名称");--%>
            <%--} else {--%>
                <%--$.post("${ctx}/addsavelinkinfo/check_repeat?packname=" + linkname, function (data) {--%>
                    <%--if (data == "0") {--%>
                        <%--alert(linkname + "不存在数据库中");--%>
                    <%--} else {--%>
                        <%--alert(linkname + "已经存在数据库中");--%>
                    <%--}--%>
                <%--})--%>
            <%--}--%>
        <%--}--%>
        <%--}--%>

        //方法2.根据link查询表信息
        function button_search_bylink() {
            var js_link = document.getElementById('text_link').value;
            if (js_link == "") {
                alert("请输入link地址");
                } else {
                $.post("${ctx}/savelinkinfopage/searchbylink?link=" + js_link , function (data) {
                if (data == "0") {
                alert("输入的link在savelink表中不存在");
                } else {
                        var infos = data.split("|");
//                        document.getElementById('text_pid').innerHTML = infos[0];
                        document.getElementById('text_projectname').value = infos[1];
                        document.getElementById('text_linkname').value = infos[2];
                        document.getElementById('text_link').value = infos[3];
                        document.getElementById('text_username').value = infos[4];
                        document.getElementById('text_passwd').value = infos[5];
//                        if (infos[3] == "1") {
//                            document.getElementById('text_isautopack').checked = true;
//                        } else if (infos[5] == "0") {
//                            document.getElementById('text_isautopack').checked = false;
//                        }
                    }
                })
            }
        }

        //方法4：根据packname删除记录
        function delete_data() {
            var link = document.getElementById('text_linkname').value;
            if (link == "") {
                alert("请输入link");
            } else {
                if (confirm('确定删除当前信息?')) {
                    $.post("${ctx}/savelinkinfopage/deletesavelink?link=" + link, function (data) {
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

    </script>

</head>
<body>
<center>

    <p class="title_2">新增链接页面</p>
    <a href="${ctx}/pc" class="font_20 color_blue">返回打包页面</a>
    <a href="${ctx}/savelink" class="font_20 color_blue">链接展示页面</a>
    <%--<a href="${ctx}/addsavelinkproject" class="font_20 color_blue">新增项目页面</a>--%>
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
                <td><p class="text_yellow" id="text_pid">auto create</p></td>
                <td><p class="text_yellow">数据库定位符</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">项目名称:<br>soma或hime或其他项目名</p></td>
                <td><input class="text2" type="text" id="text_projectname" value="soma"></td>
                <td><p class="text_yellow">对应的项目名称</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">链接名称:<br>(soma news预发环境后台/hime team预发环境后台)</p></td>
                <td><input class="text2" type="text" id="text_linkname" value="soma news预发环境后台"></td>
                <td><p class="text_yellow">*产品-模块功能-预发/线上-后台</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">链接地址:<br></p></td>
                <td><input class="text2" type="text" id="text_link"></td>
                <td><p class="text_yellow">*https或http均可</p></td>
            </tr>

            <tr>
                <td><p class="text_yellow">用户名:</p></td>
                <td><input class="text2" type="text" id="text_username" value="testuser"></td>
                <td><p class="text_yellow">登录的用户名称</p></td>
            </tr>
            <tr>
                <td><p class="text_yellow">密码<br></p></td>
                <td><input class="text2" type="text" id="text_passwd" value="world"></td>
                <td><p class="text_yellow">登录的用户密码</p></td>
            </tr>
            <tr>
                <td><input class="but_style" type="button" onclick="button_search_bylink()" id="button_search_bylink"
                           value="通过链接查询"></td>
                <td><input class="but_style" type="button" onclick="save_data()" id="button_submit" value="保存"></td>
                <td><input class="but_style" type="button" onclick="delete_data()" id="delete_data"
                           value="删除该条记录"></td>
                <%--<td><input class="but_style" type="button" onclick="button_search_bylinkname()"--%>
                           <%--id="button_search_bylinkname"--%>
                           <%--value="通过链接名称查询"></td>--%>
            </tr>

        </table>
    </div>

    <br><br>

</center>
</body>
</html>