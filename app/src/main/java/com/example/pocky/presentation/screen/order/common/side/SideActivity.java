package com.example.pocky.presentation.screen.order.common;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.Arrays;
import java.util.List;

public class SideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);

        RecyclerView recyclerView = findViewById(R.id.side_RecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_baconcheesewedgepotato,
                R.drawable.resize_cheesewedgepotato,
                R.drawable.resize_chickenbaconminiwrap,
                R.drawable.resize_chips,
                R.drawable.resize_chocolatechip,
                R.drawable.resize_cornsoup,
                R.drawable.resize_doublechocolatechip,
                R.drawable.resize_hashbrown,
                R.drawable.resize_milk,
                R.drawable.resize_mushroomsoup,
                R.drawable.resize_oatmealrasin,
                R.drawable.resize_raspberrycheesecake,
                R.drawable.resize_wedgepotato,
                R.drawable.resize_whitechocomacadamia
                // 소스 이미지 추가
        );

        SouceAdapter adapter = new SouceAdapter(imageList);
        recyclerView.setAdapter(adapter);
    }

    public class SouceAdapter extends RecyclerView.Adapter<SouceAdapter.SouceViewHolder> {

        private final List<Integer> imageList;

        public SouceAdapter(List<Integer> imageList) {
            this.imageList = imageList;
        }

        @Override
        public SouceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_middle, parent, false);
            return new SouceViewHolder(view);
        }

        @Override
        public void onBindViewHolder(SouceViewHolder holder, int position) {
            Glide.with(holder.itemView.getContext())
                    .load(imageList.get(position))
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭시 이벤트 발생 (추가 예정)
                }
            });
        }

        @Override
        public int getItemCount() {
            return imageList.size();
        }

        class SouceViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;

            public SouceViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.MiddleImageView);
            }
        }
    }
}
