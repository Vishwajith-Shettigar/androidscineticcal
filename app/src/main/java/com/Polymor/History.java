package com.Polymor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

public class History extends AppCompatActivity {
ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ArrayList<History_data>history_data= new ArrayList<>();
        Intent i = getIntent();
        history_data = (ArrayList<History_data>) ((Intent) i).getSerializableExtra("history_data");


    listView = (ListView) findViewById(R.id.listview);

    try {

        Collections.reverse(history_data);
        AdapterHistory adpt = new AdapterHistory(this, R.layout.historylayout, history_data);
        listView.setAdapter(adpt);
        Log.e("cru","------------------");
    }
    catch (Exception e)
    {
        Log.e("cru","------------------"+ e);
    }


//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {

//                History_data obj=(History_data) adapterView.getItemAtPosition(i);
//                String result=obj.getResult();
//                String exp=obj.getProblem();
//                Intent send = new Intent(getApplicationContext(), MainActivity.class);
//                Log.e("hello",result);
//                send.putExtra("result",result);
//                send.putExtra("exp",exp);
//
//                startActivity(send);
//            }

//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


      listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
Log.e("cru","------------------");

              History_data obj=(History_data) adapterView.getItemAtPosition(i);
                String result=obj.getResult();
                String exp=obj.getProblem();
                Intent send = new Intent(getApplicationContext(), MainActivity.class);
                Log.e("hello",result);
                send.putExtra("result",result);
                send.putExtra("exp",exp);

                startActivity(send);
          }
      });
    }
}