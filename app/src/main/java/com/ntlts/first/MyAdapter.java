package com.ntlts.first;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> mDataset;
    private OnTermClickListener mOnTermClickListener;

    //Constructor
    public MyAdapter(List<String> list, OnTermClickListener onTermClickListener){
        mDataset = list;
        this.mOnTermClickListener = onTermClickListener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listview_layout, parent,false);
        MyViewHolder vh = new MyViewHolder(v, mOnTermClickListener);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(mDataset.get(position)); // modified for list
    }

    @Override
    public int getItemCount() {
        return mDataset.size(); //modified for list
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
        implements View.OnClickListener {
        public TextView textView;
        OnTermClickListener onTermClickListener;

        public MyViewHolder(TextView v, OnTermClickListener onTermClickListener){
            super(v);
            textView = v;
            //listener
            this.onTermClickListener = onTermClickListener;
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onTermClickListener.onClick(getAdapterPosition());
        }
    }

    public interface OnTermClickListener{
        void onClick(int position);
    }
}
