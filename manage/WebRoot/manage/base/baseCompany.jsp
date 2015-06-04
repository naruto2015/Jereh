<%@page pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>公司信息管理</title>


 <script src="../console_ui/js/jquery-1.7.2.min.js"></script>
<script src="../console_ui/js/jquery.easyui.min.js"></script>
<link type="text/css" href="../console_ui/themes/icon.css" rel="stylesheet" />
<link type="text/css" href="../console_ui/themes/default/easyui.css" rel="stylesheet" />

<script>
	//文档就绪
	$(function(){

		$("td:odd").css({"padding-left":"80px"});
		$("td:even").css({"padding-left":"40px"});
		
	
	
	
	
		$.ajax({
		type:'post',
		async:false,
		url:'/manage/base/GetCompanyServlet',
		success:function(data){
		   //如果是新用户随机一个编号
			if(data==null){
			$.ajax({
		    type:'post',
		    async:false,
			url:'/manage/base/GetCompanyCodeServlet',success:function(data){
			$("input[name='compcode']").val(data);
			}
		});
			}else{
			$("input[name='compcode']").val(data.code);
			$("input[name='compname']").val(data.compName);
			$("input[name='compaddress']").val(data.compAddress);
			$("input[name='compzip']").val(data.compPostCode);
			$("input[name='comptel']").val(data.compPhone);
			$("input[name='compfax']").val(data.compFax);
			$("input[name='compurl']").val(data.compUrl);
			$("input[name='compemail']").val(data.compEmail);
			$("input[name='compegaler']").val(data.compEgaler);
			$("input[name='agent']").val(data.compAgent);
			$("input[name='account']").val(data.compAccount);
			$("input[name='bank']").val(data.compBank);
			$("input[name='tax']").val(data.compTax);
			$("input[name='remark']").val(data.remarks);
			
			
			}
		}
		});
		
		});	
		
	//提交表单保存
	function subSave(){
		$("#compForm").submit();
		}
		
		
	//取消表单提交
	
	function subCancel(){
	
		$("input").val("");
		}
</script>
<style>
#compList{ margin-left:10px; margin-top:10px;}
	table{ border:1px #0CF solid;  padding:0px;}
	table tr{ height:40px; text-align:center; margin:0px;}
	table tr td{ border-bottom:#0CF solid 2px;margin:12px }
	table tr td input{ width:100%; height:24px; margin:0p; padding:0px;}
	.icon{ color:#F00; }
	 button{ width:360px; height:28px; border:0px; margin-top:8px;}
	.subSaveBt{ margin-right:2px;}
	.subSaveBt:hover{ background:#F69; border:2px #F8FDFE solid;}
	.subCancelBt:hover{ background:#09C; border:2px #F8FDFE solid;}
</style>
</head>

<body>

	<div id="compList" >
    <div class="compForm">
    <form id="compForm" action="/manage/base/AddCompanyServlet" method="post">
    	<table>
        	<tr>
            	<td><i class="icon">*</i>公司代码:</td><td><input type="text" name="compcode" /></td>
                <td><i class="icon">*</i>公司名称:</td><td><input type="text" name="compname" /></td>
            </tr>
            
            <tr>
            	<td>公司地址:</td><td><input type="text" name="compaddress" /></td>
                <td>公司邮编:</td><td><input type="text" name="compzip" /></td>
            </tr>
            
            <tr>
            	<td>公司电话:</td><td><input type="text" name="comptel" /></td>
                <td>公司传真:</td><td><input type="text" name="compfax" /></td>
            </tr>
          
            <tr>
            	<td>公司网址:</td><td><input type="text" name="compurl" /></td>
                <td>电子邮箱:</td><td><input type="text" name="compemail" /></td>
            </tr>
            
             <tr>
            	<td>法&nbsp;&nbsp;&nbsp;&nbsp;人:</td><td><input type="text" name="compegaler" /></td>
                <td>委托代理:</td><td><input type="text" name="agent" /></td>
            </tr>
             
          
             <tr>
            	<td>账&nbsp;&nbsp;&nbsp;&nbsp;号:</td><td><input type="text" name="account" /></td>
                <td>银&nbsp;&nbsp;&nbsp;&nbsp;行:</td><td><input type="text" name="bank" /></td>
            </tr>
           
             <tr>
            	<td>税&nbsp;&nbsp;&nbsp;&nbsp;号:</td><td><input type="text" name="tax" /></td>
                <td>备&nbsp;&nbsp;&nbsp;&nbsp;注:</td><td><input type="text" name="tax" /></td>
            </tr>
        </table>
      </form>
        </div>
        <div class="bt">
        	<button class="subSaveBt" onclick="subSave()">保存</button><button class="subCancelBt" onclick="subCancel()">关闭</button>
        </div>
    </div>
</body>
</html>
