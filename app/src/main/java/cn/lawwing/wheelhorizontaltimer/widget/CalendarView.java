package cn.lawwing.wheelhorizontaltimer.widget;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.lawwing.wheelhorizontaltimer.R;
import cn.lawwing.wheelhorizontaltimer.utils.DateBean;
import cn.lawwing.wheelhorizontaltimer.utils.TimeUtils;
import cn.lawwing.wheelhorizontaltimer.utils.ToolUtils;

public class CalendarView extends LinearLayout
{
    private int YEAR = 2016;
    
    private int MONTH = 6;
    
    private int DATE = 11;
    
    private int SELECTYEAR = 2016;
    
    private int SELECTMONTH = 6;
    
    private int SELECTDATE = 11;
    
    private int w;
    
    private int h;
    
    private String[] weeks = { "日", "一", "二", "三", "四", "五", "六" };
    
    private ArrayList<Integer> dates = new ArrayList<Integer>();
    
    private TextView[] datesTextViews;
    
    public CalendarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        YEAR = TimeUtils.getNowYear();
        MONTH = TimeUtils.getNowMonth();
        DATE = TimeUtils.getNowDate();
        SELECTYEAR = TimeUtils.getNowYear();
        SELECTMONTH = TimeUtils.getNowMonth();
        SELECTDATE = TimeUtils.getNowDate();
        w = ToolUtils.getScreenW() / 7;
        h = ToolUtils.getScreenW() / 9;
        init(context, w, h);
    }
    
    /**
     * 初始化控件
     */
    private void init(final Context context, final int w, final int h)
    {
        final LinearLayout lll = this;
        this.setOrientation(LinearLayout.VERTICAL);
        for (int i = 0; i < 31; i++)
        {
            dates.add(i + 1);
        }
        datesTextViews = new TextView[TimeUtils.getMonthDatas(YEAR, MONTH)];
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams paramsRight = new RelativeLayout.LayoutParams(
                h, h);
        paramsRight.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        RelativeLayout.LayoutParams paramsCenter = new RelativeLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, h);
        paramsCenter.addRule(RelativeLayout.CENTER_IN_PARENT);
        RelativeLayout.LayoutParams paramsLeft = new RelativeLayout.LayoutParams(
                h, h);
        paramsLeft.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.weight = 1.0f;
        params.gravity = Gravity.CENTER;
        LinearLayout.LayoutParams paramsTitle = new LinearLayout.LayoutParams(w,
                h);
        Log.e("--", "w:" + w + "  h:" + h);
        // 選擇日子
        RelativeLayout selectLayout = new RelativeLayout(context);
        selectLayout.setBackgroundColor(Color.RED);
        TextView tv2 = new TextView(context);
        tv2.setText(YEAR + "年" + MONTH + "月");
        tv2.setTextColor(Color.WHITE);
        tv2.setLayoutParams(paramsCenter);
        tv2.setGravity(Gravity.CENTER);
        selectLayout.addView(tv2);
        
        ImageView tvleft = new ImageView(context);
        tvleft.setBackgroundResource(R.mipmap.left);
        tvleft.setLayoutParams(paramsLeft);
        tvleft.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                if (MONTH > 1)
                {
                    MONTH--;
                    lll.removeAllViews();
                    init(context, w, h);
                }
                else
                {
                    MONTH = 12;
                    YEAR--;
                    lll.removeAllViews();
                    init(context, w, h);
                }
            }
        });
        selectLayout.addView(tvleft);
        
        ImageView tvright = new ImageView(context);
        tvright.setBackgroundResource(R.mipmap.right);
        tvright.setLayoutParams(paramsRight);
        tvright.setOnClickListener(new OnClickListener()
        {
            
            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                if (MONTH < 12)
                {
                    MONTH++;
                    lll.removeAllViews();
                    init(context, w, h);
                }
                else
                {
                    MONTH = 1;
                    YEAR++;
                    lll.removeAllViews();
                    init(context, w, h);
                }
            }
        });
        selectLayout.addView(tvright);
        this.addView(selectLayout, params);
        // 标题行
        LinearLayout titleLayout = new LinearLayout(context);
        titleLayout.setOrientation(LinearLayout.HORIZONTAL);
        
        for (int i = 0; i < 7; i++)
        {
            TextView tv = new TextView(context);
            tv.setText(weeks[i]);
            tv.setGravity(Gravity.CENTER);
            tv.setBackgroundColor(Color.parseColor("#999999"));
            titleLayout.addView(tv, paramsTitle);
        }
        this.addView(titleLayout, params);
        int week = TimeUtils.getWeekNum(YEAR, MONTH, 1);
        // 第一行
        LinearLayout eachLayout1 = new LinearLayout(context);
        eachLayout1.setOrientation(LinearLayout.HORIZONTAL);
        for (int j = 0; j < week; j++)
        {
            TextView tv = new TextView(context);
            tv.setText("");
            tv.setGravity(Gravity.CENTER);
            eachLayout1.addView(tv, paramsTitle);
        }
        for (int k = 0; k < 7 - week; k++)
        {
            datesTextViews[k] = new TextView(context);
            datesTextViews[k].setText("" + dates.get(k));
            datesTextViews[k].setGravity(Gravity.CENTER);
            if (SELECTDATE == k && SELECTMONTH == MONTH && SELECTYEAR == YEAR)
            {
                datesTextViews[k].setBackgroundResource(R.drawable.select_date);
            }
            else
            {
                datesTextViews[k]
                        .setBackgroundResource(R.drawable.unselect_date);
            }
            final int kk = k;
            datesTextViews[k].setOnClickListener(new OnClickListener()
            {
                
                @Override
                public void onClick(View arg0)
                {
                    // TODO Auto-generated method stub
                    changeSelect(kk, YEAR, MONTH);
                }
            });
            eachLayout1.addView(datesTextViews[k], paramsTitle);
        }
        this.addView(eachLayout1, params);
        // 其他行
        int less = 7 - week + 1;
        int line;
        if (((TimeUtils.getMonthDatas(YEAR, MONTH) - (7 - week)) % 7) == 0)
        {
            
            line = (TimeUtils.getMonthDatas(YEAR, MONTH) - (7 - week)) / 7;
        }
        else
        {
            
            line = (TimeUtils.getMonthDatas(YEAR, MONTH) - (7 - week)) / 7 + 1;
        }
        for (int i = 0; i < line; i++)
        {
            
            LinearLayout eachLayout = new LinearLayout(context);
            eachLayout.setOrientation(LinearLayout.HORIZONTAL);
            for (int j = 0; j < 7; j++)
            {
                if (less <= TimeUtils.getMonthDatas(YEAR, MONTH))
                {
                    datesTextViews[less - 1] = new TextView(context);
                    datesTextViews[less - 1].setText("" + dates.get(less - 1));
                    if (SELECTDATE == (less) && SELECTMONTH == MONTH
                            && SELECTYEAR == YEAR)
                    {
                        datesTextViews[less - 1]
                                .setBackgroundResource(R.drawable.select_date);
                    }
                    else
                    {
                        datesTextViews[less - 1].setBackgroundResource(
                                R.drawable.unselect_date);
                    }
                    datesTextViews[less - 1].setGravity(Gravity.CENTER);
                    final int aa = less - 1;
                    datesTextViews[less - 1]
                            .setOnClickListener(new OnClickListener()
                            {
                                
                                @Override
                                public void onClick(View arg0)
                                {
                                    // TODO Auto-generated method stub
                                    changeSelect(aa, YEAR, MONTH);
                                }
                            });
                    eachLayout.addView(datesTextViews[less - 1], paramsTitle);
                    less++;
                }
                else
                {
                    TextView tv = new TextView(context);
                    tv.setText("");
                    tv.setGravity(Gravity.CENTER);
                    eachLayout.addView(tv, paramsTitle);
                }
            }
            
            this.addView(eachLayout, params);
        }
        
    }
    
    protected void changeSelect(int position, int year, int month)
    {
        // TODO Auto-generated method stub
        for (int i = 0; i < TimeUtils.getMonthDatas(year, month); i++)
        {
            if (position == i)
            {
                datesTextViews[i].setBackgroundResource(R.drawable.select_date);
            }
            else
            {
                datesTextViews[i]
                        .setBackgroundResource(R.drawable.unselect_date);
            }
        }
        SELECTYEAR = YEAR;
        SELECTMONTH = MONTH;
        SELECTDATE = position + 1;
        Log.e("--", "选择的日期是：" + (position + 1));
    }
    
    public DateBean getDate()
    {
        DateBean db = new DateBean(SELECTYEAR, SELECTMONTH, SELECTDATE);
        return db;
    }
    
}
