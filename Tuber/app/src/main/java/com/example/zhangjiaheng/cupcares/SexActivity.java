package com.example.zhangjiaheng.cupcares;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SexActivity extends AppCompatActivity implements View.OnClickListener{

    TextView toolBar;
    TextView text_male;
    TextView text_female;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sex);
        Toolbar toolbar = (Toolbar) findViewById(R.id.sex_bar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        text_female=(TextView) findViewById(R.id.text_female);
        text_female.setOnClickListener(this);
        toolBar= (TextView) findViewById(R.id.toolBar);
        toolBar.setText("Sex");
        text_male=(TextView) findViewById(R.id.text_male);
        text_male.setOnClickListener(this);

        preferences= getSharedPreferences("setting", MODE_PRIVATE);
        editor= preferences.edit();

        if(preferences.getString("sex", null)==null){
            text_male.setTextColor(Color.BLACK);
            text_female.setTextColor(Color.BLACK);
        }else if(preferences.getString("sex", null).equals("male")){
            text_male.setTextColor(Color.parseColor("#1976D2"));
        }else   if(preferences.getString("sex", null).equals("female")){
            text_female.setTextColor(Color.parseColor("#1976D2"));
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_male:

                editor.putString("sex", "male");
                editor.commit();

                text_male.setTextColor(Color.parseColor("#1976D2"));
                text_female.setTextColor(Color.BLACK);
                finish();
                break;
            case R.id.text_female:

                editor.putString("sex", "female");
                editor.commit();
                text_female.setTextColor(Color.parseColor("#1976D2"));
                text_male.setTextColor(Color.BLACK);
finish();
                break;

        }
    }
}
