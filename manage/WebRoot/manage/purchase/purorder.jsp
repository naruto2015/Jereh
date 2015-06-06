<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>采购订单管理</title>
<script src="../console_ui/js/jquery-1.7.2.min.js"></script>
<script src="../console_ui/js/jquery.easyui.min.js"></script>
<link type="text/css" href="../console_ui/themes/default/easyui.css" rel="stylesheet" />
<link type="text/css" href="../console_ui/themes/icon.css" rel="stylesheet" />
<style>
form{ font-size:14px;}
tr{text-align:center;}
#t{ background-color:#D0DBE8; width:800px;}
#t tr{ background-color:#FFF; text-align:left;}

</style>
<script>
$(function(){
	$("#orderList").datagrid({
		title:'订单列表',
		fit:true,
		//fitColumns:true,
		pagination:true,
		pageList:[2,5,10,15],
		pageSize:2,
		isField:'code',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:'#orderTools',
		columns:[[
			{field:'check',checkbox:true},
			{field:'code',title:'订单编号',width:100},
			{field:'date',title:'订单日期',width:100},
			{field:'cname',title:'供应商名',width:100},
			{field:'count',title:'数量',width:100},
			{field:'amount',title:'金额',width:100},
			{field:'linkman',title:'联系人',width:100},
			{field:'way',title:'联系方式',width:100},
			{field:'state',title:'审核状态',width:100},
			{field:'operator',title:'操作员',width:100},
			{field:'opt',title:'操作',width:100,formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick='delRow("+row.code+")'/>";
				content+="<input type='button' value='修改' onclick='update("+idx+")'/>";
				return content;
			}}
		]]
	});
	$("#searchList").datagrid({
		//fit:true,
		fitColumns:true,
		isField:'code',
		singleSelect:false,
		checkOnSelect:false,
		columns:[[
			{field:'code',title:'询价编号'},
			{field:'partNo',title:'配件件号'},
			{field:'partName',title:'配件名称'},
			{field:'partBrand',title:'配件品牌'},
			{field:'partModel',title:'配件型号'},
			{field:'count',title:'数量'},
			{field:'price',title:'单价'},
			{field:'money',title:'金额'},
			{field:'remark',title:'备注'},
			{field:'opt',title:'操作',formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick='del("+row.code+")'/>";
				return content;
			}},
			{field:'lastPrice',title:'上次价格'},
		]]
	});
	/*$("#searchList").datagrid("appendRow",{
		name:'合计'
	});*/
	$("#purchase").datagrid({            
		 //fit:true,
		 fitColumns:true,
		 idField:'id',
		 toolbar:"#head",
		 singleSelect:false,
		 checkOnSelect:false,
		 columns:[[
			   {field:'id',checkbox:'checked',formatter:function(idx){
				  return idx;
			   }},
			   {field:'code',title:'询价编号'},
			   {field:'adddate',title:'询价日期'},
			   {field:'supplierCode',title:'供应商名'},
			   {field:'nums',title:'数量'},
			   {field:'numsPrice',title:'金额'},
			   {field:'noNums',title:'未选数量'},
			   {field:'noPrice',title:'未选金额'},
			   {field:'state',title:'审核状态'}
		 ]]
	});
	$("#partsList").datagrid({
		//fit:true,
		fitColumns:true,
		idField:'id',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:"#partstool",
		columns:[[	
			{field:'id',checkbox:true},
			{field:'partsNo',title:'配件件号'},
			{field:'partsName',title:'配件名称'},
			{field:'partsBrand',title:'配件品牌'},
			{field:'partsModel',title:'配件型号'},
			{field:'salePrice',title:'单价'},
			{field:'person',title:'供应商'},
			{field:'lastPrice',title:'上次价格'},
			{field:'remarks',title:'备注'}
		]],
	});
	$("#personList").datagrid({
		//fit:true,
		fitColumns:true,
		idField:'id',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:"#persontool",
		columns:[[	
			{field:'id',checkbox:true},
			{field:'personNo',title:'供应商代号'},
			{field:'personName',title:'供应商名称'},
			{field:'linkman',title:'联系人员'},
			{field:'tel',title:'电话'},
			{field:'zip',title:'传真'},
			{field:'address',title:'地址'}
		]],
	});
	$("input.easyui-datebox").datebox({
		formatter: function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return y+"-"+m+"-"+d;
		},
		parse:function(date){
		 var t=Date.parse(date);
		if (!isNaN(t)){
			return new Date(t);
		} else {
			return new Date();
		}
		}
	});
	$("#mydg1").dialog("close");
	$("#mydg2").dialog("close");
	$("#mydg3").dialog("close");
	$("#mydg4").dialog("close");
});
function showFrm(){
	if($("#see").css("display")=='none'){
		$("#see").show();
	}else{
		$("#see").hide();
	}
}
function add(){
	$("#mydg1").dialog("open");
}
function delBatchRow(){
	var rows=$("#orderList").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("信息提示","请选择一条记录");
	}else{
		$.messager.confirm("删除确认","确实要删除记录吗？",
		function(r){
			if(r){
				var codes="";
				for(var i=0;i<rows.length;i++){
					codes+=rows[i].code+" ";
					var rowidx=$("#orderList").datagrid("getRowIndex",rows[i]);
					$("#orderList").datagrid("deleteRow",rowidx);
					//后台通信
					
				}	
				//$.messager.alert("信息提示",codes);
			}
		});
	}
}
function delRow(code){
	//alert(code);
	var rowidx=$("#orderList").datagrid("getRowIndex",code);
	//alert(rowidx);
	$("#orderList").datagrid("deleteRow",rowidx);
}
function update(idx){
	$("#mydg1").dialog("open");
}
function del(code){
	//alert(code);
	var rowidx=$("#searchList").datagrid("getRowIndex",code);
	//alert(rowidx);
	$("#searchList").datagrid("deleteRow",rowidx);
}
function show1(){
	$("#mydg2").dialog("open");
}
function clo(){
	$("#mydg1").dialog("close");
}
function addPart(){
	$("#mydg3").dialog("open");
}
function choosePerson(){
	$("#mydg4").dialog("open");
}
</script>
</head>

