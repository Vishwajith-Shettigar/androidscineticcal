package com.Polymor;

import static java.lang.Math.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.mozilla.javascript.Context;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button zero, dot, equal, one, two, three, plus, mod, four, five, six, minus, seven, eight, nine, mult, ac, div;
    Button szero, sdot, sequal, sone, stwo, sthree, smod, splus, sfour, sfive, ssix, sminus, sseven, seight, snine, smult, sac, sdiv;
    Button sin, cos, tan, cosec, sec, cot, leftp, rightp, sln;
    HorizontalScrollView scrollview;
    ImageView settingicon;
    boolean isdeg;
    LinearLayout firstl, secondl;
    ImageView scientificnavi;
Button deg;
   boolean scrolled = false;
    boolean   scrolledb  = false;
    Button square, root;
    ImageView back, history;
    ImageView sback;
    TextView textViewtop;
    TextView textViewbottom;
    String text;
    String hres, hpro;
    //    ArrayList<String> textbottom= new ArrayList<>();
//    ArrayList<String> texttop= new ArrayList<>();
    ArrayList<History_data> history_data = new ArrayList<>();

    Settingvalues sv;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }

    public static boolean isScreenOrientationPortrait(MainActivity context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    public static float pxToDp(MainActivity context, final float px) {
        return px / context.getResources().getDisplayMetrics().density;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isdeg=true;
        boolean p = isScreenOrientationPortrait(this);
        textViewtop = (TextView) findViewById(R.id.textviewtop);
        textViewbottom = (TextView) findViewById(R.id.textviewbottom);
        Log.e("p", p + "");
        if (p == true) {
            firstl = findViewById(R.id.firstl);
            secondl = findViewById(R.id.secondl);

        }


        int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
        int height = getApplicationContext().getResources().getDisplayMetrics().heightPixels;
        Log.e("p", width + " " + height + "");
        float lw = pxToDp(this, width);
        Log.e("p", width + " " + height + "  --" + lw);
        //-----------
        if (p == true) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) width,
                    LinearLayout.LayoutParams.WRAP_CONTENT); // or set height to any fixed value you want

            firstl.setLayoutParams(lp);
            secondl.setLayoutParams(lp);

        }
        //-------
        scientificnavi = (ImageView) findViewById(R.id.scientificnavi);

        settingicon = (ImageView) findViewById(R.id.settingicon);
        scrollview = (HorizontalScrollView) findViewById(R.id.scrollview);
        scientificnavi = (ImageView) findViewById(R.id.scientificnavi);
        Intent hi = getIntent();
        hres = (String) ((Intent) hi).getStringExtra("result");
        hpro = (String) ((Intent) hi).getStringExtra("exp");

        Log.e("setting", "hehhehhehheehhe");
        loadSetting();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            scrollview.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    Log.e("%%", "scrolled");
                    int bottom = (scrollview.getChildAt(scrollview.getChildCount() - 1)).getWidth() - scrollview.getWidth() - i;

                    if (i3 == 0) {
                        //top detected
                        scrolledb=false;
                        scientificnavi.setImageResource(R.drawable.idontknowh_foreground);
//                        if(!scrolled)
//                        {
//                            scrolled=true;
//                            scientificnavi.setImageResource(R.drawable.scienceforscrolll);
//
//                        }
//                        else
//                        {
//                            scrolled=false;
//                            scientificnavi.setImageResource(R.drawable.scienceneww);
//                        }

                    }
                    if (bottom == 0) {
                        //bottom detected
//                        if(!scrolled)
//                        {
//                            scrolled=true;
//                            scientificnavi.setImageResource(R.drawable.scienceforscrolll);
//
//                        }
//                        else
//                        {
//                            scrolled=false;
//
//                        }
                        scrolledb=true;
                        scientificnavi.setImageResource(R.drawable.idontknowd_foreground);


                    }

                }
            });
        }
        //normal button
        getHistory();
        deg=(Button)findViewById(R.id.deg);
        zero = (Button) findViewById(R.id.btn_zero);
        dot = (Button) findViewById(R.id.btn_dot);
        equal = (Button) findViewById(R.id.btn_equal);
        one = (Button) findViewById(R.id.btn_one);
        two = (Button) findViewById(R.id.btn_two);
        three = (Button) findViewById(R.id.btn_three);
        plus = (Button) findViewById(R.id.btn_plus);
        four = (Button) findViewById(R.id.btn_four);
        five = (Button) findViewById(R.id.btn_five);
        six = (Button) findViewById(R.id.btn_six);
        minus = (Button) findViewById(R.id.btn_minus);
        seven = (Button) findViewById(R.id.btn_seven);
        eight = (Button) findViewById(R.id.btn_eight);
        nine = (Button) findViewById(R.id.btn_nine);
        mult = (Button) findViewById(R.id.btn_mult);
        ac = (Button) findViewById(R.id.btn_ac);
        div = (Button) findViewById(R.id.btn_div);
        back = (ImageView) findViewById(R.id.btn_back);
        mod = (Button) findViewById(R.id.btn_mod);


        history = (ImageView) findViewById(R.id.history);


        if (hi != null) {

            textViewtop.setText(hpro);
            textViewbottom.setText(hres);

        }
        //scientific
        square = (Button) findViewById(R.id.sbtn_square);
        root = (Button) findViewById(R.id.sbtn_root);

        sin = (Button) findViewById(R.id.sbtn_sin);
        cos = (Button) findViewById(R.id.sbtn_cos);
        tan = (Button) findViewById(R.id.sbtn_tan);
        cosec = (Button) findViewById(R.id.sbtn_cosec);
        sec = (Button) findViewById(R.id.sbtn_sec);
        cot = (Button) findViewById(R.id.sbtn_cot);
        sln = (Button) findViewById(R.id.sbtn_ln);


        szero = (Button) findViewById(R.id.sbtn_zero);
        sdot = (Button) findViewById(R.id.sbtn_dot);
        sequal = (Button) findViewById(R.id.sbtn_equal);
        sone = (Button) findViewById(R.id.sbtn_one);
        stwo = (Button) findViewById(R.id.sbtn_two);
        sthree = (Button) findViewById(R.id.sbtn_three);
        splus = (Button) findViewById(R.id.sbtn_plus);
        sfour = (Button) findViewById(R.id.sbtn_four);
        sfive = (Button) findViewById(R.id.sbtn_five);
        ssix = (Button) findViewById(R.id.sbtn_six);
        sminus = (Button) findViewById(R.id.sbtn_minus);
        sseven = (Button) findViewById(R.id.sbtn_seven);
        seight = (Button) findViewById(R.id.sbtn_eight);
        snine = (Button) findViewById(R.id.sbtn_nine);
        smult = (Button) findViewById(R.id.sbtn_mult);
        sac = (Button) findViewById(R.id.sbtn_ac);
        sdiv = (Button) findViewById(R.id.sbtn_div);
        sback = (ImageView) findViewById(R.id.sbtn_back);
        smod = (Button) findViewById(R.id.sbtn_mod);
        leftp = (Button) findViewById(R.id.sbtn_leftp);
        rightp = (Button) findViewById(R.id.sbtn_rightp);

        // coloing button
        setButtonsColor();

        deg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isdeg)
                {
                    deg.setText("RAD");
                    isdeg=false;
                }else
                {
                    deg.setText("DEG");
                    isdeg=true;

                }
            }
        });

          scrolledb = false;
        scientificnavi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!scrolledb) {
                    scrollview.scrollTo(1900, 0);
                    Toast.makeText(getApplicationContext(), "lol", Toast.LENGTH_SHORT).show();
                    scrolledb= true;

                }
                else
                {
                    scrollview.scrollTo(0, 0);
                    Toast.makeText(getApplicationContext(), "lol", Toast.LENGTH_SHORT).show();
                    scrolledb= false;
                }
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getHistory();
                Intent send = new Intent(getApplicationContext(), History.class);
                send.putExtra("history_data", history_data);


                startActivity(send);
            }
        });

        settingicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent send = new Intent(getApplicationContext(), Settingoption.class);

                send.putExtra("sv", sv);

                startActivity(send);
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "0");
                text_out();

            }
        });
        mod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "%");
                text_out();

            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + ".");
                text_out();

            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "1");
                text_out();

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "2");
                text_out();

            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "3");
                text_out();

            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "+");
                text_out();

            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "4");
                text_out();

            }
        });


        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "5");
                text_out();

            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "6");
                text_out();

            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "-");
                text_out();

            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "7");
                text_out();

            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "8");
                text_out();

            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "9");
                text_out();

            }
        });
        mult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "x");
                text_out();

            }
        });
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "";
                textViewbottom.setText("");
                textViewtop.setText("");
                text_out();

            }
        });
        div.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "/");
                text_out();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    try {

                        if (textViewbottom.getText().toString() != "") {
                            text = textViewbottom.getText().toString();
                            String temp = text.substring(0, text.length() - 1);

                            textViewbottom.setText(temp);
                            text_out();
                        }
                    } catch (Exception e) {

                    }


                }

            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    text = textViewbottom.getText().toString();
                    String temp = text;
                    temp = temp.replaceAll("x", "*");

                    Log.e("---", temp);
