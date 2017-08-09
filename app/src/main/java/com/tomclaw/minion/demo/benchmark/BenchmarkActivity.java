package com.tomclaw.minion.demo.benchmark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.tomclaw.minion.Minion;
import com.tomclaw.minion.demo.R;
import com.tomclaw.minion.demo.utils.TaskExecutor;
import com.tomclaw.minion.storage.MemoryStorage;
import com.tomclaw.minion.storage.Writable;

/**
 * Created by solkin on 01.08.17.
 */
public class BenchmarkActivity extends AppCompatActivity implements BenchmarkTask.BenchmarkCallback {

    private BenchmarkRecyclerAdapter adapter;
    private Minion minion;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benchmark);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(R.string.benchmark);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        adapter = new BenchmarkRecyclerAdapter(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        prepareMinion();
        startBenchmark();
    }

    private void prepareMinion() {
        Writable writable = MemoryStorage.create();
        minion = Minion
                .lets()
                .store(writable)
                .sync();
    }

    private void startBenchmark() {
        TaskExecutor
                .getInstance()
                .execute(new GroupCreationBenchmarkTask(minion, adapter, this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onComplete(int id) {
        switch (id) {
            case 0x01:
                TaskExecutor
                        .getInstance()
                        .execute(new ItemsCreationBenchmarkTask(minion, adapter, this));
                break;
        }
    }
}