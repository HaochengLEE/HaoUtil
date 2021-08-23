package com.haoutil.chart.calendar;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

/**
 * * @author lihaocheng
 * * @createtime 2021/8/23
 */
public class Calendar {
    /**
     * 按给定范围生成日期
     * @param startTime
     * @param endTime
     * @param t  value 默认值:可接受 Number 及其子类
     * @param <T>
     * @return
     * @throws ParseException
     */
    public static <T extends Number> Map<LocalDate,T> getCalendar(LocalDate startTime, LocalDate endTime, T t) throws ParseException {
        //检查起止日期
        if (startTime==null||endTime==null){
            throw new IllegalArgumentException("getCalendar 输入日期不合法，有空值");
        }
        if (startTime.isAfter(endTime)){
            throw new IllegalArgumentException("getCalendar 输入日期不合法，截止日期小于起始日期");
        }

        Map<LocalDate,T> dateMap = new TreeMap<>();
        //计算日期间隔
        long distance= ChronoUnit.DAYS.between(startTime,endTime);

        //迭代日期，间隔日期限制迭代次数
        Stream.iterate(startTime, d->d.plusDays(1))
                .limit(distance+1)
                .forEach(
                        f->dateMap.put(f,t)
                );

        return dateMap;
    }

    /**
     * 按给定范围生成日期，按 Java 9 的新特性做了修改
     * @param startTime
     * @param endTime
     * @param t  value 默认值:可接受 Number 及其子类
     * @param <T>
     * @return
     * @throws ParseException
     */
    public static <T extends Number> Map<LocalDate,T> getCalendar9(LocalDate startTime, LocalDate endTime,T t) throws ParseException {
        //检查起止日期
        if (startTime==null||endTime==null){
            throw new IllegalArgumentException("getCalendar 输入日期不合法，有空值");
        }
        if (startTime.isAfter(endTime)){
            throw new IllegalArgumentException("getCalendar 输入日期不合法，截止日期小于起始日期");
        }

        Map<LocalDate,T> dateMap = new TreeMap<>();

        //迭代日期，间隔日期限制迭代次数
        Stream.iterate(startTime, d->d.plusDays(1))
                .takeWhile(d->d.isAfter(endTime))
                .forEach(
                        f->dateMap.put(f,t)
                );

        return dateMap;
    }
}
