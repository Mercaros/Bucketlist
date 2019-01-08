package adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

import hva.bucketlist.R;
import models.BucketList;
import viewmodels.BucketListViewModel;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListAdapter.MyViewHolder> {
    private List<BucketList> bucketLists;
    private final OnItemClickListener listener;
    private BucketListViewModel bucketListViewModel;
    private Context context;

    public interface OnItemClickListener {
        void onItemClick(BucketList bucketList);
    }

    public BucketListAdapter(List<BucketList> bucketLists, OnItemClickListener listener) {
        this.bucketLists = bucketLists;
        this.listener = listener;
        bucketListViewModel = new BucketListViewModel(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int i) {
        final BucketList bucketList = bucketLists.get(i);
        holder.title.setText(bucketList.getTitle());
        holder.description.setText(bucketList.getDescription());
        holder.bind(bucketLists.get(i), listener);

        if (bucketList.isChecked()) {
            holder.checkBox.setChecked(true);
            holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.description.setPaintFlags(holder.description.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.checkBox.setChecked(false);
            holder.title.setPaintFlags(holder.title.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
            holder.description.setPaintFlags(holder.description.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));

        }



        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.checkBox.isChecked()) {
                    bucketList.setChecked(true);
                } else {
                    bucketList.setChecked(false);
                }
                updateBucket(bucketList);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bucketLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;
        public TextView title;
        public TextView description;
        ;

        public MyViewHolder(View view) {
            super(view);
            checkBox = view.findViewById(R.id.checkbox);
            title = view.findViewById(R.id.title);
            description = view.findViewById(R.id.description);
        }

        public void bind(final BucketList bucketList, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(bucketList);
                }
            });
        }
    }

    public void swapList(List<BucketList> newList) {
        bucketLists = newList;
        if (newList != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }

    private void updateBucket(BucketList bucketList) {
        bucketListViewModel.update(bucketList);
    }

}
