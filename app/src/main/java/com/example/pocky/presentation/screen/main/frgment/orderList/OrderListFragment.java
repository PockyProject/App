package com.example.pocky.presentation.screen.main.frgment.orderList;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentOrderlistBinding;
import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.presentation.screen.main.MainActivity;
import com.example.pocky.presentation.screen.main.frgment.main.MainFrgment;
import com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Objects;


public class OrderListFragment extends Fragment implements qrOrderValue {
    private FragmentOrderlistBinding binding;
    private OrderViewModel viewModel;
    private OrderListAdapter orderAdapter;
    private OrderModalBottomSheet bottomsheet;

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
        bottomsheet = new OrderModalBottomSheet();


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



        // QR 코드 생성 버튼 클릭 이벤트 설정
        setupQrButton(view);
        binding.gotoMainFrgmentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainFrgment(v);
            }
        });

    }

    @SuppressLint("ResourceType")
    private void goToMainFrgment(View view){
        FragmentTransaction tr = getActivity().getSupportFragmentManager().beginTransaction();
        MainFrgment main = new MainFrgment();
        tr.replace(super.getId(),main);
        tr.commit();

        MainActivity activity = (MainActivity) getActivity();
        activity.setSelectedIconColor(R.id.bottomHomeBtn); // 변경할 바텀 아이콘 아이디 넣기
    }

    // QR 코드 생성 버튼 클릭 이벤트 처리
    private void setupQrButton(View view) {
        Button qrButton = view.findViewById(R.id.orderQrBtn);  // QR 코드 생성 버튼
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedOrder != null) {
                    // 선택된 Favor 데이터로 QR 코드 생성
                    generateQrCode(selectedOrder);
                    Log.d("FavorFrgment","선택된 데이터 : " + selectedOrder.getMenuName());
                } else {
                    // 선택된 Favor 데이터가 없으면 메시지 출력
                    Toast.makeText(getContext(), "먼저 아이템을 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // QR 코드 생성 처리 메서드
    private void generateQrCode(Order order) {
        // bottomsheet가 이미 표시되어 있다면 닫기
        if (bottomsheet.isAdded()) {
            bottomsheet.dismiss();
        }
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기

            String content = ConvertQrValue(order);
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);




            bottomsheet.setQrBitmap(bitmap);
            bottomsheet.show(getChildFragmentManager(),OrderModalBottomSheet.TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //최종적으로 qr 데이터 변환 메서드
    private String ConvertQrValue(Order order) {
        String temp = "";
        //메뉴 이름
        switch (order.getMenuName()) {
            case "비엘티샌드위치": {
                temp += "M1 ";
                break;

            }
            case "치킨아보카도샌드위치": {
                temp += "M2 ";
                break;
            }
            case "치킨슬라이스샌드위치": {
                temp += "M3 ";
                break;
            }
            case "치킨데리야끼샌드위치": {
                temp += "M4 ";
                break;
            }
            case "에그슬라이스샌드위치": {
                temp += "M5 ";
                break;
            }
            case "에그마요샌드위치": {
                temp += "M6 ";
                break;
            }
            case "햄샌드위치": {
                temp += "M7 ";
                break;
            }
            case "이탈리안비엠티샌드위치": {
                temp += "M8 ";
                break;
            }
            case "K비비큐샌드위치": {
                temp += "M9 ";
                break;
            }
            case "폴포크치즈샌드위치": {
                temp += "M10 ";
                break;
            }
            case "로티세리샌드위치": {
                temp += "M11 ";
                break;
            }
            case "로티세리비비큐샌드위치": {
                temp += "M12 ";
                break;
            }
            case "쉬림프샌드위치": {
                temp += "M13 ";
                break;
            }
            case "스파이시이탈리안샌드위치": {
                temp += "M14 ";
                break;
            }
            case "스파이시쉬림프샌드위치": {
                temp += "M15 ";
                break;
            }
            case "스테이크앤치즈샌드위치": {
                temp += "M16 ";
                break;
            }
            case "서브웨이클럽샌드위치": {
                temp += "M17 ";
                break;
            }
            case "베지샌드위치": {
                temp += "M18 ";
                break;
            }


        }
        // 빵 이름
        switch (order.getBread()) {
            case "화이트": {
                temp += "B1 ";
                break;
            }
            case "휘트": {
                temp += "B2 ";
                break;
            }
            case "파마산": {
                temp += "B3 ";
                break;
            }
            case "허니오트": {
                temp += "B4 ";
                break;
            }
            case "하티": {
                temp += "B5 ";
                break;
            }
            case "플랫브레드": {
                temp += "B6 ";
                break;
            }
        }

        if (!order.getToping().isEmpty()) {
            if (order.getToping().size() == 2) {
                temp += "T00";
            } else if (order.getToping().size() == 1) {
                temp += "T0000";
            } else {
                temp += "T";
            }

            for (int i = 0; i < order.getToping().size(); i++) {
                // 토핑 이름
                switch (order.getToping().get(i)) {
                    case "아보카도":
                        temp += "01";
                        break;
                    case "베이컨":
                        temp += "02";
                        break;
                    case "에그슬라이스":
                        temp += "03";
                        break;
                    case "미트":
                        temp += "04";
                        break;
                    case "오믈렛":
                        temp += "05";
                        break;
                    case "페퍼로니":
                        temp += "06";
                        break;
                    case "에그마요":
                        temp += "07";
                        break;
                    case "아메리칸치즈":
                        temp += "08";
                        break;
                    case "모짜렐라치즈":
                        temp += "09";
                        break;
                    case "슈레드치즈":
                        temp += "10";
                        break;
                    case "오이":
                        temp += "11";
                        break;
                    case "할라피뇨":
                        temp += "12";
                        break;
                    case "양상추":
                        temp += "13";
                        break;
                    case "올리브":
                        temp += "14";
                        break;
                    case "양파":
                        temp += "15";
                        break;
                    case "피클":
                        temp += "16";
                        break;
                    case "피망":
                        temp += "17";
                        break;
                    case "토마토":
                        temp += "18";
                        break;
                }
            }
        }


        if (!order.getSauce().isEmpty()) {
            if (order.getSauce().size() == 2) {
                temp += " SAU00";
            } else if (order.getSauce().size() == 1) {
                temp += " SAU0000";
            } else {
                temp += " SAU";
            }

            for (int i = 0; i < order.getSauce().size(); i++) {
                // 소스 이름
                switch (order.getSauce().get(i)) {
                    case "비비큐":
                        temp += "01";
                        break;
                    case "허니머스타드":
                        temp += "02";
                        break;
                    case "핫칠리":
                        temp += "03";
                        break;
                    case "이탈리안드레싱":
                        temp += "04";
                        break;
                    case "마요네즈":
                        temp += "05";
                        break;
                    case "머스터드":
                        temp += "06";
                        break;
                    case "올리브오일":
                        temp += "07";
                        break;
                    case "페퍼":
                        temp += "08";
                        break;
                    case "랜치":
                        temp += "09";
                        break;
                    case "레드와인":
                        temp += "10";
                        break;
                    case "소금":
                        temp += "11";
                        break;
                    case "스모크비비큐":
                        temp += "12";
                        break;
                    case "사우스웨스트":
                        temp += "13";
                        break;
                    case "소이":
                        temp += "14";
                        break;
                    case "스위트칠리":
                        temp += "15";
                        break;
                    case "스위트어니언":
                        temp += "16";
                        break;
                    case "타르타르":
                        temp += "17";
                        break;
                    case "사우전드아일랜드":
                        temp += "18";
                        break;
                    case "와사비마요":
                        temp += "19";
                        break;
                }
            }
        }

        if (!order.getSide().isEmpty()) {
            temp += " SI";
            // 사이드 이름
            switch (order.getSide()) {
                case "베이컨치즈웨지포테이토":
                    temp += "01";
                    break;
                case "치즈웨지포테이토":
                    temp += "02";
                    break;
                case "치킨베이컨랩":
                    temp += "03";
                    break;
                case "감자칩":
                    temp += "04";
                    break;
                case "초콜렛칩":
                    temp += "05";
                    break;
                case "콘수프":
                    temp += "06";
                    break;
                case "더블초콜릿칩":
                    temp += "07";
                    break;
                case "해쉬브라운":
                    temp += "08";
                    break;
                case "우유":
                    temp += "09";
                    break;
                case "머쉬룸스프":
                    temp += "10";
                    break;
                case "오트밀라진쿠키":
                    temp += "11";
                    break;
                case "라지베리치즈케이크쿠키":
                    temp += "12";
                    break;
                case "웨지포테이토":
                    temp += "13";
                    break;
                case "화이트마카다미아쿠키":
                    temp += "14";
                    break;
            }
        }

        //음료 이름
        if (Objects.equals(order.getRequid(), "음료 여부 : 예")) {
            temp += " YES";
        } else {
            temp += " NO";
        }
        return temp;
    }
}
