package com.example.pocky.presentation.screen.main.frgment.feed.bottomsheet;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.domain.model.comment.CommentData;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorAdapter;
import com.example.pocky.presentation.screen.main.frgment.favor.FavorViewModel;

import java.util.Objects;

public class CommentAdapter extends ListAdapter<CommentData, CommentAdapter.CommentViewHolder> {
    private static final String TAG = "FavorAdapter";

    private static FavorAdapter.OnItemClickListener listener; // 클릭 이벤트를 전달할 리스너
    private FavorViewModel viewModel;
    private Boolean isFeed = false; // 재활용을 위한 UI상태 변수

    private int selectedPosition = RecyclerView.NO_POSITION; // 선택된 아이템 없음


    public interface OnItemClickListener {
        void onItemClick(Favor favor); // 클릭된 아이템 데이터를 전달할 메서드
    }


    // 생성자
    public CommentAdapter() { //OnItemClickListener listener
        super(CommentDiffUtil);
    }

    public void setIsFeed(Boolean isFeed){
        this.isFeed = isFeed;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favor_recycerview, parent, false);

        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        // 데이터 바인딩
        CommentData favor = getItem(position);
        boolean isSelected = position == selectedPosition; // 현재 아이템이 선택된 아이템인지 확인
        holder.bind(favor,isSelected);
    }

    // ViewHolder 정의
    public class CommentViewHolder extends RecyclerView.ViewHolder {

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);

            //피드에서 어댑터를 호출하면 즐겨찾기 삭제 버튼 비활성화
            if(isFeed){
                itemView.findViewById(R.id.cancelBtn).setVisibility(View.INVISIBLE);
            }
            // 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                    }
                }
            });
            itemView.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 현재 클릭된 포지션 가져오기
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {

                    }
                }
            });
        }

        public void bind(CommentData favor,Boolean isSelected) {

        }
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
