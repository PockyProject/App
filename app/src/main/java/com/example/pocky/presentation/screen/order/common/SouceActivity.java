package com.example.pocky.presentation.screen.order.common;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class SouceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_souce);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_bbq,
                R.drawable.resize_honeymustard,
                R.drawable.resize_horseadish,
                R.drawable.resize_hotchili,
                R.drawable.resize_italiandressing,
                R.drawable.resize_mayo,
                R.drawable.resize_mustard,
                R.drawable.resize_oliveoli,
                R.drawable.resize_pepper,
                R.drawable.resize_ranch,
                R.drawable.resize_redwinevinegar,
                R.drawable.resize_salt,
                R.drawable.resize_smokebbq,
                R.drawable.resize_southwest,
                R.drawable.resize_soy,
                R.drawable.resize_sweetchili,
                R.drawable.resize_sweetonion,
                R.drawable.resize_tartar,
                R.drawable.resize_thousandisland,
                R.drawable.resize_wasabimayo
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
