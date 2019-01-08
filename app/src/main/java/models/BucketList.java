package models;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

@Entity(tableName = "bucketList")
public class BucketList implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private Long id;
    private String title;
    private String description;
    private boolean isChecked = false;

    public BucketList() {
    }

    protected BucketList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        description = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<BucketList> CREATOR = new Creator<BucketList>() {
        @Override
        public BucketList createFromParcel(Parcel in) {
            return new BucketList(in);
        }

        @Override
        public BucketList[] newArray(int size) {
            return new BucketList[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "BucketList{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isChecked=" + isChecked +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeByte((byte) (isChecked ? 1 : 0));
    }
}
