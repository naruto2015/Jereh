<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主界面</title>
<script src="console_ui/js/jquery-1.7.2.min.js" language="javascript"></script>
<script src="console_ui/js/jquery.easyui.min.js"></script>
<link href="console_ui/themes/default/easyui.css" rel="stylesheet" type="text/css"/></link>
<link href="console_ui/themes/icon.css" rel="stylesheet" type="text/css"/></link>
<style>
ul,li{margin:0px; padding:0px}
#nav li{
	list-style:none;
	height:30px;
	margin:0px 2px;
	font-size:12px;
	line-height:30px; 
	border-bottom:#eee 1px solid;
	
}
li a{
	padding:0px 14px;
	text-decoration:none;
	color:#333;
	
}
li a:hover{
	display:block;
	height:100%;
	background-color:#FFC;
	}
	

</style>
<script>
function addTab(t,u){
	
	if($("#tt").tabs("exists",t)){
		$("#tt").tabs("select",t);
	}else{
		var tabContent="<iframe src=\""+u+"\" width=\"100%\" height=\"100%\" scrolling=\"yes\" frameborder=\"0\"></iframe>";
		$("#tt").tabs("add",{
			title:t,
			closable:true,
			fit:true,
			content:tabContent
			});
	}
}
</script>
</head>

<body>
<div class="easyui-layout" fit="true">
	<div region="north"  style="height:50px;">
    	<iframe src="top/head.html" frameborder="0" scrolling="no" width="100%" height="100%"></iframe>	
    </div>
    <div  data-options="region:'west',title:'导航',split:true"
    style="width:150px;">
    	<div id="nav" class="easyui-accordion" data-options="fit:true">
        	<div data-options="title:'采购管理',iconCls:'icon-add'" selected="true" >
                <ul >
                 <li><a href="#" onclick="addTab('询价单据管理','purchase/search.html')">询价单据管理</a></li>
                 <li><a href="#" onclick="addTab('采购订单管理','purchase/order.html')">采购订单管理</a></li>
                 <li><a href="#" onclick="addTab('采购退货管理','purchase/return.html')">采购退货管理</a></li>
                 <li><a href="#" onclick="addTab('采购合同管理','purchase/pact.html')">采购合同管理</a></li>
                </ul>
            </div>
            <div title="销售管理" iconCls="icon-remove">
                <ul >
               		<li><a href="#" onclick="addTab('报价单据管理','sell/offer.html')" >报价单据管理</a></li>   
                	<li><a href="#" onclick="addTab('销售订单管理','sale/order.jsp')" >销售订单管理</a></li>
                    <li><a href="#" onclick="addTab('销售退货管理','sell/return.html')" >销售退货管理</a></li>
                    <li><a href="#" onclick="addTab('销售合同管理','sell/pact.html')" >销售合同管理</a></li>     
                </ul>
            </div>
            <div title="库存管理" iconCls="icon-edit">
            	<ul>
                	<li><a href="#" onclick="addTab('入库单据管理','stock/put.html')">入库单据管理</a></li>
                    <li><a href="#" onclick="addTab('出库单据管理','stock/out.html')">出库单据管理</a></li>
                    <li><a href="#" onclick="addTab('库存盘点调价','stock/check.html')">库存盘点调价</a></li>
                    <li><a href="#" onclick="addTab('库存调拨管理','stock/allot.html')">库存调拨管理</a></li>
                    <li><a href="#" onclick="addTab('库存信息管理','stock/info.html')">库存信息管理</a></li>
                </ul>
            </div>
            <div title="财务管理" iconCls="icon-edit">
            	<ul>
                	<li><a href="#" onclick="addTab('采购付款管理','finance/pay.html')">采购付款管理</a></li>
                    <li><a href="#" onclick="addTab('采购收票管理','finance/out.html')">采购收票管理</a></li>
                    <li><a href="#" onclick="addTab('销售收款管理','finance/sell.html')">销售收款管理</a></li>
                    <li><a href="#" onclick="addTab('库存调拨管理','finance/allot.html')">库存调拨管理</a></li>
                    <li><a href="#" onclick="addTab('预付款项管理','finance/advance.html')">预付款项管理</a></li>
                    <li><a href="#" onclick="addTab('预收款项管理','finance/account.html')">预收款项管理</a></li>
                    <li><a href="#" onclick="addTab('费用转账管理','finance/transfer.html')">费用转账管理</a></li>
                </ul>
            </div>
            <div title="统计管理" iconCls="icon-large-chart">
            	<ul>
                	<li><a href="#" onclick="addTab('销售走势管理','statistics/pay.html')">销售走势管理</a></li>
                    <li><a href="#" onclick="addTab('销售排行管理','statistics/out.html')">销售排行管理</a></li>
                    <li><a href="#" onclick="addTab('采购走势管理','statistics/sell.html')">采购走势管理</a></li>
                    <li><a href="#" onclick="addTab('采购排行管理','statistics/allot.html')">采购排行管理</a></li>
                    <li><a href="#" onclick="addTab('应收应付管理','statistics/advance.html')">应收应付管理</a></li>
                    <li><a href="#" onclick="addTab('毛利润量管理','statistics/account.html')">毛利润量管理</a></li>
                    <li><a href="#" onclick="addTab('历史询价管理','statistics/transfer.html')">历史询价管理</a></li>
                    <li><a href="#" onclick="addTab('费用统计管理','statistics/transfer.html')">费用统计管理</a></li>
                    <li><a href="#" onclick="addTab('费用转账管理','statistics/transfer.html')">历史报价管理</a></li> 
                </ul>
            </div>
            <div title="基础管理" iconCls="icon-gear">
            	<ul>
                	<li><a href="#" onclick="addTab('往来单位管理','base/supplier.jsp')">往来单位管理</a></li>
                    <li><a href="#" onclick="addTab('字典内容管理','base/dictionaryManager2.jsp')">字典内容管理</a></li>
                    <li><a href="#" onclick="addTab('配件类别管理','base/PartTypeManage.jsp')">配件类别管理</a></li>
                    <li><a href="#" onclick="addTab('配件信息管理','base/baseparts.jsp')">配件信息管理</a></li>
                    <li><a href="#" onclick="addTab('公司信息管理','base/baseCompany.jsp')">公司信息管理</a></li>
                </ul>
            </div>
            <div title="系统管理" iconCls="icon-gear">
            	<ul>
                	<li><a href="#" onclick="addTab('管理人员管理','sys/user.html')">管理人员管理</a></li>
                    <li><a href="#" onclick="addTab('角色权限管理','sys/role.html')">角色权限管理</a></li>
                    <li><a href="#" onclick="addTab('期初数据管理','sys/data.html')">期初数据管理理</a></li>
                    <li><a href="#" onclick="addTab('期初库存管理','sys/stock.html')">期初库存管理</a></li>  
                </ul>
            </div>
        </div>
    
    </div>
    <div region="center"   title="工作区域">
    
    	<div id="tt" class="easyui-tabs" fit="true" >
        	<div  title="首页" closable="false"  fit="true" >		
        	<iframe src="home.html" width="100%" height="100%" scrolling="no" frameborder="0"></iframe>   
            
            </div>
           
        </div>
    
    
    </div>
<div data-options="region:'south',title:'底部区域'" style="height:60px"></div>
</div>
</body>
</html>
