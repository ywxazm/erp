$(function(){
    $('#grid').datagrid({
        title: '员工信息列表',
        url:'empAction_listByPage',
        columns:[[
            {checkbox:true},
            {field:'uuid',title:'编号',width:100},
            {field:'username',title:'系统登陆名称',width:100},
            {field:'name',title:'真实姓名',width:100},
            {field:'gender',title:'性别',width:100,formatter:function(gender){
                switch(gender){
                    case 0: return '女';
                    case 1: return '男';
                    default: return '未知';
                }
            }},
            {field:'email',title:'电子邮箱地址',width:100},
            {field:'tele',title:'联系电话',width:100},
            {field:'address',title:'联系地址',width:100},
            {field:'birthday',title:'出生年月日',width:100,formatter:function(birthday){
                if(birthday){
                    return new Date(birthday).Format("yyyy-MM-dd");
                }
                return "";
            }},
            {field:'depDo',title:'部门',width:100,formatter:function(depDo){
                if(depDo){
                    return depDo.name;
                }
                return "";
            }},
            {field:'-',title:'操作',width:200,formatter:function(value,row,index){
                var operation = '<a href="javascript:void(0)" onclick="updatePwd_reset(' + row.uuid + ')">重置密码</a> ';
                return operation;
            }}
        ]],
        singleSelect: true,
        pagination: true,
        rownumbers: true
    });
});

function updatePwd_reset(uuid){
    $.ajax({
        url: 'empAction_resetPwd?id=' + uuid,
        type: 'get',
        success: function(rtn){
            if(rtn.success){
                $.messager.alert("提示信息", "重置密码成功!", 'info', function(){
                    //刷新列表
                    $('#grid').datagrid('reload');
                });
            }else{
                $.messager.alert("提示信息", "重置密码失败!<br>" + rtn.msg, 'info');
            }
        }
    });
}
