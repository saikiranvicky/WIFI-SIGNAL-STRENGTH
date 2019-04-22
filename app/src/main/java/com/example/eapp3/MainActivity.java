package com.example.eapp3;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WifiManager wifiManager;
    private Button button;
    private ListView listView;
    //private TextView textView;
    private CustomAdapter adapter;
    private ArrayList<String> arrayList = new ArrayList<>();
    private int size =5;
    private List<ScanResult> results;
    private ArrayList<String> arrList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //textView = (TextView)findViewById(R.id.textView);
        listView = (ListView)findViewById(R.id.listWifi);
        listView.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if(!wifiManager.isWifiEnabled()){
            Toast.makeText(this,"Wifi is DISABLED", Toast.LENGTH_LONG).show();
            wifiManager.setWifiEnabled(true);
        }

        adapter = new CustomAdapter(this, arrayList);
        listView.setAdapter(adapter);
        scanWifi();

    }

    private void scanWifi(){
        arrayList.clear();
        registerReceiver(wifiReceiver, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        wifiManager.startScan();
        Toast.makeText(this, "Scanning WIFI", Toast.LENGTH_LONG).show();
    }


    BroadcastReceiver wifiReceiver =new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            results = wifiManager.getScanResults();

            unregisterReceiver(this);
            //System.out.println(results);

           Collections.sort(results, new Comparator<ScanResult>() {
               @Override
               public int compare(ScanResult o1, ScanResult o2) {
                   return (o1.level > o2.level ? -1
                           : (o1.level == o2.level ? 0 : 1));
               }
           });

           for(int i = 0; i < results.size();i++){


               arrayList.add("Signal Name: "+(results.get(i).SSID)+ " \n "+
                       "Signal Strength: " +String.valueOf(results.get(i).level));
               adapter.notifyDataSetChanged();
           }
          // System.out.println(arrayList);

        }
    };


}
