package repository;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import db.AppDatabase;
import db.BucketListDao;
import models.BucketList;

public class BucketListRepository {
    private AppDatabase mAppDatabase;
    private BucketListDao bucketListDao;
    private LiveData<List<BucketList>> mBucketList;
    private Executor mExecutor = Executors.newSingleThreadExecutor();


    public BucketListRepository(Context context) {
        mAppDatabase = AppDatabase.getInstance(context);
        bucketListDao = mAppDatabase.bucketListDao();
        mBucketList = bucketListDao.getAll();
    }

    public LiveData<List<BucketList>> getAllBucketLists() {
        return mBucketList;
    }

    public void insert(final BucketList bucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.insert(bucketList);
            }
        });
    }

    public void update(final BucketList bucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.update(bucketList);
            }
        });
    }


    public void delete(final BucketList bucketList) {
        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                bucketListDao.delete(bucketList);
            }
        });
    }
}
