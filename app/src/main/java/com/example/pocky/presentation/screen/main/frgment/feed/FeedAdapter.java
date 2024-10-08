package com.example.pocky.presentation.screen.main.frgment.feed;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    public interface OnItemClickListener { // 인터페이스 생성
        void onItemClick(View v, int position);
    }

    private static OnItemClickListener listener; // 받아올 리스너 객체

    public void setOnItemClickListener(OnItemClickListener listener) { // 받아온 리스너를 설정하는 메서드
        this.listener = listener;
    }

    private List<String> feedName;
    private List<Integer> feedImg;

    @SuppressLint("NotifyDataSetChanged")
    public void setArr(List<String> name, List<Integer> img) {
       // this.feedName = name;
       // this.feedImg = img; ( 리사이클러 아이템 뷰 만들었을 때 가져올 예정 )
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_recyclerview, parent, false); // 이미지 파일 정해지면 제작 예정
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 아직 바인딩 기능은 구현하지 않음 (틀만 유지)
    }

    @Override
    public int getItemCount() {
        return feedName != null ? feedName.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView feedName;
        ImageView feedPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // 나중에 이미지나 텍스트 추가 예정

            itemView.setOnClickListener(new View.OnClickListener() { // 아이템 클릭 리스너 구현
                @Override
                public void onClick(View v) { // 클릭이벤트 구현 예정 }
            };
        });
    }}}
