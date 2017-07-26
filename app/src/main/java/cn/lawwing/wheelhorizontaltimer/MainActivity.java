package cn.lawwing.wheelhorizontaltimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import cn.lawwing.wheelhorizontaltimer.utils.DrawUtil;
import cn.lawwing.wheelhorizontaltimer.widget.WheelHorTimerView;

public class MainActivity extends AppCompatActivity {
    private WheelHorTimerView wheelHorTimerView;
    private TextView showText;
    /**
     * 中心点的刻度
     */
    private float mWeight = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wheelHorTimerView = (WheelHorTimerView) findViewById(R.id.ruler_weight);

        showText = (TextView) findViewById(R.id.showText);

        wheelHorTimerView.setParam(DrawUtil.dip2px(30), DrawUtil.dip2px(60), DrawUtil.dip2px(40),
                DrawUtil.dip2px(30), DrawUtil.dip2px(15), DrawUtil.dip2px(30));
        wheelHorTimerView.initViewParam(mWeight, 0.0f, 24.0f, 1);
        wheelHorTimerView.setValueChangeListener(new WheelHorTimerView.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {

                showText.setText("时间为：" + (int) value + "点" + (int) (60 * (value - (int) value)) + "分");
                mWeight = value;
            }
        });
    }
}
