package tech.xana.androidskilltree.component.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import tech.xana.androidskilltree.common.CommonActivity;

/**
 * Created by Xana Hopper on 2016-08-05.
 */
public class LifeCycleActivity extends CommonActivity {
    public static final String TAG = "LifeCycleActivity";

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestory");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i(TAG, "onCreate");
        setHasParent(true);
    }
}
