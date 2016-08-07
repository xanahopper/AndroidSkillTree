package tech.xana.androidskilltree.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import tech.xana.androidskilltree.R;

public class CommonActivity extends AppCompatActivity {
    protected Toolbar mToolbar;

    //    protected RelativeLayout mMainContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }

//        mMainContainer = (RelativeLayout) findViewById(R.id.main_container);
    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.main_container, fragment)
                .commit();
    }

    private boolean mHasParent = false;


    public boolean isHasParent() {
        return mHasParent;
    }

    public void setHasParent(boolean hasParent) {
        mHasParent = hasParent;
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mHasParent) this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
