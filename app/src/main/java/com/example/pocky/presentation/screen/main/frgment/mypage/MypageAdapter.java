package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.user.UserInfo;

import java.util.Objects;

public class MypageAdapter extends ListAdapter<FeedData,MypageAdapter.ViewHolder> {

    private static OnItemClickListener listener; // 클릭 이벤트를 전달할 리스너

    public interface OnItemClickListener {
        void onItemClick(FeedData feedData); // 클릭된 아이템 데이터를 전달할 메서드
    }


    public MypageAdapter(OnItemClickListener listener){
        super(FeedDiffUtil);
        MypageAdapter.listener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed_recycerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedData feedData = getItem(position);
        holder.bind(feedData);

    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView menuNameTextView;
        private final TextView titleTextView;
        private final ImageView profileImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //뷰 요소 연결
            menuNameTextView = itemView.findViewById(R.id.feed_menuNameNtext);
            titleTextView = itemView.findViewById(R.id.feed_titeText);
            profileImageView = itemView.findViewById(R.id.feed_profileImage);

            // 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });

        }

        public void bind(FeedData feedData) {
            menuNameTextView.setText(feedData.getContent());
            titleTextView.setText(feedData.getTitle());

            Glide.with(itemView)
                    .load(UserInfo.getInstance().getProfileURl())
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

    protected MypageAdapter(@NonNull DiffUtil.ItemCallback<FeedData> diffCallback) {
        super(diffCallback);
    }

    protected MypageAdapter(@NonNull AsyncDifferConfig<FeedData> config) {
        super(config);
    }



}