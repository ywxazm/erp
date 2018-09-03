$(function(){

    var url = 'ordersAction_listByPage';

    var btnText = "";
    var inoutTitle = "";
    //自己下的订单
    if(Request['oper']=='myorders'){
        //采购订单:   oper=myorders&type=1						//注意向html提交数据, 接收数据的方式
        if(Request['type']*1 == 1){
            url = "ordersAction_listByPage?t.type=1";
            document.title="我的采购订单";
            btnText = "采购申请";
            //显示供应商
            $('#addOrdersSupplier').html('供应商');
        }

        //销售订单:   oper=myorders&type=2
        if(Request['type']*1 == 2){
            url = "ordersAction_listByPage?t.type=2&t.state=0";
            document.title="我的销售订单";
            btnText = "销售订单录入";
            //显示客户
            $('#addOrdersSupplier').html('客户');
        }
    }

    //所有订单查询
    if(Request['oper'] == 'orders'){
        //采购订单
        if(Request['type']*1 == 1){
            url +="?t.type=1";
            document.title="采购订单查询";
        }
        //销售订单
        if(Request['type']*1 == 2){
            url +="?t.type=2";
            document.title="销售订单查询";
        }
    }


    //如果采购订单审核业务，加上state=0，只查询出未审核的订单
    if(Request['oper'] == 'doCheck'){
        url += "?t.type=1&t.state=0";
        document.title="采购订单审核";
    }
    //如果采购订单确认业务，加上state=1，只查询出已审核过的订单
    if(Request['oper'] == 'doStart'){
        url += "?t.type=1&t.state=1";
        document.title="采购订单确认";
    }
    //如果采购订单入库业务，加上state=2，只查询出已确认过的订单
    if(Request['oper'] == 'doInStore'){
        url += "?t.type=1&t.state=2";
        document.title="采购订单入库";
        inoutTitle = "入库";
    }
    //销售订单出库
    if(Request['oper'] == 'doOutStore'){
        url += "?t.type=2&t.state=0";
        document.title="销售订单出库";
        inoutTitle = "出库";
    }
    $('#grid').datagrid({
        url: url,
        columns:getColumns(),
        singleSelect:true,
        pagination:true,
        fitColumns:true,							//使列自动展开/收缩到合适的DataGrid宽度
        onDblClickRow:function(rowIndex, rowData){	//双击事件,返回rowIndex, rowData
            //rowIndex， 行的索引
            //rowData， 行里的数据
            //alert(JSON.stringify(rowData));
            //显示详情的属性, 并对详情窗口进行赋值
            $('#uuid').html(rowData.uuid);
            $('#suppliername').html(rowData.supplierName);
            $('#state').html(getState(rowData.state));
            $('#creater').html(rowData.createrName);
            $('#checker').html(rowData.checkerName);
            $('#starter').html(rowData.starterName);
            $('#ender').html(rowData.enderName);
            $('#createtime').html(rowData.createtime);
            $('#checktime').html(rowData.checktime);
            $('#starttime').html(rowData.starttime);
            $('#endtime').html(rowData.endtime);					//以上是rows中每一列的普通属性的加载
            //打开窗口
            $('#ordersDlg').dialog('open');
            //加载明细列表
            $('#itemgrid').datagrid('loadData',rowData.orderDetailDos);			//这一个是rows中某一个对象的加载方式
        }
    });

    //明细表格
    $('#itemgrid').datagrid({
        columns:[[
            {field:'uuid',title:'编号',width:100},
            {field:'goodsuuid',title:'商品编号',width:100},
            {field:'goodsname',title:'商品名称',width:100},
            {field:'price',title:'价格',width:100},
            {field:'num',title:'数量',width:100},
            {field:'money',title:'金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getDetailState}			//formatter:getDetailState 调用方法的方式要注意
        ]],
        fitColumns:true,
        singleSelect:true
    });

    //添加审核按钮
    var toolbar = new Array();
    if(Request['oper'] == 'doCheck'){
        toolbar.push({
            text:'审核',
            iconCls:'icon-search',
            handler:doCheck
        });
    }

    //添加确认按钮
    if(Request['oper'] == 'doStart'){
        toolbar.push({
            text:'确认',
            iconCls:'icon-search',
            handler:doStart
        });
    }
    //添加导出按钮
    toolbar.push({
        text:'导出',
        iconCls:'icon-excel',
        handler:doExport
    });
    $('#ordersDlg').dialog({
        toolbar:toolbar
    });
    //添加双击事件
    if(Request['oper'] == 'doInStore' ||  Request['oper'] == 'doOutStore'){
        $('#itemgrid').datagrid({
            onDblClickRow:function(rowIndex, rowData){
                //显示数据
                $('#itemuuid').val(rowData.uuid);
                $('#goodsuuid').html(rowData.goodsuuid);
                $('#goodsname').html(rowData.goodsname);
                $('#goodsnum').html(rowData.num);
                //打开出入库窗口
                $('#itemDlg').dialog('open');
            }
        });
    }
    //添加采购申请按钮
    if(Request['oper'] == 'myorders'){
        $('#grid').datagrid({
            toolbar:[
                {
                    text:btnText,
                    iconCls:'icon-add',
                    handler:function(){
                        $('#addOrdersDlg').dialog('open');
                    }
                }
            ]
        });
    }

    //出入库窗口
    $('#itemDlg').dialog({
        width:300,
        height:200,
        title:inoutTitle,
        modal:true,
        closed:true,
        buttons:[
            {
                text:inoutTitle,
                iconCls:'icon-save',
                handler:doInOutStore
            }
        ]
    });

    //增加订单的窗口
    $('#addOrdersDlg').dialog({
        title:'增加订单',
        width:700,
        height:400,
        modal:true,
        closed:true
    });
});


/**
 * 获取订单的状态
 * @param value
 * @returns
 * 采购: 0:未审核 1:已审核, 2:已确认, 3:已入库
 * 销售：0：未出库，1：已出库
 */
function getState(value){
    if(Request['type'] * 1 == 1){
        switch(value * 1){
            case 0:return '未审核';
            case 1:return '已审核';
            case 2:return '已确认';
            case 3:return '已入库';
            default: return '';
        }
    }
    if(Request['type'] * 1 == 2){
        switch(value * 1){
            case 0:return '未出库';
            case 1:return '已出库';
            default: return '';
        }
    }
}

/**
 * 获取订单明细的状态
 * 0=未入库，1=已入库
 * @param value
 */
function getDetailState(value){
    if(Request['type'] * 1 == 1){
        switch(value * 1){
            case 0:return '未入库';
            case 1:return '已入库';
            default: return '';
        }
    }
    if(Request['type'] * 1 == 2){
        switch(value * 1){
            case 0:return '未出库';
            case 1:return '已出库';
            default: return '';
        }
    }
}

/**
 * 审核
 */
function doCheck(){
    $.messager.confirm('确认', '确认要审核吗？', function(yes){
        if(yes){
            $.ajax({
                url: 'ordersAction_doCheck?id=' + $('#uuid').html(),
                dataType: 'json',
                type: 'post',
                success:function(rtn){
                    $.messager.alert('提示',rtn.msg,'info',function(){
                        if(rtn.success){
                            //关闭窗口
                            $('#ordersDlg').dialog('close');
                            //刷新表格
                            $('#grid').datagrid('reload');
                        }
                    });
                }
            });
        }
    });
}

/**
 * 确认
 */
function doStart(){
    $.messager.confirm('确认', '确定要确认吗？', function(yes){
        if(yes){
            $.ajax({
                url: 'ordersAction_doStart?id=' + $('#uuid').html(),
                dataType: 'json',
                type: 'post',
                success:function(rtn){
                    $.messager.alert('提示',rtn.msg,'info',function(){
                        if(rtn.success){
                            //关闭窗口
                            $('#ordersDlg').dialog('close');
                            //刷新表格
                            $('#grid').datagrid('reload');
                        }
                    });
                }
            });
        }
    });
}

/**
 * 出入库
 */
function doInOutStore(){
    var message = "";
    var url = "";
    if(Request['type'] * 1 == 1){
        message = "确认要入库吗？";
        url = "orderdetailAction_doInStore";
    }
    if(Request['type'] * 1 == 2){
        message = "确认要出库吗？";
        url = "orderdetailAction_doOutStore";
    }
    var formdata = $('#itemForm').serializeJSON();
    if(formdata.storeuuid == ''){
        $.messager.alert('提示','请选择仓库!','info');
        return;
    }
    $.messager.confirm("确认",message,function(yes){
        if(yes){
            $.ajax({
                url: url,
                data: formdata,
                dataType: 'json',
                type: 'post',
                success:function(rtn){
                    $.messager.alert('提示',rtn.msg,'info',function(){
                        if(rtn.success){
                            //关闭入库窗口
                            $('#itemDlg').dialog('close');
                            //设置明细的状态
                            $('#itemgrid').datagrid('getSelected').state = "1";
                            //刷新明细列
                            var data = $('#itemgrid').datagrid('getData');
                            $('#itemgrid').datagrid('loadData',data);
                            //如果所有明细都 入库了，应该关闭订单详情，并且刷新订单列表
                            var allIn = true;
                            $.each(data.rows,function(i,row){
                                if(row.state * 1 == 0){
                                    allIn = false;
                                    //跳出循环
                                    return false;
                                }
                            });
                            if(allIn == true){
                                //关闭详情窗口
                                $('#ordersDlg').dialog('close');
                                //刷新订单列表
                                $('#grid').datagrid('reload');
                            }
                        }
                    });
                }
            });
        }
    });
}

/**
 * 根据订单类型，获取不同的列
 */
function getColumns(){
    //采购订单
    if(Request['type'] * 1 == 1){
        return [[
            {field:'uuid',title:'编号',width:100},
            {field:'createtime',title:'生成日期',width:100},
            {field:'checktime',title:'审核日期',width:100},
            {field:'starttime',title:'确认日期',width:100},
            {field:'endtime',title:'入库日期',width:100},
            {field:'createrName',title:'下单员',width:100},
            {field:'checkerName',title:'审核员',width:100},
            {field:'starterName',title:'采购员',width:100},
            {field:'enderName',title:'库管员',width:100},
            {field:'supplierName',title:'供应商',width:100},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }
    //销售订单
    if(Request['type'] * 1 == 2){
        return [[
            {field:'uuid',title:'编号',width:100},
            {field:'createtime',title:'生成日期',width:100},
            {field:'endtime',title:'出库日期',width:100},
            {field:'createrName',title:'下单员',width:100},
            {field:'enderName',title:'库管员',width:100},
            {field:'supplierName',title:'客户',width:100},
            {field:'totalmoney',title:'合计金额',width:100},
            {field:'state',title:'状态',width:100,formatter:getState},
            {field:'waybillsn',title:'运单号',width:100}
        ]];
    }
//	下面是自己添加的,以后可能要删除
    /*if(Request['type'] * 1 != 'XX'){
        return [[
                    {field:'uuid',title:'编号',width:100},
                      {field:'createtime',title:'生成日期',width:100,formatter:formatDate},
                      {field:'checktime',title:'审核日期',width:100,formatter:formatDate},
                      {field:'starttime',title:'确认日期',width:100,formatter:formatDate},
                      {field:'endtime',title:'入库日期',width:100,formatter:formatDate},
                      {field:'createrName',title:'下单员',width:100},
                      {field:'checkerName',title:'审核员',width:100},
                      {field:'starterName',title:'采购员',width:100},
                      {field:'enderName',title:'库管员',width:100},
                      {field:'supplierName',title:'供应商',width:100},
                      {field:'totalmoney',title:'合计金额',width:100},
                      {field:'state',title:'状态',width:100,formatter:getState},
                      {field:'waybillsn',title:'运单号',width:100}
                ]];
    }*/
}

function doExport(){
    $.download("ordersAction_export",{"id":$('#uuid').html()});
}
	