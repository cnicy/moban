<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../core/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <script src="${ctx}/js/jquery-1.11.3.min.js" type="text/javascript" charset="UTF-8"></script>
    <script src="${ctx}/js/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/allin.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/jquery.form.js" type="text/javascript" charset="utf-8"></script>
    <script src="${ctx}/js/jquery.utils.js" type="text/javascript" charset="utf-8"></script>
    <link  href="${ctx}/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body>
    <div class="place">
        <span>位置：</span>
        <ul class="placeul">
            <li><a href="#">首页</a></li>
            <li><a href="#">数据表</a></li>
            <li><a href="#">基本内容</a></li>
        </ul>
    </div>
    <div class="rightinfo">
        <div class="tools">
            <ul class="toolbar">
                <li id="addBtn" class="click"><span><img src="${ctx}/images/blue/t01.png" /></span>添加</li>
                <li id="editBtn" class="click"><span><img src="${ctx}/images/blue/t02.png" /></span>修改</li>
                <li id="deleteBtn" class="click"><span><img src="${ctx}/images/blue/t03.png" /></span>删除</li>
            </ul>
            <ul class="toolbar1">
                <li><span><img src="${ctx}/images/blue/t05.png" /></span>设置</li>
            </ul>
        </div>
    </div>

    <div>
        <table id="tt"></table>
    </div>

    <div id="userDialog" class="easyui-dialog" title="操作窗口" style="width:300px;height:250px;padding:10px"
         data-options="closed:true,modal:true,buttons:'#dlg-buttons'">
        <div class="tab">
            <form class="easyui-form" id="userForm" method="post">
                <fieldset>
                    <legend>带<b>*</b>为必填项</legend>
                    <table border="0">
                        <tr>
                            <td><input id="id" name="id" type="hidden"/></td>
                        </tr>
                        <tr>
                            <td>名称<b>*</b></td><td><input id="name" name="name" type="text" class="easyui-validatebox" data-options="required:true,missingMessage:'角色名称不能为空!'"/></td>
                        </tr>
                        <tr>
                            <td>密码<b>*</b></td><td><input id="pswd" name="pswd" type="text" class="easyui-validatebox" data-options="required:true,missingMessage:'英文名称不能为空!',validType:'checkRoleEname'"/></td>
                        </tr>
                        <tr>
                            <td>类型<b>*</b></td><td><input id="type" name="type" type="text" class="easyui-validatebox"/></td>
                        </tr>
                    </table>
                </fieldset>
            </form>
        </div>
    </div>

    <div id="dlg-buttons">
        <a id="submitFormBtn" href="javascript:void(0)" iconCls="icon-ok" class="easyui-linkbutton">提交</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#userDialog').dialog('close')">关闭</a>
    </div>

    <script type="text/javascript" charset="UTF-8">
        $(function(){
            regBtnEvents();
            page();
        });

        function pagenation(){
            var pager = $('#tt').datagrid('getPager');	// get the pager of datagrid
            pager.pagination({
                layout:['list','sep','first','prev','links','next','last','sep','refresh']
            });
        }
        function page(){
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
            pagenation();
        }
        var url=null;
        function regBtnEvents(){
            // 添加按钮
            $("#addBtn").click(function(e){
                $('#userForm').form('clear');
                url = "add";
                $("#userDialog").dialog("open");
            });
            // 编辑按钮
            $("#editBtn").click(function(e){
                $('#userForm').form('clear');
                editDialog();
            });
            // 删除按钮
            $("#deleteBtn").click(function(e){
               del();
            });
            // 提交按钮
            $('#submitFormBtn').click(function(e) {
                options.url = '${ctx}/user/'+url;
                $('#userForm').ajaxSubmit(options);
                return false;
            });
        }
        var options = {
            beforeSubmit: function(){
                if($('#userForm').form('validate')){
                    pro();
                    $('#userDialog').dialog('close');
                    return true;
                }else return false;
            }, success: function(responseText){
                hide();
                $.messager.alert('提示',responseText, 'info');
                $('#tt').datagrid('clearSelections');
                $('#tt').datagrid('reload');
            }, error:function(resp){
                hide();
                $('#roleDialog').dialog('open');
                $.messager.alert('提示',eval(resp.responseText), 'info');
            }, type:'post'
        };
        function editDialog(){
            url = "edit";
            var id = getGridSelect();
            if(!id) return ;
            $.ajax({type:'GET',url:'${ctx}/user/getUserById',data:{'id':id,'r':Math.random()},
                success:function(data){
                    if(data){
                        $('#id').val(data.id);
                        $('#name').val(data.name);
                        $('#pswd').val(data.pswd);
                        $('#type').val(data.type);
                        $('#userDialog').dialog('open');
                    }else{
                        $.messager.alert('提示',"暂无用户数据!",'info');
                        hide();
                    }
                },error:function(res){
                    alert(res.responseText);
                },dataType:'json'
            });
        }
        function del(){
            var ids =  getGridSelects();
            if(null==ids || ids.length <1)
                return null;
            $.messager.confirm('提示', '确定要删除该项目吗?', function(yes){
                if(yes){
                    pro();
                    $.ajax({type:'GET',url:'${ctx}/user/delete',data:{'ids':ids},
                        success:function(text,status,xhr){
                            $.messager.alert('提示',eval(xhr.responseText),'info');
                            $('#tt').datagrid('clearSelections');
                            $('#tt').datagrid('reload');
                            hide();
                        },error:function(res){
                            hide();
                            $.messager.alert('提示',eval(res.responseText),'warning');
                        }, dataType:'json'
                    });
                }
            });
        }
        // 获取单选
        function getGridSelect(){
            var chks= $('#tt').datagrid('getChecked');
            if(null!=chks && chks.length==1){
                return chks[0].id;
            }else{
                $.messager.alert('提示',"请选择一项进行操作!",'warning');
                return null;
            }
        }
        // 获取多选
        function getGridSelects(){
            var chks= $('#tt').datagrid('getChecked');
            if(chks.length<1){
                $.messager.alert('提示','请至少选择一项!','warning');
                return ;
            }
            var arr = new Array();
            for(var c in chks){
                arr[c] = chks[c].id;
            }
            return arr;
        }
    </script>
</body>
</html>