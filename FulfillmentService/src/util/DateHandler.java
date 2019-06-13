package util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateHandler {
	private static final Logger LOG = LoggerFactory.getLogger(DateHandler.class);
	// 어제 시간
	public String beforeTime() {
		LocalDateTime yTime = LocalDateTime.now();
		yTime = yTime.minusDays(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");	
    	return yTime.format(dtf);
	}
	
	// 어제 시간
	public String beforeDay() {
		LocalDateTime yDay = LocalDateTime.now();
		yDay = yDay.minusDays(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
    	return yDay.format(dtf);
	}
	
	// 전 달
	public String beforeMonth() {
		LocalDateTime yMonth = LocalDateTime.now();
		yMonth = yMonth.minusMonths(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");	
    	return yMonth.format(dtf);
	}
	
	// 어제 날짜
	public String yesterday() {
		LocalDateTime yesterday = LocalDateTime.now();
		yesterday = yesterday.minusDays(1);
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");	
    	return yesterday.format(dtf);
	}
	
	// 현재 시간
	public String currentTime() {
		LocalDateTime cTime = LocalDateTime.now();	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    	return cTime.format(dtf);
	}
	
	// 현재 날짜
	public String getToday() {
		LocalDateTime today = LocalDateTime.now();	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	return today.format(dtf);
	}
	
	// 현재 달
	public String getCurrentMonth() {
		LocalDateTime curMonth = LocalDateTime.now();	
    	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM");
    	return curMonth.format(dtf);
	}
	
	// 문자열을 날짜로 변환
	public Date transStringToDate(String date) {
		LOG.debug(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date cpDate = null;
		try {
			cpDate = sdf.parse(date);
			LOG.debug("cpDate : " + String.valueOf(cpDate));
		} catch(Exception e) {}
		return cpDate;
	}
	
	// 문자열을 날짜로 변환 yyyy-MM-dd
	public Date transStringToDateNotHours(String date) {
		LOG.debug(date);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date cpDate = null;
		try {
			cpDate = sdf.parse(date);
			LOG.debug("cpDate : " + String.valueOf(cpDate));
		} catch(Exception e) {}
		return cpDate;
	}
	
	// 날짜를 문자열로 변환
	public String transDateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = sdf.format(date);
		return time;
	}
	
	// 날짜를 문자열로 변환 yyyy-MM-dd
	public String transDateToStringNotHours(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String time = sdf.format(date);
		return time;
	}
	
	// 오전 오후 구하기 
	public String getAmPm(Date time) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		LOG.debug("getAmPm() : " + String.valueOf(cal.getTime()));
		if (cal.get(Calendar.AM_PM) == Calendar.AM)
			return "am";
		else if(cal.get(Calendar.AM_PM) == Calendar.PM)
			return "pm";
		return null;
	}
	
	// 오전 오후 시간
	public String getNumericTime(String amPm) {
		String timeStr = null;
		if (amPm.equals("am"))
			timeStr = " 09:00:00";
		if (amPm.equals("pm"))
			timeStr = " 18:00:00";
		return timeStr;
	}
	
	// 납품 전용 시간 
	public String getReleaseTime(String time) {
		String releaseTime = null;
		if(time.compareTo(beforeDay()) == 0) {
			releaseTime = getToday() + " 10:00:00";
			return releaseTime;
		}
		return time;
	}
	
	// 청구 전용 시간
	public String getChargeTime(String time) {
		String chargeTime = null;
		if(time.equals(beforeMonth())) {
			chargeTime = getCurrentMonth() + "-01";
			return chargeTime;
		}
		return time;
	}
	
	// 지급 전용 시간
	public String getPayTime(String time) {
		String payTime = null;
		if(time.equals(beforeMonth())) {
			payTime = getCurrentMonth() + "-01";
			return payTime;
		}
		return time;
	}
}
