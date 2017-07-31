package com.tomclaw.minion.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.tomclaw.minion.Minion;
import com.tomclaw.minion.ResultCallback;
import com.tomclaw.minion.storage.MemoryStorage;
import com.tomclaw.minion.storage.StringStorage;
import com.tomclaw.minion.storage.Writable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ResultCallback callback = new ResultCallback() {

            @Override
            public void onReady(Minion minion) {

            }

            @Override
            public void onFailure(Exception ex) {

            }
        };

        Minion.lets()
                .load(StringStorage.create("[group]\nkey=value\nkey2=value2"))
                .async(callback);

        Minion minion = Minion.lets()
                .load(StringStorage.create("[group]\nkey=value\nkey2=value2"))
                .sync();

        Writable storage = MemoryStorage.create();
        Minion.lets()
                .store(storage)
                .sync();
        minion.setValue("settings", "lang", "ru,en");
        minion.store();
        Log.d("~@~", storage.toString());
//
//        MemoryStorage memoryStorage = MemoryStorage.create();
//        Minion.lets()
//                .load(memoryStorage)
//                .and()
//                .store(memoryStorage)
//                .sync(callback);
    }
}
