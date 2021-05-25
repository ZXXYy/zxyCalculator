package com.example.zxycalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ExprListAdapter extends
        RecyclerView.Adapter<ExprListAdapter.ExprViewHolder>  {

    private final LinkedList<String> mExprList;
    private final TextView textView;
    private LayoutInflater mInflater;

    class ExprViewHolder extends RecyclerView.ViewHolder
                                    implements View.OnClickListener{
        public final TextView exprItemView;
        final ExprListAdapter mAdapter;

        public ExprViewHolder(View itemView, ExprListAdapter adapter) {
            super(itemView);
            exprItemView = itemView.findViewById(R.id.expr);
            mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.
            String element = mExprList.get(mPosition);
            // Change the word in the mWordList.
            textView.setText(element);
            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }



    public ExprListAdapter(Context context,
                           LinkedList<String> wordList, TextView textView) {
        mInflater = LayoutInflater.from(context);
        this.mExprList = wordList;
        this.textView = textView;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ExprListAdapter.ExprViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.exprlist_item,
                parent, false);
        return new ExprViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull ExprListAdapter.ExprViewHolder holder, int position) {
        String mCurrent = mExprList.get(position);
        holder.exprItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return mExprList.size();
    }
}
