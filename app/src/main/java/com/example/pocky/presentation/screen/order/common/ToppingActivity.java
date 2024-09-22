package com.example.pocky.presentation.screen.order.common;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.Arrays;
import java.util.List;

public class ToppingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topping);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_abocado,
                R.drawable.resize_bacon,
                R.drawable.resize_eggslice,
                R.drawable.resize_meet,
                R.drawable.resize_omelet,
                R.drawable.resize_pepperoni,
                R.drawable.resize_eggmayo,
                R.drawable.resize_americancheese,
                R.drawable.resize_mozzarellacheese,
                R.drawable.resize_shreddedcheese,
                R.drawable.resize_cucumber,
                R.drawable.resize_halapinyo,
                R.drawable.resize_lettuce,
                R.drawable.resize_olive,
                R.drawable.resize_onion,
                R.drawable.resize_pickle,
                R.drawable.resize_pimento,
                R.drawable.resize_tomato
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
                    .inflate(R.layout.item_short, parent, false);
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
                imageView = itemView.findViewById(R.id.ShortImageView);
            }
        }
    }
}
