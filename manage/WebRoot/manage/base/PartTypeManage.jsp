<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>配件类型管理</title>
<script src="../console_ui/js/jquery-1.7.2.min.js"></script>
<script src="../console_ui/js/jquery.easyui.min.js"></script>
<link type="text/css" href="../console_ui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="../console_ui/themes/default/easyui.css" rel="stylesheet" />
<style>
form{
	font-size:14px;
	padding:2px;
}
tr{
	text-align:center;
}
#t td{
	display:block;
	height:25px;
	line-height:25px;
	text-align:center;
	margin:0px;
	padding:5px;
	border:#999 solid 1px;
	float:left;
}
#t #t1{
	display:block;
	width:170px;
	text-align:left;
}
#t #t2{
	display:block;
	width:435px;
	text-align:left;
}
#t #t2 input{
	display:block;
	width:400px;
}
</style>
<script>
function del(code){
	var id="00"+code;
	//var rowidx=$("#newsList").datagrid("getRowidx",code);
	//alert(id);
	$.messager.confirm("删除提醒","确认要删除记录吗？",function(r){
		if(r){
			$.ajax({
				url:'/manage/base/DeletePartTypeServlet',
				data:{"id":id},
				success:function(data){
					if(data==1){
						$.messager.alert("信息提示","删除成功！");
						$("#newsList").datagrid("reload");
					}else{
						$.messager.alert("信息提示","删除失败！");
					}
				}
			});
		}
	});
}
function delBatchRow(){
	var rows=$("#newsList").datagrid("getSelections");
	alert(rows);
	if(rows.length==0){
		$.messager.alert("信息提示","请选择一条记录");
	}else{
		$.messager.confirm("删除确认","确实要删除记录吗？",
		function(r){
			if(r){
				var codes="";
				for(var i=0;i<rows.length;i++){
						if(i!=rows.length-1){
							codes+=rows[i].id+",";
						}else{
							codes+=rows[i].id;
						}		
				}	
				//$.messager.alert("信息提示",codes);
				$.ajax({
					url:'/manage/base/DeleteBatchServlet',
					data:{"codes":codes},
					type:'post',
					success:function(data){
						if(data==1){
							$.messager.alert("信息提示","删除成功！");
							$("#newsList").datagrid("reload");
						}else{
							$.messager.alert("信息提示","删除失败！");
					}
					}
				});
			}
		});
	}
}
function add(){
	$("#mydg1").dialog("open");
}
function update(idx){
	$("#mydg2").dialog("open");
	var row=$("#newsList").datagrid("getRows")[idx];
	$("input[name='remark']").val(row.remark);
	$("input[name='id1']").val(row.id);
	$("input[name='name']").val(row.name);
	$("input[name='state']").val(row.state);
	
}
$(function(){
	$("#newsList").datagrid({
		title:'信息列表',
		url:'/manage/base/GetPartTypeJsonServlet',
		fit:true,
		pagination:true,//带分页
		pageList:[2,5,10,15],
		pageSize:2,//初始1条一页
		idField:'id',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:'#newTools',
		columns:[
			  [
			  {field:'check',checkbox:true},
			  {field:'id',title:'类型编号',width:150},
			  {field:'name',title:'类别名称',width:150},
			  {field:'date',title:'操作日期',width:100},
			  {field:'remark',title:'备注',width:150,},
			  {field:'operator',title:'操作员',width:150},
			  {field:'state',title:'显示状态',width:150},
			  {field:'opt',title:'操作',width:150,
				 formatter:function(val,row,idx){
					  var content="<input type='button' value='删除' onclick='del("+row.id+")'/>";
					  content+="<input type='button' value='修改' onclick='update("+idx+")'/>";
					  return content;
			  }
			  }
			  ]
			  ]
	});
	$("#newsList").datagrid("getPager").pagination({
    	displayMsg:'当前显示从第 {from}到第 {to}，共 {total} 条记录'
	}); 
	$("#mydg1").dialog({
		title:'添加配件',width:600,height:200,
		buttons:[
		{text:'保存',handler:function(){
			$("#myFrm1").submit();
			$("#mydg1").dialog("close");
		}},{text:'打印',handler:function(){
			print();
		}},{text:'返回',handler:function(){
			$("#mydg1").dialog("close");
		}}
		]
	});
	$("#mydg1").dialog("close");
	$("#mydg2").dialog({
		title:'修改配件',width:600,height:200,
		buttons:[
		{text:'保存',handler:function(){
			$("#myFrm2").submit();
			$("#mydg2").dialog("close");
		}},{text:'打印',handler:function(){
			print();
		}},{text:'返回',handler:function(){
			$("#mydg2").dialog("close");
		}}
		]
	});
	$("#mydg2").dialog("close");
	$.ajax({
		url:'/manage/base/GetNameServlet',
		type:'post',
		dataType:'json',
		success:function(data){
			for(var i=0;i<data.length;i++){
				$("<option>").appendTo("select[name='name']").html(data[i]);
			}
		}
	});
});
function search(){
	var name=$("select[name='name']").val();
	//alert(name);
	data={"name":name};
	$("#newsList").datagrid("reload",data);
}
function showFrm(){
	if($("#see").css("display")=='none'){
		$("#see").show();
	}else{
		$("#see").hide();
	}
}
function excel(){
	
}
function print(){
	//var type="一级类别";
	var type=$("select[name='t']").val();
	var id=$("input[name='id1']").val();
	var name=$("input[name='name']").val();
	var state=$("input[name='state']").val();
	var remark=$("input[name='remark']").val();
	//alert(type);
	//alert(id);
	//alert(name);
	//alert(state);
	$.ajax({
		url:'/manage/util/PrintServlet',
		data:{"type":type,"id":id,"name":name,"state":state,"remark":remark,"ftl":"tem.ftl"},
		type:'post',
		datatype:'json',
		success:function(data){
			if(data.fileName!=null){
				window.location.href="/manage/cus/downloadServlet?fileName="+data.fileName;
				//$.messager.alert("信息提示","打印完成！");
			}
		}
	});
	
}
</script>
</head>
<body>
<div id="newTools" >
<form style="display:none" id="see">
	检索条件:	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类型编号：<input type="text" name="id"/>
          所属类别：<select name="name">
    		<option>--选择类别--</option>
    		
    		</select>
    <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置" />
