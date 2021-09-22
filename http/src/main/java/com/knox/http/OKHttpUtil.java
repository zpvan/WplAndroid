package com.knox.http;

import android.util.Log;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

class OKHttpUtil {

  private static final String TAG = "OKHttpUtil";

  /**
   * OKHttp
   * 1. 结构
   * - OkHttpClient
   *   - 可配置项很多, 构造器设计模式
   * - Request
   * - RealCall
   *   - 同步请求 response = execute()
   *   - 异步请求 enqueue(callback(response))，内置ExecutorService
   * - Dispatcher
   * - Interceptor
   *   - 责任链模式，默认有5步拦截
   *     - 1. RetryAndFollowUpInterceptor 负责失败重试以及重定向
   *     - 2. BridgeInterceptor 请求时，对必要的Header进行一些添加，接收响应时，移除必要的Header
   *     - 3. CacheInterceptor 负责读取缓存直接返回、更新缓存
   *     - 4. ConnectInterceptor 负责和服务器建立连接
   *     - 5. CallServerInterceptor 负责向服务器发送请求数据、从服务器读取响应数据
   * - Response
   *
   * 2. 请求流程
   * - 同步请求流程
   * - 异步请求流程
   *
   * 3. 网络请求缓存处理 - CacheInterceptor
   *
   * 4. 连接池 - ConnectInterceptor
   *
   */
  static void requestSync(String url) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();

    try (Response response = client.newCall(request).execute()) {
      Log.i(TAG, response.body().string());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  static void requestAsync(String url) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder().url(url).build();

    client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        Log.e(TAG, "requestAsync onFailure");
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        Log.i(TAG, "requestAsync response: " + response.body().string());
      }
    });
  }
}
