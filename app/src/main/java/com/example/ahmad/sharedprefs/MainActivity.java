package com.example.ahmad.sharedprefs;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private int mCount=0;
private int mColor;
private TextView mShowCountTextView;
private final String COUNT_KEY="count";
private final String COLOR_KEY="color";
private SharedPreferences mPreferences;
private static final String mSharedPrefFile="net.sundevs.denis.pertemuan10_starter.MainActivity";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCountTextView=(TextView)findViewById(R.id.count_textview);
        mPreferences=getSharedPreferences(mSharedPrefFile,MODE_PRIVATE);
        mCount=mPreferences.getInt(COUNT_KEY,0);
        mShowCountTextView.setText(String.format("%s",mCount));
        mColor=mPreferences.getInt(COLOR_KEY,mColor);
        mShowCountTextView.setBackgroundColor(mColor);
    }
    public void changeBackground(View view){
    int color=((ColorDrawable)view.getBackground()).getColor();
    mShowCountTextView.setBackgroundColor(color);
    mColor=color;
    }
    public void countUp(View view){
        mCount++;
        mShowCountTextView.setText(String.format("%s",mCount));
    }
    @Override
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT_KEY,mCount);
        outState.putInt(COLOR_KEY,mColor);
    }
    @Override
    protected void onPause(){
        super.onPause();
        SharedPreferences.Editor preferenceEditor=mPreferences.edit();
        preferenceEditor.putInt(COLOR_KEY,mColor);
        preferenceEditor.putInt(COUNT_KEY,mCount);
        preferenceEditor.apply();
    }
    public void reset(View view){
        mCount=0;
        mShowCountTextView.setText(String.format("%s",mCount));
        mColor= ContextCompat.getColor(this,R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);
        SharedPreferences.Editor preferenceEditor=mPreferences.edit();
        preferenceEditor.clear();
        preferenceEditor.apply();
    }
}
