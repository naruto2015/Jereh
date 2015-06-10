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
<script src="/manage/manage/console_ui/js/jquery.easyui.min.js"></script>
<link rel="stylesheet" type="text/css" href="../console_ui/themes/icon.css" />
<link rel="stylesheet" type="text/css" href="../console_ui/themes/default/easyui.css" />
<script>

//随机生成随机数
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
	//日期转化
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var hour=date.getHours();
			var minute=date.getMinutes();
			return d+"-"+m+"月-"+y;
		},
		parse:function(date){
			var time = Date.parse(date);
			if(!isNaN(time))
				return new Date(time);
			else
				return new Date();
		}
	});

//订单列表
	$("#list").datagrid({
		url:'/manage/sale/GetSaleOrderServlet',
		toolbar:'#bt',
		idField:'code',
		fit:true,
		fitColumns:true,
		pagination:true, 
		pageList:[2,5,10],
		singleSelect:false,
	    checkOnSelect:false,
		columns:[[
			{field:'box',checkbox:'checked'},
			{field:'code',title:'订单编号',witdh:80},
			{field:'orderDate',title:'订单日期',width:80},
			{field:'customerCode',title:'客户名称',width:80,
				//formatter:function(val,row,idx){return val.csName ;}
				},
			{field:'nums',title:'数量',witdh:80},
			{field:'numsPrice',title:'总货值',width:80},
			{field:'contacter',title:'联系人',width:80},
			{field:'tel',title:'联系方式',width:80},
			{field:'state',title:'审核状态',width:80},
			{field:'businesser',title:'操纵员',width:80},
			{field:'opt',title:'操作',width:80,
				 formatter:function(val,row,idx){
				 var optBt="<input type='button' value='修改' onclick=\"modify('"+row.code+"')\"/>";
					 optBt+="<input type='button' value=\"删除\" onclick=\"delRow('"+row.code+"')\" />";
					 return optBt;
				}}]]
		});
		
		$("#mydg4").dialog("close");
		
		$("#list").datagrid({onDblClickRow:function(idx,data){
		showOrderDetail(idx,data);
		}});
		
		//客户选择
		$("#personList").datagrid({onDblClickRow:function(idx,data){
		$("input[name='clientName']").val(data.csName);
		$("input[name='contacter']").val(data.linkMan);
		$("input[name='tel']").val(data.phone);
		$("input[name='fax']").val(data.fax);
		}});
		
		$("#orderShow").dialog({
		title:"单据编号为xxxx的明细如下列",
		width:1000,
		height:200,
		close:false,
		modal:true,
		cache:false,
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
			{field:'remarks',title:'备注',width:100},
			{field:'lastOpt',title:'上次操作',width:60},
			{field:'opt',title:'操作',width:80,
			  formatter:function(val,row,idx){						
						var optBt="<input type='button' value='删除' onclick=\"delOrder('"+row.code+"')\"/>";							
							return optBt;
						}}
		   ]]
		});
	
	})
	
	
	
	//订单明细框
	function showOrderDetail(idx,data){
		$("#orderShow").dialog("open");
		alert(data.code);
		$("#orderDetail").datagrid({	
		url:'/manage/sale/GetSaleOrderByCodeServlet?code='+data.code,
		columns:[[
			{field:'quotedPriceNo',title:'报价单号',width:80},
			{field:'partNumber',title:'件号',width:80},
			{field:'fittingName',title:'配件名称',width:80},
			{field:'fittingBrand',title:'配件品牌',width:80},
			{field:'fittingType',title:'配件型号',width:80},
			{field:'num',title:'数量',width:40},
			{field:'price',title:'单价',width:40},
			{field:'money',title:'金额',width:40},
			{field:'remarks',title:'备注',width:100},
		   ]]
		});
	}
	

