package com.jiea.bull.common.utils;

import java.time.*;
import java.util.Date;
import java.util.Objects;

/**
 * Date LocalDate LocalDateTime 互转
 */
public class DateConvertUtils {

    public static LocalDateTime toLocalDateTime(Date date) {
        Objects.requireNonNull(date, "date 对象不能为 null");
        Instant instant = date.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return localDateTime;
    }

    public static LocalDate toLocalDate(Date date) {
        return toLocalDateTime(date).toLocalDate();
    }

    public static LocalTime toLocalTime(Date date) {
        return toLocalDateTime(date).toLocalTime();
    }

    public static Date toDate(LocalDateTime localDateTime) {
        Objects.requireNonNull(localDateTime, "localDateTime 对象不能为 null");
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date toDate(LocalDate localDate){
        Objects.requireNonNull(localDate, "localDate 对象不能为 null");
        return toDate(localDate.atStartOfDay());
    }

    public static void main(String[] args) {
        System.out.println(toLocalDateTime(null));
    }
}
