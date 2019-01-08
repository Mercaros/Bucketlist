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

public class EditBucketListActivity  extends AppCompatActivity {
    private FloatingActionButton saveBucketListButton;
    private EditText title;
    private EditText description;
    private BucketListViewModel mBucketListViewModel;
    private BucketList updateBucketList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bucket_list);
        title = findViewById(R.id.titleInput);
        description = findViewById(R.id.descriptionInput);
        saveBucketListButton = findViewById(R.id.btn_save_bucketList);
        initValues();
        saveBucketListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString())) {
                    updateBucketList = new BucketList();
                    updateBucketList.setTitle(title.getText().toString());
                    updateBucketList.setDescription(description.getText().toString());

                    mBucketListViewModel = new BucketListViewModel(getApplicationContext());
                    mBucketListViewModel.update(updateBucketList);
                    Intent intent = new Intent(EditBucketListActivity.this, MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(EditBucketListActivity.this, "BucketList updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditBucketListActivity.this, "Empty Fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Init values and set the text
    private void initValues() {
        updateBucketList = getIntent().getParcelableExtra(MainActivity.TAG);
        title.setText(updateBucketList.getTitle());
        description.setText(updateBucketList.getDescription());
    }

}
