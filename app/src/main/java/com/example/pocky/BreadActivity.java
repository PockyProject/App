package com.example.pocky;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

public class BreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bread);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<Integer> imageList = Arrays.asList(
                R.drawable.resize_white,
                R.drawable.resize_wheet,
                R.drawable.resize_parmesan,
                R.drawable.resize_honeyoat,
                R.drawable.resize_hathi,
                R.drawable.resize_flat
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
