package com.knox.memory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.knox.memory.R;
import com.knox.memory.clazz.ByteArr2Clazz;
import com.knox.memory.clazz.EmptyClazz;
import com.knox.memory.clazz.IntClazz;
import com.knox.memory.clazz.IntegerClazz;
import com.knox.memory.clazz.ListClazz;
import com.knox.memory.clazz.MapClazz;
import com.knox.memory.clazz.MoreIntClazz;
import com.knox.memory.util.ContextClazz;

public class NextActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "NextActivity";

    EmptyClazz    mNormalClazz   = new EmptyClazz();
    IntClazz      mIntClazz      = new IntClazz();
    IntegerClazz  mIntegerClazz  = new IntegerClazz();
    ListClazz     mListClazz     = new ListClazz();
    ByteArr2Clazz mByteArr2Clazz = new ByteArr2Clazz();
    MapClazz      mMapClazz      = new MapClazz();
    MoreIntClazz  mMoreIntClazz  = new MoreIntClazz();
    private TextView mViewById;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        ContextClazz.setContext(this);

        mViewById = findViewById(R.id.tv_next);
        mViewById.setText("next");
        mViewById.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(NextActivity.this, ThirdActivity.class);
        startActivity(intent);
    }
}
