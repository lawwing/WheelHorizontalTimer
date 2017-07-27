package cn.lawwing.wheelhorizontaltimer;

import java.util.Calendar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.lawwing.wheelhorizontaltimer.utils.ChineseDate;
import cn.lawwing.wheelhorizontaltimer.utils.DrawUtil;
import cn.lawwing.wheelhorizontaltimer.utils.ToolUtils;
import cn.lawwing.wheelhorizontaltimer.widget.RoundCalendarView;
import cn.lawwing.wheelhorizontaltimer.widget.WheelHorTimerView;

public class MainActivity extends AppCompatActivity
{
    private WheelHorTimerView wheelHorTimerView;
    
    private TextView showText;
    
    private RoundCalendarView calendarView;
    
    private Button btn;
    
    /**
     * 中心点的刻度
     */
    private float mWeight = 0.0f;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        ToolUtils.setScreenW(metric.widthPixels);
        ToolUtils.setScreenH(metric.heightPixels);
        setContentView(R.layout.activity_main);
        
        wheelHorTimerView = (WheelHorTimerView) findViewById(R.id.ruler_weight);
        calendarView = (RoundCalendarView) findViewById(R.id.cv);
        btn = (Button) findViewById(R.id.btn);
        
        showText = (TextView) findViewById(R.id.showText);
        
        wheelHorTimerView.setParam(DrawUtil.dip2px(30),
                DrawUtil.dip2px(100),
                DrawUtil.dip2px(40),
                DrawUtil.dip2px(40),
                DrawUtil.dip2px(15),
                DrawUtil.dip2px(30),
                6,
                6.0f,
                WheelHorTimerView.NUM_TOP,
                false);
        wheelHorTimerView.initViewParam(mWeight, 0.0f, 24.0f, 1);
        wheelHorTimerView.setValueChangeListener(
                new WheelHorTimerView.OnValueChangeListener()
                {
                    @Override
                    public void onValueChange(float value)
                    {
                        String showtime = "";
                        showtime = getShowTime(value);
                        showText.setText(showtime);
                        mWeight = value;
                    }
                });
        
        btn.setOnClickListener(new View.OnClickListener()
        {
            
            @Override
            public void onClick(View arg0)
            {
                // TODO Auto-generated method stub
                Calendar cal = Calendar.getInstance();
                if (calendarView.getDate() == null)
                {
                    Toast.makeText(getApplicationContext(),
                            "请选择日期",
                            Toast.LENGTH_LONG).show();
                    return;
                }
                
                cal.set(calendarView.getDate().getYear(),
                        calendarView.getDate().getMonth() - 1,
                        calendarView.getDate().getDate());
                ChineseDate chineseDate = new ChineseDate(cal);
                // Toast.makeText(getApplicationContext(),
                // calendarView.getDate().toString(), Toast.LENGTH_LONG).show();
                
                Toast.makeText(getApplicationContext(),
                        calendarView.getDate().toString() + "\n农历:"
                                + chineseDate.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    
    private String getShowTime(float value)
    {
        String showtime = "";
        int totalmin = (int) (60 * value);
        
        int showmin = totalmin % 60;
        int showhour = totalmin / 60;
        
        showtime = String.format("%02d", showhour) + ":"
                + String.format("%02d", showmin);
        return showtime;
    }
}
