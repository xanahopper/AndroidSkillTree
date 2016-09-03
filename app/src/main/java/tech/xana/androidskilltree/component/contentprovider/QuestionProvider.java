package tech.xana.androidskilltree.component.contentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by Xana Hopper on 2016/8/15.
 *
 */
public class QuestionProvider extends ContentProvider {
    private static final UriMatcher matcher;

    private static final String AUTHORITY = "tech.xana.androidskilltree.QuestionProvider";
    private static final int QUESTION_ALL = 0;
    private static final int QUESTION_ONE = 1;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);


    }
    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
