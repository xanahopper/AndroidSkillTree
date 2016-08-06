package tech.xana.androidskilltree.component.service;

import android.os.Bundle;
import android.support.annotation.Nullable;

import tech.xana.androidskilltree.common.CommonActivity;

/**
 * Created by Xana Hopper on 2016-08-05.
 */
public class ServiceActivity extends CommonActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFragment(new ServiceFragment());
    }
}
