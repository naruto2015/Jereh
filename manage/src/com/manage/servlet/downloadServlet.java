package com.manage.servlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class downloadServlet extends HttpServlet {
	
	/**
	 * Constructor of the object.
	 */
	public downloadServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		 //获得请求文件名  
        String fileName = request.getParameter("fileName");  
        //System.out.println(fileName);  
          
        //设置文件MIME类型  
        response.setContentType(getServletContext().getMimeType(fileName)); 
        
        //设置Content-Disposition  
        //服务端向客户端游览器发送文件时，如果是浏览器支持的文件类型，一般会默认使用浏览器打开，比如txt、jpg等，会直接在浏览器中显示，
        //如果需要提示用户保存，就要利用Content-Disposition进行一下处理，关键在于一定要加上attachment：
        
        response.setHeader("Content-Disposition", "attachment;filename="+fileName);  
       
        //读取目标文件，通过response将目标文件写到客户端  
        //获取目标文件的绝对路径  	
        String fullFileName = getServletContext().getRealPath("/download/" + fileName);  
       // String fullFileName = getServletContext().getRealPath("/download/" + fileName);  
        //System.out.println(fullFileName);  
        
        //读取文件  
        
        InputStream in = new FileInputStream(fullFileName);  
        OutputStream out = response.getOutputStream();  
          
        //写文件  
        byte[] buff = new byte[2048];
        int b= 0;
        while(-1!=(b= in.read(buff,0,buff.length)))  //将输入流中最多buff.length个数据字节读入 byte 数组。
        {
            out.write(buff,0,b);
        }
          
        in.close();  
        out.close();  
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
