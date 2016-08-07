package tech.xana.androidskilltree.component;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by Xana Hopper on 2016-08-07.
 */
public class ComponentFragment extends Fragment {
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

    private static final int[] Icons = {-1, -1};
    private static final String[] Names = {"Activity生命周期", "Service生命周期"};
    private static final String[] Descriptipons = {"控制台输出", "控制台&Toast输出"};
    private static final int[] Colors = {android.R.color.darker_gray, R.color.textSecondary};
    private static final Class[] ActivityClass = {LifeCycleActivity.class, ServiceActivity.class};
    private static final DemoInfo[] Infos = {null, null};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mItems = new ArrayList<>();
        for (int i = 0; i < Names.length; ++i) {
            Class<? extends AppCompatActivity> c = (Class<? extends AppCompatActivity>) ActivityClass[i];
            DemoItem item = new DemoItem(Icons[i], Names[i], Descriptipons[i], Colors[i], c);
            if (Infos[i] != null) item.setInfo(Infos[i]);
            mItems.add(item);
        }

        mDemoAdapter = new DemoAdapter(getContext(), mItems);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mDemoAdapter);
    }
}
