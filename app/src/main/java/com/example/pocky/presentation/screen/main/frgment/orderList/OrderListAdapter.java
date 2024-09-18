package com.example.pocky.presentation.screen.main.frgment.orderList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pocky.R;
import com.example.pocky.databinding.ItemOrderRecycerviewBinding;
import com.example.pocky.domain.repository.orderList.Order;

import java.util.Objects;

public class OrderListAdapter extends ListAdapter<Order, OrderListAdapter.OrderViewHolder> {

    private static OrderListAdapter.OnItemClickListener listener; // 클릭 이벤트를 전달할 리스너
    private OrderViewModel viewModel;
    private ItemOrderRecycerviewBinding binding;

    public interface OnItemClickListener {
        void onItemClick(Order Order); // 클릭된 아이템 데이터를 전달할 메서드
    }


    // 생성자
    public OrderListAdapter(OrderListAdapter.OnItemClickListener listener, OrderViewModel viewModel) {
        super(OrderDiffUtil);
        this.viewModel = viewModel;
        OrderListAdapter.listener = (OnItemClickListener) listener;
    }


    // ViewHolder 정의
    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private final TextView menuNameTextView;
        private final ImageView menuImageView;
        private final TextView breadTextView;
        private final TextView sauceTextView;
        private final TextView topingTextView;
        private final TextView sideTextView;
        private final TextView requidTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 요소 연결
            menuNameTextView = itemView.findViewById(R.id.orderMenuText);
            menuImageView = itemView.findViewById(R.id.orderFoodImageView);
            breadTextView = itemView.findViewById(R.id.orderBread);
            sauceTextView = itemView.findViewById(R.id.orderSauce);
            topingTextView = itemView.findViewById(R.id.orderToping);
            sideTextView = itemView.findViewById(R.id.orderSide);
            requidTextView = itemView.findViewById(R.id.orderRequid);
            // 클릭 이벤트 처리
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
            itemView.findViewById(R.id.cancelBtn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 현재 클릭된 포지션 가져오기
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // 포지션에 해당하는 데이터 가져오기
                        Order orderToDelete = getItem(position);
                        viewModel.deleteFavor(orderToDelete);
                    }
                }
            });
        }

        public void bind(Order order) {
            // 데이터 바인딩
            menuNameTextView.setText(order.getMenuName()); // 메뉴 이름 설정
            menuImageView.setImageResource(order.getMenuImage()); // 메뉴 이미지 설정
            breadTextView.setText(order.getBread()); // 빵  설정
            sauceTextView.setText(order.getSauce()); // 소스 설정
            topingTextView.setText(order.getToping()); // 토핑 설정
            sideTextView.setText(order.getSide()); // 사이드 메뉴 설정
            requidTextView.setText(order.getRequid().toString()); // 기타 요청 사항 설정
        }
    }

    @NonNull
    @Override
    public OrderListAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 레이아웃을 인플레이트하여 ViewHolder 생성
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_recycerview, parent, false);

        return new OrderListAdapter.OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.OrderViewHolder holder, int position) {
        // 데이터 바인딩
        Order order = getItem(position);
        holder.bind(order);
    }

    // DiffUtil 정의
    public static final DiffUtil.ItemCallback<Order> OrderDiffUtil = new DiffUtil.ItemCallback<Order>() {
        @Override
        public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            // 각 항목의 고유성을 비교 (age 기준으로 동일 여부 판단)
            return Objects.equals(oldItem.getFavorNumber(), newItem.getFavorNumber());
        }

        @Override
        public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
            // 항목의 내용이 동일한지 확인 (equals 메서드로 비교)
            return oldItem.equals(newItem);
        }
    };
}