package com.root.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	private static SimpleDateFormat dateFormat=
			new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat time=
			new SimpleDateFormat("hh:mm:ss");
	private static SimpleDateFormat longFormat=new 
			SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	private static SimpleDateFormat chFormat=new 
			SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
	private static SimpleDateFormat chFormat2=new 
			SimpleDateFormat("yyyy年MM月dd日 E");
	private static SimpleDateFormat dateCode=
			new SimpleDateFormat("yyyyMMddhhmmss");
	public static Date toDate(String strDate) 
			throws ParseException{
		return dateFormat.parse(strDate);
		
	}
	public static String toFormatDate(long time){
		return chFormat.format(new Date(time));
	}
	
	public static String toFormatDate(Date date){
		return chFormat.format(date);
	}
	public static String toFormatDateWithWeek(Date date){
		return chFormat2.format(date);
	}
	
	public static long getTime(){
		
		return new Date().getTime();
	}
	public static String getNamedByTime(){
		Calendar c=Calendar.getInstance();
		String name=c.get(Calendar.YEAR)+""+c.get(Calendar.MONTH)+""+c.get(Calendar.DAY_OF_MONTH)+
				c.get(Calendar.HOUR)+""+c.get(Calendar.MINUTE)+""+c.get(Calendar.SECOND);
		
		return name;
		
	}
	
	public static String getCompCode(Date time){
		return dateCode.format(time);
	}
	
	
}
