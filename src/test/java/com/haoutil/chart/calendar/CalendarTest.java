package com.haoutil.chart.calendar;




import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


/**
 * * @author lihaocheng
 * * @createtime 2021/8/23
 */
public class CalendarTest {
    /**
     * 常规 getCalendar 测试
     * @throws ParseException
     */
    @Test
    public void getCalendarTest0() throws ParseException {
        LocalDate a=LocalDate.of(2021,6,1),
                b=LocalDate.of(2021,10,1);
        Map<LocalDate,? extends Number> map=Calendar.getCalendar(a,b,Integer.parseInt("1"));

        assertNotNull(map,"map is NULL");

        for(Map.Entry entry:map.entrySet()){
            assertEquals(a,entry.getKey(),"日期错误");
            assertEquals(1,entry.getValue(),"默认值错误");
            a=a.plusDays(1);
        }

    }

    /**
     * getCalendar 日期边界测试
     * @throws ParseException
     */
    @Test
    public void getCalendarTest1() throws ParseException {
        LocalDate a=LocalDate.of(2021,11,1),
                b=LocalDate.of(2021,10,1),
                c=null,
                d=LocalDate.of(2021,10,1);
        //测试截止日期小于起止日期的情况
        Throwable throwable1=assertThrows(IllegalArgumentException.class, ()->Calendar.getCalendar(a,b,0),"起始日期大于结束日期异常未被发现");
        assertEquals("getCalendar 输入日期不合法，截止日期小于起始日期",throwable1.getMessage(),"异常返回不一致");
        //Local为null的情况
        Throwable throwable2=assertThrows(IllegalArgumentException.class,()->Calendar.getCalendar(a,c,0),"输入日期不合法异常未被捕获");
        assertEquals("getCalendar 输入日期不合法，有空值",throwable2.getMessage());
        //输入两个相同日期的情况
        Map<LocalDate,Integer> map=Calendar.getCalendar(b,d,0);
        map.forEach((key,value)->{
            assertTrue(key.isEqual(d));
            assertEquals(value,0);
        });
    }

    /**
     * getCalendar 不变式测试
     * @throws ParseException
     */
    @Test
    public void getCalendarTest2() throws ParseException {
        Double num1=Double.valueOf(10.0);
        Integer num2=Integer.valueOf(123);
        Short num3=Short.valueOf("1");

        final LocalDate a=LocalDate.of(2021,9,1),
                b=LocalDate.of(2021,10,1);
        //测试不同的输入值
        Map<LocalDate,Double> map=Calendar.getCalendar(a,b,num1);
        Map<LocalDate,Integer> map2=Calendar.getCalendar(a,b,num2);

        //不变式测试
        map.forEach((key,value)->
        {
            assertTrue(key.equals(a)||key.isAfter(a),"生成日期比起始日期小");
            assertTrue(key.equals(b)||key.isBefore(b),"生成日期比截止日期大");
            assertTrue(value.equals(num1),"默认值初始化错误");
        });
        map2.forEach((key,value)->
        {
            assertTrue(key.equals(a)||key.isAfter(a),"生成日期比起始日期小");
            assertTrue(key.equals(b)||key.isBefore(b),"生成日期比截止日期大");
            assertTrue(value.equals(num2),"默认值初始化错误");
        });
    }

    /**
     * 常规 getCalendar9 测试
     * @throws ParseException
     */
    @Test
    public void getCalendar9Test0() throws ParseException {
        LocalDate a=LocalDate.of(2021,6,1),
                b=LocalDate.of(2021,10,1);
        Map<LocalDate,? extends Number> map=Calendar.getCalendar9(a,b,Integer.parseInt("1"));

        assertNotNull(map,"map is NULL");

        for(Map.Entry entry:map.entrySet()){
            assertEquals(a,entry.getKey(),"日期错误");
            assertEquals(1,entry.getValue(),"默认值错误");
            a=a.plusDays(1);
        }

    }

    /**
     * getCalendar9 日期边界测试
     * @throws ParseException
     */
    @Test
    public void getCalendar9Test1() throws ParseException {
        LocalDate a=LocalDate.of(2021,11,1),
                b=LocalDate.of(2021,10,1),
                c=null,
                d=LocalDate.of(2021,10,1);
        //测试截止日期小于起止日期的情况
        Throwable throwable1=assertThrows(IllegalArgumentException.class, ()->Calendar.getCalendar9(a,b,0),"起始日期大于结束日期异常未被发现");
        assertEquals("getCalendar 输入日期不合法，截止日期小于起始日期",throwable1.getMessage(),"异常返回不一致");
        //Local为null的情况
        Throwable throwable2=assertThrows(IllegalArgumentException.class,()->Calendar.getCalendar9(a,c,0),"输入日期不合法异常未被捕获");
        assertEquals("getCalendar 输入日期不合法，有空值",throwable2.getMessage());
        //输入两个相同日期的情况
        Map<LocalDate,Integer> map=Calendar.getCalendar9(b,d,0);
        map.forEach((key,value)->{
            assertTrue(key.isEqual(d));
            assertEquals(value,0);
        });
    }

    /**
     * getCalendar9 不变式测试
     * @throws ParseException
     */
    @Test
    public void getCalendar9Test2() throws ParseException {
        Double num1=Double.valueOf(10.0);
        Integer num2=Integer.valueOf(123);
        Short num3=Short.valueOf("1");

        final LocalDate a=LocalDate.of(2021,9,1),
                b=LocalDate.of(2021,10,1);
        //测试不同的输入值
        Map<LocalDate,Double> map=Calendar.getCalendar9(a,b,num1);
        Map<LocalDate,Integer> map2=Calendar.getCalendar9(a,b,num2);

        //不变式测试
        map.forEach((key,value)->
        {
            assertTrue(key.equals(a)||key.isAfter(a),"生成日期比起始日期小");
            assertTrue(key.equals(b)||key.isBefore(b),"生成日期比截止日期大");
            assertTrue(value.equals(num1),"默认值初始化错误");
        });
        map2.forEach((key,value)->
        {
            assertTrue(key.equals(a)||key.isAfter(a),"生成日期比起始日期小");
            assertTrue(key.equals(b)||key.isBefore(b),"生成日期比截止日期大");
            assertTrue(value.equals(num2),"默认值初始化错误");
        });
    }

}
