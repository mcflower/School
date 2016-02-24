package com.softdesign.school.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.ui.activites.MainActivity;

/**
 * Created by Ilya_Zelenskiy on 31.01.16.
 */
public class SettingFragment extends Fragment {

    View mainView;

    @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            if (mainView == null) {
                // Если представления нет, создаем его*//*
                mainView = inflater.inflate(R.layout.fragment_setting, container, false);}
            getActivity().setTitle(getResources().getString(R.string.drawer_setting));
            /*View convertView = inflater.inflate(R.layout.fragment_setting, null, false);
            getActivity().setTitle(getResources().getString(R.string.drawer_setting));*/
//            ((MainActivity) getActivity()).lockAppBar(true, getResources().getString(R.string.drawer_setting));
            return mainView;
        }

}
