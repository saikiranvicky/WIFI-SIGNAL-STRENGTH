package com.example.eapp3;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<String> {
    Activity mActivity;
    List<String> ports;
    private LayoutInflater inflater;

    public CustomAdapter(Activity a,List<String> ports){
        super(a,R.layout.wifi_list,ports);
        mActivity=a;
        inflater = LayoutInflater.from(mActivity);
        this.ports=ports;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        converView = inflater.inflate(R.layout.wifi_list,parent,false);
        TextView textView = (TextView)converView.findViewById(R.id.textView);
        textView.setText(ports.get(position));
        //if(Integer.parseInt(ports.get(position))>-80){
           // textView.setTextColor(Color.BLACK);
        //}

        return converView;
    }





}
