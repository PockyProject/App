package com.example.pocky.presentation.screen.order.common.souce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.ArrayList;
import java.util.List;

public class SouceAdapter extends RecyclerView.Adapter<SouceAdapter.SouceViewHolder> {

    private final OnItemClickListener listener;

    private final List<Integer> imageList;
    private final List<String> souceName;
    private final List<String> qrSouceName; //메뉴 객체에 저장할 영어 이름


    private final List<Integer> selectedPositions = new ArrayList<>(); // 선택된 포지션들 저장
    private static final int MAX_SELECTIONS = 3; // 최대 선택 가능 수

    public interface OnItemClickListener {
        void onItemClick(View v, int imageResId, String menuName, String qrName); // 클릭된 이미지 리소스 ID 전달
    }

    public SouceAdapter(List<Integer> imageList, List<String> souceName, List<String> qrSouceName,OnItemClickListener listener) {
        this.listener = listener;
        this.imageList = imageList;
        this.souceName = souceName;
        this.qrSouceName = qrSouceName;
    }

    @Override
    public SouceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_souce, parent, false);
        return new SouceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SouceViewHolder holder, int position) {
        boolean isSelected = selectedPositions.contains(position); // 현재 아이템이 선택된 아이템인지 확인

        Glide.with(holder.itemView.getContext()) // 메뉴 이미지
                .load(imageList.get(position))
                .into(holder.imageView);

        holder.textView.setText(souceName.get(position));


        //클릭 되었는지 안되어있는지
        holder.bind(isSelected);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class SouceViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public SouceViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Souce_ImageView);
            textView = itemView.findViewById(R.id.Souce_TextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    if (selectedPositions.contains(position)) {
                        // 이미 선택된 아이템이면 선택 해제
                        selectedPositions.remove(Integer.valueOf(position));
                    } else {
                        // 선택 가능한 최대 수를 초과한 경우, 첫 번째 선택된 아이템을 제거
                        if (selectedPositions.size() == MAX_SELECTIONS) {
                            selectedPositions.remove(0);
                        }
                        // 새로운 아이템을 선택
                        selectedPositions.add(position);
                    }

                    // 클릭된 아이템 정보 전달
                    listener.onItemClick(v, imageList.get(position), souceName.get(position), qrSouceName.get(position));

                    // 전체 리스트를 갱신하여 선택 상태 반영
                    notifyDataSetChanged();
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
