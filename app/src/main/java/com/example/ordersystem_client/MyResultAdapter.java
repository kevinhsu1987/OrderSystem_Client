package com.example.ordersystem_client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyResultAdapter extends RecyclerView.Adapter<MyResultAdapter.innerViewHolder> {
    private Context mContext;
    private ArrayList<String> mTypes;
    private ArrayList<String> mNames;
    private ArrayList<String> mCosts;
    private ArrayList<String> mNums;

    private OnItemClickListener onItemClickListener;

    public MyResultAdapter(Context context,ArrayList<String> mTypes, ArrayList<String> mNames, ArrayList<String> mCosts, ArrayList<String> mNums) {
        this.mContext = context;
        this.mTypes = mTypes;
        this.mNames = mNames;
        this.mCosts = mCosts;
        this.mNums = mNums;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public innerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.result_items, viewGroup, false);
        innerViewHolder holder = new innerViewHolder(view);
        holder.tType = view.findViewById(R.id.tType);
        holder.tName = view.findViewById(R.id.tName);
        holder.tCost = view.findViewById(R.id.tCost);
        holder.tNums = view.findViewById(R.id.tNums);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final innerViewHolder viewHolder, final int i) {
        String type = mTypes.get(i);
        String name = mNames.get(i);
        String cost = mCosts.get(i);
        String nums = mNums.get(i);
        viewHolder.tType.setText(type);
        viewHolder.tName.setText(name);
        viewHolder.tCost.setText(cost);
        viewHolder.tNums.setText(nums);
        if(onItemClickListener!=null){

            viewHolder.tName.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                    //onItemClickListener.onItemClick(viewHolder.editText, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    class innerViewHolder extends RecyclerView.ViewHolder {
        public TextView tType;
        public TextView tName;
        public TextView tCost;
        public TextView tNums;

        public innerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public String getNamesByPosition(int position){
        return mNames.get(position);
    }
}

