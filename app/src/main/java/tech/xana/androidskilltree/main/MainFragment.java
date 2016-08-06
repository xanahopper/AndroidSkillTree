package tech.xana.androidskilltree.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import tech.xana.androidskilltree.R;
import tech.xana.androidskilltree.common.data.DemoAdapter;
import tech.xana.androidskilltree.common.data.DemoInfo;
import tech.xana.androidskilltree.common.data.DemoItem;
import tech.xana.androidskilltree.component.activity.LifeCycleActivity;
import tech.xana.androidskilltree.component.service.ServiceActivity;

public class MainFragment extends Fragment {
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        setRetainInstance(true);
        return view;
    }

    private List<DemoItem> mItems;
    private DemoAdapter mDemoAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mItems = new ArrayList<>();

        DemoItem item = new DemoItem(-1, "Activity生命周期", "控制台输出", LifeCycleActivity.class);
        item.setInfo(new DemoInfo());
        mItems.add(item);

        item = new DemoItem(-1, "Service生命周期", "控制台&Toast输出", ServiceActivity.class);
//        item.setInfo(new DemoInfo());
        mItems.add(item);

        mDemoAdapter = new DemoAdapter(getContext(), mItems);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mDemoAdapter);
    }
}
