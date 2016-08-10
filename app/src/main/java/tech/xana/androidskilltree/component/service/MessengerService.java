package tech.xana.androidskilltree.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Xana Hopper on 2016-08-10.
 *
 * Service communication using Messenger.
 */
public class MessengerService extends Service {
    public static final int MSG_SAY_HELLO = 1;

    class ImcomingHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if (mReplyMessenger == null && msg.replyTo != null) mReplyMessenger = msg.replyTo;
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    if (mReplyMessenger != null) {
                        Message reMsg = Message.obtain(this, ServiceFragment.MSG_SHOW_SNACK, 0, 0);
                        Bundle data = new Bundle();
                        data.putString("data", "MessagenerService finished MSG_SAY_HELLO.");
                        reMsg.setData(data);
                        try {
                            mReplyMessenger.send(reMsg);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Hello!", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private Messenger mReplyMessenger = null;

    final Messenger mMessenger = new Messenger(new ImcomingHandler());

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(), "MessengerService Binding", Toast.LENGTH_SHORT).show();
        return mMessenger.getBinder();
    }
}
