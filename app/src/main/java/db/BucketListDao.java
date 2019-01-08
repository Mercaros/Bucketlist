package db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import models.BucketList;

@Dao
public interface BucketListDao {
    @Query("SELECT * FROM bucketList")
    public LiveData<List<BucketList>> getAll();

    @Insert
    public void insert(BucketList bucketList);

    @Delete
    public void delete(BucketList bucketList);

    @Update
    public void update(BucketList bucketList);
}
