package com.example.a1_7.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a1_7.R;
import com.example.a1_7.bean.ETC;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class czAdapter extends BaseAdapter {
    private ArrayList<ETC> etcs=new ArrayList<>();
    private LayoutInflater inflater;
    public czAdapter(Context context,ArrayList<ETC> etcs)
    {
        this.etcs=etcs;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        int ret=0;
        if (ret==0)
        {
            ret=etcs.size();
        }
        return ret;
    }

    @Override
    public Object getItem(int position) {
        return etcs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ETC etc= (ETC) this.getItem(position);
        ViewHolder viewHolder;
        if (convertView==null)
        {
            viewHolder=new ViewHolder();
            convertView=inflater.inflate(R.layout.cz_item,null);
            viewHolder.num=convertView.findViewById(R.id.ch);
            viewHolder.je=convertView.findViewById(R.id.je);
            viewHolder.tm=convertView.findViewById(R.id.tm);
            viewHolder.czr=convertView.findViewById(R.id.czr);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.num.setText(etc.getCarnum());
        viewHolder.je.setText(etc.getCzje());
        viewHolder.tm.setText(etc.getCztm());
        viewHolder.czr.setText("老八");
        return convertView;
    }
    public class ViewHolder
    {
        public TextView num;
        public TextView je;
        public TextView tm;
        public TextView czr;
    }
}