<body>
<div id="orderTools">
	<form action="" method="post" id="see" style="display:none">
	检索条件:	&nbsp;&nbsp;订单编号：<input type="text" name="code" />
    开始日期：<input name="beginDate" type="text" class="easyui-datebox"  /> 
    结束日期：<input name="endDate" type="text" class="easyui-datebox"  /> 
    供应商名：<input name="cname" type="text" /> 
    <input type="submit" value="搜索"/><input type="reset" value="重置" />
    </form>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="showFrm()">查询</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-add'" onclick="add()">增加</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="delBatchRow()">批量删除</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'" onclick="excel()">导出EXCEL</a>
</div>
<div id="orderList">
<table>
	<tr><td></td><td>001</td><td>2012-05-11</td></tr>
</table>
</div>
<div id="mydg1" class="easyui-dialog" title="采购订单" style="margin:5px;">
	<form id="myFrm" method="post">
    <table id="t">
    <tr><td>订单编号:</td><td><input type="text" name="code1" /></td>
    <td>订单日期:</td><td><input type="text" name="date1" class="easyui-datebox" /></td></tr>
    <tr><td>供应商名:</td><td><input type="button" name="cname1" value="请双击选择供应商" ondblclick="choosePerson()"/></td>
    <td>联系人员:</td><td><input type="text" name="linkman" /></td></tr>
    <tr><td>电话:</td><td><input type="text" name="tel" /></td>
    <td>传真:</td><td><input type="text" name="zip" /></td></tr>
    <tr><td>运输方式:</td><td><select name="way">
    	<option>圆通快递</option><option>EMS</option><option>中通快递</option>
    </select></td>
    <td>交货日期:</td><td><input type="text" name="date2" class="easyui-datebox" /></td></tr>
    <tr><td>业务人员:</td><td><input type="text" name="person" /></td>
    <td>备注:</td><td><input type="text" name="remarks" /></td></tr>
    </table>
    <input type="button" value="新增" onclick="subFrm1()"/>
    <input type="button" value="选询价单" onclick="show1()" />
    <input type="button" value="添加配件" onclick="addPart()"/>
    <input type="button" value="保存"  onclick="subFrm2()" />
    <input type="button" value="审核"  onclick="check()" />
    <input type="reset" value="撤销" />
    <input type="button" value="打印"  onclick="print()" />
    <input type="button" value="生成合同" />
    <input type="button" value="生成word" onclick="word()" />
    <input type="button" value="生成入库单" />
    <input type="button" value="关闭" onclick="clo()" />
    </form>
    <div id="searchList">
    <table><tr><td>001</td></tr></table>
    </div>
</div>
<div id="mydg2" class="easyui-dialog" title="请选择询价单" style="width:850px;">
	<div id="head">
      <form  id="query">
           检索条件:询价编号:<input type="text" name="code" />
           开始日期:<input class="easyui-datebox" type="text" name="beginTime" />
           结束日期:<input class="easyui-datebox" type="text" name="endTime" />
           <input type="button" value="搜索" /><input type="reset" value="重置" />
      </form>
    </div>
    <div id="purchase">
     <table><tr><td></td><td>001</td></tr></table>
    </div>
</div>
<div id="mydg3" class="easyui-dialog" title="请选择配件（温馨提示您：双击某行选中数据）" style="width:600px;">
    <div id="partstool">
        <form id="find" method="post">
            检索条件:件号：<input type="text" name="partsNo"/>
            名称：<input type="text" name="partsName"/>
            <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
        </form>
    </div>
    <div id="partsList">
    	<table><tr><td></td><td>001</td></tr></table>
    </div>
</div>
<div id="mydg4" class="easyui-dialog" title="请选择供应商（温馨提示您：双击某行选中数据）" style="width:700px;">
    <div id="persontool">
        <form id="find" method="post">
            检索条件:供应商代号：<input type="text" name="personNo"/>
            供应商名称：<input type="text" name="personName"/>
            <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
        </form>
    </div>
    <div id="personList">
    	<table><tr><td></td><td>001</td></tr></table>
    </div>
</div>
</body>
</html>
