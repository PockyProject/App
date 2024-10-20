package com.example.pocky.presentation.screen.main.frgment.feed;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {
    private List<Integer> feedImg;
    private OnItemClickListener listener; // 받아올 리스너 객체

    public interface OnItemClickListener { // 인터페이스 생성
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { // 받아온 리스너를 설정하는 메서드
        this.listener = listener;
    }

    public FeedAdapter(List<Integer> feedImage) {
        this.feedImg = feedImage;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setArr(List<Integer> img) {
        this.feedImg = img;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        return new ViewHolder(view, listener); // listener 전달
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        Glide.with(holder.itemView.getContext())
                .load(feedImg.get(position))
                .error(R.drawable.resize_bestpartyflatter)
                .into(holder.feedPhoto);
    }

    @Override
    public int getItemCount() {
        return feedImg.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView feedPhoto;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            feedPhoto = itemView.findViewById(R.id.feedview);

            itemView.setOnClickListener(new View.OnClickListener() { // 아이템 클릭 리스너 구현
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (listener != null) {
                        listener.onItemClick(v, pos);
                    }
                }
            });
        }
    }
}
