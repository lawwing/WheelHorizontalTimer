package cn.lawwing.wheelhorizontaltimer.utils;

import java.util.Calendar;

import android.util.Log;

public class TimeUtils
{
    public static String getWeekName(int year, int month, int data)
    {
        Calendar time = Calendar.getInstance();
        // 下面代码设置开始日期，注：不要设置为周末
        // 假设设置年(2011)月(8)日(18)，注：如果是8月，设置时候要减1，如下：
        time.set(year, month - 1, data);
        int day = time.get(Calendar.DAY_OF_WEEK) - 1;
        // 一周第一天是在java里是星期天，所以要减1
        if (day == 0)
        {
            return "星期天";
        }
        else if (day == 1)
        {
            return "星期一";
        }
        else if (day == 2)
        {
            return "星期二";
        }
        else if (day == 3)
        {
            return "星期三";
        }
        else if (day == 4)
        {
            return "星期四";
        }
        else if (day == 5)
        {
            return "星期五";
        }
        else if (day == 6)
        {
            return "星期六";
        }
        else
        {
            return "星期七";
            
        }
    }
    
    /**
     * 
     * @param year
     * @param month
     * @param data
     * @return 0是周日，其他对应相应的
     * 
     */
    public static int getWeekNum(int year, int month, int date)
    {
        Calendar time = Calendar.getInstance();
        // 下面代码设置开始日期，注：不要设置为周末
        // 假设设置年(2011)月(8)日(18)，注：如果是8月，设置时候要减1，如下：
        time.set(year, month - 1, date);
        int day = time.get(Calendar.DAY_OF_WEEK) - 1;
        // 一周第一天是在java里是星期天，所以要减1
        return day;
    }
    
    public static int getMonthDatas(int year, int month)
    {
        // TODO Auto-generated method stub
        // 获取当前时间
        Calendar cal = Calendar.getInstance();
        // 下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
        cal.set(year, month - 1, 1);
        Log.e("--",
                year + "年" + month + "月"
                        + cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    
    public static int getNowYear()
    {
        // TODO Auto-generated method stub
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        return year;
    }
    
    public static int getNowMonth()
    {
        // TODO Auto-generated method stub
        Calendar now = Calendar.getInstance();
        int month = now.get(Calendar.MONTH) + 1;
        return month;
    }
    
    public static int getNowDate()
    {
        // TODO Auto-generated method stub
        Calendar now = Calendar.getInstance();
        int date = now.get(Calendar.DATE);
        return date;
    }
}
