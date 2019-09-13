/**
 * 
 */
package com.rockingengineering.bms.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import lombok.experimental.UtilityClass;

/**
 * @author naveen
 *
 * @date 04-Sep-2019
 */
@UtilityClass
public class DateUtils {

	public static LocalDate getLocalDateFromDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalTime getLocalTimeFromDate(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
	}

	public static Date convertLocalDateToDate(LocalDate localdate) {

		if (localdate == null) {
			return null;
		}

		return Date.from(localdate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}

	public static Date convertLocalTimeToDate(LocalTime localTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(0, 0, 0, localTime.getHour(), localTime.getMinute(), localTime.getSecond());
		return calendar.getTime();
	}
}