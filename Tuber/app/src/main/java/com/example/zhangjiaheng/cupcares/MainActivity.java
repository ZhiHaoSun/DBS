package com.example.zhangjiaheng.cupcares;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import org.w3c.dom.Text;

public class MainActivity extends BlunoLibrary implements View.OnClickListener {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    ImageView image_1;
    ImageView image_2;
    ImageView image_3;
    ImageView image_4;


    ImageButton account;
    Toolbar toolBar;
    TextView toolbar;
    LinearLayout tab_1;
    LinearLayout tab_2;
    LinearLayout tab_3;
    LinearLayout tab_4;
    LinearLayout connection;
    LinearLayout account_button;

    TextView tab_1_text;
    TextView tab_2_text;
    TextView tab_3_text;
    TextView tab_4_text;

    ImageButton connect;

    FirstFragment fragment_1;
    SecondFragment fragment_2;
    ThirdFragment fragment_3;
    FourthFragment fragment_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolBar = (Toolbar) findViewById(R.id.app_bar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        onCreateProcess();														//onCreate Process by BlunoLibrary
        serialBegin(9600);

        initLayout();

    }

    private void initLayout(){

        preferences= getSharedPreferences("setting", MODE_PRIVATE);
        editor= preferences.edit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        toolbar=(TextView) findViewById(R.id.toolBar) ;
        account = (ImageButton) findViewById(R.id.account);
        connect =(ImageButton) findViewById(R.id.connect);
        connection= (LinearLayout) findViewById(R.id.connection);
        tab_1_text=(TextView)findViewById(R.id.tab_1_text);
        tab_2_text=(TextView)findViewById(R.id.tab_2_text);
        tab_3_text=(TextView)findViewById(R.id.tab_3_text);
        tab_4_text=(TextView)findViewById(R.id.tab_4_text);

        image_1=(ImageView) findViewById(R.id.image_1);
        image_2=(ImageView) findViewById(R.id.image_2);
        image_3=(ImageView) findViewById(R.id.image_3);
        image_4=(ImageView) findViewById(R.id.image_4);

        account_button= (LinearLayout) findViewById(R.id.account_button);
        tab_1= (LinearLayout) findViewById(R.id.tab_1);
        tab_2= (LinearLayout) findViewById(R.id.tab_2);
        tab_3= (LinearLayout) findViewById(R.id.tab_3);
        tab_4= (LinearLayout) findViewById(R.id.tab_4);

        account_button.setOnClickListener(this);
        tab_1.setOnClickListener(this);
        tab_2.setOnClickListener(this);
        tab_3.setOnClickListener(this);
        tab_4.setOnClickListener(this);
        connection.setOnClickListener(this);

        fragment_1 = new FirstFragment();
        fragment_2 = new SecondFragment();
        fragment_3 = new ThirdFragment();
        fragment_4 = new FourthFragment();
        FragmentTransaction transaction= getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container, fragment_1);
        transaction.commit();
        toolbar.setText(R.string.first_tab);
        tab_1_text.setTextColor(Color.parseColor("#2196F3"));
        image_1.setImageResource(R.drawable.today_pressed);

    }

    public  void initTextColor(){
        tab_1_text.setTextColor(Color.parseColor("#BDBDBD"));
        tab_2_text.setTextColor(Color.parseColor("#BDBDBD"));
        tab_3_text.setTextColor(Color.parseColor("#BDBDBD"));
        tab_4_text.setTextColor(Color.parseColor("#BDBDBD"));

        image_1.setImageResource(R.drawable.today);
        image_2.setImageResource(R.drawable.app);
        image_3.setImageResource(R.drawable.history);
        image_4.setImageResource(R.drawable.message);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.tab_1:
                initTextColor();
                fragmentTransaction.replace(R.id.container, fragment_1);
                toolbar.setText(R.string.first_tab);
                tab_1_text.setTextColor(Color.parseColor("#2196F3"));
                image_1.setImageResource(R.drawable.today_pressed);

                break;

            case R.id.tab_2:
                initTextColor();
                fragmentTransaction.replace(R.id.container, fragment_2);
                toolbar.setText(R.string.second_tab);
                tab_2_text.setTextColor(Color.parseColor("#2196F3"));
                image_2.setImageResource(R.drawable.app_pressed);

                break;

            case R.id.tab_3:
                initTextColor();

                fragmentTransaction.replace(R.id.container, fragment_3);
                toolbar.setText(R.string.third_tab);
                tab_3_text.setTextColor(Color.parseColor("#2196F3"));

                image_3.setImageResource(R.drawable.history_pressed);
                break;

            case R.id.tab_4:
                initTextColor();

                fragmentTransaction.replace(R.id.container, fragment_4);
                toolbar.setText(R.string.fourth_tab);
                tab_4_text.setTextColor(Color.parseColor("#2196F3"));

                image_4.setImageResource(R.drawable.message_pressed);
                break;

            case R.id.account_button:
                intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.scale_in, R.anim.alpha_out);
                break;

            case R.id.connection:
                intent= new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
                break;
        }

        fragmentTransaction.commit();
    }


    private void showTip(String string){
        final String text = string;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        onCreateProcess();														//onCreate Process by BlunoLibrary
        serialBegin(9600);

        if(preferences.getString("device", null)==null){
            connect.setImageResource(R.drawable.scan);
        }else{
            connect.setImageResource(R.drawable.scan_success);
        }


    }


    @Override
    public void onSerialReceived(String theString) {							//Once connection data received, this function will be called
        // TODO Auto-generated method stub
       // serialReceivedText.append(theString);							//append the text into the EditText
        //The Serial data from the BLUNO may be sub-packaged, so using a buffer to hold the String is a good choice.

    }

    @Override
    public void onConectionStateChange(connectionStateEnum theConnectionState) {//Once connection state changes, this function will be called
        switch (theConnectionState) {											//Four connection state
            case isConnected:
              //  buttonScan.setText("Connected");
                break;
            case isConnecting:
            //    buttonScan.setText("Connecting");
                break;
            case isToScan:
                //buttonScan.setText("Scan");
                break;
            case isScanning:
             //   buttonScan.setText("Scanning");
                break;
            case isDisconnecting:
            //    buttonScan.setText("isDisconnecting");
                break;
            default:
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroyProcess();														//onDestroy Process by BlunoLibrary
    }

    protected void onStop() {
        super.onStop();
        onStopProcess();														//onStop Process by BlunoLibrary
    }

    @Override
    protected void onPause() {
        super.onPause();
       // onPauseProcess();														//onPause Process by BlunoLibrary
    }



}
