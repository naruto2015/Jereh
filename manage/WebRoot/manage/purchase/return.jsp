<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购退货管理</title>
<script src="../console_ui/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../console_ui/js/jquery.easyui.min.js" type="text/javascript"></script>
<link href="../console_ui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="../console_ui/themes/icon.css" rel="stylesheet" type="text/css"/>
<script>
//格式化日期
/*$("input.easyui-datebox").datebox({
	formatter:function(date){
		var y = date.getFullYear();
		var m = date.getMonth() + 1;
		var d = date.getDate();
		var h=date.getHours();
		var minute=date.getMinutes();
		var second=date.getSeconds();
		return y+m+d+h+minute+second;
	},
	parse:function(date){
		var time = Date.parse(date);
		if(!isNaN(time)){
			return new Date(time);
		}else{
			return new Date();
		}
	}
});*/
function getCode(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	if(month<10)month="0"+month;
	var day=date.getDay();
	if(day<10)day="0"+day;
	var hour=date.getHours();
	if(hour<10)hour="0"+hour;
	var minute=date.getMinutes();
	if(minute<10)minute='0'+minute;
	var second=date.getSeconds();
	if(second<10)second="0"+second;
	return "MTCT"+year+month+day+hour+minute+second;
}
$(function(){
	$("#returnList").datagrid({
		title:'采购退货管理',
		fit:true,
		fitColumns:true,
		idField:'code',
		singleSelect:false,
		pagination:true,
		pageList:[10,15,20],
		pageSize:10,
		/*onDblClickRow:function(){
			//dblclick();
			$("#detailList").datagrid({
		fitColumns:true,
		fitColumns:true,
		idField:'code',
		singleSelect:false,
		pagination:true,
		pageList:[10,15,20],
		pageSize:10,
		toolbar:'#detailtool',
		columns:[[
			{field:'',title:'入库单号',width:100},
			{field:'',title:'配件件号',width:100},
			{field:'',title:'配件名称',width:100},
			{field:'',title:'配件品牌',width:100},
			{field:'',title:'数量',width:100},
			{field:'',title:'单价',width:100},
			{field:'',title:'金额',width:100},
			{field:'',title:'备注',width:100}
		]],
	});
		},*/
		toolbar:'#returntool',
		columns:[[
			{field:'id',checkbox:'checked',formatter:function(idx){
				return idx;
			}},
			{field:'code',title:'采退编号',width:100},
			{field:'rdate',title:'采退日期',width:100},
			{field:'supplitername',title:'供应商名称',width:100},
			{field:'nums',title:'数量',width:100},
			{field:'numsprice',title:'采退金额',width:100},
			{field:'contacter',title:'联系人',width:100},
			{field:'telphone',title:'联系方式',width:100},
			{field:'state',title:'审核状态',width:100},
			{field:'addusername',title:'操作员',width:100},
			{field:'opt',title:'操作',width:100,formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick=\"(del('"+row.code+"'))\"/>";
				content+="<input type='button' value='修改' onclick=\"('"+idx+"')\"/>";
				return content;
			}}
		]],
	});
});
function showsb(){
	if($("#sb").css("display")=='none'){
		$("#sb").show();
	}else{
		$("#sb").hide();
	}
}
function addshow(){
	$("#mydy").dialog({
		title:'采购退货',
	});
	$("input[name='code']").val(getCode());
	//$("#sav").linkbutton('disable')=='none';
	//$("input[name='save']").linkbutton('disable');
	$("#detail").datagrid({
		fit:true,
		fitColumns:true,
		idField:'code',
		singleSelect:false,
		columns:[[
			{field:'code',title:'入库单号',width:100},
			{field:'partsNo',title:'配件件号',width:100},
			{field:'partsName',title:'配件名称',width:100},
			{field:'partsBrand',title:'配件品牌',width:100},
			{field:'',title:'数量',width:100},
			{field:'salePrice',title:'单价',width:100},
			{field:'',title:'金额',width:100},
			{field:'g',title:'备注',width:100},
			{field:'h',title:'操作',width:100}
		]],
	});
}
function cssuppliter(){
	$("#suppliter").dialog({
		title:'供应商',
	});
	$("#suppliterList").datagrid({
		columns:[[
			{field:'j',title:'供应商代码',width:100},
			{field:'',title:'供应商名称',width:100},
			{field:'',title:'联系人员',width:100},
			{field:'',title:'电话',width:100},
			{field:'',title:'传真',width:100},
			{field:'',title:'地址',width:100}
		]],
	});
}
function chooseparts(){
	$("#parts").dialog({
		title:'选择配件',
	});
	$("#partsList").datagrid({
		columns:[[
			{field:'',title:'入库单号',width:100},
			{field:'',title:'入库日期',width:100},
			{field:'',title:'配件件号',width:100},
			{field:'',title:'配件名称',width:100},
			{field:'',title:'配件品牌',width:100},
			{field:'',title:'配件型号',width:100},
			{field:'',title:'数量',width:100},
			{field:'',title:'单价',width:100},
			{field:'',title:'金额',width:100}
		]],
	});
}
function cls(){
	$("#mydy").dialog("close");
}
</script>
</head>
<body>
<div id="returnList">
</div>
<div id="returntool">
<form id="sb" style="display:none">
	检索条件：
    采退编号：<input type="text" name="cod"/>
    开始日期：<input type="text" name="" class="easyui-datebox"/>
    结束日期：<input type="text" name="" class="easyui-datebox"/>
    供应商：<input type="text" name="sname"/>
    <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
</form>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'" onclick="showsb()">查询</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addshow()">增加</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="delsel()">批量删除</a>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导出EXCEL</a>
</div>
<div id="detailList">
<div>
<div id="detailtool">
	单据标号为：的明细如下所列！
</div>
<div id="mydy">
    <table border="1" cellpadding="3" cellspacing="0" align="center" bordercolor="#95ADCC" width="800">
        <tr>
            <td align="right">采退编号：</td>
            <td><input type="text" name="code"/></td>
            <td align="right">采退日期：</td>
            <td><input type="text" name="" class="easyui-datebox"/></td>
        </tr>
        <tr>
            <td align="right">供应商名：</td>
            <td><input type="text" name="" ondblclick="cssuppliter()"/></td>
            <td align="right">联系人员：</td>
            <td><input type="text" name=""/></td>
        </tr>
        <tr>
            <td align="right">电话：</td>
            <td><input type="text" name=""/></td>
            <td align="right">传真：</td>
            <td><input type="text" name=""/></td>
        </tr>
        <tr>
            <td align="right">备注：</td>
            <td colspan="3"><input type="text" name=""/></td>
        </tr>
        <tr>
            <td colspan="4"><input type="button" value="新增"/>
            <input type="button" value="选人库单" onclick="chooseparts()"/>
            <input type="button" id="sav" name="save" value="保存"/>
            <input type="button" value="审核"/>
            <input type="button" value="撤销"/>
            <input type="button" value="打印"/>
            <input type="button" value="关闭" onclick="cls()"/></td>
        </tr>
    </table>
	<div id="detail">
	<div>
</div>
<div id="suppliter">
	检索条件：
    供应商代码号：<input type="text" name="cod"/>
    供应商名称：<input type="text" name="sname"/>
    <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
    <div id="suppliterList">
    </div>
</div>
<div id="parts">
	检索条件：
    件号：<input type="text" name="cod"/>
    名称：<input type="text" name="sname"/>
    <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
    <div id="partsList">
    </div>
</div>
</body>
</html>
