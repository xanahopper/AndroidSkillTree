package tech.xana.androidskilltree.component.broadcastreceiver;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tech.xana.androidskilltree.common.CommonActivity;

/**
 * Created by Xana Hopper on 2016/8/11.
 *
 */
public class BroadcastReceiverActivity extends CommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(new BroadcastReceiverFragment());
        setHasParent(true);
    }
}
