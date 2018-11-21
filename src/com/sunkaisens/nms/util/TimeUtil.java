package com.sunkaisens.nms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {


    public static String getMysqlFormatTime() {
        Calendar ca = Calendar.getInstance();
        Date date = ca.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat(
                "yyyy-MM-dd-HH:mm:dd");
        String now = dateformat.format(date).toString();
        return now;
    }
    
    public static String getNowTime(){
        Calendar ca = Calendar.getInstance();
        Date date = ca.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat(
                "yyyy-MM-dd  HH:mm:ss");
        String now = dateformat.format(date).toString();
        return now ;
    }

    public static String getExportFormatTime() {
        Calendar ca = Calendar.getInstance();
        Date date = ca.getTime();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String now = dateformat.format(date).toString();
        return now;
    }

    public static String getLocalTime() {
        Calendar ca = Calendar.getInstance();
        long lnow = ca.getTimeInMillis();
        String strnow = Long.toString(lnow);
        return strnow;
    }

    public static long getLongNowTime() {
        Calendar ca = Calendar.getInstance();
        long lnow = ca.getTimeInMillis();
        return lnow;
    }
    
    public static String getFormatDateString(String timeInMillis) {

		if ((timeInMillis.equals("None"))||(timeInMillis.equals("" ))) {
			return "00:00:00";
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(Long.valueOf(timeInMillis));
		return new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(calendar.getTime());
	}
    
    public static String getFormatTimeString(String s) {
    	Long timeInMillis = Long.valueOf(s)*1000;
    	if ((timeInMillis.equals("None"))||(timeInMillis.equals("" ))) {
    		return "00:00:00";
    	}
//    	Long d = timeInMillis/(24*60*60);
//    	Long m = timeInMillis%(24*60*60);
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(timeInMillis);
    	return new java.text.SimpleDateFormat("dd HH:mm:ss")
    	.format(calendar.getTime());
    }
    
    public static int getTheDay(String dateS) {
    	Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(Long.valueOf(dateS));
		return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getTheHour(String dateS) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(Long.valueOf(dateS));
    	return cal.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getTheMonth(String dateS) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTimeInMillis(Long.valueOf(dateS));
    	return cal.get(Calendar.MONTH)+1;
    }
    
    public static String dateToFormatTime(Date date) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    String time = dateFormat.format(date);
	    return time;
    }
    
    //ת�� ������ʱ ������ʱ���ʽ Ϊ���Ӧ��ms
    public static String  dateToMillionSeconds(Date date, String hour) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
 	    String time = dateFormat.format(date).toString();
 	    time=time+"-"+hour;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd-hh");
		long millionSeconds=sdf.parse(time).getTime();
		String st=Long.toString(millionSeconds);
		return st; 
	}
    
    public static String formatTimeToMs(String source) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long millionSeconds=sdf.parse(source).getTime();
    	String st=Long.toString(millionSeconds);
    	return st; 
    }
    
    public static String yearToMs(String year) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
    	long millionSeconds=sdf.parse(year).getTime();
    	String st=Long.toString(millionSeconds);
    	return st; 
    }
    public static String yearMonthToMs(String yearMonth) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
    	long millionSeconds=sdf.parse(yearMonth).getTime();
    	String st=Long.toString(millionSeconds);
    	return st; 
    }
    /*
     * �� yyyy-MM-dd��ʽ��ʱ��ת��Ϊ��Ӧ��ms
     */
    public static String yearMonthDayToMs(String yearMonthDay) throws ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	long millionSeconds=sdf.parse(yearMonthDay).getTime();
    	String st=Long.toString(millionSeconds);
    	return st; 
    }
    
    public static Long dateToMSLong(Date date, String hour) throws ParseException{
    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
    	String time = dateFormat.format(date).toString();
    	time=time+"-"+hour;
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd-hh");
    	long millionSeconds=sdf.parse(time).getTime();
    	return millionSeconds; 
    }
    
    public static String dateToFormate(Date date,String hour) throws ParseException {
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	String dateF = df.format(date);
    	String time = dateF+"  "+hour;
    	return time;
    }
    
    public static String dateToString(Date date) {
    	long time = date.getTime();
		String timeS = Long.toString(time); 
		return timeS;
    }
    
    public static String getInitialTimeString() {
    	Date date = new Date(0);
    	long time = date.getTime();
    	String timeS = Long.toString(time); 
    	return timeS;
    }
    
    public static String getNowTimeString() {
    	Date date = new Date(System.currentTimeMillis());
    	long time = date.getTime();
    	String timeS = Long.toString(time); 
    	return timeS;
    }
    
	private static long MIN = 60;
	private static long HOUR = 60 * MIN;
	private static long DAY = 24 * HOUR;

	public static String formatTimeGap(long sec) {
		long day = sec / DAY;
		sec = sec % DAY;
		long hour = sec / HOUR;
		sec = sec % HOUR;
		long min = sec / MIN;
		sec = sec % MIN;
		return String.format("%d:%02d:%02d:%02d", day, hour, min, sec);
	}
	
	public static void getCalendarTime () {
//		Calendar cal = Calendar.getInstance();
//		int val = cal.getFirstDayOfWeek();
		int days = Calendar.DAY_OF_MONTH;
		System.out.println(days);
		Calendar.getInstance();
	}
	 
	 public static String getFirstDayofMonth(int month) {
		 Calendar cal = Calendar.getInstance();
		 cal.set(cal.get(Calendar.YEAR),month, 1, 0, 0, 0);//First Day
		 Date date = cal.getTime();
		 return TimeUtil.dateToString(date);
//		 return TimeUtil.dateToFormatTime(date);
	 }
	 
	 public static String getLastDayofMonth(int month) {
		 Calendar cal = Calendar.getInstance();
		 cal.set(Calendar.MONTH, month+1);
		 cal.set(Calendar.DAY_OF_MONTH, 0);
		 cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH), cal.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59);//First Day
		 Date date = cal.getTime();
		 return TimeUtil.dateToString(date);
//		 return TimeUtil.dateToFormatTime(date);
	 }
	 
	public static long getTodayEarliest() {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),cal.get(Calendar.DATE), 
				0, 0, 0);
		return cal.getTimeInMillis();
	}
	
	public static int getNowYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public static int getNowMonth(){
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	/*
	 * ��õ�ǰ�µĵ�ǰ��
	 */
	public static int getDayOfMonth(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	public static int getNowWeekOfMonth(){
		return Calendar.getInstance().get(Calendar.WEEK_OF_MONTH);
	}
	public static long getTodayLatest(){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE),23, 59,59);
		return cal.getTimeInMillis();
	}
	
	 /*
	  * ���ĳ��ĳ�µ��������
	  */
	public static int getMaxDay(int year,int month){
		Calendar cal = Calendar.getInstance();
		cal.set(year, month,1);
		cal.add(Calendar.DAY_OF_YEAR, -1);
		return cal.get(Calendar.DAY_OF_MONTH);
	}
	
}
