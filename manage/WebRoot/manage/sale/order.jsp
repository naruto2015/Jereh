<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>销售订单管理</title>
<script src="../console_ui/js/jquery-1.7.2.min.js"></script>
<script src="../console_ui/js/jquery.min.js"></script>
<script src="../console_ui/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../console_ui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="../console_ui/themes/default/easyui.css" />
<script>
//删除
function delRow(){
	var idx=$("#list").datagrid("getRowIndex");
	$("#list").datagrid("deleteRow",idx);
	}
//批量删除
function delRows(){
	var rows=$("#list").datagrid("getSelections");
	if(rows.length==0){
		$.messager.alert("请选择一条记录");
		}else{
			$.messager.confirm("确定删除","确定删除记录？",
				function(r){
					if(r){
						var codes="";
						for(var i=0;i<rows.length;i++){
							codes+=rows[i].code;		
						var rowIdx=$("#list").datagrid("getRowIndex",rows[i]);
					
						$("#list").datagrid("deleteRow",rowIdx);
						}
						}
					});
			}
	}

$(function(){
	//样式设置
	
	//日期转化
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();

	var hour=date.getHours();
	var minute=date.getMinutes();
	return y+"-"+m+"-"+d;
   
		},
		parse:function(date){
			var time = Date.parse(date);
			if(!isNaN(time))
				return new Date(time);
			else
				return new Date();
		}
		
	});
	$("#list").datagrid({
		toolbar:'#bt',
		idField:'orderCode',
		fit:true,
		pagination:true, 
		pageList:[2,5,10],
		singleSelect:false,
	    checkOnSelect:false,
		columns:[[
				{field:'box',checkbox:'checked'},
				{filed:'orderCode',title:'订单编号',width:120},
				{field:'orderDate',title:'订单日期',width:100},
				{field:'clientName',title:'客户名称',width:100},
				{field:'num',title:'数量',width:60},
				{field:'allPrice',title:'总货币',width:100},
				{field:'linkman',title:'联系人',width:100},
				{field:'linkStyle',title:'联系方式',width:100},
				{field:'checkState',title:'审核状态',width:100},
				{field:'operator',title:'操作员',width:100},
				{field:'opt',title:'操作',
					formatter:function(val,row,idx){
						
						var optBt="<input type='button' value='修改' onclick='modify()'/>";					
							optBt+="<input type='button' value='删除' onclick='delRow()'/>";
							return optBt;
						}}
				]]
		});


//订单明细	
	$("#orderShow").dialog({
		title:"单据编号为xxxx的明细如下列",
		width:1000,
		height:200,
		close:false,
		modal:true,
		cache:false,
		});

//订单datagrid	
	$("#orderDetail").datagrid({	
		columns:[[
			{field:'quotedPriceNo',title:'报价单号',width:80},
			{field:'partNumber',title:'件号',width:80},
			{field:'fittingName',title:'配件名称',width:80},
			{field:'fittingBrand',title:'配件品牌',width:80},
			{field:'fittingType',title:'配件型号',width:80},
			{field:'num',title:'数量',width:60},
			{field:'price',title:'单价',width:60},
			{field:'money',title:'金额',width:60},
			{field:'remark',title:'备注',width:170}
		]]
		});	
		$("#orderShow").dialog("close");

//增加订单datagrid
	$("#addOrderList").datagrid({	
		columns:[[
			{field:'quotedPriceNo',title:'报价单号',width:80},
			{field:'partNumber',title:'件号',width:80},
			{field:'fittingName',title:'配件名称',width:80},
			{field:'fittingBrand',title:'配件品牌',width:80},
			{field:'fittingType',title:'配件型号',width:80},
			{field:'num',title:'数量',width:40},
			{field:'price',title:'单价',width:40},
			{field:'money',title:'金额',width:40},
			{field:'remark',title:'备注',width:100},
			{field:'lastOpt',title:'上次操作',width:60},
			{field:'opt',title:'操作',width:80,
			  formatter:function(val,row,idx){						
						var optBt="<input type='button' value='删除' onclick='delOrder()'/>";							
							return optBt;
						}}
		]]
		})
	

	});
	
//搜索
	function searchBt(){}	
	
