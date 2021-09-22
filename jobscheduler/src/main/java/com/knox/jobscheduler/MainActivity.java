package com.knox.jobscheduler;

import androidx.appcompat.app.AppCompatActivity;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 开启 JobScheduler
     */
    private void startJobScheduler() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
            JobInfo.Builder builder = new JobInfo.Builder(1,
                    new ComponentName(getPackageName(), JobService.class.getName()));
            // 设置仅在 充电和WIFI 下才使用 JobScheduler 进行批量任务处理
            builder.setRequiresCharging(true)
                    .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED);
            jobScheduler.schedule(builder.build());
        }
    }
}