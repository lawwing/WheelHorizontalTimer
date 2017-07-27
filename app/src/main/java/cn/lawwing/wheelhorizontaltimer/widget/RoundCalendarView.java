package cn.lawwing.wheelhorizontaltimer.widget;

import java.util.ArrayList;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.lawwing.wheelhorizontaltimer.R;
import cn.lawwing.wheelhorizontaltimer.adapter.CalendarAdapter;
import cn.lawwing.wheelhorizontaltimer.model.CalendarBean;
import cn.lawwing.wheelhorizontaltimer.utils.DateBean;
import cn.lawwing.wheelhorizontaltimer.utils.TimeUtils;

/**
 * author lawwing time 2017/7/27 13:56 describe
 **/
public class RoundCalendarView extends LinearLayout
{
    private int YEAR = 2017;
    
    private int MONTH = 7;
    
    private int DATE = 11;
    
    private int w;
    
    private int h;
    
    private String[] weeks = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };
    
    private ArrayList<CalendarBean> dates = new ArrayList<CalendarBean>();
    
    private TextView[] datesTextViews;
    
    private LayoutInflater inflater;
    
    private TextView nowMonth;
    
    private CalendarAdapter adapter;
    
    public RoundCalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        YEAR = TimeUtils.getNowYear();
        MONTH = TimeUtils.getNowMonth();
        DATE = TimeUtils.getNowDate();
        inflater = LayoutInflater.from(context);
        
        init(context);
    }
    
    private void init(final Context context)
    {
        View view = inflater.inflate(R.layout.calendar_layout, null);
        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        nowMonth = (TextView) view.findViewById(R.id.nowMonth);
        ImageView lastMonth = (ImageView) view.findViewById(R.id.lastMonth);
        ImageView nextMonth = (ImageView) view.findViewById(R.id.nextMonth);
        
        lastMonth.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (MONTH > 1)
                {
                    MONTH--;
                }
                else
                {
                    MONTH = 12;
                    YEAR--;
                }
                nowMonth.setText(YEAR + "年" + MONTH + "月");
                initData();
                adapter.notifyDataSetChanged();
            }
        });
        
        nextMonth.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (MONTH < 12)
                {
                    MONTH++;
                }
                else
                {
                    MONTH = 1;
                    YEAR++;
                }
                nowMonth.setText(YEAR + "年" + MONTH + "月");
                initData();
                adapter.notifyDataSetChanged();
            }
        });
        nowMonth.setText(YEAR + "年" + MONTH + "月");
        
        initData();
        adapter = new CalendarAdapter(dates, context);
        gridView.setAdapter(adapter);
        this.addView(view);
    }
    
    /**
     * 整理数据
     */
    private void initData()
    {
        dates.clear();
        
        int week = TimeUtils.getWeekNum(YEAR, MONTH, 1);
        
        int dateNum = TimeUtils.getMonthDatas(YEAR, MONTH);
        
        int lastdateWeek = TimeUtils.getWeekNum(YEAR, MONTH, dateNum);
        
        int lastmonthNum = TimeUtils.getLastMonthDatas(YEAR, MONTH);
        /**
         * 上个月的数据
         */
        if (week != 0)
        {
            for (int i = lastmonthNum - week; i < lastmonthNum; i++)
            {
                CalendarBean bean = new CalendarBean(i + 1, false, false);
                dates.add(bean);
            }
        }
        /**
         * 本月的数据
         */
        for (int i = 0; i < dateNum; i++)
        {
            if (i == DATE)
            {
                CalendarBean bean = new CalendarBean(i + 1, true, true);
                dates.add(bean);
            }
            else
            {
                CalendarBean bean = new CalendarBean(i + 1, false, true);
                dates.add(bean);
            }
        }
        /**
         * 下个月的数据
         */
        for (int i = 0; i < 7 - lastdateWeek - 1; i++)
        {
            CalendarBean bean = new CalendarBean(i + 1, false, false);
            dates.add(bean);
        }
    }
    
    public DateBean getDate()
    {
        for (CalendarBean data : dates)
        {
            if (data.isCheck())
            {
                DateBean db = new DateBean(YEAR, MONTH, data.getDate());
                return db;
            }
        }
        return null;
    }
}