//重置
	function resetBt(){
		alert("dfas");
		$("input[name='orderCode_s']").val("");
		$("input[name='startDate_s']").val("");
		$("input[name='endDate_s']").val("");
		}
	
	//增加订单 
	function addOrder(){
		$("#add").dialog({
		title:'添加订单',
		width:800,
		height:460,
		});
		$("input[name='orderCode']").val("dfaf");
		}
	//保存订单
	function saveOrder(){
		
		$("#addOrder").attr({action:"/manage/sale/AddSaleOrderServlet"});
		
		$("#addOrder").submit(); 
	};
	//新增加
	function addNew(){
		$("input[name='orderCode']").val("dfaf");
		}
	//删除添加的订单
	function delOrder(){
		var idx=$("#addOrderList").datagrid("getRowIndex");
		$("#addOrderList").datagrid("deleteRow",idx);
		}
</script>
<style>
#addcss{ margin:20px;}
#add table .table1{ border:1px #022264 solid; padding:20px 100px}
#add .table1 td{ border:1px #636161 solid;width:180px; text-align:center;}
#add .table1 td:odd{width:230px; }
#add input{ margin:0; padding:0}
.i{ color:#E34144}

</style>
</head>

<body>
<div id="bt">
	<span>检索条件：</span>&nbsp;订单编号<input  name="orderCode_s"/>
    开始时间<input class="easyui-datebox" name="startDate_s"/>
    结束时间<input class="easyui-datebox" name="endDate_s"/>
    <button onclick="searchBt()">搜索</button>
    <button onclick="resetBt()">重置</button>
	<hr />
	<a href="#" class="easyui-linkbutton " iconCls="icon-search">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addOrder()">增加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="delRows()">批量删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-large-chart" onclick="outExcel">导出Excel</a>   
    <hr />
</div>
<!--订单明细-->
<div id="orderShow">
<div id="orderDetail"> 
	<table>
    <td>dfad</td>
    </table>
</div>
</div>
<div id="list" >
	<table>
    <tr><td></td><td>001</td></tr>
    <tr><td></td><td>001</td></tr>
    <tr><td></td><td>001</td></tr>
    </table>
</div>

<div id="add">
	<div id="addcss">
	<form id="addOrder" method="post">
    <table class="table1">
    <tr>
    	<td class="orderCode"><i class="i">*</i>订单编号</td>
        <td><input class="easyui-validatebox" type="text" name="orderCode"></input></td>
    
        <td class="orderDate"><i class="i">*</i>订单日期</td>
        <td><input class="easyui-datebox" type="text" name="orderDate"></input></td>
     </tr>
     <tr>
    	<td class="clientName"><i class="i">*</i>客户名称</td>
        <td><input class="easyui-validatebox" type="text" name="clientName"></input></td>
        <td class="linkman"><i class="i">*</i>联系人员</td> 
        <td><input class="easyui-validatebox" type="text" name="contacter"></input></td> 
      </tr>
     
      <tr>
        <td class="tel">电&nbsp;&nbsp;&nbsp;&nbsp;话</td>
        <td><input class="easyui-validatebox" type="text" name="tel"></input></td>
        <td class="fax">传&nbsp;&nbsp;&nbsp;&nbsp;真</td>
        <td><input class="easyui-validatebox" type="text" name="fax"></input></td>
      </tr>
      <tr>
        <td class="transportway">运输方式</td>
        <td><select name="trans" id="select"><option>圆通快递</option></select></td>
        <td class="serviceman">业务人员</td>
        <td><input class="easyui-validatebox" type="text" name="bussnesser"></input></td>
      </tr>
      <tr>
        <td class="remark">备&nbsp;&nbsp;&nbsp;&nbsp;注</td>
        <td><input class="easyui-validatebox" type="text" name="remark"></input></td>
        <td class="delivery"><i class="i">*</i>交货日期</td>
        <td><input class="easyui-datebox" type="text" name="delivery"></input></td>
      </tr>
      
    </table>
    </form>
    <div>
   	  <span>
      <button onclick="addNew()">新增</button>
      <button>选报价单</button>
      <button>添加配件</button>
      <button>审核</button>
      <button onclick="saveOrder()">保存</button>
      <button >撤销</button>
      <button>打印</button>
      <button>生成合同</button>
      <button>生成word</button>
      <button>生成出库</button>
      <button onclick="finishOrder()">完成订单</button>
      
      </span>
   </div>
    <div id="addOrderList">
    	<table>
        <tr><td>001</td></tr>
       </table>
    </div>
   </div>
  
</div>
</body>
</html>
