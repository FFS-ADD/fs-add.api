package com.accenture.fsadd.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatterBuilder;

public class FsaddUtil {
    public static String convertLocaldateTimeToString(LocalDateTime ldt,String pattern) {

        if (ldt == null) {
            return null;
        }
        return LocalDateTime.now().format(new DateTimeFormatterBuilder().appendPattern(pattern).toFormatter());
    }
}
