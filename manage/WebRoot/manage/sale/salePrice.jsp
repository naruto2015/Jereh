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
$("#showData").hide();
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
	    url:'/manage/sale/getSalePriceServlet',
		title:'报价单据管理',
		idField:'code',
		singleSelect:false,
		fitColumns:true,
		//fit:true,
		pageSize:10,
		pagination:true,
		pageList:[2,5,10],
		toolbar:'#Tool',
		columns:[[	
			//{checkbox:true},
			{field:'code',title:'报价单号',width:70,
			 formatter:function(val,row,idx){
                       return  "<a onclick=\"show('"+row.code+"')\" href='#' target='_self'>"+val+"</a>";
                  }
			},
			{field:'sqdate',title:'报价日期',width:70},
			{field:'cs',title:'客户名称',width:70,
			formatter:function(val,row,idx){
			   return val.csName;
			}},
			{field:'nums',title:'数量',width:70},
			{field:'numsPrice',title:'总货值',width:70},
			{field:'contacter',title:'联系人',width:70},
			{field:'telphone',title:'联系方式',width:70},
			{field:'state',title:'审核状态',width:70,
			formatter:function(val,row,idx){
			      if(val=="1"){
			             return "完成";
			             }
			             if(val=="2"){
			             return "未完成";
			             }
			}},
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
   
   
   function show(code){
  
   $("#codeinfo").text(code);  
   $("#showData").show();
   $("#showList").datagrid({
   
  
       // idField:'code',
		singleSelect:false,
		fitColumns:true,
		//fit:true,
		pageSize:10,
		pagination:true,
		pageList:[2,5,10],
        url:'/manage/sale/getPriceDetailServlet',
        queryParams:{'code':code},
        columns:[[{field:'code',title:'报价单明细编号',width:70},
                 {field:'scode',title:'报价单编号',width:70},
                 {field:'pcode',title:'配件编号',width:70},
                 {field:'nums',title:'数量',width:70},
                 {field:'price',title:'单价',width:70},
                 {field:'deliverymode',title:'交货期',width:70},
                 {field:'remarks',title:'备注',width:70}
       ]]
       
   
   });
}
function del(cid){
	
	$.ajax({
	url:'/manage/sale/deleteSalePriceServlet?cid='+cid,
	success:function(data){
	 $("#sale").datagrid("reload");
	}
	});
}
   function change(code){
     window.location.href="/manage/sale/updateSalePriceServlet?code="+code;
   }
function add(){
   	window.location.href="/manage/sale/addSalePriceServlet";
}
  function search(){
   var code=$("input[name='code']").val();
    var csName=$("input[name='pname']").val();
    var sqdate=$("input[name='startdate']").val();
  //var addDate=$("input[name='enddate']").val();
    $("#sale").datagrid("reload",{'code':code,'csName':csName,'sqdate':sqdate});
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
<div id="showData">
		单据编号为: <strong id="codeinfo"></strong>的明细如下列！
		<div id="showList"></div>
		</div>
  </body>
</html>
