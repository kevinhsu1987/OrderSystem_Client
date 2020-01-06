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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.innerViewHolder> {
    private Context mContext;
    private ArrayList<String> mNames;
    private ArrayList<String> mCosts;

    private OnItemClickListener onItemClickListener;

    public MyAdapter(Context context, ArrayList<String> mNames, ArrayList<String> mCosts) {
        this.mContext = context;
        this.mNames = mNames;
        this.mCosts = mCosts;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    @Override
    public innerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.items, viewGroup, false);
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
        viewHolder.tName.setText(name);
        viewHolder.tCost.setText(cost);
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

    public String getNamesByPosition(int position){
        return mNames.get(position);
    }
};

