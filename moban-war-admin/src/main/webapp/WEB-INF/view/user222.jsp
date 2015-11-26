<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="jquery,ui,easy,easyui,web">
    <meta name="description" content="easyui help you build your web page easily!">
    <title>jQuery EasyUI Demo</title>

    <script type="text/javascript" src="${ctx}/js/jquery-1.11.3.min.js" charset="utf-8"></script>
    <script src="../../js/allin.js" type="text/javascript" charset="utf-8"></script>
    <%--<script type="text/javascript" src="http://code.jquery.com/jquery-1.6.1.min.js"></script>--%>
    <script type="text/javascript" src="${ctx}/js/jquery.easyui.min.js"></script>
    <%--<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>--%>

    <script>
        $(function(){
            $('#tt').datagrid({
                url:'${ctx}/user/page',
                pagination:true,
                rownumbers : true,
                fitColumns : true,
                collapsible : true,
                autoRowHeight : true,
                loadMsg : "数据加载中,请稍等...",
                frozenColumns : [[{field : 'ck',checkbox : true}]],

                columns:[[
                    {title:'主键id', field:'id',width:50,align:'center'},
                    {title:'名称', field:'name',width:50,align:'center'},
                    {title:'密码', field:'pswd',width:50,align:'center'},
                    {title:'类型', field:'type',width:50,align:'center'}
                ]]
            });

            var pager = $('#tt').datagrid('getPager');	// get the pager of datagrid
            pager.pagination({
                total:114,
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });
        });
    </script>
</head>
<body>
<h1>DataGrid</h1>
<table id="tt"></table>


<%--<table id="tt" title="Load Data" class="easyui-datagrid" style="width:550px;height:250px"--%>
       <%--url="${ctx}/user/page"--%>
       <%--iconCls="icon-save" pagination="true">--%>
    <%--<thead>--%>
    <%--<tr>--%>
        <%--<th field="id" width="80">Item ID</th>--%>
    <%--</tr>--%>
    <%--</thead>--%>
<%--</table>--%>
</body>
</html>