</form>
    <a href="#" class="easyui-linkbutton"  data-option="iconCls:'icon-search'" onclick="showFrm()">查询</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-add'" onclick="add()">增加</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="delBatchRow()">批量删除</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-edit'" onclick="excel()">导出EXCEL</a>
</div>
<div id="newsList">

</div>
<div id="mydg1" style="padding:10px">
	<form id="myFrm1" method="post" action="/manage/base/AddPartTypeSevlet">
	<table id="t">
           <input type="hidden" name="operator" value="演示账号"/>
           <input type="hidden" name="optname" value="min"/>
		<tr><td>所属类别：</td><td id="t1"><select name="t">
           	<option>--选择类别--</option>
               <option>一级类别</option>
               <option>二级类别</option>
           </select></td><td>类别编号：</td><td id="t1"><input type="text" name="id1" /></td></tr>
		<tr><td>类别名称：</td><td id="t1"><input type="text" name="name"/></td>
           <td>显示状态：</td><td id="t1">
           	<input type="radio" name="state" value="1"/>显示
               <input type="radio" name="state" value="0"/>隐藏
           </td></tr>
           <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;备注：</td><td colspan="3" id="t2"><input type="text" name="remark"/></td></tr>
    </table>
	</form>		
	</div>	
    <div id="mydg2" style="padding:10px">
	<form id="myFrm2" method="post" action="/manage/base/UpdatePartTypeServlet">
		<table id="t">
			<tr><td>所属类别：</td><td id="t1"><select name="t">
            	<option>--选择类别--</option>
                <option>一级类别</option>
                <option>二级类别</option>
            </select></td><td>类别编号：</td><td id="t1"><input type="text" name="id1" /></td></tr>
			<tr><td>类别名称：</td><td id="t1"><input type="text" name="name"/></td>
            <td>显示状态：</td><td id="t1">
            	<input type="radio" name="state" value="1" checked="checked"/>显示
                <input type="radio" name="state" value="0"/>隐藏
            </td></tr>
            <tr><td>&nbsp;&nbsp;&nbsp;&nbsp;备注：</td><td colspan="3" id="t2"><input type="text" name="remark"/></td></tr>
          </table>
		</form>
	</div>		
</body>
</html>


