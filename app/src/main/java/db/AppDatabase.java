package db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import models.BucketList;

@Database(entities = {BucketList.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BucketListDao bucketListDao();

    private final static String DB_NAME = "bucketList_db";

    //Static instance
    private static AppDatabase INSTANCE;


    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, AppDatabase.class, DB_NAME).build();
        }
        return INSTANCE;
    }
}
