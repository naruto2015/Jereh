<%@page import="java.util.Calendar"%>
<%@page import="com.root.base.entity.PageBean"%>
<%@page import="com.root.base.entity.BaseParts"%>
<%@page import="java.util.List"%>
<%@page import="com.root.base.service.impl.BasePartsService"%>
<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配件信息管理</title>
<script src="../console_ui/js/jquery-1.7.2.min.js" type="text/javascript"></script>
<script src="../console_ui/js/jquery.easyui.min.js" type="text/javascript"></script>
<link href="../console_ui/themes/default/easyui.css" rel="stylesheet" type="text/css"/>
<link href="../console_ui/themes/icon.css" rel="stylesheet" type="text/css"/>
<style>
#mydy td{
	width:65px;}
</style>
<script>
$(function(){
	$("input.easyui-datebox").datebox({
		formatter:function(date){
			var y = date.getFullYear();
			var m = date.getMonth() + 1;
			var d = date.getDate();
			var h=date.getHours();
			var min=date.getMinutes();
			var second=date.getSeconds();
			return y+m+d+hour+minute+second;
		},
		parse:function(date){
			var time = Date.parse(date);
			if(!isNaN(time))
				return new Date(time);
			else
				return new Date();
		}
	});
	$("#partsList").datagrid({
		title:'配件信息管理列表',
		fit:true,
		fitColumns:true,
		url:'/manage/base/GetBasePartsServlet',
		idField:'partsCode',
		singleSelect:false,
		pagination:true,
		pageList:[5,10,15],
		pageSize:5,
		toolbar:"#partstool",
		columns:[[	
			{field:'id',checkbox:'checked',formatter:function(idx){
				return idx;
			}},
			{field:'partsCode',title:'配件编码',width:120},
			{field:'partsNo',title:'配件件号', width:100},
			{field:'partsCategory',title:'配件类别',width:90},
			{field:'partsName',title:'配件名称', width:100},
			{field:'partsBrand',title:'配件品牌', width:90},
			{field:'partsModel',title:'配件型号', width:100},
			{field:'partsModelOld',title:'配件旧型号', width:100},
			{field:'salePrice',title:'配件销售价格', width:100},
			{field:'isShow',title:'显示状态', width:100},
			{field:'addUserName',title:'操作员', width:100},
			{field:'remarks',title:'备注', width:100},
			{field:'opt',title:'操作',width:100,
			formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick=\"del('"+row.partsCode+"')\"/>";
				content+="<input type='button' value='修改' onclick=\"setshow('"+idx+"')\"/>";
				return content;
			}}
		]],
	});
	$("#mydy").dialog("close");
});
function getCurDate(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDay();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	return "MTPJ"+year+month+day+hour+minute+second; 
}
function addshow(){
	$("#mydy").dialog("open");
	$("input[name='partscode']").val(getCurDate());
}
function addPart(){
	$("#myFrm").attr("action","/manage/base/AddBasePartsServlet");
	$("#myFrm").submit();
	$("#mydy").dialog("close");
}
function setshow(idx){
	$("#mydy").dialog("open");
	var row=$("#partsList").datagrid("getRows")[idx];
	$("input[name='partscode']").val(row.partsCode);
	$("input[name='partsname']").val(row.partsName);
	$("input[name='partscategory']").val(row.partsCategory);
	$("input[name='partsbrand']").val(row.partsBrand);
	$("input[name='partsno']").val(row.partsNo);
	$("input[name='partsgeneralpartsno']").val(row.partsGeneralPartsNo);
	$("input[name='partsmodel']").val(row.partsModel);
	$("input[name='partsmodelold']").val(row.partsModelOld);
	$("input[name='partssize']").val(row.partsSize);
	$("input[name='partsweight']").val(row.partsWeight);
	$("input[name='partsimg']").val(row.partsImg);
	$("input[name='partsunit']").val(row.partsUnit);
	$("input[name='saleprice']").val(row.salePrice);
	$("input[name='isshow']").val(row.isShow);
	$("input[name='remarks']").val(row.remarks);
}
function setPart(){
	$("#myFrm").attr("action","/manage/base/UpdateBasePartsServlet");
	$("#myFrm").submit();
	$("#mydy").dialog("close");
}
function del(partsCode){
	$.messager.confirm("删除提醒","确认删除吗？",function(r){
		if(r){
			$.ajax({
				url:'/manager/base/DelBasePartsServlet?partsCode='+partsCode,
				success:function(data){
					$("#partsList").datagrid("reload");
				}
			});
		}	
	});
}
function delsel(){
	var rows=$("#partsList").datagrid("getSelections");
	//alert(rows.length);
	if(rows.length==0){
		$.messager.alert("信息提示","请选择一条记录");
	}else{
		$.messager.confirm("删除确认","确实要删除记录吗？",function(r){
			if(r){
				var codes="";
				for(var i=0;i<rows.length;i++){
						if(i!=rows.length-1){
							codes+=rows[i].partsCode+",";
						}else{
							codes+=rows[i].partsCode;
						}		
				}	
				//alert(codes);
				$.ajax({
					url:'/manage/base/DelselServlet',
					data:{"partscodes":codes},
					type:'post',
					success:function(data){
						if(data==1){
							$.messager.alert("信息提示","删除成功！");
							$("#partsList").datagrid("reload");
						}else{
							$.messager.alert("信息提示","删除失败！");
					}
					}
				});
			}
		});
	}
}
function cls(){
	$("#mydy").dialog("close");
}
function search(){
	var No=$("input[name='partsNo']").val();
	var Name=$("input[name='partsName']").val();
	var Category=$("select[name='partsCategory']").val();
	data={"partsNo":No,"partsName":Name,"partsCategory":Category};
	$("#partsList").datagrid("reload",data);
}
function print(){
	$("#myFrm").attr("action","/manage/base/printBasePartsServlet");
	$("#myFrm").submit();
	$("#mydy").dialog("close");
}
</script>
</head>
<body>
<div id="partsList">
</div>
<div id="partstool" style="padding:3px">
	<table>
	<form id="find" method="post">
	    <tr>
	        <td><span>检索条件:</span></td>
	        <td>件号：<input type="text" name="partsNo"/></td>
	        <td>名称：<input type="text" name="partsName"/></td>
	        <td>类别：<select name="partsCategory"><option>--选择类别--</option>
	        <option value="轴承">轴承</option>
            <option value="皮带">皮带</option></select></td>
	        <td><input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置"/></td>
		</tr>
	</form>
	    <tr>
	        <td colspan="5"><a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addshow()">增加</a>
	    	<a href="#" id="elsel" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="delsel()">批量删除</a>
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">导出EXCEL</a>
	    	<a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-adjust'">价格调整</a></td>
	    </tr>
	</table>
