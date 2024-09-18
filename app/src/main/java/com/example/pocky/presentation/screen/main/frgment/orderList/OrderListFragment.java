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

import com.example.pocky.databinding.FragmentOrderlistBinding;
import com.example.pocky.domain.repository.orderList.Order;


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


    }
}
