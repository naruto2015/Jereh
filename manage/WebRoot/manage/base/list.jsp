
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.manage.util.GetCode"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  <Style>
  #table{
    border:1px blue solid;
    background-color: #08FE08;
  }
  </Style>
  <script src="/manage/manage/console_ui/js/jquery-1.7.2.min.js"></script>
		<script src="${ pageContext.request.contextPath }/manage/console_ui/js/jquery.easyui.min.js"></script>
		<link type="text/css" href="${ pageContext.request.contextPath }/manage/console_ui/themes/default/easyui.css" rel="stylesheet" />
		<link type="text/css" href="${ pageContext.request.contextPath }/manage/console_ui/themes/icon.css" rel="stylesheet" />
		 <script type="text/javascript">
		 </script>
		 <script>
		 function add(){
		 
		   $("input[name='opt']").val(1);
		   $("#clist").submit();
		 }
		 function save(){
		   $("input[name='opt']").val(2);
		   $("#clist").submit();
		 
		 }
		 function print(){
		 
		 }
		 function close(){
		    window.opener=null;
    window.open('','_self','');
    window.close();
		 }
		 </script>
  <body>
 <%
  Date date=new Date();
		    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time=sdf.format(date);
  %>
  <form action="/manage/cus/DoSupplier" method="post" id="clist">
  <input type="hidden"  name="opt"/>
  <table id="table" >
  
  <tr><td>*代码:</td><td><input type="text" readonly="readonly" value="${list.get(0).getCode() }" name="code" /></td>
  <td>*操作日期:</td><td><input type="text" value="<%=time %>" readonly="readonly" name="date"/></td></tr>
  <tr><td>*名称:</td><td><input type="text" value="${list.get(0).getCsName() }" name="pname"/></td><td>联系人员:</td><td><input type="text" value="${list.get(0).getLinkMan() }" name="linkman"/></td></tr>
  <tr><td>电话:</td><td><input type="text" value="${list.get(0).getPhone()}" name="telphone"/></td><td>传真:</td><td><input type="text" value="${list.get(0).getFax() }" name="fax"/></td></tr>
  <tr><td>邮编:</td><td><input type="text" value="${list.get(0).getPostCode() }" name="mcode"/></td><td>邮箱:</td><td><input type="text" value="${list.get(0).getEmail() }" name="email"/></td></tr>
  <tr><td>省市:</td><td><select value="${list.get(0).getProvince() }" name="province"><option value="1">北京</option>
				               <option value="2">山东</option>
				               <option value="3">广东</option>
				      </select>
				      <select value="${list.get(0).getCity() }" name="city"><option value="1">北京</option>
				               <option value="2">烟台</option>
				               <option value="3">深圳</option>
				      </select></td>
				<td>地址:</td><td><input type="text" value="${list.get(0).getAddress() }" name="address"/></td></tr>
  <tr><td>法人代表:</td><td><input type="text" value="${list.get(0).getLegaler() }" name="legaler"/></td>
  <td>网址:</td><td><input type="text" value="${list.get(0).getNetAddress() }" name="url"/></td></tr>
  <tr><td>QQ:</td><td><input type="text" value="${list.get(0).getQq() }" name="qq"/></td><td>MSN:</td><td><input type="text" value="${list.get(0).getMsn() }" name="msn"/></td></tr>
  <tr><td>阿里旺旺:</td><td><input type="text" value="${list.get(0).getWangWang() }" name="wangwang"/></td><td>委托代理:</td><td><input type="text" value="${list.get(0).getAgent() }" name="agent"/></td></tr>
  <tr><td>银行:</td><td><input type="text" value="${list.get(0).getBank() }" name="bank"/></td><td>开户帐号:</td><td><input type="text" value="${list.get(0).getAccount() }" name="account"/></td></tr>
  <tr><td>税号:</td><td><input type="text" value="${list.get(0).getTax() }" name="tax"/></td><td>类别:</td><td>
  <input type="radio" value="1" name="type"/>客户
  <input type="radio" value="2" name="type"/>供应商
    </td>
    <td> 状态:</td>
  <td><input type="radio" value="1" name="isshow" />显示
      <input type="radio" value="2" name="isshow" />隐藏</td></tr>
  <tr><td>备注:</td><td colspan=4><input type="text" value="${list.get(0).getRemarks() }" name="remarks"/></td></tr>
  
  
 
  </table>
  <input type="button" value="新增" onclick="add()" />
      <input type="button" value="保存"  onclick="save()" />
      <input type="button" value="打印" onclick="print()"/>
      <input type="button" value="关闭" onclick="close()"/>
 
  </form>
  </body>
</html>
