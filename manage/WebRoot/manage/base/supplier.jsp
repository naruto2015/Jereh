<%@page import="com.manage.entity.PageBean"%>
<%@page import="com.manage.service.impl.CSServiceImpl"%>
<%@page import="com.manage.service.CustomerSupplierService"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<script src="${ pageContext.request.contextPath }/manage/console_ui/js/jquery.min.js" language="javascript"></script>
<script src="${ pageContext.request.contextPath }/manage/console_ui/js/jquery.easyui.min.js" language="javascript"></script>
<link href="${ pageContext.request.contextPath }/manage/console_ui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${ pageContext.request.contextPath }/manage/console_ui/themes/icon.css" rel="stylesheet" type="text/css" />
<script>

		
$(function(){

  
	
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
	$("#customers").datagrid({
	    url:'/manage/cus/ShowCustomerSupplier',
		title:'客户与供应商',
		idField:'code',
		singleSelect:false,
		fitColumns:true,
		fit:true,
		pageSize:10,
		pagination:true,
		pageList:[2,5,10],
		toolbar:'#Tool',
		columns:[[	
			{checkbox:true},
			{field:'code',title:'代码',width:130},
			{field:'csName',title:'名称',width:150},
			{field:'type',title:'类别',width:70,
			formatter:function(val,row,idx){
			          if(val=="1"){
			             return "客户";
			             }
			             if(val=="2"){
			             return "供应商";
			             }
			          
			       }
			},
			{field:'linkMan',title:'联系人',width:70},
			{field:'phone',title:'电话',width:120},
			{field:'address',title:'地址',width:200},
			{field:'isShow',title:'显示状态',width:70,
			 formatter:function(val,row,idx){
			          if(val=="1"){
			             return "显示";
			             }
			             if(val=="2"){
			             return "隐藏";
			             }
			          
			       }
			},
			{field:'opt',title:'操作',formatter:function(val,row,idx){
			var content = "<input type='button' value='删除' onclick=\"del('" + row.code + "')\" />";
				content +="<input type='button' value='修改' onclick=\"change('" + row.code + "')\"/>";
				return content;	
			}}
		]],
		
		});
	$('#customers').datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from}到第 {to}，共 {total} 条记录'
	}); 
});
function change(code){

    window.location.href="/manage/cus/updateServlet?code="+code;
}
function del(cid){
	
	$.ajax({
	url:'/manage/cus/DeleteServlet?cid='+cid,
	success:function(data){
	 $("#customers").datagrid("reload");
	}
	});
}

function delBatch(){
	var rows = $("#customers").datagrid("getSelections");
	if(rows.length == 0)
		$.messager.alert("提示","请选择一条记录");
	else{
		$.messager.confirm("删除","确实要删除记录吗？",function(r){
			if(r){
				for(var i = 0; i < rows.length; i++){
					var code=rows[i].code;
					$.ajax({
					url:'/manage/cus/DeleteBetchServlet',
					data:{'code':code},
					success:function(data){
					    $("#customers").datagrid("reload");
					}
					
					});
				}
			}
		});
	}
}

function add(){
   window.location.href="/manage/cus/addBaseCustomerServlet";
}
function search(){
 var code=$("input[name='code']").val();
  var csName=$("input[name='csName']").val();
  var addDate=$("input[name='adddate']").val();
  $("#customers").datagrid("reload",{'code':code,'csName':csName,'addDate':addDate});
 }
function outExcel(){
 window.location.href="/manage/cus/outExcelServlet";
}
</script>
</head>

<body>
<div id="Tool">
	<form action="" method="post">
        <b>检索条件：</b>
        代码：<input type="text" name="code" width="10px;"/>
        名称：<input type="text" name="csName" />
        操作日期：<input type="text" class="easyui-datebox" name="adddate" />
        <input type="button" value="搜索" onclick="search()" />
        <input type="reset" value="重置" />
    </form> 
	<button class="easyui-linkbutton" iconCls="icon-search" onclick="Singlesearch();">查询</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="add();">增加</button>
    <button class="easyui-linkbutton" iconCls="icon-remove" onclick="delBatch();">批量删除</button>
    <button class="easyui-linkbutton" iconCls="icon-ok" onclick="outExcel()">导出excel</button>
</div>
<div id="customers">
	<input  type="hidden" name="cid"/> 
</div>
</body>
</html>
