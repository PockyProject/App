package com.example.pocky.presentation.screen.order.sandwitch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pocky.R;

import java.util.Arrays;
import java.util.List;

public class SandwitchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwitch);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_bltsandwitch,
                R.drawable.resize_chickenbaconavocadosandwitch,
                R.drawable.resize_chickenslicesandwitch,
                R.drawable.resize_chickenteriyakisandwitch,
                R.drawable.resize_eggslicesandwitch,
                R.drawable.resize_eggmayosandwitch,
                R.drawable.resize_hamsandwitch,
                R.drawable.resize_italianbmtsandwitch,
                R.drawable.resize_kbbqsandwitch,
                R.drawable.resize_pullporkcheesesandwitch,
                R.drawable.resize_roastedchickensandwitch,
                R.drawable.resize_rotisseriebbqchickensandwitch,
                R.drawable.resize_shrimpsandwitch,
                R.drawable.resize_spicyitaliansandwitch,
                R.drawable.resize_spicyshrimpsandwitch,
                R.drawable.resize_steakandcheesesandwitch,
                R.drawable.resize_steakcheesesalad,
                R.drawable.resize_subwayclubsandwitch,
                R.drawable.resize_veggiesandwitch
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