//                    Context rhino = Context.enter();
//                    rhino.setOptimizationLevel(-1);
                    String finalresult;
//
//                    Scriptable scriptable = rhino.initStandardObjects();
//                    finalresult = rhino.evaluateString(scriptable, temp, "Javascript", 1, null).toString();
//                    Expression exp1= new Expression(temp);
//                    finalresult= String.valueOf(exp1.calculate());
                    finalresult = String.valueOf(eval(temp));
                    finalresult = makeRound(finalresult);
                    textViewtop.setText(text);
                    textViewbottom.setText(finalresult);
//                    textbottom.add(finalresult);
//                    texttop.add(text);


                    history_data.add(new History_data(finalresult, text));
                    saveHistory();
                } catch (Exception e) {
                    Log.v("##", e.toString());
                }

            }
        });


        //  scientific


        sln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "ln(");
                text_out();
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "^(2)");
                text_out();
            }
        });
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "sqrt(");
                text_out();
            }
        });

        rightp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + ")");
                text_out();

            }
        });
        leftp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "(");
                text_out();

            }
        });
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "sin(");
                text_out();

            }
        });
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "cos(");
                text_out();

            }
        });

        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "tan(");
                text_out();

            }
        });
        cosec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "cosec(");
                text_out();

            }
        });
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "sec(");
                text_out();

            }
        });
        cot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "cot(");
                text_out();

            }
        });


        szero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "0");
                text_out();

            }
        });

        sdot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + ".");
                text_out();

            }
        });

        sone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "1");
                text_out();

            }
        });

        stwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "2");
                text_out();

            }
        });

        sthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "3");
                text_out();

            }
        });

        splus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "+");
                text_out();

            }
        });
        sfour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "4");
                text_out();

            }
        });


        sfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "5");
                text_out();

            }
        });

        ssix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "6");
                text_out();

            }
        });

        sminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "-");
                text_out();

            }
        });

        sseven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "7");
                text_out();

            }
        });

        seight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "8");
                text_out();

            }
        });

        snine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "9");
                text_out();

            }
        });

        smod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "%");
                text_out();

            }
        });
        smult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "x");
                text_out();

            }
        });
        sac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text = "";
                textViewbottom.setText("");
                textViewtop.setText("");
                text_out();

            }
        });
        sdiv.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                text = textViewbottom.getText().toString();
                textViewbottom.setText(text + "/");
                text_out();

            }
        });

        sback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    try {

                        if (textViewbottom.getText().toString() != "") {
                            text = textViewbottom.getText().toString();
                            String temp = text.substring(0, text.length() - 1);

                            textViewbottom.setText(temp);
                            text_out();
                        }
                    } catch (Exception e) {

                    }


                }

            }
        });

        sequal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    text = textViewbottom.getText().toString();
                    String temp = text;
                    temp = temp.replaceAll("x", "*");

                    Context rhino = Context.enter();
                    rhino.setOptimizationLevel(-1);
                    String finalresult;

