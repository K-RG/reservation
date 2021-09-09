package egovframework.com.reservation.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeUtil {
	
	/**
	 * 원하는 날짜 패턴으로 변경
	 * @param regDate 변경할 날짜
	 * @param pattern 변경할 패턴
	 * @return
	 */
	public static String getDateTimeByPattern(Date regDate, String pattern) {
		if (regDate == null) return null;
		
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.KOREA);
		return formatter.format(regDate);
	}
	
	public static Date strToDateTime(String strDate, String pattern) throws ParseException {
		if (strDate == null || "".equals(strDate)) return null;

		SimpleDateFormat formatter = new SimpleDateFormat(pattern);

		return formatter.parse(strDate);
	}
}
