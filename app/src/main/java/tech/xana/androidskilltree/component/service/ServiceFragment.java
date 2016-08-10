package tech.xana.androidskilltree.component.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


import tech.xana.androidskilltree.R;

/**
 * Created by Xana Hopper on 2016-08-05.
 *
 * Service Fragment for ServiceActivity.
 */
public class ServiceFragment extends Fragment implements View.OnClickListener {
    private Button mStartServiceButton;
    private Button mStopServiceButton;

    private Button mBindServiceButton;
    private Button mUnbindServiceButton;
    private Button mPlayButton;
    private Button mStopButton;
    private Button mStartIntentService;

    private Button mCallBoundService;
    private BoundService mBoundService;

    private Button mCallMessengerService;
    private Messenger mMessengerService = null;
    private MessengerConnection mMessengerConnection = new MessengerConnection();

    private Intent demoService;
    private DemoService.IDemoBinder mDemoBinder;

    private View mRoot = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment, container, false);

        mStartServiceButton = (Button) view.findViewById(R.id.start_service);
        mStopServiceButton = (Button) view.findViewById(R.id.stop_service);

        mBindServiceButton = (Button) view.findViewById(R.id.bind_service);
        mUnbindServiceButton = (Button) view.findViewById(R.id.unbind_service);
        mPlayButton = (Button) view.findViewById(R.id.service_play);
        mStopButton = (Button) view.findViewById(R.id.service_stop);

        mStartIntentService = (Button) view.findViewById(R.id.start_intent_service);
        mCallBoundService = (Button) view.findViewById(R.id.call_bound_service);
        mCallMessengerService = (Button) view.findViewById(R.id.call_messenger_service);
        mRoot = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mStartServiceButton.setOnClickListener(this);
        mStopServiceButton.setOnClickListener(this);

        mBindServiceButton.setOnClickListener(this);
        mUnbindServiceButton.setOnClickListener(this);

        mPlayButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);

        mStartIntentService.setOnClickListener(this);

        mCallBoundService.setOnClickListener(this);
        mCallMessengerService.setOnClickListener(this);

        demoService = new Intent(getContext(), DemoService.class);
    }

    @Override
    public void onDestroy() {
        if (mDemoBinder != null)
            getContext().unbindService(mConnection);
        if (mBoundService != null) {
            getContext().unbindService(mBoundConnection);
        }
        if (mMessengerService != null) {
            getContext().unbindService(mMessengerConnection);
        }
        super.onDestroy();
    }

    private DemoConnection mConnection = new DemoConnection();
    private BoundConnection mBoundConnection = new BoundConnection();

    public static int JobNumber = 0;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_service:
                getContext().startService(demoService);
                break;
            case R.id.stop_service:
                getContext().stopService(demoService);
                break;
            case R.id.bind_service:
                getContext().bindService(demoService, mConnection, Context.BIND_AUTO_CREATE);
                mUnbindServiceButton.setEnabled(true);
                break;
            case R.id.unbind_service:
                getContext().unbindService(mConnection);
                mUnbindServiceButton.setEnabled(false);
                mDemoBinder = null;
                mPlayButton.setEnabled(false);
                mStopButton.setEnabled(false);
                break;
            case R.id.service_play:
                mDemoBinder.play("Android的心");
                break;
            case R.id.service_stop:
                mDemoBinder.stop();
                break;
            case R.id.start_intent_service:
                Intent job = new Intent(getContext(), JobService.class);
                job.putExtra(JobService.JOB_NUMBER, JobNumber++);
                getContext().startService(job);
                break;
            case R.id.call_bound_service:
                if (mBoundService == null) {
                    Intent bound = new Intent(getContext(), BoundService.class);
                    getContext().bindService(bound, mBoundConnection, Context.BIND_AUTO_CREATE);
                } else {
                    mBoundService.getNumber();
                }
                break;
            case R.id.call_messenger_service:
                if (mMessengerService == null) {
                    Intent msIntent = new Intent(getContext(), MessengerService.class);
                    getContext().bindService(msIntent, mMessengerConnection, Context.BIND_AUTO_CREATE);
                } else {
                    Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
                    try {
                        mMessengerService.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    private class BoundConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) iBinder;
            mBoundService = binder.getService();
            mBoundService.getNumber();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    private class DemoConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mDemoBinder = (DemoService.IDemoBinder) iBinder;
            mPlayButton.setEnabled(true);
            mStopButton.setEnabled(true);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mDemoBinder = null;
            Log.i("ServiceFragment", "Service Disconnected.");
            mPlayButton.setEnabled(false);
            mStopButton.setEnabled(false);
        }
    }

    public static final int MSG_SHOW_SNACK = 1;

    private class MsgHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_SHOW_SNACK:
                    Snackbar.make(mRoot, msg.getData().getString("data"), Snackbar.LENGTH_SHORT).show();
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private class MessengerConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            mMessengerService = new Messenger(service);

            Message msg = Message.obtain(null, MessengerService.MSG_SAY_HELLO, 0, 0);
            msg.replyTo = new Messenger(new MsgHandler());
            try {
                mMessengerService.send(msg);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mMessengerService = null;
        }
    }
}
