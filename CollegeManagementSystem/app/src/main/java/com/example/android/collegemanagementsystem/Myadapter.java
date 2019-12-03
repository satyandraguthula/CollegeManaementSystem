package com.example.android.collegemanagementsystem;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by satyandra on 6/3/15.
 */
public class Myadapter extends BaseAdapter {
    String [] name;
    Context ctxt;
    int layout;
    int textview;
    LayoutInflater myinflater;
    int ele[];
    public Myadapter(Context c,int res,int txtview,String[] naam)
    {
        ctxt=c;
        layout=res;
        textview=txtview;
        name=naam;
        ele=new int[naam.length];
        myinflater=(LayoutInflater)ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int i;
        if (convertView==null)
        {
            convertView=myinflater.inflate(layout,parent,false);
            TextView tv=(TextView)convertView.findViewById(textview);
            tv.setText(name[position]);
            return convertView;
        }
        else
        {
            TextView tv=(TextView)convertView.findViewById(textview);
            tv.setText(name[position]);
            tv.setBackgroundColor(0);
            if (ele[position]==1)
                tv.setBackgroundColor(Color.GREEN);
            return convertView;
        }

    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public int getCount() {
        return name.length;
    }
    public void addit(int pos)
    {
        ele[pos]=1;
    }
    public void remit(int pos)
    {
        ele[pos]=0;
    }
}
