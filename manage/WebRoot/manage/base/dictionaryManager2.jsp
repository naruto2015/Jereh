<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>My JSP 'dictionayManager.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script src="../console_ui/js/jquery-1.7.2.min.js"></script>
	<script src="../console_ui/js/jquery.easyui.min.js"></script>
	<link type="text/css" href="../console_ui/themes/icon.css" rel="stylesheet" />
	<link type="text/css" href="../console_ui/themes/default/easyui.css" rel="stylesheet" />
	<script type="text/javascript">
	   $(function(){
	        $("#baseContent").datagrid({
	                 //pagePosition:bottom,
	                 url:'${pageContext.request.contextPath }/baseContentServlet',
	                 fit:true,
					 fitColumns:true,
					// idField:'id',
					
					 pagination:true,
					 
	                 pageList:[2,4,8],
	                 toolbar:"#bt",
	                 singleSelect:false,
	                 checkOnSelect:false,
	                 columns:[[
	                       {field:'id',checkbox:'checked',formatter:function(idx){
	                        
	                          return idx;
	                       	 
	                       }},
	                       {field:'categoryCode',title:'所属类别',width:100},
	                       {field:'code',title:'字典编号',width:100},
	                       {field:'codeName',title:'字典名称',width:100},
	                       {field:'compCode',title:'公司名称',width:100},
	                       {field:'orderNo',title:'排序编号',width:100},
	                       {field:'remarks',title:'备注',width:100},
	                       {field:'addUser',title:'操作员',width:100},
	                       {field:'isShow',title:'显示状态',width:100},
	                       {field:'addIp',title:'操作',width:120,
	                           formatter:function(val,row,idx){	
	                                  //  var va=row.code;
										var content="<input type='button' value='修改' onclick=\"set("+idx+")\"/>";
											content+="<input type='button' value='删除' onclick=\"del('"+row.code+"','"+row.categoryCode+"')\"/>";
											content+="<input type='button' value='打印' onclick=\"print("+idx+")\"/>";
										return content;
										}
	                       }
	                 ]]
	        });
	       
	          $.ajax({
	              url:'${pageContext.request.contextPath }/baseCategoryCodeServlet',
	              dataType:'json',
	              async:false,
	              success:function(data){
	                          var select2obj=document.getElementById("select2");
	                          for(var i=0;i<data.length;i++){
	                              //alert(data[i]);
	                              var optobj=$("<option>").appendTo(select2obj);
	                              optobj.append(data[i]);
	                              optobj.val(data[i]);
                           }
	              }
	         });
	   
	   });
	  
	function print(idx){
	     var row=$("#baseContent").datagrid("getRows")[idx];
	     var categoryCode=row.categoryCode;
	     //alert(categoryCode);
	     var orderNo=row.orderNo;
	     var codeName=row.codeName;
	     var isShow=row.isShow;
	     var code=row.code;
	     var remarks="";
 	 
 	     var obj={'categoryCode':categoryCode,'code':code,'orderNo':orderNo,'codeName':codeName,'isShow':isShow,'remarks':remarks}; 
 	     $.ajax({
 	       dataType:'json',
 	       type:'post',
 	       async:false,
 	       data:obj,
 	       url:'/manage/printBaseContentServlet',
 	       success:function(){
 	          window.location.href="/manage/downoadBaseContent?fileName=BaseContent.doc";
 	         // $("#baseContent").datagrid("reload");
 	       }
 	     });
	}  
	function set(idx){
	    //alert(value);
	    var row=$("#baseContent").datagrid("getRows")[idx];
 	    $("input[id='categoryCode']").val(row.categoryCode);
 	    $("input[id='code']").val(row.code);
 	    $("input[id='codeName']").val(row.codeName);
 	    $("input[id='compName']").val(row.compCode);
 	    $("input[id='addUser']").val(row.addUser);
 	    $("input[id='isShow']").val(row.isShow);
 	    $("input[id='code']").attr({disabled:"disabled"});
 	    $("input[id='categoryCode']").attr({disabled:"disabled"});
	    $('#add_base').dialog({
	          title:'修改',
	          width:250,
 	          height:300,
 	          buttons:[{text:'保存',
 	            handler:function(){
 	                 var categoryCode=$('#categoryCode').val();
 	                 var code=$('#code').val();
 	                 var codeName=$('#codeName').val();
 	                 var compName=$('#compName').val();
 	                 var addUser=$('#addUser').val();
 	                 var isShow=$('#isShow').val();
 	                 var obj={'categoryCode':categoryCode,'code':code,'codeName':codeName,'addUser':addUser,'isShow':isShow,'compName':compName};
 	                 $.ajax({
 	                   url:'${pageContext.request.contextPath }r/updateBaseContentServlet',
 	                   type:'post',
 	                   async:false,
 	                   data:obj,
 	                   success:function(data){
 	                    $('#add_base').dialog('close');
 	                    $("#baseContent").datagrid("reload");
 	                   
 	                   }
 	                 
 	                  });
 	                 
 	            }},
 	          {text:'关闭',
 	            handler:function(){
 	                 $('#add_base').dialog('close');
 	            }}
 	          ]
	    });
	}
	
	 
	function del(code,categoryCode){
	        var obj={'code':code,'categoryCode':categoryCode};
	        alert(categoryCode);
	        $.ajax({
	             async:false,
	             data:obj,
	             type:'post',
	             url:'${pageContext.request.contextPath }/delBaseContentServlet',
	             success:function(){
	                   
	                  $("#baseContent").datagrid("reload");
	             }
	           
	        
	          });
           	
 	}
 	function addBase(){
 	    //$("input[id='categoryCode']").attr({disabled:"false"});
 	    $("input[id='categoryCode']").removeAttr("disabled");
 	    $("input[id='categoryCode']").removeAttr("value");
 	    $("input[id='code']").removeAttr("value");
 	    $("input[id='codeName']").removeAttr("value");
 	    $("input[id='compName']").removeAttr("value");
 	    $("input[id='addUser']").removeAttr("value");
 	    $("input[id='isShow']").removeAttr("value");
 	    $("input[id='code']").removeAttr("disabled");
 	   
 	    $('#add_base').dialog({
 	          title:'My Dialog',
 	          width:250,
 	          height:300,
 	          buttons:[
 	              {text:'保存',iconCls:'icon-add',
 	              handler:function(){
 	                 var categoryCode=$('#categoryCode').val();
 	                 var code=$('#code').val();
 	                 var codeName=$('#codeName').val();
 	                 var compName=$('#compName').val();
 	                 var addUser=$('#addUser').val();
 	                 var isShow=$('#isShow').val();
 	                 //alert(isShow);
 	                 var obj={'categoryCode':categoryCode,'code':code,'codeName':codeName,'addUser':addUser,'isShow':isShow,'compName':compName};
 	                 
 	                 $.ajax({
 	                   url:'${pageContext.request.contextPath }/addBaseContentServlet',
 	                   type:'post',
 	                   async:false,
 	                   data:obj,
 	                   success:function(){
 	                    $('#add_base').dialog('close');
 	                    $("#baseContent").datagrid("reload");
 	                    
 	                   }
 	                 
 	                 });
 	              }
 	              },
 	               {text:'关闭',iconCls:'icon-remove',
 	              handler:function(){
 	                 $('#add_base').dialog('close');
 	              }
 	              }
 	          ]
 	    });
 	}
	
	 function delBatchRow(){
	        var rows=$("#baseContent").datagrid("getChecked");
	          alert(rows.length);
	          if(rows.length==0){
	              $.messager.alert("信息提示","请选择一条记录");
	          }else{
	          
	              $.messager.confirm("删除确认","确实要删除这天记录吗？",
					                function(r){
					                    if(r){
					                       for(var i=0;i<rows.length;i++){
					                       
					                           var categoryCode=rows[i].categoryCode;
					                           var code=rows[i].code;
					                           var obj={'categoryCode':categoryCode,'code':code};
					                           $.ajax({
					                                 url:'${ pageContext.request.contextPath }/delBatchRowServlet',
					                                 data:obj,
					                                 async:false,
					                                 type:'post',
					                                  
					                           });
					                       }
					                         $("#baseContent").datagrid("reload");
					                    }
					                  });
	          } 
	  
	 }
	 
	 function getRow(){
	 
	    var code2=$("#code2").val();
	    var codeName2=$("#codeName2").val();
	    var categoryCode2=$("#select2").val();
	    if(categoryCode2.trim()=="--选择类别--"){
	        categoryCode2="";
	    }
	    
	    obj={code2:code2,codeName2:codeName2,'categoryCode2':categoryCode2};
	    $("#baseContent").datagrid('reload',obj);
	    /* $.ajax({
	         url:'/Manager/getRowServlet',
	         data:obj,
	         async:false,
	         type:'post',
	         success:function(){
	             $("#baseContent").datagrid("reload");
	         }
	    
	    }); */
	 
	 }
	 
	</script>
 

  </head>
  
  <body>
  
  
     <div id="baseContent">
     </div>
   <div id="bt"> 
    <form>
      <table>
        <tr>
        <td>检索条件:</td>
        <td>字典编号:</td><td><input type="text" id="code2" /></td>
        <td>字典名称:</td><td><input type="text" id="codeName2" /></td>
        <td>所属类别:</td><td><select id="select2"><option>--选择类别--</option></select></td>
        <td><input type="button" value="搜索" onclick=getRow() /></td><td><input type="reset" value="重置" /></td>
      </tr>
     </table>
    </form>
    <hr>
       <a id="btn1" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true">查询</a>|  
       <a id="btn2" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true " onclick="addBase()">增加</a> | 
       <a id="btn3" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true " onclick="delBatchRow()">批量删除</a> | 
       <a id="btn4" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true " onclick="ExporterExcel()"> 导出EXCEL</a>  |
   </div>
 
 

     
     <table id="add_base" >
	     <tr><td>所属类别:</td><td><input type="text" id="categoryCode" /></td></tr>
	     <tr><td>字典编号:</td><td><input type="text" id="code" /></td></tr>
	     <tr><td>字典内容:</td><td><input type="text" id="codeName" /></td></tr>
	     <tr><td>公司名称:</td><td><input type="text" id="compName" /></td></tr>
	     <tr><td>操作员:</td><td><input type="text" id="addUser" /></td></tr>
	     <tr><td>显示状态:</td><td><select id="isShow"><option value="1">显示</option><option value="0">隐藏</option></select></td></tr>  
     </table>
    
</body>
</html>
 