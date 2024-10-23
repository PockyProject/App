package com.example.pocky.presentation.screen.main.frgment.feed;

import android.annotation.SuppressLint;
import android.nfc.Tag;
import android.util.Log;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.domain.model.feed.FeedData;

import java.util.List;
import java.util.Objects;

public class FeedAdapter extends ListAdapter<FeedData,FeedAdapter.ViewHolder> {

    private List<FeedData> feedList;
    private static OnItemClickListener listener; // 받아올 리스너 객체

    public interface OnItemClickListener { // 인터페이스 생성
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) { // 받아온 리스너를 설정하는 메서드
        this.listener = listener;
    }

    public FeedAdapter(OnItemClickListener listener){
        super(FeedDiffUtil);
        FeedAdapter.listener = listener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed_recycerview, parent, false);
        return new ViewHolder(view, listener); // listener 전달
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       FeedData feedData = getItem(position);
        holder.bind(feedData);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuNameTextView;
        private final TextView titleTextView;
        private final ImageView profileImageView;

        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);

            menuNameTextView = itemView.findViewById(R.id.feed_menuNameNtext);
            titleTextView = itemView.findViewById(R.id.feed_titeText);
            profileImageView = itemView.findViewById(R.id.feed_profileImage);


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
        public void bind(FeedData feedData){
            menuNameTextView.setText(feedData.getContent());
            titleTextView.setText(feedData.getTitle());
            Log.d("123",String.valueOf(feedData.getMenuImage()));

            Glide.with(itemView)
                    .load(feedData.getMenuImage())
                    .circleCrop()
                    .into(profileImageView);
        }
        }

    // DiffUtil 정의
    public static final DiffUtil.ItemCallback<FeedData> FeedDiffUtil = new DiffUtil.ItemCallback<FeedData>() {

        @Override
        public boolean areItemsTheSame(@NonNull FeedData oldItem, @NonNull FeedData newItem) {
            // 각 항목의 고유성을 비교 (age 기준으로 동일 여부 판단)
            return Objects.equals(oldItem.getFeedUid(), newItem.getFeedUid());
        }

        @Override
        public boolean areContentsTheSame(@NonNull FeedData oldItem, @NonNull FeedData newItem) {
            // 항목의 내용이 동일한지 확인 (equals 메서드로 비교)
            return oldItem.equals(newItem);
        }
    };

    protected FeedAdapter(@NonNull DiffUtil.ItemCallback<FeedData> diffCallback) {
        super(diffCallback);
    }

    protected FeedAdapter(@NonNull AsyncDifferConfig<FeedData> config) {
        super(config);
    }
}