</div>
<div id="mydy" style="padding:10px" class="easyui-dialog" title="配件信息">
<form id="myFrm" method="post">
	<table>
    	<tr>
        	<td>配件编号：</td>
            <td><input type="text" name="partscode"/></td>
            <td>配件名称：</td>
            <td><input type="text" name="partsname"/></td>
        </tr>
        <tr>
        	<td>配件类别：</td>
            <td><select name="partscategory"><option value="轴承">轴承</option>
            <option value="皮带">皮带</option></select></td>
            <td>配件品牌：</td>
            <td><select name="partsbrand"><option value="徐工">徐工</option>
            <option value="中联">中联</option></select></td>
        </tr>
        <tr>
        	<td>件号：</td>
            <td><input type="text" name="partsno"/></td>
            <td>通用件号：</td>
            <td><input type="text" name="partsgeneralpartsno"/></td>
        </tr>
        <tr>
        	<td>型号：</td>
            <td><input type="text" name="partsmodel"/></td>
            <td>旧型号：</td>
            <td><input type="text" name="partsmodelold"/></td>
        </tr>
        <tr>
        	<td>配件尺寸：</td>
            <td><input type="text" name="partssize"/></td>
            <td>配件重量：</td>
            <td><input type="text" name="partsweight"/></td>
        </tr><tr>
        	<td>配件图片：</td>
            <td><input type="button" name="partsimg" value="选择图片"/></td>
            <td>配件单位：</td>
            <td><select name="partsunit"><option value="套">套</option>
            <option value="件">件</option></select></td>
        </tr><tr>
        	<td>销售价格：</td>
            <td><input type="text" name="saleprice"/></td>
            <td>显示状态：</td>
            <td><input type="radio" name="isshow" value="1" checked="checked"/>显示
            <input type="radio" value="isshow" value="0"/>隐藏</td>
        </tr>
        <tr>
        	<td>备注：</td>
            <td colspan="3"><input type="text" name="remarks"/></td>
        </tr>
        <tr>
        	<td colspan="4"><input type="button" name="opt" value="新增" onclick="addPart()"/>
            <input type="button" name="opt" value="保存" onclick="setPart()"/>
            <input type="button" value="同步"/>
            <input type="button" value="打印" onclick="print()"/>
            <input type="button" value="关闭" onclick="cls()"/></td>
        </tr>
    </table>
</form>
</div>
</body>
</html>
