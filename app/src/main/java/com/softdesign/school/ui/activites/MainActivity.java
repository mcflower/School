package com.softdesign.school.ui.activites;

import android.annotation.TargetApi;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
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
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.fragments.ContactsFragment;
import com.softdesign.school.ui.fragments.ProfileFragment;
import com.softdesign.school.ui.fragments.SettingFragment;
import com.softdesign.school.ui.fragments.TasksFragment;
import com.softdesign.school.ui.fragments.TeamFragment;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar mToolBar;

    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;

    private Fragment mFragment;
    private FrameLayout mFrameContainer;

    public AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolbar;
    private View mHeaderLayout;

    AppBarLayout.LayoutParams params = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Lg.e(this.getLocalClassName(), "onCreate()");
        setContentView(R.layout.activity_main);

        mNavigationDrawer = (DrawerLayout) findViewById(R.id.navigation_drawer);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);

        mAppBar = (AppBarLayout) findViewById(R.id.appbar_layout);
        mCollapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setTitle("School lesson 1");


        mToolBar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();
        setupDrawer();

        /**
         * Если устройство поддерживает делаем прозрачным status bar
         */
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }


        if (savedInstanceState != null) {

        } else {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit();
        }

    }

    /**
     * Сворачиваем и разворачиваем ToolBar
     * @param collapse - true (свернуть)
     */
    public void lockAppBar(boolean collapse, String title) {

        mCollapsingToolbar.setTitle(title);

        if (collapse) {

            mAppBar.setExpanded(false);
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout mAppBar, int verticalOffset) {
                    if (mCollapsingToolbar.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                        params.setScrollFlags(0);
                        mCollapsingToolbar.setLayoutParams(params);
                        mAppBar.removeOnOffsetChangedListener(this);
                    }
                }
            };
            mAppBar.addOnOffsetChangedListener(mListener);

        } else {
            mAppBar.setExpanded(true);
            params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
            mCollapsingToolbar.setLayoutParams(params);

        }
    }

    /**
     *
     * @return Возвращает высоту статус бар
     */
    private int getStatusBarHeight() {
        int result = 0;
        int resourseId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourseId > 0) {
            result = getResources().getDimensionPixelSize(resourseId);
        }
        return result;
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
        params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();

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
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
