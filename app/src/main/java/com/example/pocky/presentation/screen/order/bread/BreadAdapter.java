package com.example.pocky.presentation.screen.order.bread;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.List;

public class BreadAdapter extends RecyclerView.Adapter<BreadAdapter.BreadViewHolder> {

    private final BreadAdapter.OnItemClickListener listener;
    private final List<Integer> imageList;
    private final List<String> isSelecteds;
    private final List<String> qrBreadList;
    private final List<String> breadList;
    private int selectedPosition = RecyclerView.NO_POSITION; // 처음에는 선택된 아이템 없음

    public BreadAdapter(OnItemClickListener listener, List<Integer> imageList, List<String> isSelecteds, List<String> qrBreadList, List<String> breadList) {
        this.listener = listener;
        this.imageList = imageList;
        this.isSelecteds = isSelecteds;
        this.qrBreadList = qrBreadList;
        this.breadList = breadList;
    }

    public interface OnItemClickListener {
        void onItemClick(View v,int imageResId, String menuName, String qrName); // 클릭된 이미지 리소스 ID 전달
    }

    @Override
    public BreadAdapter.BreadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bread, parent, false);
        return new BreadAdapter.BreadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BreadAdapter.BreadViewHolder holder, int position) {
        boolean isSelected = position == selectedPosition; // 현재 아이템이 선택된 아이템인지 확인


        Glide.with(holder.itemView.getContext())
                .load(imageList.get(position))
                .into(holder.imageView);

        //클릭 되었는지 안되어있는지
        holder.bind(isSelected);

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    class BreadViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public BreadViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.LongImageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();


                    // 클릭된 아이템의 이미지 리소스 ID를 리스너로 전달
                    listener.onItemClick(v, imageList.get(position),breadList.get(position),qrBreadList.get(position));

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