package com.knox.http;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private static final String TAG = "MainActivity";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    findViewById(R.id.okhttp_btn).setOnClickListener(this);
    findViewById(R.id.retrofit_btn).setOnClickListener(this);
    findViewById(R.id.volley_btn).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.okhttp_btn) {
      Log.i(TAG, "do okhttp");

      final String url = "https://cn.bing.com/";
      final String url2 = "https://square.github.io/okhttp/";

      //Executors.newSingleThreadExecutor().submit(() -> OKHttpUtil.requestSync(url));
      OKHttpUtil.requestAsync(url);
    }
    if (v.getId() == R.id.retrofit_btn) {
      Log.i(TAG, "do retrofit");
    }
    if (v.getId() == R.id.volley_btn) {
      Log.i(TAG, "do volley");
    }
  }
}
