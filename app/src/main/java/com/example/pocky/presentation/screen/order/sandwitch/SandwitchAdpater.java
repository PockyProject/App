package com.example.pocky.presentation.screen.order.sandwitch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.List;

public class SandwitchAdpater extends RecyclerView.Adapter<SandwitchAdpater.SandwitchViewHolder> {

private final OnItemClickListener listener;

private final List<Integer> imageList;
private final List<String> sideName;
private final List<String> arrName; //메뉴 객체에 저장할 영어 이름

private int selectedPosition = RecyclerView.NO_POSITION; // 처음에는 선택된 아이템 없음

public interface OnItemClickListener {
    void onItemClick(View v, int imageResId, String menuName, String qrName); // 클릭된 이미지 리소스 ID 전달
}

public SandwitchAdpater(List<Integer> imageList, List<String> sideName, List<String> arrName, OnItemClickListener listener) {
    this.listener = listener;
    this.imageList = imageList;
    this.sideName = sideName;
    this.arrName = arrName;
}

@Override
public SandwitchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_sandwitch, parent, false);
    return new SandwitchViewHolder(view);
}

@Override
public void onBindViewHolder(SandwitchViewHolder holder, int position) {
    boolean isSelected = position == selectedPosition; // 현재 아이템이 선택된 아이템인지 확인

    Glide.with(holder.itemView.getContext()) // 메뉴 이미지
            .load(imageList.get(position))
            .into(holder.imageView);

    holder.textView.setText(sideName.get(position));


    //클릭 되었는지 안되어있는지
    holder.bind(isSelected);

}

@Override
public int getItemCount() {
    return imageList.size();
}

class SandwitchViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;

    public SandwitchViewHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.sandwitch_ImageView);
        textView = itemView.findViewById(R.id.sandwitch_TextView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = getAdapterPosition();


                // 클릭된 아이템의 이미지 리소스 ID를 리스너로 전달
                listener.onItemClick(v, imageList.get(position),sideName.get(position),arrName.get(position));

                // 이전 선택된 아이템을 업데이트
                notifyItemChanged(selectedPosition);

                // 현재 선택된 아이템 위치 저장
                selectedPosition = position;

                // 현재 선택된 아이템을 업데이트
                notifyItemChanged(selectedPosition);
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