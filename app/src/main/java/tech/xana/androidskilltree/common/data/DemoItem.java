package tech.xana.androidskilltree.common.data;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Xana Hopper on 2016-08-05.
 */
public class DemoItem {
    private int mIconId;
    private String mName;
    private String mDescription;
    private DemoInfo mInfo;
    private int mColorId;
    private Class<? extends AppCompatActivity> mActivityClass;

    public int getIconId() {
        return mIconId;
    }

    public void setIconId(int mIconId) {
        this.mIconId = mIconId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String Name) {
        this.mName = mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String Description) {
        this.mDescription = mDescription;
    }

    public Class<? extends AppCompatActivity> getActivityClass() {
        return mActivityClass;
    }

    public void setActivityClass(Class<? extends AppCompatActivity> ActivityClass) {
        this.mActivityClass = mActivityClass;
    }

    public DemoItem(int mIconId, String mName, String mDescription, int colorId, Class<? extends AppCompatActivity> mActivityClass) {
        this.mIconId = mIconId;
        this.mName = mName;
        this.mDescription = mDescription;
        mColorId = colorId;
        this.mActivityClass = mActivityClass;
        this.mInfo = null;
    }

    public DemoInfo getInfo() {
        return mInfo;
    }

    public void setInfo(DemoInfo info) {
        mInfo = info;
    }

    public int getColorId() {
        return mColorId;
    }

    public void setColorId(int colorId) {
        mColorId = colorId;
    }
}
