package com.Polymor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipDescription;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Settingoption extends AppCompatActivity {
    Spinner spinner, spinnerround;
    ArrayList<Colorvalues> colorArray;
    ArrayList<Integer> roundvalues;
    Settingvalues sv;
TextView submitfeed;
EditText feedback;
ImageView hisdel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settingoption);
        Intent i = getIntent();
        sv = (Settingvalues) ((Intent) i).getSerializableExtra("sv");
        Log.e("setting", "^^^^^^^^^^^^^^^" + sv.getColor());
        colorArray = new ArrayList<>();
        roundvalues = new ArrayList<>();

 hisdel=(ImageView)findViewById(R.id.hisdel);


        colorArray.add(new Colorvalues(Color.RED));
        colorArray.add(new Colorvalues(Color.GREEN));
        colorArray.add(new Colorvalues(Color.YELLOW));
        colorArray.add(new Colorvalues(Color.CYAN));
        colorArray.add(new Colorvalues(Color.BLUE));
        colorArray.add(new Colorvalues(Color.MAGENTA));
        roundvalues.add(1);
        roundvalues.add(2);
        roundvalues.add(3);
        roundvalues.add(4);
        roundvalues.add(5);



        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerround = (Spinner) findViewById(R.id.spinnerround);
        feedback=(EditText) findViewById(R.id.feedback);
        submitfeed= (TextView) findViewById(R.id.submitfeed);
        Adaptercolor adpt = new Adaptercolor(this, R.layout.colorsforsetting, colorArray);
        spinner.setAdapter(adpt);
        Log.e("round", "pppppppp||ppppppppppppp" + sv.getRound() + " -- " + sv.getRound());
        ArrayAdapter roundadptr = new ArrayAdapter(this, android.R.layout.simple_spinner_item, roundvalues);
        spinnerround.setAdapter(roundadptr);
        spinner.setSelection(sv.getColorindex());
        spinnerround.setSelection(sv.getRound() - 1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Colorvalues checked = (Colorvalues) adapterView.getItemAtPosition(i);
                sv.setColor(checked.getClr());

                if (checked.getClr() == Color.RED)
                    sv.setColorindex(0);
                else if (checked.getClr() == Color.GREEN)
                    sv.setColorindex(1);
                else if (checked.getClr() == Color.YELLOW)
                    sv.setColorindex(2);
                else if (checked.getClr() == Color.CYAN)
                    sv.setColorindex(3);
                else if (checked.getClr() == Color.BLUE)
                    sv.setColorindex(4);
                else if (checked.getClr() == Color.MAGENTA)
                    sv.setColorindex(5);

                Log.e("round", "ppppppppppppppppppppp" + sv.getColor() + " -- " + sv.getColorindex());
                spinner.setSelection(sv.getColorindex());
                saveSettingvalues();
                loadSetting();

//        colorArray.remove(checked);
//colorArray.add(0,new Colorvalues( checked.getClr()));
//
//        Adaptercolor adpt = new Adaptercolor(getApplicationContext(),R.layout.colorsforsetting, colorArray);
//
//        spinner.setAdapter(adpt);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        spinnerround.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int v = (int) adapterView.getItemAtPosition(i);
                Log.e("round", v + "--------");
                sv.setRound(v);
                spinnerround.setSelection(sv.getRound() - 1);
                saveSettingvalues();
                loadSetting();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        submitfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType(ClipDescription.MIMETYPE_TEXT_PLAIN);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zekromvishwa56789@gmail.com"});
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Feedback calculator");
                intent.putExtra(android.content.Intent.EXTRA_TEXT, feedback.getText());
                startActivity(Intent.createChooser(intent,"Send Email"));
                feedback.setText("");
            }
        });

        hisdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    SharedPreferences sharedPreferences=getSharedPreferences("history",MODE_PRIVATE);

                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    Gson gson=new Gson();
                    String json=gson.toJson(new ArrayList<>());
                    editor.putString("historydata",json);
                    editor.apply();
                Toast.makeText(getApplicationContext(),("history cleared"),Toast.LENGTH_SHORT).show();





            }
        });

    }

    private void saveSettingvalues() {
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        Log.e("round", "pppppppp|save|ppppppppppppp" + sv.getRound() + " -- " + sv.getRound());
        String json = gson.toJson(sv);
        editor.putString("settngvalues", json);
        editor.apply();


    }

    private void loadSetting() {
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);


        Gson gson = new Gson();
        String json = sharedPreferences.getString("settngvalues", null);
        Type type = new TypeToken<Settingvalues>() {
        }.getType();
        sv = gson.fromJson(json, type);
        Log.e("round", "-----------------------------" + (sv.getRound()));

        if (sv == null) {
            sv = new Settingvalues();
        }



    }


    @Override
    public void onBackPressed() {

        Intent send = new Intent(getApplicationContext(), MainActivity.class);


        startActivity(send);
    }
}