package com.softdesign.school.ui.activites;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.softdesign.school.R;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    public static final String VISIBLE_KEY = "visible";
//    public static final String STATE_COLOR_TOOLBAR = "toolbar";
//    public static final String STATE_COLOR_STATUSBAR = "statusbar";
//    private int mCurrentBackgroundToolBar;
//    private int mCurrentBackgroundStatusBar;

    //    CheckBox mCheckBox;
//    EditText mEditText2;
    Toolbar mToolBar;


//    Button mBtnBlue;
//    Button mBtnGreen;
//    Button mBtnRed;

    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;

    private Fragment mFragment;
    private FrameLayout mFrameContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onCreate()");
        setContentView(R.layout.activity_main);

        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);


        setTitle("School lesson 1");

        /**
         * Если устройство поддерживает делаем прозрачным status bar
         */
        if (Build.VERSION.SDK_INT >= 21) {
            // Set the status bar to dark-semi-transparentish
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

//        mCheckBox = (CheckBox) findViewById(R.id.checkBox);
//        mCheckBox.setOnClickListener(this);

//        mEditText2 = (EditText) findViewById(R.id.editText2);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);

//        mBtnBlue = (Button) findViewById(R.id.btn_blue);
//        mBtnGreen = (Button) findViewById(R.id.btn_green);
//        mBtnRed = (Button) findViewById(R.id.btn_red);

//        mBtnBlue.setOnClickListener(this);
//        mBtnGreen.setOnClickListener(this);
//        mBtnRed.setOnClickListener(this);

        setupToolbar();
        setupDrawer();

        if (savedInstanceState != null) {

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit();
        }

    }

    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragment = new ProfileFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_profile);
                        break;
                    case R.id.drawer_contacts:
                        mFragment = new ContactsFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_contacts);
                        break;
                    case R.id.drawer_setting:
                        mFragment = new SettingFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_setting).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_setting);
                        break;
                    case R.id.drawer_tasks:
                        mFragment = new TasksFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_tasks);
                        break;
                    case R.id.drawer_team:
                        mFragment = new TeamFragment();
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setCheckable(true);
                        mNavigationView.setCheckedItem(R.id.drawer_team);
                        break;
                }

                if (mFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, mFragment).addToBackStack(null).commit();

                } else {

                }

                mNavigationDrawer.closeDrawers();
                return false;
            }
        });

    }


    /**
     * Метод обработки нажатия клавиши back
     */
    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            finish();
            System.exit(0);
        } else {
            getSupportFragmentManager().popBackStack();
        }
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
            mNavigationDrawer.openDrawer(GravityCompat.START);
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
        /*int id = v.getId();
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
                mCurrentBackgroundToolBar = R.color.default_blue_color;
                mToolBar.setBackgroundResource(mCurrentBackgroundToolBar);                       //(2)

                mCurrentBackgroundStatusBar = getResources().getColor(R.color.default_dark_blue_color);
                setColorStatusBar(mCurrentBackgroundStatusBar);
                break;
            case R.id.btn_green:
                mCurrentBackgroundToolBar = R.color.default_green_color;
                mToolBar.setBackgroundResource(mCurrentBackgroundToolBar);

                mCurrentBackgroundStatusBar = getResources().getColor(R.color.default_dark_green_color);
                setColorStatusBar(mCurrentBackgroundStatusBar);
                break;
            case R.id.btn_red:
                mCurrentBackgroundToolBar = R.color.default_red_color;
                mToolBar.setBackgroundResource(mCurrentBackgroundToolBar);

                mCurrentBackgroundStatusBar = getResources().getColor(R.color.default_dark_red_color);
                setColorStatusBar(mCurrentBackgroundStatusBar);
                break;
        }*/
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setColorStatusBar(int color) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(color);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Lg.e(this.getLocalClassName(), "onSaveInstanceState()");
//        outState.putBoolean(VISIBLE_KEY, mEditText2.getVisibility() == View.VISIBLE);
//        outState.putInt(STATE_COLOR_TOOLBAR, mCurrentBackgroundToolBar);
//        outState.putInt(STATE_COLOR_STATUSBAR, mCurrentBackgroundStatusBar);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onRestoreInstanceState()");

        /*int visibleState = savedInstanceState.getBoolean(VISIBLE_KEY) ? View.VISIBLE : View.INVISIBLE;
        mEditText2.setVisibility(visibleState);

        int backgroundToolBarColor = savedInstanceState.getInt(STATE_COLOR_TOOLBAR, R.color.default_blue_color);
        mToolBar.setBackgroundResource(backgroundToolBarColor);

        int backgroundStatusBarColor = savedInstanceState.getInt(STATE_COLOR_STATUSBAR, R.color.default_dark_blue_color);
        setColorStatusBar(backgroundStatusBarColor);*/
    }
}
