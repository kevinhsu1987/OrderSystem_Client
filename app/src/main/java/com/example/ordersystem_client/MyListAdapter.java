package com.example.ordersystem_client;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.innerViewHolder> {
    private Context mContext;
    private ArrayList<String> mNames;
    private ArrayList<String> mCosts;
    private ArrayList<String> mNums;

    private OnItemClickListener onItemClickListener;

    public MyListAdapter(Context context, ArrayList<String> mNames, ArrayList<String> mCosts, ArrayList<String> mNums) {
        this.mContext = context;
        this.mNames = mNames;
        this.mCosts = mCosts;
        this.mNums = mNums;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {this.onItemClickListener = onItemClickListener;}

    public interface OnItemClickListener{void onItemClick(View view, int position);}

    @Override
    public innerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_items, viewGroup, false);
        innerViewHolder holder = new innerViewHolder(view);
        holder.tName = view.findViewById(R.id.tName);
        holder.tCost = view.findViewById(R.id.tCost);
        holder.editText = view.findViewById(R.id.editText);
        return holder;
    }



    @Override
    public void onBindViewHolder(@NonNull final innerViewHolder viewHolder, final int i) {
        String name = mNames.get(i);
        String cost = mCosts.get(i);
        String nums;
        if(!mNums.isEmpty()) {
            nums = mNums.get(i);
            viewHolder.editText.setText(nums);
        }
        viewHolder.tName.setText(name);
        viewHolder.tCost.setText(cost);

        //mNums.add(viewHolder.editText);
        if(onItemClickListener!=null){

            viewHolder.tName.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                    onItemClickListener.onItemClick(viewHolder.editText, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mNames.size();
    }

    class innerViewHolder extends RecyclerView.ViewHolder {
        public TextView tName;
        public TextView tCost;
        public EditText editText;

        public innerViewHolder(View itemView) {
            super(itemView);
        }
    }

    public String getNamesByPosition(int pos){
        return mNames.get(pos);
    }
}

