package com.softdesign.school.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.softdesign.school.R;
import com.softdesign.school.data.storage.models.User;
import com.softdesign.school.ui.activites.MainActivity;
import com.softdesign.school.ui.adapters.RecycleUserAdapter;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<User> mUsers = new ArrayList<User>();
    View mainView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        generateUsers();
        mAdapter = new RecycleUserAdapter(mUsers);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View convertView = inflater.inflate(R.layout.fragment_contacts, null, false);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.getSupportActionBar().setTitle(R.string.drawer_contacts);
//        ((MainActivity) getActivity()).lockAppBar(true, getResources().getString(R.string.drawer_contacts));
        if (mainView == null) {
            // Если представления нет, создаем его*//*
            mainView = inflater.inflate(R.layout.fragment_contacts, container, false);}
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));

//        generateUsers();

        mRecyclerView = (RecyclerView) mainView.findViewById(R.id.recycle_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

//        mAdapter = new RecycleUserAdapter(mUsers);
        mRecyclerView.setAdapter(mAdapter);

        ((MainActivity) getActivity()).lockAppBar(true);

//        ((MainActivity) getActivity()).lockAppBar(true);
        return mainView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getString(R.string.drawer_contacts));

        super.onActivityCreated(savedInstanceState);
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) fab.getLayoutParams();
        params.setAnchorId(R.id.coordinator_container);
        params.anchorGravity = Gravity.BOTTOM | Gravity.RIGHT;
        fab.setLayoutParams(params);
        fab.setImageResource(R.drawable.ic_add_24dp);
    }

    private void generateUsers() {
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_face_24dp), "Алексей", "Иринин"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_face_24dp), "Алексей", "Тютюнник"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_face_24dp), "Светлана", "Веселова"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_face_24dp), "Иванов", "Иван"));
        mUsers.add(new User(getResources().getDrawable(R.drawable.ic_face_24dp), "Петров", "Петр"));
    }
}
