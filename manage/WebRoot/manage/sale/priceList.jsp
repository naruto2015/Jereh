<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'priceList.jsp' starting page</title>
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
   $("#detail").datagrid({
        url:'/manage/base/GetBasePartsServlet',
		title:'明细',
		idField:'partsNo',
		singleSelect:false,
		fitColumns:true,
		//fit:true,
		pageSize:10,
		pagination:true,
		pageList:[2,5,10],
		columns:[[	
			//{checkbox:true},
			{field:'partsNo',title:'件号',width:70},
			{field:'partsName',title:'配件名称',width:70},
			{field:'partsBrand',title:'配件品牌',width:70},
			{field:'partsModel',title:'配件型号',width:70},
			{field:'nums',title:'数量',width:70},
			{field:'price',title:'单价',width:70},
			//{field:'nums*price',title:'金额',width:70},
			{field:'deliverymode',title:'交货期',width:70},
			{field:'remarks',title:'备注',width:70},
			{field:'opt',title:'操作',formatter:function(val,row,idx){
			var content = "<input type='button' value='删除' onclick=\"del('" + row.code + "')\" />";
				
				return content;	
			}}
		]],
    
    });
$('#detail').datagrid('getPager').pagination({
    	displayMsg:'当前显示从第 {from}到第 {to}，共 {total} 条记录'
	}); 
  $("#dialog").dialog({});
  $("#info").datagrid({
        url:'/manage/cus/ShowCustomerSupplier',
		idField:'code',
		singleSelect:false,
		fitColumns:true,
		fit:true,
		pageSize:10,
		pagination:true,
		pageList:[2,5,10],
		toolbar:'#tool',
		columns:[[	  
			//{checkbox:true},
			{field:'code',title:'客户代码',width:70},
			{field:'csName',title:'客户名称',width:70},
			{field:'linkMan',title:'联系人员',width:70},
			{field:'phone',title:'电话',width:70},
			{field:'fax',title:'传真',width:70},
			{field:'address',title:'地址',width:70},
		
		]],
   
   });

});
function add(){
  $("input[name='opt']").val(1);
		   $("#slist").submit();
}
  function save(){
		   $("input[name='opt']").val(2);
		   $("#slist").submit();
	
	}
	
	</script>

  </head>
  <%
  Date date=new Date();
     SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	 String time=sdf.format(date);
  %>
		 
  <body>
	<form action="/manage/sale/doSalePriceServlet" method="post" id="slist">
	<input type="hidden" name="opt" />
		<div>
			<table>
				<tr>
					
					<td>报价编号：</td>
					<td><input type="text" value="${list.get(0).getCode() }"
						readonly="readonly" name="priceCode">
					</td>
					<td>报价日期：</td>
					<td><input type="text" value="<%=time %>" class="easyui-datebox" name="sqdate">
					</td>
				</tr>
				<tr>
					<td>客户名称</td>
					<td><input type="text" value="${list.get(0).getCs().getCsName() }" name="csname">
					</td>
					<td>联系人员</td>
					<td><input type="text" value="${list.get(0).getContacter() }" name="contacter">
					</td>
				</tr>
				<tr>
					<td>电话</td>
					<td><input type="text" value="${list.get(0).getTelphone() }" name="telphone">
					</td>
					<td>传真</td>
					<td><input type="text" value="${list.get(0).getFax() }" name="fax">
					</td>
				</tr>
				<tr>
					<td>备注</td>
					<td colspan=4><input type="text" value="${list.get(0).getRemarks() }" name="remarks">
					</td>
				</tr>
			</table>

		</div>
		<input type="button" value="新增" onclick="add()" /> <input
			type="button" value="添加配件" onclick="addlpart()" /> <input
			type="button" value="保存" onclick="save()" /> <input type="button"
			value="撤销" onclick="retu()" /> <input type="button" value="打印"
			onclick="print()" /> <input type="button" value="生成word"
			onclick="outWord()" /> <input type="button" value="生成订单"
			onclick="outOrder()" /> <input type="button" value="关闭"
			onclick="close()" />
	</form>

	<div id="detail"></div>


	<div id="tool">
		<form method="">
			<b>检索条件</b>
			 客户代号：<input type="text" name="code"> 
			客户名称：<input type="text" name="name"> 
			<input type="button" value="搜索" onclick="search()" /> 
			<input type="reset" value="重置" />
		</form>
	</div>
	<div id="dialog"  title="请选择客户" style="height:300px ; width:700px;">
 <div id="info">
  </div>
  </div>

  </body>
</html>