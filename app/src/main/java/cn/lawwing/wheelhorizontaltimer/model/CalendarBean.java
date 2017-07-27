package cn.lawwing.wheelhorizontaltimer.model;

/**
 * author lawwing time 2017/7/27 15:10 describe
 **/
public class CalendarBean
{
    private int date;
    
    // 是否是当前月份
    private boolean isNowMonth;
    
    // 是否已选
    private boolean isCheck;
    
    public int getDate()
    {
        return date;
    }
    
    public void setDate(int date)
    {
        this.date = date;
    }
    
    public boolean isCheck()
    {
        return isCheck;
    }
    
    public void setCheck(boolean check)
    {
        isCheck = check;
    }
    
    public boolean isNowMonth()
    {
        return isNowMonth;
    }
    
    public void setNowMonth(boolean nowMonth)
    {
        isNowMonth = nowMonth;
    }
    
    public CalendarBean(int date, boolean isCheck, boolean isNowMonth)
    {
        this.date = date;
        this.isCheck = isCheck;
        this.isNowMonth = isNowMonth;
    }
}
