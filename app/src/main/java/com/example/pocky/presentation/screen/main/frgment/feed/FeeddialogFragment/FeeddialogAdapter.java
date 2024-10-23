package com.example.pocky.presentation.screen.main.frgment.feed.FeeddialogFragment;

import android.util.Log;
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
import com.example.pocky.domain.model.Comment.CommentData;


import java.util.List;
import java.util.Objects;

public class FeeddialogAdapter extends ListAdapter<CommentData,FeeddialogAdapter.ViewHolder> {

    private List<CommentData> commentList;
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        FeeddialogAdapter.listener = listener;
    }

    public FeeddialogAdapter(OnItemClickListener listener) {
        super(ViewHolder.CommentDiffUtil);
        FeeddialogAdapter.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_feeddialog, parent, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CommentData commentData = getItem(position);
        holder.bind(commentData);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nickNameTextView;
        private final TextView commentTextView;
        private final ImageView profileImageView;

        public ViewHolder(@NonNull View itemView, FeeddialogAdapter.OnItemClickListener listener) {
            super(itemView);

            nickNameTextView = itemView.findViewById(R.id.userName);
            commentTextView = itemView.findViewById(R.id.userComment);
            profileImageView = itemView.findViewById(R.id.userImg);


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

        public void bind(CommentData commentData) {
            nickNameTextView.setText(commentData.getWriter());
            commentTextView.setText(commentData.getCommentUid());
            Log.d("123", String.valueOf(commentData.getWriterImage()));

            Glide.with(itemView)
                    .load(commentData.getWriterImage())
                    .circleCrop()
                    .into(profileImageView);
        }


        // DiffUtil 정의
        public static final DiffUtil.ItemCallback<CommentData> CommentDiffUtil = new DiffUtil.ItemCallback<CommentData>() {

            @Override
            public boolean areItemsTheSame(@NonNull CommentData oldItem, @NonNull CommentData newItem) {
                // 각 항목의 고유성을 비교 (age 기준으로 동일 여부 판단)
                return Objects.equals(oldItem.getFeedUid(), newItem.getFeedUid());
            }

            @Override
            public boolean areContentsTheSame(@NonNull CommentData oldItem, @NonNull CommentData newItem) {
                // 항목의 내용이 동일한지 확인 (equals 메서드로 비교)
                return oldItem.equals(newItem);
            }
        };
    }
}