package com.wanblog.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    private static SharedPreferencesUtil mSharedPreferencesUtil;
    private static SharedPreferences mSharedPreferences;

    private SharedPreferencesUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesUtil getInstance(Context context) {
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = new SharedPreferencesUtil(context);
        }
        return mSharedPreferencesUtil;
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void putString(String key, String value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void putInt(String key, int value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putInt(key, value);
        edit.commit();
    }

    public long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }

    public void putLong(String key, long value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putLong(key, value);
        edit.commit();
    }

    public Boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, true);
    }

    public void putBoolean(String key, boolean value) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putBoolean(key, value);
        edit.commit();
    }

    /**
     * 通过key进行删除
     *
     * @param key
     */
    public void remove(String key) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.remove(key);
        edit.commit();
    }

    /*
     * 清楚所有数据*/
    public void clearSp() {
        mSharedPreferences.edit().clear().commit();
    }
}

