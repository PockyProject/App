package com.example.pocky.presentation.screen.order.breakfast;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.List;

public class BreakFastAdapter  extends RecyclerView.Adapter<BreakFastAdapter.BreakFastViewHolder> {

    private final OnItemClickListener listener;
    private List<Integer> imageArr;
    private List<String> nameArr;
    private int selectedPosition = RecyclerView.NO_POSITION; // 처음에는 선택된 아이템 없음

    // 클릭 이벤트를 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v,int imageResId, String menuName); // 클릭된 이미지 리소스 ID 전달
    }

    // 생성자에서 데이터와 리스너를 받아옴
    public BreakFastAdapter(List<Integer> menuImage,List<String> menuName, OnItemClickListener listener) {
        this.imageArr = menuImage;
        this.nameArr = menuName;
        this.listener = listener;
    }


    @NonNull
    @Override
    public BreakFastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_breakfast, parent, false);
        return new BreakFastViewHolder(view);
    }


    @Override
    public void onBindViewHolder(BreakFastViewHolder holder, int position) {

        boolean isSelected = position == selectedPosition; // 현재 아이템이 선택된 아이템인지 확인

        //메뉴 이미지 설정
        Glide.with(holder.itemView.getContext())
                .load(imageArr.get(position))
                .into(holder.imageView);

        //클릭 되었는지 안되어있는지
        holder.bind(isSelected);

        //메뉴 이름 설정
        holder.textView.setText(nameArr.get(position));
    }

    @Override
    public int getItemCount() {
        return nameArr.size(); // 메뉴 이미지랑 메뉴 이름 갯수 동일해서 상관 X
    }

    // ViewHolder 클래스
    class BreakFastViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public BreakFastViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.LongImageView);
            textView = itemView.findViewById(R.id.breakFast_TextView);

            // 아이템 클릭 리스너 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    // 유효한 포지션인지 확인
                    if (position != RecyclerView.NO_POSITION) {
                        // 클릭된 아이템의 이미지 리소스 ID를 리스너로 전달
                        listener.onItemClick(v, imageArr.get(position),nameArr.get(position));

                        // 이전 선택된 아이템을 업데이트
                        notifyItemChanged(selectedPosition);

                        // 현재 선택된 아이템 위치 저장
                        selectedPosition = position;

                        // 현재 선택된 아이템을 업데이트
                        notifyItemChanged(selectedPosition);
                    }
                }
            });
        }

        public void bind(Boolean isSelected){
            // 선택 상태에 따라 테두리 색상 변경
            if (isSelected) {
                itemView.setBackgroundResource(R.drawable.selectedvalue); // 선택된 아이템 테두리
            } else {
                itemView.setBackgroundResource(R.drawable.defaultselected); // 기본 아이템 테두리
            }
        }
    }

}
