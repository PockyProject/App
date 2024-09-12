package com.example.pocky.presentation.screen.main.frgment.favor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.domain.model.favor.FavorDTO;

import java.util.ArrayList;

public class FavorRecycerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<FavorDTO> arr;

    public FavorRecycerViewAdapter(ArrayList<FavorDTO> list){
        arr = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favor_recycerview,parent,false);
        FavorRecycerViewAdapter.ViewHolder viewHolder = new FavorRecycerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //position
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
