package hva.bucketlist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import hva.bucketlist.R;
import models.BucketList;
import viewmodels.BucketListViewModel;

public class AddBucketListActivity extends AppCompatActivity {
    private FloatingActionButton saveBucketListButton;
    private EditText title;
    private EditText description;
    private BucketListViewModel mBucketListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket_list);
        title = findViewById(R.id.titleInput);
        description = findViewById(R.id.descriptionInput);
        saveBucketListButton = findViewById(R.id.btn_save_bucketList);
        saveBucketListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                    BucketList bucketList = new BucketList();
                    bucketList.setTitle(title.getText().toString());
                    bucketList.setDescription(description.getText().toString());

                    mBucketListViewModel = new BucketListViewModel(getApplicationContext());
                    mBucketListViewModel.insert(bucketList);
                    Intent intent = new Intent(AddBucketListActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(AddBucketListActivity.this, "BucketList added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddBucketListActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}