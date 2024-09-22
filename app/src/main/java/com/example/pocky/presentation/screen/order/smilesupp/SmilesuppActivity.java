package com.example.pocky.presentation.screen.order.smilesupp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.Arrays;
import java.util.List;

public class SmilesuppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smilesupp);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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
        );

        BreadAdapter adapter = new BreadAdapter(imageList);
        recyclerView.setAdapter(adapter);
    }

    public class BreadAdapter extends RecyclerView.Adapter<BreadAdapter.BreadViewHolder> {

        private final List<Integer> imageList;

        public BreadAdapter(List<Integer> imageList) {
            this.imageList = imageList;
        }

        @Override
        public BreadViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_long, parent, false);
            return new BreadViewHolder(view);
        }

        @Override
        public void onBindViewHolder(BreadViewHolder holder, int position) {
            Glide.with(holder.itemView.getContext())
                    .load(imageList.get(position))
                    .into(holder.imageView);

            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 클릭시 이벤트 발생 ( 추가 예정 )
                }
            });

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
            }
        }
    }
}
