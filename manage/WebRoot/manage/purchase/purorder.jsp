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
		//fit:true,
		//fitColumns:true,
		url:'/manage/purchase/GetPurorderServlet',
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
			{field:'odate',title:'订单日期',width:65},
			{field:'supplier',title:'供应商名',formatter:function(val,row,idx){
				return val.csName;
			}},
			{field:'nums',title:'数量',width:100},
			{field:'amount',title:'金额',width:100},
			{field:'linkman',title:'联系人',width:100},
			{field:'tel',title:'联系方式',width:100},
			{field:'state',title:'审核状态',width:100},
			{field:'operator',title:'操作员',width:100},
			{field:'opt',title:'操作',width:150,formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick=\"delRow('"+row.code+"')\"/>";
				content+="<input type='button' value='修改' onclick='update("+idx+")'/>";
				return content;
			}}
		]]
	});
	$("#orderList").datagrid({onDblClickRow:function(idx,data){
		showDetail(idx);
	}});
	$("#orderList").datagrid("getPager").pagination({
    	displayMsg:'当前显示从第 {from}条到第 {to}条，共 {total}条记录'
	});
	
	$("input.easyui-datebox").datebox({
		formatter: function(date){
		var y = date.getFullYear();
		var m = date.getMonth()+1;
		var d = date.getDate();
		return d+"-"+m+"月-"+y;
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
function getCurDate(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDay();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	return "MTCG"+year+month+day+hour+minute+second; 
}
function add(){
	$("#mydg1").dialog("open");
	var ocode=$("input[name='code1']").val(getCurDate());
	show2(ocode);
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
						if(i!=rows.length-1){
							codes+=rows[i].code+",";
						}else{
							codes+=rows[i].code;
						}		
				}	
				//$.messager.alert("信息提示",codes);
				$.ajax({
					url:'/manage/purchase/DeleteBatchPoServlet',
					data:{"codes":codes},
					type:'post',
					success:function(data){
						if(data==1){
							$.messager.alert("信息提示","删除成功！");
							$("#orderList").datagrid("reload");
							$("#detailList").datagrid("reload");
						}else{
							$.messager.alert("信息提示","删除失败！");
						}
					}
				});
			}
		});
	}
}
function update(idx){
	$("#mydg1").dialog("open");
	var row=$("#orderList").datagrid("getRows")[idx];
	$("input[name='remarks']").val(row.remark);
	var ocode=$("input[name='code1']").val(row.code);
	show2(ocode);
}
function subFrm2(){
	$("#myFrm").attr("action","/manage/purchase/UpdatePurorderServlet");
	$("#myFrm").submit();
	$("#mydg1").dialog("close"); 
}
function delRow(ocode){
	//alert(ocode);
	$.messager.confirm("删除提醒","确认要删除记录吗？",function(r){
		if(r){
			$.ajax({
				url:'/manage/purchase/DeletePoServlet?ocode='+ocode,
				success:function(data){
					if(data==1){
						$.messager.alert("信息提示","删除成功！");
						$("#orderList").datagrid("reload");
						$("#detailList").datagrid("reload");
					}else{
						$.messager.alert("信息提示","删除失败！");
					}
				}
			});
		}
	});
}
function show1(){
	$("#mydg2").dialog("open");
	$("#purchase").datagrid({            
		 //fit:true,
		 url:'/manage/purchase/GetPurInQueryServlet',
		 fitColumns:true,
		 idField:'id',
		 toolbar:"#head",
		 singleSelect:false,
		 checkOnSelect:false,
		 columns:[[
			   {field:'id',checkbox:true},
			   {field:'code',title:'询价编号'},
			   {field:'addDate',title:'询价日期'},
			   {field:'supplier',title:'供应商名',formatter:function(val,row,idx){
				return val.csName;
			   }},
			   {field:'nums',title:'数量'},
			   {field:'numsPrice',title:'金额'},
			   {field:'num',title:'未选数量'},
			   {field:'numPrice',title:'未选金额'},
			   {field:'state',title:'审核状态'}
		 ]]
	});
	
	$("#purchase").datagrid({onDblClickRow:function(idx,data){
		$("input[name='nums1']").val(data.nums);
		$("input[name='numsPrice1']").val(data.numsPrice);
		var ocode=$("input[name='code1']").val();
		var xcode=data.code;
		$.ajax({
			url:'/manage/purchase/AddPoDetailServlet',
			data:{"ocode":ocode,"xcode":xcode}
		});
		$("#searchList").datagrid("reload",ocode);
	}});
}
function clo(){
	$("#mydg1").dialog("close");
}
function addPart(){
	$("#mydg3").dialog("open");
	$("#partsList").datagrid({
		//fit:true,
		url:'/manage/purchase/GetPartsServlet',
		fitColumns:true,
		idField:'id',
		singleSelect:false,
		checkOnSelect:false,
		toolbar:"#partstool",
		columns:[[	
			{field:'id',checkbox:true},
			{field:'partsCode',hidden:true},
			{field:'partsNo',title:'配件件号'},
			{field:'partsName',title:'配件名称'},
			{field:'partsBrand',title:'配件品牌'},
			{field:'partsModel',title:'配件型号'},
			{field:'salePrice',title:'单价'},
			{field:'compcode',title:'供应商'},
			{field:'lastPrice',title:'上次价格'},
			{field:'remarks',title:'备注'}
		]],
	});
	$("#partsList").datagrid({onDblClickRow:function(idx,data){
		var pcode=data.partsCode;
		var ocode=$("input[name='code1']").val();
		$.ajax({
			url:'/manage/purchase/AddPartsServlet',
			data:{"ocode":ocode,"pcode":pcode,"nums":"5"}
		});
	}});
	$("#searchList").datagrid("reload",ocode);
}
function choosePerson(){
	$("#mydg4").dialog("open");
	$("#personList").datagrid({
		//fit:true,
		url:'/manage/purchase/GetSupplierServlet',
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
	$("#personList").datagrid({onDblClickRow:function(idx,data){
		$("input[name='csname']").val(data.csName);
		$("input[name='supplierCode']").val(data.code);
		$("input[name='linkman']").val(data.linkMan);
		$("input[name='tel']").val(data.phone);
		$("input[name='zip']").val(data.fax);
		$("#mydg4").dialog("close");
	}});
}
function search(){
	var code=$("input[name='ocode']").val();
	var beginDate=$("input[name='beginDate']").val();
	var endDate=$("input[name='endDate']").val();
	var csname=$("input[name='cname']").val();
	data={"code":code,"beginDate":beginDate,"endDate":endDate,"csname":csname};
	$("#orderList").datagrid("reload",data);
}
function subFrm1(){
	$("#myFrm").attr("action","/manage/purchase/AddPurorderServlet");
	$("#myFrm").submit();
	$("#mydg1").dialog("close");  
}
function showDetail(idx){
	var row=$("#orderList").datagrid("getRows")[idx];
	$("#detailList").css("display:block");
	$("#detailList").datagrid({
		title:'单据编号为：'+row.code+'的明细如下所列！',
		url:'/manage/purchase/GetPoDetailServlet?ocode='+row.code,
		columns:[[
			{field:'xcode',title:'询价编号',width:120},
			{field:'part',title:'配件件号',width:100,formatter:function(val,row,idx){
				  return val.partsNo;
			   }},
			{field:'partsName',title:'配件名称',width:100,formatter:function(val,row,idx){
				  return row.part.partsName;
			}},
			{field:'partsBrand',title:'配件品牌',width:100,formatter:function(val,row,idx){
				  return row.part.partsBrand;
			}},
			{field:'partsModel',title:'配件型号',width:100,formatter:function(val,row,idx){
				  return row.part.partsModel;
			}},
			{field:'nums',title:'数量',width:100},
			{field:'salePrice',title:'单价',width:100,formatter:function(val,row,idx){
				  return row.part.salePrice;
			}},
			{field:'price',title:'金额',width:100},
			{field:'remark',title:'备注',width:100},
			{field:'lastPrice',title:'上次价格'}
		]]
	});
}
function show2(ocode){
	$("#searchList").datagrid({
		//fit:true,
		url:'/manage/purchase/GetPoDetailServlet?ocode='+ocode,
		fitColumns:true,
		isField:'xcode',
		singleSelect:false,
		checkOnSelect:false,
		columns:[[
			{field:'dcode',hidden:true},
			{field:'xcode',title:'询价编号'},
			{field:'part',title:'配件件号',formatter:function(val,row,idx){
				  return val.partsNo;
			}},
			{field:'partsName',title:'配件名称',formatter:function(val,row,idx){
				  return row.part.partsName;
			}},
			{field:'partsBrand',title:'配件品牌',formatter:function(val,row,idx){
				  return row.part.partsBrand;
			}},
			{field:'partsModel',title:'配件型号',formatter:function(val,row,idx){
				  return row.part.partsModel;
			}},
			{field:'nums',title:'数量'},
			{field:'salePrice',title:'单价',formatter:function(val,row,idx){
				  return row.part.salePrice;
			}},
			{field:'price',title:'金额'},
			{field:'remark',title:'备注'},
			{field:'opt',title:'操作',formatter:function(val,row,idx){
				var content="<input type='button' value='删除' onclick=\"del('"+row.dcode+"')\"/>";
				return content;
			}},
			{field:'lastPrice',title:'上次价格'},
		]]
	});
}
function del(dcode){
	$.ajax({
		url:'/manage/purchase/DeletePoDetailServlet?dcode='+dcode,
		success:function(data){
			if(data==1){
				$("#searchList").datagrid("reload");
			}
		}
	});
}
function check(){
	$("input[name='state']").val("1");
}
function print(){
	var ocode=$("select[name='code1']").val();
	var odate=$("input[name='odate']").val();
	var csname=$("input[name='csname']").val();
	var linkman=$("input[name='linkman']").val();
	var tel=$("input[name='tel']").val();
	var fax=$("input[name='zip']").val();
	var trans=$("input[name='way']").val();
	var ddate=$("input[name='ddate']").val();
	var person=$("input[name='person']").val();
	var remark=$("input[name='remarks']").val();
	$.ajax({
		url:'/manage/util/PrintPurOrderServlet',
		data:{"ocode":ocode,"odate":odate,"csname":csname,"linkman":linkman,"tel":tel,"fax":fax,
			"trans":trans,"ddate":ddate,"person":person,"remark":remark,"ftl":"Purorder.ftl"},
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
<div id="orderTools">
	<form id="see" style="display:none">
	    检索条件:&nbsp;&nbsp;订单编号：<input type="text" name="ocode" />
	    开始日期：<input name="beginDate" type="text" class="easyui-datebox"  /> 
	    结束日期：<input name="endDate" type="text" class="easyui-datebox"  /> 
	    供应商名：<input name="cname" type="text" /> 
    <input type="button" value="搜索" onclick="search()"/><input type="reset" value="重置" />
    </form>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-search'" onclick="showFrm()">查询</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-add'" onclick="add()">增加</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-cancel'" onclick="delBatchRow()">批量删除</a>
    <a href="#" class="easyui-linkbutton"  data-options="iconCls:'icon-undo'" onclick="excel()">导出EXCEL</a>
</div>
<div id="orderList" style=" height:300px">

</div>
<div id="mydg1" class="easyui-dialog" title="采购订单" style="margin:5px;">
	<form id="myFrm" method="post">
	<input type="hidden" name="supplierCode">
	<input type="hidden" name="operator" value="bimt">
	<input type="hidden" name="nums1" >
	<input type="hidden" name="numsPrice1" >
	<input type="hidden" name="state" value="0">
    <table id="t">
    <tr><td>订单编号:</td><td><input type="text" name="code1" /></td>
    <td>订单日期:</td><td><input type="text" name="odate" class="easyui-datebox" /></td></tr>
    <tr><td>供应商名:</td><td><input type="button" name="csname" value="请双击选择供应商" ondblclick="choosePerson()"/></td>
    <td>联系人员:</td><td><input type="text" name="linkman" /></td></tr>
    <tr><td>电话:</td><td><input type="text" name="tel" /></td>
    <td>传真:</td><td><input type="text" name="zip" /></td></tr>
    <tr><td>运输方式:</td><td><select name="way">
    	<option>圆通快递</option><option>EMS</option><option>中通快递</option>
    </select></td>
    <td>交货日期:</td><td><input type="text" name="ddate" class="easyui-datebox" /></td></tr>
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
    	
    </div>
</div>
<div id="detailList" style=" height:200px; position:fixed; bottom:0px; display:none">
</div>
</body>
</html>
