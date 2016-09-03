package tech.xana.androidskilltree.component.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import tech.xana.androidskilltree.R;

/**
 * Created by Xana Hopper on 2016/8/11.
 *
 */
public class BroadcastReceiverFragment extends Fragment implements View.OnClickListener {
    private View mRoot = null;
    private LocalReceiver mLocalReceiver = new LocalReceiver();
    private IntentFilter mFilter;
    private boolean mRegistered = false;

    private Button mRegisterLocalReceiver;
    private Button mSendLocalBroadcast;

    private EditText mBroadcastInfo;

    private static final String DEMO_BROADCAST_TAG = "tech.xana.androidskilltree";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.broadcast_fragment, container, false);
        mRoot = view;

        mFilter = new IntentFilter();
        mFilter.addAction(DEMO_BROADCAST_TAG);

        mRegisterLocalReceiver = (Button) view.findViewById(R.id.register_local_receiver);
        mSendLocalBroadcast = (Button) view.findViewById(R.id.send_local_broadcast);

        mBroadcastInfo = (EditText) view.findViewById(R.id.broadcast_info);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mRegisterLocalReceiver.setOnClickListener(this);
        mSendLocalBroadcast.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRegistered)
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(mLocalReceiver);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_local_receiver:
                LocalBroadcastManager.getInstance(getContext()).registerReceiver(mLocalReceiver, mFilter);
                mRegistered = true;
                Snackbar.make(mRoot, "注册成功", Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.send_local_broadcast:
                Intent intent = new Intent(DEMO_BROADCAST_TAG);
                if (mBroadcastInfo.length() > 0) {
                    intent.putExtra("Content", mBroadcastInfo.getText().toString());
                } else {
                    intent.putExtra("Content", "本地信息！");
                }
                LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
                break;
        }
    }


    public class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("Content")) {
                Snackbar.make(mRoot, "接受到广播：" + intent.getStringExtra("Content"), Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
