package viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import models.BucketList;
import repository.BucketListRepository;

public class BucketListViewModel extends ViewModel {
    private BucketListRepository mRepository;
    private LiveData<List<BucketList>> mBucketList;

    public BucketListViewModel(Context context) {
        mRepository = new BucketListRepository(context);
        mBucketList = mRepository.getAllBucketLists();
    }

    public LiveData<List<BucketList>> getBucketLists() {
        return mBucketList;
    }

    public void insert(BucketList bucketList) {
        mRepository.insert(bucketList);
    }

    public void update(BucketList bucketList) {
        mRepository.update(bucketList);
    }

    public void delete(BucketList bucketList) {
        mRepository.delete(bucketList);
    }
}
