package com.softdesign.school;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String VISIBLE_KEY = "visible";
    public static final String COLOR_TOOLBAR = "color";
    private int currentBackgroundToolBar;

    CheckBox mCheckBox;
    EditText mEditText2;
    Toolbar mToolBar;


    Button mBtnBlue;
    Button mBtnGreen;
    Button mBtnRed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onCreate()");
        setContentView(R.layout.activity_main);

        setTitle("School lesson 1");

        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
        mCheckBox.setOnClickListener(this);

        mEditText2 = (EditText) findViewById(R.id.editText2);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        mBtnBlue = (Button) findViewById(R.id.btn_blue);
        mBtnGreen = (Button) findViewById(R.id.btn_green);
        mBtnRed = (Button) findViewById(R.id.btn_red);

        mBtnBlue.setOnClickListener(this);
        mBtnGreen.setOnClickListener(this);
        mBtnRed.setOnClickListener(this);

        setupToolbar();

    }

    private void setupToolbar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Toast.makeText(this, "Menu click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Lg.e(this.getLocalClassName(), "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Lg.e(this.getLocalClassName(), "onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Lg.e(this.getLocalClassName(), "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Lg.e(this.getLocalClassName(), "onStop()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Lg.e(this.getLocalClassName(), "onRestart()");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Lg.e(this.getLocalClassName(), "onDestroy()");
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.checkBox:
                Toast.makeText(this, "Click!", Toast.LENGTH_SHORT).show();
                if (mCheckBox.isChecked()) {
                    mEditText2.setVisibility(View.INVISIBLE);
                } else {
                    mEditText2.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_blue:
                //todo какой метод правильный (1) или (2)?
//                mToolBar.setBackgroundColor(getResources().getColor(R.color.default_blue_color)); (1)
                mToolBar.setBackgroundResource(R.color.default_blue_color);                       //(2)
                currentBackgroundToolBar = R.color.default_blue_color;
                setColorStatusBar(getResources().getColor(R.color.default_dark_blue_color));
                break;
            case R.id.btn_green:
                mToolBar.setBackgroundResource(R.color.default_green_color);
                currentBackgroundToolBar = R.color.default_green_color;
                setColorStatusBar(getResources().getColor(R.color.default_dark_green_color));
//                Toast.makeText(this, "" + mToolBar.getBackground().toString(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_red:
                mToolBar.setBackgroundResource(R.color.default_red_color);
                currentBackgroundToolBar = R.color.default_red_color;
                setColorStatusBar(getResources().getColor(R.color.default_dark_red_color));
                break;
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setColorStatusBar(int color){
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(color);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "onSaveInstanceState()");
        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
        outState.putInt(COLOR_TOOLBAR, currentBackgroundToolBar);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onRestoreInstanceState()");

        int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText2.setVisibility(visibleState);

        int backgroundToolBarColor = savedInstanceState.getInt(COLOR_TOOLBAR, R.color.default_blue_color);
        mToolBar.setBackgroundResource(backgroundToolBarColor);
    }
}
