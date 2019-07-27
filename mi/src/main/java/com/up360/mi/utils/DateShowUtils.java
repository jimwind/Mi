package com.up360.mi.utils;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description 显示今天/明天/后天代替这三天的日期显示
 * @author mi.gao
 * @date  2015-12-5
 */
public class DateShowUtils {
	public static final String TIME_ERROR = "time error";
	public static final String TIME_ERROR_1 = "不能选过去的时间";
	public static final String TIME_ERROR_2 = "结束时间小于开始时间了";
	public static final String TIME_ERROR_3 = "请留至少30分钟的练习时间";

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static DateFormat df_yMdHm = new SimpleDateFormat("yyyy.MM.dd HH:mm");
	public static DateFormat df_H_m = new SimpleDateFormat("HH:mm");
	public static DateFormat df_y_M_d = new SimpleDateFormat("yyyy-MM-dd");
	public static DateFormat df_y_M_d_H_m = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static DateFormat df_CN_y_M_d = new SimpleDateFormat("yyyy年MM月dd日");
	public static DateFormat df_filename = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
	public static DateFormat df_yMd = new SimpleDateFormat("yyyy.MM.dd");
	public static DateFormat df_y_M_d2 = new SimpleDateFormat("yyyy/MM/dd");
	public static DateFormat df_Md_Hm = new SimpleDateFormat("MM月dd日 HH:mm");
	public static DateFormat df_Md_Hm2 = new SimpleDateFormat("MM月dd日HH:mm");
	public static DateFormat df_Hm = new SimpleDateFormat("HH:mm");

