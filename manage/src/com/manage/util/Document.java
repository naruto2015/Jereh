package com.manage.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Document {
private Configuration con=null;
	
	public Document(){
		
		con = new Configuration();
		con.setDefaultEncoding("utf-8");
	}
	
	public void createDoc(Map<String,Object> dataMap,String fileName) throws UnsupportedEncodingException{
		
		//����ģ��װ�÷�����·��
		//ģ�����temp����
		con.setClassForTemplateLoading(this.getClass(),"/template");
		Template t=null;
		
		//Ϊ test.ftlװ��ģ��
		try {
			t=con.getTemplate("Document.ftl");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//����ĵ�·��������
		File outFile=new File(fileName);
		Writer out =null;
		FileOutputStream fos=null;
		
		try {
			
			fos=new FileOutputStream(outFile);
			OutputStreamWriter osw=new OutputStreamWriter(fos,"UTF-8");//����ط������ı��벻�ɻ�ȱ										
			out	=new BufferedWriter(osw);
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			//����ı�
			t.process(dataMap, out);
			out.close();
			fos.close();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
