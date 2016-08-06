package tech.xana.androidskilltree.main;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tech.xana.androidskilltree.common.CommonActivity;

/**
 * Created by Xana Hopper on 2016-08-04.
 */
public class MainActivity extends CommonActivity {
    private MainFragment mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainFragment = new MainFragment();
        setFragment(mainFragment);
    }
}