	/**
	 *
	 * @param s
	 * @param mD_value TODO 这个一定要给, 为了保证当前时间就是服务器时间
     * @return
     */
	public static String DateShow(String s, long mD_value){//mD_value 服务器与手机的时间差 单位是毫秒,服务器时间减去手机时间
		try {
			long cur_time = System.currentTimeMillis();
			cur_time += mD_value; //与服务器时间的偏差
			Date curDate = new Date(cur_time);
			Date selectDate = sdf.parse(s);
			long diff = selectDate.getTime() + 2 * 60 * 1000- curDate.getTime();//与当前时间间隔
			if(diff < 0){
				return TIME_ERROR_1;
			}
			//今天还有几毫秒!
			long today_remain = 1000 * 24 * 60 * 60 - curDate.getHours() * 60 * 60 * 1000 - curDate.getMinutes() * 60 * 1000 - curDate.getSeconds() * 1000;
			if(today_remain > diff){//今天剩下的秒，比所选时间与当前时间差大
				return "今天 "+ df_H_m.format(selectDate);// selectDate.getHours()+":"+selectDate.getMinutes();
			} else {
				long days = (diff - today_remain) / (1000 * 24 * 60 * 60);
				if(days < 1L){
					return "明天 "+ df_H_m.format(selectDate);//selectDate.getHours()+":"+selectDate.getMinutes();
				} else if(days < 2L){
					return "后天 "+ df_H_m.format(selectDate);//selectDate.getHours()+":"+selectDate.getMinutes();
				} else {
					return df_y_M_d_H_m.format(selectDate);
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return TIME_ERROR;
	}

	/**
	 * 开始时候可以是过去的(特别是在编辑状态下)
	 * @param s
	 * @param mD_value TODO 这个一定要给, 为了保证当前时间就是服务器时间
     * @return
     */
	public static String StartDateShow(String s, long mD_value){//mD_value 服务器与手机的时间差 单位是毫秒,服务器时间减去手机时间
		try {
			long cur_time = System.currentTimeMillis();
			cur_time += mD_value; //与服务器时间的偏差
			Date curDate = new Date(cur_time);
			Date selectDate = sdf.parse(s);
			long diff = selectDate.getTime() + 2 * 60 * 1000- curDate.getTime();//与当前时间间隔
			//过去的时间直接显示
			if(diff < 0){
				return df_y_M_d_H_m.format(selectDate);
			}
			//今天还有几毫秒!
			long today_remain = 1000 * 24 * 60 * 60 - curDate.getHours() * 60 * 60 * 1000 - curDate.getMinutes() * 60 * 1000 - curDate.getSeconds() * 1000;
			if(today_remain > diff){//今天剩下的秒，比所选时间与当前时间差大
				return "今天 "+ df_H_m.format(selectDate);// selectDate.getHours()+":"+selectDate.getMinutes();
			} else {
				long days = (diff - today_remain) / (1000 * 24 * 60 * 60);
				if(days < 1L){
					return "明天 "+ df_H_m.format(selectDate);//selectDate.getHours()+":"+selectDate.getMinutes();
				} else if(days < 2L){
					return "后天 "+ df_H_m.format(selectDate);//selectDate.getHours()+":"+selectDate.getMinutes();
				} else {
					return df_y_M_d_H_m.format(selectDate);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return TIME_ERROR;
	}

	/**
	 * 格式化显示日期 mi.gao
	 * @param dateString 需要显示的源日期
	 * @param mD_value  与服务器时间的偏差
     * @return
     */
	public static String DateFormat(String dateString, long mD_value){
		try {
			long cur_millisec = System.currentTimeMillis();
			cur_millisec += mD_value; //与服务器时间的偏差
			//需要格式化的时间
			Date selectDate = sdf.parse(dateString);

			//现在
			Date curDate = new Date(cur_millisec);
			//昨天
			Date yesterdayDate = new Date(cur_millisec - 24 * 60 * 60 * 1000);
			//明天
			Date tomorrowDate = new Date(cur_millisec + 24 * 60 * 60 * 1000);
			//后天
			Date afterTomorrowDate = new Date(cur_millisec + 24 * 60 * 60 * 1000 * 2);

			if(df_y_M_d.format(curDate).equals(df_y_M_d.format(selectDate))){
				return "今天 " + df_H_m.format(selectDate);
			}
			if(df_y_M_d.format(yesterdayDate).equals(df_y_M_d.format(selectDate))){
				return "昨天 " + df_H_m.format(selectDate);
			}
			if(df_y_M_d.format(tomorrowDate).equals(df_y_M_d.format(selectDate))){
				return "明天 " + df_H_m.format(selectDate);
			}
			if(df_y_M_d.format(afterTomorrowDate).equals(df_y_M_d.format(selectDate))){
				return "后天 " +df_H_m.format(selectDate);
			}
			return df_y_M_d_H_m.format(selectDate);
		} catch (ParseException e){
			e.printStackTrace();
		}
		return dateString;
	}
	public static String remainderTimeShow(String begin, String end, long d_value){
		Date curDate = new Date(System.currentTimeMillis() + d_value);
		String remainderTime = "";
		try {
			Date dateBegin = DateShowUtils.sdf.parse(begin);
			Date dateEnd = DateShowUtils.sdf.parse(end);
			if (dateEnd.compareTo(curDate) > 0
					&& dateBegin.compareTo(curDate) < 0) {
				long remain_day = (dateEnd.getTime() - curDate.getTime())
						/ (24 * 60 * 60 * 1000);
				if (remain_day >= 1) {
					remainderTime = remain_day + "天";
				} else {
					long remain = (dateEnd.getTime() - curDate.getTime()) / 3600000;
					if (remain >= 1) {
						remainderTime = String.valueOf(remain) + "小时";
					} else {
						remain = (dateEnd.getTime() - curDate.getTime()) / 60000;
						remainderTime = String.valueOf(remain) + "分钟";
					}
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return remainderTime;
	}
	/**
	 *
	 * @param sourceDate yyyy-MM-dd HH:mm:ss
	 * @param todayFormat
	 * @param otherFormat
	 * @return
	 */
	public static String showFormatDate(String sourceDate, DateFormat todayFormat, DateFormat otherFormat){
		if(!TextUtils.isEmpty(sourceDate)){
			String currentDate = DateShowUtils.df_y_M_d.format(System.currentTimeMillis());
			String yesterdayDate = DateShowUtils.df_y_M_d.format(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
			try {
				Date date = DateShowUtils.sdf.parse(sourceDate);
				String sDate = DateShowUtils.df_y_M_d.format(date);
				// 今天的时间
				if(currentDate.equals(sDate)){
					return todayFormat.format(date);
				} else if(yesterdayDate.equals(sDate)){
					return "昨天";
				} else {
					return otherFormat.format(date);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return sourceDate;
			}
		} else {
			return "";
		}
	}
	public static String showFormatDate(String sourceDate, DateFormat dateFormat){
		if(TextUtils.isEmpty(sourceDate)){
			return "";
		}
		try {
			Date date = sdf.parse(sourceDate);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return sourceDate;
		}
	}
	public static String showFormatSecond(int second){
		int minute =  second / 60;
		int sec = second % 60;
		if(minute == 0){
			return sec +"''";
		}
		return minute +"'"+sec +"''";
	}
	public static String showFormatSecondCn(int second){
		int minute =  second / 60;
		int sec = second % 60;
		if(minute == 0){
			return sec +"秒";
		}
		if(sec == 0){
			return minute +"分";
		}
		return minute +"分"+sec +"秒";
	}
	public static final String FORMAT_1 = "mm:ss";
	public static final String FORMAT_2 = "mm'ss''";
	public static String showFormatSecond(int second, String format){
		if(FORMAT_1.equals(format)){
			int minute = second / 60;
			int sec = second % 60;
			return String.format("%02d:%02d", minute, sec);

		} else if(FORMAT_2.equals(format)){
			int minute =  second / 60;
			int sec = second % 60;
			if(minute == 0){
				return String.format("%02d''", sec);
			}
			return String.format("%02d'%02d''", minute, sec);
		} else {
			return String.valueOf(second);
		}
	}
}
