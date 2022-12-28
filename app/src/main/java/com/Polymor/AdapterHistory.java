package com.Polymor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterHistory extends ArrayAdapter<History_data> {
    private Context mcontext;
    private  int mresource;
    public AdapterHistory(@NonNull Context context, int resource, @NonNull ArrayList<History_data> objects) {
        super(context, resource, objects);
        mcontext=context;
        mresource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String result_data=getItem(position).getResult();
        String problem_data=getItem(position).getProblem();
        LayoutInflater inflater=  LayoutInflater.from(mcontext);

        convertView=inflater.inflate(mresource,parent,false);

        TextView result=(TextView) convertView.findViewById(R.id.result);
        TextView problem=(TextView) convertView.findViewById(R.id.problem);
        result.setText(result_data);
        problem.setText(problem_data);
        return  convertView;

    }
}
