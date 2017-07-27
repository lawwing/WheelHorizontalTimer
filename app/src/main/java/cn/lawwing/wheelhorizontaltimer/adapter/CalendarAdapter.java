package cn.lawwing.wheelhorizontaltimer.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.lawwing.wheelhorizontaltimer.R;
import cn.lawwing.wheelhorizontaltimer.model.CalendarBean;

/**
 * author lawwing time 2017/7/27 14:24 describe
 **/
public class CalendarAdapter extends BaseAdapter
{
    private Context context;
    
    private ArrayList<CalendarBean> datas;
    
    private LayoutInflater inflater;
    
    public CalendarAdapter(ArrayList<CalendarBean> datas, Context context)
    {
        this.datas = datas;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    
    @Override
    public int getCount()
    {
        return datas.size();
    }
    
    @Override
    public Object getItem(int i)
    {
        return i;
    }
    
    @Override
    public long getItemId(int i)
    {
        return i;
    }
    
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup)
    {
        CalendarHolder holder = new CalendarHolder();
        if (view == null)
        {
            view = inflater.inflate(R.layout.calendar_item, null);
            holder = new CalendarHolder();
            holder.date = (TextView) view.findViewById(R.id.date);
            holder.bossLayout = (LinearLayout) view
                    .findViewById(R.id.bossLayout);
            view.setTag(holder);
        }
        else
        {
            holder = (CalendarHolder) view.getTag();
        }
        
        holder.date.setText(datas.get(i).getDate() + "");
        
        if (datas.get(i).isCheck())
        {
            holder.date.setBackgroundResource(R.drawable.select_date);
            holder.date.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.date.setBackgroundResource(R.drawable.unselect_date);
            if (datas.get(i).isNowMonth())
            {
                holder.date.setTextColor(Color.parseColor("#6d89ac"));
            }
            else
            {
                holder.date.setTextColor(Color.parseColor("#cccccc"));
                
            }
        }
        
        final CalendarHolder finalHolder = holder;
        holder.bossLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (datas.get(i).isNowMonth())
                {
                    for (int j = 0; j < datas.size(); j++)
                    {
                        if (i != j)
                        {
                            datas.get(j).setCheck(false);
                        }
                        else
                        {
                            datas.get(j).setCheck(true);
                        }
                    }
                    notifyDataSetChanged();
                }
            }
        });
        return view;
    }
    
    private class CalendarHolder
    {
        TextView date;
        
        LinearLayout bossLayout;
        
    }
}
