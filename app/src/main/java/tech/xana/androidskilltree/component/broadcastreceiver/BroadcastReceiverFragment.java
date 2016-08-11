package tech.xana.androidskilltree.component.broadcastreceiver;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tech.xana.androidskilltree.R;

/**
 * Created by Xana Hopper on 2016/8/11.
 *
 */
public class BroadcastReceiverFragment extends Fragment implements View.OnClickListener {
    private View mRoot = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.broadcast_fragment, container, false);
        mRoot = view;
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View view) {

    }
}
