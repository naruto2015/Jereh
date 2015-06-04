<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     
    
    <title>My JSP 'purchaseInQuery.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script src="../console_ui/js/jquery-1.7.2.min.js"></script>
	<script src="../console_ui/js/jquery.easyui.min.js"></script>
	<link type="text/css" href="../console_ui/themes/icon.css" rel="stylesheet" />
	<link type="text/css" href="../console_ui/themes/default/easyui.css" rel="stylesheet" />



     <script type="text/javascript">
           $(function(){
	        $("#purchase").datagrid({
	                 //pagePosition:bottom,
	                // url:'${pageContext.request.contextPath }/baseContentServlet',
	                 fit:true,
					 fitColumns:true,
					// idField:'id',
					 pagination:true,
	                 pageList:[2,4,8],
	                 toolbar:"#head",
	                 singleSelect:false,
	                 checkOnSelect:false,
	                 columns:[[
	                       {field:'id',checkbox:'checked',formatter:function(idx){
	                        
	                          return idx;
	                       	 
	                       }},
	                       
	                       {field:'code',title:'询价编号',width:100},
	                       {field:'adddate',title:'询价日期',width:100},
	                       {field:'supplierCode',title:'供应商名',width:100},
	                       {field:'nums',title:'数量',width:100},
	                       {field:'numsPrice',title:'金额',width:100},
	                       {field:'contacter',title:'联系人',width:100},
	                       {field:'telphone',title:'联系方式',width:100},
	                       {field:'state',title:'审核状态',width:100},
	                       {field:'addUser',title:'操作员',width:100},
	                       {field:'addIp',title:'操作',width:120,
	                           formatter:function(val,row,idx){	
	                                  //  var va=row.code;
										var content="<input type='button' value='修改' onclick=\"set("+idx+")\"/>";
											content+="<input type='button' value='删除' onclick=\"del('"+row.code+"','"+row.categoryCode+"')\"/>";
										return content;
										}
	                       }
	                 ]]
	        });
	      
	$("#dt,#dt2").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
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
	       
	   });
	 function showQuery(){
		if($("#query").css("display")=='none'){
			$("#query").show();
		}else{
			$("#query").hide();
		}
       }
     
     </script>
  </head>
  
  <body>
  <div id="head">
  <form  id="query"><!-- style="display:none" -->
    <table>
    <tr>
	    <td>检索条件:询价编号:<input type="text" name="code" /></td><td>开始日期:<input id="dt" type="text" name="beginTime" /></td>
	    <td>结束日期:<input id="dt2" type="text" name="endTime" /></td><td>供应商名:<input type="text" name="supplierCode" /></td>
	    <td><input type="button" value="搜索" /><input type="reset" value="重置" /></td>
    </tr>
    </table>
    </form>
      <a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" onclick="showQuery()">查询</a>|  
      <a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true " onclick=addBase()>增加</a> | 
      <a id="btn3" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true " onclick=delBatchRow()>批量删除</a> | 
      <a id="btn4" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true " onclick=ExporterExcel()>导出EXCEL</a>  |
  </div>
  <div id="purchase"></div>
     
  </body>
</html>
