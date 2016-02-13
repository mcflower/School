package com.softdesign.school.data.storage.models;


import android.graphics.drawable.Drawable;

public class User {

    private String mFirstName;
    private String mLastName;
    private int mRait;
    private Drawable mImage;
    private String mVkLink;
    private String mGitLink;
    private int mHomeTask;

    public User(Drawable mImage, String mLastName, String mFirstName) {
        this.mLastName = mLastName;
        this.mFirstName = mFirstName;
        this.mImage = mImage;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public int getmRait() {
        return mRait;
    }

    public Drawable getmImage() {
        return mImage;
    }

    public String getmVkLink() {
        return mVkLink;
    }

    public String getmGitLink() {
        return mGitLink;
    }

    public int getmHomeTask() {
        return mHomeTask;
    }
}