//                    Scriptable scriptable = rhino.initStandardObjects();
//                    finalresult = rhino.evaluateString(scriptable, temp, "Javascript", 1, null).toString();
//                    Expression exp1= new Expression(temp);
//                     finalresult= String.valueOf(exp1.calculate());

                    finalresult = String.valueOf(eval(temp));
                    finalresult = makeRound(finalresult);

//
                    textViewtop.setText(text);
                    textViewbottom.setText(finalresult);
//                    textbottom.add(finalresult);
//                    texttop.add(text);


                    history_data.add(new History_data(finalresult, text));
                    saveHistory();
                } catch (Exception e) {
                    Log.v("##", e.toString());
                }

            }
        });

    }

    public  double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction

                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) {

                        x *= parseFactor();
                    } // multiplication

                    else if (eat('%')) {
                        Log.e("$$$", "loll");
                        x = x % parseFactor();
                    } // division
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = sqrt(x);
                    else if (func.equals("sin")) {
                            if(isdeg) {
                                x = sin(toRadians(x));
                            }else
                            {
                                x=sin(x);
                            }
                    }
                    else if (func.equals("cos")) {
                        if(isdeg) {
                            x = cos(toRadians(x));
                        }
                        else
                        {
                            x=cos(x);
                        }
                    }
                    else if (func.equals("tan")) {

                        if(isdeg) {
                            x = tan(toRadians(x));
                        }
                        else
                        {
                            x=tan(x);
                        }

                    }
                    else if (func.equals("cosec")) {

                        if(isdeg) {
                            x = 1 / sin(toRadians(x));
                        }
                        else
                        {
                            x = 1 / sin((x));
                        }

                    }
                    else if (func.equals("sec")) {

                        if(isdeg) {
                            x = 1 / cos(toRadians(x));
                        }
                        else
                        {
                            x = 1 / cos((x));
                        }
                    }
                    else if (func.equals("cot")) {
                        if(isdeg) {
                            x = 1 / tan(toRadians(x));
                        }
                        else
                        {
                            x = 1 / tan((x));
                        }
                    }
                    else if (func.equals("log")) x = log10(x);
                    else if (func.equals("ln")) x = log(x);
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

    public void text_out() {

        if (text.length() >= 50) {
            String s = (String) textViewbottom.getText();
            String t = s.substring(0, s.length() - 1);
            textViewbottom.setText(t);
            Toast.makeText(getApplicationContext(), "cant add more", Toast.LENGTH_SHORT).show();


        } else if (text.length() >= 30) {
            textViewbottom.setTextSize(22);
        } else if (text.length() >= 10) {
            textViewbottom.setTextSize(32);
        } else {
            textViewbottom.setTextSize(42);

        }


    }

    private void saveHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(history_data);
        editor.putString("historydata", json);
        editor.apply();


    }

    private void getHistory() {
        SharedPreferences sharedPreferences = getSharedPreferences("history", MODE_PRIVATE);


        Gson gson = new Gson();
        String json = sharedPreferences.getString("historydata", null);
        Type type = new TypeToken<ArrayList<History_data>>() {
        }.getType();
        history_data = gson.fromJson(json, type);


        if (history_data == null) {
            history_data = new ArrayList<>();
        }


    }

    private void saveSettingvalues() {
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
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


        if (sv == null) {
            sv = new Settingvalues();
        }


    }

    private void setButtonsColor() {
        mod.setTextColor(sv.getColor());
        div.setTextColor(sv.getColor());
        mult.setTextColor(sv.getColor());
        minus.setTextColor(sv.getColor());
        plus.setTextColor(sv.getColor());

        sin.setTextColor(sv.getColor());
        cos.setTextColor(sv.getColor());
        tan.setTextColor(sv.getColor());
        cosec.setTextColor(sv.getColor());
        sec.setTextColor(sv.getColor());
        cot.setTextColor(sv.getColor());
    }

    @Override
    public void onBackPressed() {
        Intent start = new Intent(Intent.ACTION_MAIN);
        start.addCategory(Intent.CATEGORY_HOME);
        start.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(start);

    }

    private String makeRound(String finalresult) {
        double num;
        Log.e("#", "000000000000000000 " + String.valueOf(sv.getRound()));
        DecimalFormat df = null;
        num = Double.parseDouble(finalresult);
        if (sv.getRound() == 1) {

            df = new DecimalFormat("0.0");
        } else if (sv.getRound() == 2) {
            df = new DecimalFormat("0.00");
        } else if (sv.getRound() == 3) {
            Log.e("#", "000000000000000000 " + String.valueOf(sv.getRound()));
            df = new DecimalFormat("0.000");
        } else if (sv.getRound() == 4) {
            df = new DecimalFormat("0.0000");
        }
        if (sv.getRound() == 5) {
            df = new DecimalFormat("0.00000");
        }
        num = Double.parseDouble(df.format(num));

        return String.valueOf(num);

    }
}


