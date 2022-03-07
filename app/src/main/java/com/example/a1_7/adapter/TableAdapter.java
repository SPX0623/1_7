package com.example.a1_7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.a1_7.R;
import com.example.a1_7.bean.HLD;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class TableAdapter extends BaseAdapter {
    private ArrayList<HLD> hlds;
    private LayoutInflater inflater;
    public TableAdapter(Context context, ArrayList<HLD> hlds)
    {
        this.hlds=hlds;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        int ret=0;
        if (ret==0)
        {
            ret=hlds.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return hlds.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HLD hld= (HLD) this.getItem(position);
        ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.hld_item,null);
            viewHolder.num=convertView.findViewById(R.id.lk);
            viewHolder.red=convertView.findViewById(R.id.hd);
            viewHolder.green=convertView.findViewById(R.id.ld);
            viewHolder.yellow=convertView.findViewById(R.id.huangd);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.num.setText(String.valueOf(hlds.get(position).getNum()));
        viewHolder.red.setText(String.valueOf(hlds.get(position).getRed()));
        viewHolder.green.setText(String.valueOf(hlds.get(position).getGreen()));
        viewHolder.yellow.setText(String.valueOf(hlds.get(position).getYellow()));
        return convertView;
    }
    public class ViewHolder
    {
        public TextView num;
        public TextView red;
        public TextView yellow;
        public TextView green;
    }
}
