package com.example.zhangjiaheng.cupcares;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.Result;

import org.w3c.dom.Text;

import me.dm7.barcodescanner.zxing.ZXingScannerView;



public class ScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Toolbar toolBar;
    ScanFragment scanFragment;
    TextView text_connect;
    FragmentTransaction transaction;
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_scan);                // Set the scanner view as the content view
        preferences= getSharedPreferences("setting", MODE_PRIVATE);
        editor= preferences.edit();
        text_connect=(TextView) findViewById(R.id.toolBar);
        text_connect.setText("Connect");
        toolBar = (Toolbar) findViewById(R.id.register_bar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }

        scanFragment= new ScanFragment();
    transaction= getSupportFragmentManager().beginTransaction();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here


    }

    @Override
    protected void onResume() {
        super.onResume();
        transaction.add(R.id.scan_container, scanFragment);
        transaction.commit();
    }
}
