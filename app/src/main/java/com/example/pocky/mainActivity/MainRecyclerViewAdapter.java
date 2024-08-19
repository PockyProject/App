package com.example.pocky.mainActivity;

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


public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
    public interface OnItemClickListener{ //인터페이스 생성
        void onItemClick(View v, int position);
    }
    private static OnItemClickListener listener; // 메인에서 받아올 리스너 객체
    public void setOnItemClickListener(OnItemClickListener listener){ // 받아온 리스너 담을 메서드
        this.listener = listener;
    }
    List<String> menuName;
    List<Integer> menuImg;



    @SuppressLint("NotifyDataSetChanged")
    public void setArr(List<String> name, List<Integer> img) {
       this.menuName = name;
       this.menuImg = img;
       notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_recyclerview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.menuName.setText(menuName.get(position));
        holder.menuPhoto.setImageResource(menuImg.get(position));
        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return menuName != null ? menuName.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView menuName;
        ImageView menuPhoto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            menuPhoto = itemView.findViewById(R.id.foodImageView1);
            menuName = itemView.findViewById(R.id.foodTitleText);

            itemView.setOnClickListener(new View.OnClickListener() { //인터페이스 구현
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition() ; //아이템 포지션 확인
                    if(listener!=null && position!= RecyclerView.NO_POSITION){
                        //포지션 값을 반환해서 클릭이벤트 때 메인에서 활성화 한다
                        listener.onItemClick(v, position);
                    }
                }
            });
        }

    }
}
