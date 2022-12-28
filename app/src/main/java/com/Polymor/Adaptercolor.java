package com.Polymor;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptercolor extends ArrayAdapter<Colorvalues> {
    private Context mcontext;
    private  int mresource;
    public Adaptercolor(@NonNull Context context, int resource, @NonNull ArrayList<Colorvalues> objects) {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        return  initView(position,convertView,parent);

    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position,convertView,parent);
    }

    private View initView(int position,  View convertView,  ViewGroup parent)
    {
        if(convertView==null) {
            LayoutInflater inflater = LayoutInflater.from(mcontext);
            convertView = inflater.inflate(R.layout.colorsforsetting, parent, false);
        }
        TextView c=(TextView) convertView.findViewById(R.id.citem);
        if(c!=null) {
            int result_data = getItem(position).getClr();
            c.setBackgroundColor((result_data));

        }
return  convertView;
    }
}
