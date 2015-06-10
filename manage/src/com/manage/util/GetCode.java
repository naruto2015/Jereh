package com.manage.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetCode {
public String get(){
   Date date=new Date();
   SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
   String time=sdf.format(date);
   String code="KGS"+time;
   return code;
	}
public String getPriceCode(){
	 Date date=new Date();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
	   String time=sdf.format(date);
	   String priceCode="MTBJ"+time;
	return priceCode;
}
}
