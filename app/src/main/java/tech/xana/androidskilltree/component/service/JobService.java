package tech.xana.androidskilltree.component.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Xana Hopper on 2016-08-06.
 */
public class JobService extends IntentService {
    public JobService() {
        super("JobService");
    }

    public static final String JOB_NUMBER = "JobNumber";
    public static final String TAG = "JobService";

    @Override
    protected void onHandleIntent(Intent intent) {
        int number = 0;
        if (intent.hasExtra(JOB_NUMBER)) {
            number = intent.getIntExtra(JOB_NUMBER, 0);
        }
        long endTime = System.currentTimeMillis() + 3 * 1000;
        Log.i(TAG, String.format("Start a job %d", number));
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                    ;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.i(TAG, String.format("Finish job %d", number));
    }
}
