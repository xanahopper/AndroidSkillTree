package tech.xana.androidskilltree.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.util.Random;

public class BoundService extends Service {
    private final Random mGenerator = new Random();
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public int getNumber() {
        int x = mGenerator.nextInt();
        Toast.makeText(this, String.format("Random: %d", x), Toast.LENGTH_SHORT).show();
        return x;
    }
}
