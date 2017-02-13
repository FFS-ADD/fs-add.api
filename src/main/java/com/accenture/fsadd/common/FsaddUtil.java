package com.accenture.fsadd.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

import org.springframework.util.StringUtils;

public class FsaddUtil {
    public static String convertLocaldateTimeToString(LocalDateTime ldt,String pattern) {

        if (ldt == null) {
            return null;
        }
        return LocalDateTime.now().format(new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter());
    }
    
	public static String nullOrEmptyToZero(String value) {
		if (StringUtils.isEmpty(value)) {
			return "0";
		}
		return value;
	}
}
