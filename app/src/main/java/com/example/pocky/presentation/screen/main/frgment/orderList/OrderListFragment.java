package com.example.pocky.presentation.screen.main.frgment.orderList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentOrderlistBinding;
import com.example.pocky.domain.repository.orderList.Order;

import java.util.UUID;


public class OrderListFragment extends Fragment {
    private FragmentOrderlistBinding binding;
    private OrderViewModel viewModel;
    private OrderListAdapter orderAdapter;

    private Order selectedOrder;  // 클릭된 Order 데이터를 저장할 변수


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //바안딩 초기화
        binding = FragmentOrderlistBinding.inflate(getLayoutInflater());

        //뷰모델 초기화
        OrderListViewModelFactory factory = new OrderListViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this,factory).get(OrderViewModel.class);

        //qr 바텀 다이얼로그 초기화


        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //어댑터 초기화

        orderAdapter = new OrderListAdapter(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Order order) {
                Log.d("OrderListFrgament","선택된 데이터 : " + order.getMenuName());
                selectedOrder = order;
            }
        },viewModel);

        binding.orderListRecycerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.orderListRecycerView.setAdapter(orderAdapter);



        // ViewModel에서 데이터를 가져와서 RecyclerView에 반영
        viewModel.getFavorList().observe(getViewLifecycleOwner(), favors -> {

            if(favors == null || favors.isEmpty()){ // 즐겨찾기 내역이 있다면, 없다면
                binding.orderListRecycerView.setVisibility(View.INVISIBLE); // 내역 보여주는 리사이클러뷰 숨기기
                binding.emptyView.setVisibility(View.VISIBLE); // 주문유도창 보이기
            }else{
                binding.orderListRecycerView.setVisibility(View.VISIBLE); // 내역 보여주는 리사이클러뷰 보이기
                binding.emptyView.setVisibility(View.INVISIBLE);            // 주문유도창 숨기기
                orderAdapter.submitList(favors);  // 데이터를 어댑터에 설정

            }
        });

        // 데이터 삽입 예시, 주문프로세스 완성 후 삭제 예정
        binding.orderBtn.setOnClickListener(v -> {
            Order order1 = new Order(R.drawable.resize_foldfork,
                    "Burger",
                    UUID.randomUUID().toString(),
                    "bread",
                    "sauce",
                    "toping",
                    "side",
                    false);
            Order order2 = new Order(R.drawable.resize_hamcheeze,
                    "Burger",
                    UUID.randomUUID().toString(),
                    "bread",
                    "sauce",
                    "toping",
                    "side",
                    true);
            Order order3 = new Order(R.drawable.resize_bestpartyflatter,
                    "Burger",
                    UUID.randomUUID().toString(),
                    "bread",
                    "sauce",
                    "toping",
                    "side",
                    false);
            viewModel.insertAll(order1,order2,order3);
        });

    }
}
