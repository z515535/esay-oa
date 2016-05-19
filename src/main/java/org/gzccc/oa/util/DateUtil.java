package org.gzccc.oa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** 
* @author 作者 吴 耿锋: 
* @version 创建时间：2016年5月17日 下午8:48:26<p> 
* 类说明 日期时间工具类<p>
* 使用为13位java标准时间戳
*/
public class DateUtil {
	private DateUtil(){}
	
	public static SimpleDateFormat getDateFormat(){
		return new SimpleDateFormat("yyyy-MM-dd");
	}
	
	public static SimpleDateFormat getDateFormats(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}
	
	public int timeInterval(String timeStamp){
		return 0;
	}
	
	/**
	 * 获取当前时间 YYYY-MM-DD HH:MM:SS
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat sf = getDateFormats();
		return sf.format(new Date());
	}
	
	/**
	 * 时间戳转换日期 yyyy-MM-dd
	 * @param timeInterval 时间戳
	 * @return
	 */
	public static String time2Date(long timeInterval){
		try {
			SimpleDateFormat sf = getDateFormat();
			return sf.format(new Date(timeInterval));
		} catch (Exception e) {
			System.out.println("time2Date:时间转换失败");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String time2Date(String timeInterval){
		return time2Date(Long.parseLong(timeInterval));
	}
	
	/**
	 * 时间戳转换成具体日期 yyyy-MM-dd HH:mm:ss
	 * @param timeInterval 时间戳
	 * @return
	 */
	public static String time2DetailedDate(long timeInterval){
		try {
			SimpleDateFormat sf = getDateFormats();
			return sf.format(new Date(timeInterval));
		} catch (Exception e) {
			System.out.println("time2DetailedDate:时间转换失败");
			e.printStackTrace();
			return null;
		}
	}
	
	public static String time2DetailedDate(String timeInterval){
		return time2DetailedDate(Long.parseLong(timeInterval));
	}
	
	/**
	 * 计算两个时间戳的时间间隔 小时
	 * @param begin
	 * @param end
	 * @return
	 */
	public static int interval2TimeStamp(long begin,long end){
		try {
			long total_minute = end - begin;
			return (int) (total_minute/3600000);
		} catch (Exception e) {
			System.out.println("interval2TimeStamp:时间转换失败");
			e.printStackTrace();
			return -1;
		}
	}
	
	public static int interval2TimeStamp(String begin,String end){
		return interval2TimeStamp(Long.parseLong(begin), Long.parseLong(end));
	}

}
 