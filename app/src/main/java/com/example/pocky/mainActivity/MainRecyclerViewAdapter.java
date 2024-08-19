package com.example.pocky.mainActivity;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    List<String> menuName;
    List<Integer> menuImg;

    @SuppressLint("NotifyDataSetChanged")
    public void setArr(List<String> name, List<Integer> img) {
       this.menuName = name;
       this.menuImg = img;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.menuName.setText(menuName.get(position));
        holder.menuPhoto.setImageResource(menuImg.get(position));
    }

    @Override
    public int getItemCount() {
        return menuName != null ? menuName.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        ImageView menuPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuPhoto = itemView.findViewById(R.id.foodImageView1);
            menuName = itemView.findViewById(R.id.foodTitleText);
        }
    }
}
