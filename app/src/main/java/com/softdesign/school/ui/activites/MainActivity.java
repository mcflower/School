package com.softdesign.school.ui.activites;

import android.annotation.TargetApi;
import android.support.v4.app.FragmentManager;
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
import com.softdesign.school.utils.ConstantManager;
import com.softdesign.school.utils.Lg;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Fragment mFragment;
    private View mHeaderLayout;
    private String mFragmentTag = null;
    private FragmentManager mFragmentManager;

    public AppBarLayout.LayoutParams params = null;

    Toolbar mToolBar;

    private NavigationView mNavigationView;
    private DrawerLayout mNavigationDrawer;


    private FrameLayout mFrameContainer;

    public AppBarLayout mAppBar;
    private CollapsingToolbarLayout mCollapsingToolbar;


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

        // инициализируем встроенные методы Activity
        mFragmentManager = getSupportFragmentManager();
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
            mFragment = fragmentInstanceByTag(ConstantManager.FRAGMENT_TAG_PROFILE);
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame_container, new ProfileFragment()).commit();
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
    public void onBackPressed() {
        Fragment findingFragment = (Fragment) getSupportFragmentManager().findFragmentById(R.id.main_frame_container);

        if (findingFragment != null && findingFragment instanceof ProfileFragment) {
            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }
        super.onBackPressed();
    }

    /**
     * Инициализирует ToolBar
     */
    public void setupToolbar() {
        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        params = (AppBarLayout.LayoutParams) mCollapsingToolbar.getLayoutParams();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_24dp);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Инициализирует NavigationDrawer
     */
    private void setupDrawer() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.drawer_profile:
                        mFragmentTag = ConstantManager.FRAGMENT_TAG_PROFILE;
                        mNavigationView.getMenu().findItem(R.id.drawer_profile).setChecked(true);
                        break;
                    case R.id.drawer_contacts:
                        mFragmentTag = ConstantManager.FRAGMENT_TAG_CONTACTS;
                        mNavigationView.getMenu().findItem(R.id.drawer_contacts).setChecked(true);
                        break;
                    case R.id.drawer_setting:
                        mFragmentTag = ConstantManager.FRAGMENT_TAG_SETTINGS;
                        mNavigationView.getMenu().findItem(R.id.drawer_setting).setChecked(true);
                        break;
                    case R.id.drawer_team:
                        mFragmentTag = ConstantManager.FRAGMENT_TAG_TEAM;
                        mNavigationView.getMenu().findItem(R.id.drawer_team).setChecked(true);
                        break;
                    case R.id.drawer_tasks:
                        mFragmentTag = ConstantManager.FRAGMENT_TAG_TASKS;
                        mNavigationView.getMenu().findItem(R.id.drawer_tasks).setChecked(true);
                        break;
                }

                mFragment = fragmentInstanceByTag(mFragmentTag);
                mFragmentManager.beginTransaction().replace(R.id.main_frame_container, mFragment, mFragmentTag)
                        .addToBackStack(mFragmentTag).commit();

                mNavigationDrawer.closeDrawers();
                return false;
            }
        });
    }

    /**
     * Сворачивает ToolBar
     *
     * @param collapse true - свернуть / false -  развернуть
     */
    public void lockAppBar(boolean collapse, String title) {
        setTitle(title);
        if (collapse) {
            AppBarLayout.OnOffsetChangedListener mListener = new AppBarLayout.OnOffsetChangedListener() {
                @Override
                public void onOffsetChanged(AppBarLayout mAppBar, int verticalOffset) {
                    if (mCollapsingToolbar.getHeight() + verticalOffset <= ViewCompat.getMinimumHeight(mCollapsingToolbar) + getStatusBarHeight()) {
                        mAppBar.removeOnOffsetChangedListener(this);
                        LockToolBar();
                    }
                }
            };
            mAppBar.addOnOffsetChangedListener(mListener);
            mAppBar.setExpanded(false);
        } else {
            UnLockToolBar();
            mAppBar.setExpanded(true);
        }
    }

    /**
     * Снимает блокировку с ToolBar выставляя scrollFlag
     */
    private void LockToolBar() {
        params.setScrollFlags(0);
        mCollapsingToolbar.setLayoutParams(params);
    }

    /**
     * Блокирует ToolBar выставляя scrollFlag
     */
    private void UnLockToolBar() {
        params.setScrollFlags(AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED);
        mCollapsingToolbar.setLayoutParams(params);
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


    /**
     * Создаем фрагмент по его тегу
     *
     * @param mFragmentTag - тег фрагмента
     * @return фрагмент
     */
    private Fragment fragmentInstanceByTag(String mFragmentTag) {

        Fragment newFragment;
        switch (mFragmentTag) {
            case ConstantManager.FRAGMENT_TAG_PROFILE:
                newFragment = mFragmentManager.findFragmentByTag(mFragmentTag);
                if (newFragment == null) {
                    newFragment = new ProfileFragment();
                }
                break;
            case ConstantManager.FRAGMENT_TAG_CONTACTS:
                newFragment = mFragmentManager.findFragmentByTag(mFragmentTag);
                if (newFragment == null) {
                    newFragment = new ContactsFragment();
                }
                break;
            case ConstantManager.FRAGMENT_TAG_SETTINGS:
                newFragment = mFragmentManager.findFragmentByTag(mFragmentTag);
                if (newFragment == null) {
                    newFragment = new SettingFragment();
                }
                break;
            case ConstantManager.FRAGMENT_TAG_TASKS:
                newFragment = mFragmentManager.findFragmentByTag(mFragmentTag);
                if (newFragment == null) {
                    newFragment = new TasksFragment();
                }
                break;
            case ConstantManager.FRAGMENT_TAG_TEAM:
                newFragment = mFragmentManager.findFragmentByTag(mFragmentTag);
                if (newFragment == null) {
                    newFragment = new TeamFragment();
                }
                break;
            default:
                newFragment = mFragmentManager.findFragmentById(R.id.main_frame_container);
                break;
        }
        return newFragment;
    }
}
