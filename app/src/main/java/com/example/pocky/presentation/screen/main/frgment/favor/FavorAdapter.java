package com.example.pocky.presentation.screen.main.frgment.favor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.domain.repository.Favor;

import java.util.Objects;

public class FavorAdapter extends ListAdapter<Favor, FavorAdapter.FavorViewHolder> {

    private static OnItemClickListener listener; // 클릭 이벤트를 전달할 리스너

    public interface OnItemClickListener {
        void onItemClick(Favor favor); // 클릭된 아이템 데이터를 전달할 메서드
    }


    // 생성자
    public FavorAdapter(OnItemClickListener listener) {
        super(FavorDiffUtil);
        FavorAdapter.listener = listener;
    }


    // ViewHolder 정의
    public class FavorViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuNameTextView;
        private final ImageView menuImageView;
        private final TextView breadTextView;
        private final TextView sauceTextView;
        private final TextView topingTextView;
        private final TextView sideTextView;
        private final TextView requidTextView;

        public FavorViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 요소 연결
            menuNameTextView = itemView.findViewById(R.id.menuText);
            menuImageView = itemView.findViewById(R.id.foodImageView);
            breadTextView = itemView.findViewById(R.id.bread);
            sauceTextView = itemView.findViewById(R.id.sauce);
            topingTextView = itemView.findViewById(R.id.toping);
            sideTextView = itemView.findViewById(R.id.side);
            requidTextView = itemView.findViewById(R.id.requid);
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

        public void bind(Favor favor) {
            // 데이터 바인딩
            menuNameTextView.setText(favor.getMenuName()); // 메뉴 이름 설정
            menuImageView.setImageResource(favor.getMenuImage()); // 메뉴 이미지 설정
            breadTextView.setText(favor.getBread()); // 빵  설정
            sauceTextView.setText(favor.getSauce()); // 소스 설정
            topingTextView.setText(favor.getToping()); // 토핑 설정
            sideTextView.setText(favor.getSide()); // 사이드 메뉴 설정
            requidTextView.setText(favor.getRequid()); // 기타 요청 사항 설정
        }
    }

    @NonNull
    @Override
    public FavorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favor_recycerview, parent, false);
        return new FavorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavorViewHolder holder, int position) {
        // 데이터 바인딩
        Favor favor = getItem(position);
        holder.bind(favor);
    }

    // DiffUtil 정의
    public static final DiffUtil.ItemCallback<Favor> FavorDiffUtil = new DiffUtil.ItemCallback<Favor>() {
        @Override
        public boolean areItemsTheSame(@NonNull Favor oldItem, @NonNull Favor newItem) {
            // 각 항목의 고유성을 비교 (age 기준으로 동일 여부 판단)
            return Objects.equals(oldItem.getFavorNumber(), newItem.getFavorNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Favor oldItem, @NonNull Favor newItem) {
            // 항목의 내용이 동일한지 확인 (equals 메서드로 비교)
            return oldItem.equals(newItem);
        }
    };
}