//删除
function delRow(code){
	$.ajax({
		url:'/manage/sale/DelSaleOrderServlet',
		data:{code:code},
		async:false,
		success:function(data){
			$.messager.show({
						msg:data,
						timeout:2000,
						showType:'slide',
						style:{
							right:'1000',
							top:document.body.scrollTop+document.documentElement.scrollTop,
							bottom:'100'
						}
			
			});
		$("#list").datagrid("reload");
		},
	});
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
//修改框弹出
	function modify(code){
		$("#add").dialog({
			title:'订单号'+code+'的信息',
			width:1000,
			height:400,
			close:false,
			modal:true,
			cache:false,
		})
		//$.ajax({
		//url:'/manage/sale/GetSaleOrderByCodeServlet',
		//data:{code:code},
		//success:function(data){
		//$("input[name='orderDate']").val();
		//$("input[name='customerCode']").val(data.customerCode);
		//$("input[name='nums']").val(data.nums);
		//$("input[name='numsprice']").val(data.numsPrice);
		//$("input[name='contacter']").val(data.contacter);
		//$("input[name='telphone']").val(data.tel);
		//$("input[name='state']").val(data.state);
		//$("input[name='bussnesser']").val(data.bussnesser);
		//},
		//});
		$("input[name='code']").val(code);
	}
//保存修改
	function savemodorder(){
		$("#modOrder").submit();
	}
//取消修改
	function remodorder(){
		$("#modOrder input").val("");
		$("#add").dialog("close");
	}
	
//客户信息
function choosePerson(){
	$("#mydg4").dialog("open");
	$("#personList").datagrid({
		//fit:true,
		url:'/manage/cus/ShowCustomerSupplier',
		fitColumns:true,
		idField:'id',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:"#persontool",
		
		columns:[[	
			{field:'id',checkbox:true},
			{field:'code',title:'供应商代号'},
			{field:'csName',title:'供应商名称'},
			{field:'linkMan',title:'联系人员'},
			{field:'phone',title:'电话'},
			{field:'fax',title:'传真'},
			{field:'address',title:'地址'}
		]],
	});
}
//实现双击添加到输入框中

	
	
//配件datagrid
function partDataGrid(){
	$("#partInfo").datagrid({
		url:'/manage/sale/GetStockPartsServlet',
		toolbar:'#partBt',
		idField:'code',
		fit:true,
		fitColumns:true,
		pagination:true, 
		pageList:[2,5,10],
		singleSelect:false,
	    checkOnSelect:false,
		columns:[[
			{field:'box',checkbox:'checked'},
			{field:'pcode',title:'件号',width:80,formatter:function(val,row,idx){return val.partsNo;}},
			{field:'partsName',title:'配件名称',width:80,formatter:function(val,row,idx){return row.pcode.partsName;}},
			{field:'partsBrand',title:'配件品牌',width:80,formatter:function(val,row,idx){return row.pcode.partsBrand;}},
			{field:'partsModel',title:'配件型号',width:80,formatter:function(val,row,idx){return row.pcode.partsModel;}},
			{field:'hcode',title:'所属仓库',width:80},
			{field:'salePrice',title:'销售单价',width:40,formatter:function(val,row,idx){return row.pcode.salePrice;}},
			{field:'nums',title:'库存数量',width:40},
			{field:'lastPrice',title:'上次价格',width:60},
			{field:'remarks',title:'备注',width:100,formatter:function(val,row,idx){return row.pcode.remarks;}},
			]],
	});
}
//选择配件
	function addPart(){
		partDataGrid();
		$("#partdialog").dialog({
		 	title:'选择配件',
			width:1000,
			height:400,
			close:false,
			modal:true,
			cache:false,
		});
		getwareHouse();
	}
//获取所属仓库
	function getwareHouse(){
		$.ajax({
		url:'/manage/sale/GetStockPartsServlet',
		success:function(data){
			for(var i=0;i<data.rows.length;i++){
			var option=$("<option>").appendTo($("select[name='hCode']"));
			option.html(data.rows[i].hcode);
			}
		}
		});
	}
//搜索选择配件
	function  partSearch(){
		var partNo=$("#partBt input[name='partNoSerach']").val();
		var partName=$("#partBt input[name='partNameSearch']").val();
		var hcode=$("select[name='hCode']").val();
		alert(partNo);
		data={partsNo:partNo,partsName:partName,hCode:hcode};
		$("#partInfo").datagrid("reload",data);
	}
//重置搜索选择配件
	function repartSearch(){
		var partNo=$("#partBt input[name='partNoSerach']").val("");
		var partName=$("#partBt input[name='partNameSearch']").val("");
		var hcode=$(".optionwarehouse").attr({"selected":"selected"});
	}
	
	
	
	
//查询订单
	function searchBt(){
		var a=$("input[name='orderCode_s']").val();
		var b=$("input[name='startDate_s']").val();
		var c=$("input[name='endDate_s']").val();
		data=({orderCode:a,startDate:b,endDate:c});
		$("#list").datagrid("reload",data);
	}	
	
//重置
	function resetBt(){
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
		var orderCode=getCode();
		$("input[name='orderCode']").val(orderCode);
		}
	//保存订单
	function saveOrder(){
		
		$("#addOrder").attr({action:"/manage/sale/AddSaleOrderServlet"});
		$("#addOrder").submit(); 
	};
	//新增加
	function addNew(){
		var orderCode=getCode();
		$("input[name='orderCode']").val(orderCode);
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
tr{text-align:center}
.nbutton{ text-align:center;list-style:none}
.button{ width:200px; height:28px; border:0px; margin-top:8px;}
.subSaveBt{ margin-right:2px;}
.subSaveBt:hover{ background:#F69; border:2px #F8FDFE solid;}
.subCancelBt:hover{ background:#09C; border:2px #F8FDFE solid;}
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
	<a href="#" class="easyui-linkbutton " iconCls="icon-search" onclick="searchBt()">查询</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-add" onclick="addOrder()">增加</a>
	<a href="#" class="easyui-linkbutton" iconCls="icon-remove" onclick="delRows()">批量删除</a>
    <a href="#" class="easyui-linkbutton" iconCls="icon-large-chart" onclick="outExcel">导出Excel</a>   
    <hr />
</div>

<!--订单明细-->
<div id="orderShow">
	<div id="orderDetail"> 
	
	</div>
</div>

<!-- 界面显示订单信息 -->
<div id="list" >
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
        <td><input class="easyui-validatebox" type="text" name="clientName" ondblclick="choosePerson()" ></input></td>
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
        <td><input class="easyui-validatebox" type="text" name="businesser"></input></td>
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
      <button onclick="addPart()">添加配件</button>
      <button>审核</button>
      <button onclick="saveOrder()">保存</button>
      <button>撤销</button>
      <button>打印</button>
      <button>生成合同</button>
      <button>生成word</button>
      <button>生成出库</button>
      <button onclick="finishOrder()">完成订单</button>
      </span>
   </div>
    <div id="addOrderList">
    </div>	
    </div>
   </div>
   
<!-- 客户信息 -->
<div id="mydg4" class="easyui-dialog" title="请选择供应商（温馨提示您：双击某行选中数据）" style="width:700px;">
    <div id="persontool">
        <form id="find" method="post">
          	  检索条件:供应商代号：<input type="text" name="personNo"/>
          	  供应商名称：<input type="text" name="personName"/>
            <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/>
        </form>
    </div>
    <div id="personList">
    	
    </div>
</div>  
<!-- 配件信息 -->
<div id="partBt">
	检索条件:<a>件号:<input name="partNoSerach"/></a>
		  <a>名称:<input name="partNameSearch"/></a>
		     仓库:<select name="hCode"><option class="optionwarehouse">--选择仓库--</option></select>
		  <button onclick="partSearch()">搜索</button>
		  <button onclick="repartSearch()">重置</button>
</div>
	<div id="partdialog">
	  <div id="partInfo">
	  	
		</div>
	</div>
</body>
</html>
