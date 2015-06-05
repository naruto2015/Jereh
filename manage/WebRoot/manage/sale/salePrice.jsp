<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
    
    <title>My JSP 'salePrice.jsp' starting page</title>
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
  $("#sale").datagrid({
	    url:'',
		title:'报价单据管理',
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
			{field:'code',title:'报价单号',width:70},
			{field:'sqdate',title:'报价日期',width:70},
			{field:'compcode',title:'客户名称',width:70},
			{field:'nums',title:'数量',width:70},
			{field:'numsPrice',title:'总货值',width:70},
			{field:'contacter',title:'联系人',width:70},
			{field:'telphone',title:'联系方式',width:70},
			{field:'state',title:'审核状态',width:70},
			{field:'addusername',title:'操作员',width:70},
			{field:'opt',title:'操作',formatter:function(val,row,idx){
			var content = "<input type='button' value='删除' onclick=\"del('" + row.code + "')\" />";
				content +="<input type='button' value='修改' onclick=\"change('" + row.code + "')\"/>";
				return content;	
			}}
		]],
		
		});
	$('#sale').datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from}到第 {to}，共 {total} 条记录'
	}); 
   
   });
function add(){
   	window.location.href="/manage/sal/addSalePriceServlet";
}

</script>
  </head>
  
  <body>
 <div id="Tool">
	<form action="" method="post">
        <b>检索条件：</b>
        报价单号：<input type="text" name="code" width="10px;"/>
        开始日期：<input type="text" class="easyui-datebox" name="startdate" />
        结束日期：<input type="text" class="easyui-datebox" name="enddate" />
        产品名称：<input type="text" name="pname">     
        <input type="button" value="搜索" onclick="search()" />
        <input type="reset" value="重置" />
    </form> 
	<button class="easyui-linkbutton" iconCls="icon-search" onclick="Singlesearch()">查询</button>
	<button class="easyui-linkbutton" iconCls="icon-add" onclick="add()">增加</button>
    <button class="easyui-linkbutton" iconCls="icon-remove" onclick="delBatch()">批量删除</button>
    <button class="easyui-linkbutton" iconCls="icon-ok" onclick="outExcel()">导出excel</button>
</div>
<div id="sale">

</div>
  </body>
</html>
