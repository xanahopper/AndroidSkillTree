package tech.xana.androidskilltree.component;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tech.xana.androidskilltree.common.CommonActivity;

/**
 * Created by Xana Hopper on 2016-08-07.
 */
public class ComponentActivity extends CommonActivity {
    private ComponentFragment mComponentFragment;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mComponentFragment = new ComponentFragment();
        setFragment(mComponentFragment);
        setHasParent(true);
    }
}
