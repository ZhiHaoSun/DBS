package com.example.zhangjiaheng.cupcares;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class NameActivity extends AppCompatActivity implements View.OnClickListener {
Toolbar toolBar;
Button button_cancel;
    EditText text_name;
    Button button_save;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name);
        toolBar = (Toolbar) findViewById(R.id.register_bar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        preferences= getSharedPreferences("setting", MODE_PRIVATE);
        editor= preferences.edit();
        initView();
        if(preferences.getString("name",null)!=null){
            text_name.setText(preferences.getString("name", null));
            text_name.setSelection(text_name.getText().length());
        }



    }

    public void initView(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        text_name= (EditText) findViewById(R.id.text_name);
        button_save=(Button) findViewById(R.id.button_save);
        button_save.setOnClickListener(this);
        button_cancel=(Button) findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_cancel:
                finish();
                break;

            case R.id.button_save:

                if(text_name.getText().toString().isEmpty()){
                    finish();
                }else {
                    editor.putString("name", text_name.getText().toString());
                    editor.commit();
                    finish();
                }

                break;
        }
    }
}
