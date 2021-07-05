package com.knox.aop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.knox.myaspectj.MyAspectjAnnotation;
import com.knox.myaspectj.MyAspectjResult;

public class MainActivity extends AppCompatActivity {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView helloWorld = findViewById(R.id.tv_hello);
    helloWorld.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View v) {
             testRun();
            // testRun2();
          }
        });
  }

  @MyAspectjAnnotation
  private MyAspectjResult testRun() {
    MyAspectjResult report = new MyAspectjResult(1);
    Log.d(TAG, "testRun: 1=" + report);
    return report;
  }

  // private VerifierReport testRun2() {
  //  VerifierReport verifierReport = new VerifierReport(2);
  //  Log.d(TAG, "testRun2: 2=" + verifierReport);
  //  return verifierReport;
  // }
}
