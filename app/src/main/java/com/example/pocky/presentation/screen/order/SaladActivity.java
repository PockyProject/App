package com.example.pocky.presentation.screen.order;

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

public class SaladActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salad);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_bltsalad2,
                R.drawable.resize_chickenbaconavocadosalad,
                R.drawable.resize_chickenslicesalad,
                R.drawable.resize_chickenteriyakisalad,
                R.drawable.resize_eggmayosalad,
                R.drawable.resize_fullforksalad,
                R.drawable.resize_greenavocadoeggslicesalad,
                R.drawable.resize_greenricottachickensalad,
                R.drawable.resize_greenrotisseriechickenavocadosalad,
                R.drawable.resize_hamsalad,
                R.drawable.resize_italianbmtsalad,
                R.drawable.resize_kbbqsalad,
                R.drawable.resize_minirotisseriechickensalad,
                R.drawable.resize_roastchickensalad,
                R.drawable.resize_rotisseriesalad,
                R.drawable.resize_shrimpsalad,
                R.drawable.resize_spicyitaliansalad,
                R.drawable.resize_spicyitaliansalad,
                R.drawable.resize_spicyshrimpsalad,
                R.drawable.resize_steakcheesesalad,
                R.drawable.resize_subwayclubsalad,
                R.drawable.resize_tunasalad,
                R.drawable.resize_veggiesalad
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
