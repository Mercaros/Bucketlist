package hva.bucketlist.activities;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import adapters.BucketListAdapter;
import hva.bucketlist.R;
import models.BucketList;
import viewmodels.BucketListViewModel;

public class MainActivity extends AppCompatActivity {

    private List<BucketList> mBucketList;
    private BucketListAdapter bucketListAdapter;
    private RecyclerView recyclerView;
    private BucketListViewModel mBucketListViewModel;
    private FloatingActionButton fab;

    //Constants used when calling the update activity
    public static final String EXTRA_BUCKET = "Bucket";
    public static final int REQUESTCODE = 1234;
    private int mModifyPosition;
    public static final String TAG = "Bucket";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        fab = findViewById(R.id.btn_add_bucketList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBucketList = new ArrayList<>();
        mBucketListViewModel = new BucketListViewModel(getApplicationContext());
        mBucketListViewModel.getBucketLists().observe(this, new Observer<List<BucketList>>() {
            @Override
            public void onChanged(@Nullable List<BucketList> bucketLists) {
                mBucketList = bucketLists;
                updateUI();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddBucketListActivity.class);
                startActivity(intent);
            }
        });

        //Swipe to remove item
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder
                    target) {
                return false;
            }

            //Called when a user swipes left or right
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                //Get the index corresponding to the selected position
                int position = (viewHolder.getAdapterPosition());
                mBucketListViewModel.delete(mBucketList.get(position));
                Toast.makeText(MainActivity.this, "BucketList Removed", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void updateUI() {
        if (bucketListAdapter == null) {
            bucketListAdapter = new BucketListAdapter(mBucketList, new BucketListAdapter.OnItemClickListener() {
                //Called when a user clicks on a item
                @Override
                public void onItemClick(BucketList bucketList) {
                    Intent intent = new Intent(MainActivity.this, EditBucketListActivity.class);
                    intent.putExtra(TAG, bucketList);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(bucketListAdapter);
        } else {
            //Refresh list
            bucketListAdapter.swapList(mBucketList);
        }
    }
}
