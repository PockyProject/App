package com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.pocky.R;
import com.example.pocky.domain.model.comment.CommentData;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorAdapter;
import com.example.pocky.presentation.screen.main.frgment.feed.feeddetail.FeedDetailViewModel;

import java.util.Objects;

public class CommentAdapter extends ListAdapter<CommentData, CommentAdapter.CommentViewHolder> {
    private static final String TAG = "CommentAdapter";
    private static FeedDetailViewModel viewModel = null;

    private static FavorAdapter.OnItemClickListener listener; // 클릭 이벤트를 전달할 리스너

    // 생성자
    public CommentAdapter(FeedDetailViewModel viewModel) { //OnItemClickListener listener
        super(CommentDiffUtil);
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog, parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        // 데이터 바인딩
        CommentData commentData = getItem(position);
        Log.d(TAG,"어댑터 : " + commentData.getContent());
        holder.bind(commentData);
    }

    // ViewHolder 정의
    public static class CommentViewHolder extends RecyclerView.ViewHolder {
        private final TextView userName;
        private final ImageView userImage;
        private final TextView userComment;
        private final ImageButton deleteBtn;


        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            userName = itemView.findViewById(R.id.userName);
            userImage = itemView.findViewById(R.id.userImage);
            userComment = itemView.findViewById(R.id.commentContent);
            deleteBtn = itemView.findViewById(R.id.commentDeleteBtn);

        }

        public void bind(CommentData commentData) {

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.deleteComment(commentData);
                }
            });


            if(!Objects.equals(commentData.getUserUid(), UserInfo.getInstance().getUserId())){
                itemView.findViewById(R.id.commentDeleteBtn).setVisibility(ViewGroup.GONE);
            }else{
                itemView.findViewById(R.id.commentDeleteBtn).setVisibility(ViewGroup.VISIBLE);
            }



            userName.setText(commentData.getWriter());
            userComment.setText(commentData.getContent());

            Glide.with(this.itemView)
                    .load(commentData.getWriterImage())
                    .circleCrop()
                    .apply(RequestOptions.bitmapTransform(new RoundedCorners(10)))
                    .into(userImage);

        }
    }

    // DiffUtil 정의
    public static final DiffUtil.ItemCallback<CommentData> CommentDiffUtil = new DiffUtil.ItemCallback<CommentData>() {

        @Override
        public boolean areItemsTheSame(@NonNull CommentData oldItem, @NonNull CommentData newItem) {

            // 각 항목의 고유성을 비교 (age 기준으로 동일 여부 판단)
            return Objects.equals(oldItem.getCommentUid(), newItem.getCommentUid());
        }

        @Override
        public boolean areContentsTheSame(@NonNull CommentData oldItem, @NonNull CommentData newItem) {
            // 항목의 내용이 동일한지 확인 (equals 메서드로 비교)
            return oldItem.equals(newItem);
        }
    };
}
