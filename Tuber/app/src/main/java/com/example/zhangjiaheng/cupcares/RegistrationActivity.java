package com.example.zhangjiaheng.cupcares;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.jar.Attributes;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener{

    ImageButton menuButton;
    LinearLayout account_button;
    ImageButton connect;
    Toolbar toolBar;

    TextView text_welcome;
    TextView info_name;
    TextView info_sex;
    TextView info_age;
    TextView info_weight;
    TextView info_target;
    TextView text_picker;
    NumberPicker age_picker;
    LinearLayout register_name;
    LinearLayout register_age;
    LinearLayout register_sex;
    LinearLayout register_weight;
    LinearLayout register_target;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolBar = (Toolbar) findViewById(R.id.app_bar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        preferences= getSharedPreferences("setting", MODE_PRIVATE);
        editor= preferences.edit();
        initLayout();

    }

    public void initLayout(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        info_age=(TextView) findViewById(R.id.info_age);
        info_name=(TextView) findViewById(R.id.info_name);
        info_sex=(TextView) findViewById(R.id.info_sex);
        info_weight=(TextView) findViewById(R.id.info_weight);
        info_target=(TextView) findViewById(R.id.info_target);

        text_welcome=(TextView) findViewById(R.id.text_welcome);
        if(preferences.getString("name", null)==null){
            text_welcome.setText("Welcome");
        }else{
            text_welcome.setText("Welcome，"+preferences.getString("name", null));
        }
        register_target= (LinearLayout) findViewById(R.id.register_target);
        register_name=(LinearLayout) findViewById(R.id.register_name);
        register_age=(LinearLayout) findViewById(R.id.register_age);
        register_sex=(LinearLayout) findViewById(R.id.register_sex);
        register_weight=(LinearLayout) findViewById(R.id.register_weight);

        register_name.setOnClickListener(this);
        register_age.setOnClickListener(this);
        register_sex.setOnClickListener(this);
        register_weight.setOnClickListener(this);
        register_target.setOnClickListener(this);

        connect=(ImageButton) findViewById(R.id.connect);
        connect.setVisibility(View.INVISIBLE);
        account_button= (LinearLayout) findViewById(R.id.account_button);
        menuButton = (ImageButton) findViewById(R.id.account);
        menuButton.setImageResource(R.drawable.close);
        menuButton.setScaleType(ImageView.ScaleType.FIT_CENTER);
        account_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View theView = inflater.inflate(R.layout.age_selector, null);
        Intent intent;
        switch (view.getId()){
            case R.id.account_button:
                intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.a2, R.anim.a1);
                break;
            case R.id.register_name:
                intent = new Intent(RegistrationActivity.this, NameActivity.class);
                startActivity(intent);

                break;
            case R.id.register_age:

//I define the dialog and I load the xml layout: number_picker_dialog.xml into the view
                age_picker= (NumberPicker) theView.findViewById(R.id.age_picker);

                builder.setView(theView)
                        .setPositiveButton("Save",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                editor.putString("age",String.valueOf(age_picker.getValue()));
                                editor.commit();
                                if(preferences.getString("age", null)==null){
                                    info_age.setText("Your age");
                                }else{
                                    info_age.setText(preferences.getString("age", null));
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                age_picker.setMinValue(1);
                age_picker.setMaxValue(120);

                if(preferences.getString("age", null)==null){
                    age_picker.setValue(22);
                }else {
                    age_picker.setValue(Integer.valueOf(preferences.getString("age", null)));
                }
                builder.show();
                break;

            case R.id.register_weight:

//I define the dialog and I load the xml layout: number_picker_dialog.xml into the view
                age_picker= (NumberPicker) theView.findViewById(R.id.age_picker);

                text_picker= (TextView) theView.findViewById(R.id.selector_title);
                builder.setView(theView)
                        .setPositiveButton("Save",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {


                                editor.putString("weight", String.valueOf(age_picker.getValue()));
                                editor.commit();
                                if(preferences.getString("weight", null)==null){
                                    info_weight.setText("Your weight");
                                }else{
                                    info_weight.setText(preferences.getString("weight", null)+" kg");
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                text_picker.setText("Your weight (kg):");
                age_picker.setMinValue(1);
                age_picker.setMaxValue(120);
                age_picker.setValue(60);
                builder.show();
                break;

            case R.id.register_target:

                age_picker= (NumberPicker) theView.findViewById(R.id.age_picker);
                if(preferences.getString("target", null)==null){
                        age_picker.setValue(1000);
                }else{
                        age_picker.setValue((Integer.valueOf(preferences.getString("target", null))-1000)/50+1000);
                }

                text_picker= (TextView) theView.findViewById(R.id.selector_title);
                builder.setView(theView)
                        .setPositiveButton("Save",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                editor.putString("target", String.valueOf(1000+(age_picker.getValue()-1000)*50));
                                editor.commit();

                                if(preferences.getString("target", null)==null){
                                    info_target.setText("Recommendation");
                                }else{
                                    info_target.setText(preferences.getString("target", null)+" mL");
                                }
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                text_picker.setText("Target daily drinking (ml):");

                String target[]=new String[41];

                for(int i=0;i<target.length;i++){
                    target[i]=Integer.toString(1000+50*i);
                }

                age_picker.setMinValue(1000);
                age_picker.setMaxValue(1040);

                if(preferences.getString("target", null)==null){
                    age_picker.setValue(1000);
                }else {
                    age_picker.setValue((Integer.valueOf(preferences.getString("target", null))-1000)/50+1000);
                }
                age_picker.setWrapSelectorWheel(false);
                age_picker.setDisplayedValues(target);



                builder.show();

                break;

            case R.id.register_sex:

                intent= new Intent(RegistrationActivity.this, SexActivity.class);
                startActivity(intent);

                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(preferences.getString("name", null)==null){
            info_name.setText("Your name");
        }else{
            info_name.setText(preferences.getString("name", null));
        }

        if(preferences.getString("sex", null)==null){
            info_sex.setText("Male/Female");
        }else{

            if(preferences.getString("sex", null).equals("male")){
                info_sex.setText("Male");
            }else {
                info_sex.setText("Female");
            }
        }

        if(preferences.getString("age", null)==null){
            info_age.setText("Your age");
        }else{
            info_age.setText(preferences.getString("age", null));
        }

        if(preferences.getString("weight", null)==null){
            info_weight.setText("Your weight");
        }else{
            info_weight.setText(preferences.getString("weight", null)+" kg");
        }

        if(preferences.getString("target", null)==null){
            info_target.setText("Recommendation");
        }else{
            info_target.setText(preferences.getString("target", null)+" mL");
        }

        if(preferences.getString("name", null)==null){
            text_welcome.setText("Welcome");
        }else{
            text_welcome.setText("Welcome，"+preferences.getString("name", null));
        }


    }
}



