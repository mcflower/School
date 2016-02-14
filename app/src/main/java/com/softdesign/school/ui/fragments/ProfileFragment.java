package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activites.MainActivity;

/**
 * Created by Ilya_Zelenskiy on 31.01.16.
 */
public class ProfileFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View convertView = inflater.inflate(R.layout.fragment_profile, null, false);
//        getActivity().setTitle(getResources().getString(R.string.drawer_profile_name));
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setTitle(R.string.drawer_profile_name);

        ((MainActivity) getActivity()).lockAppBar(false);
        return convertView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setAnchorId(R.id.appbar_layout);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT;
        fab.setLayoutParams(params);
        fab.setImageResource(R.drawable.ic_call_white_24dp);
    }
}
