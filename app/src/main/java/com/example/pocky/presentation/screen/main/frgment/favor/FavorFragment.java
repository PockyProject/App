package com.example.pocky.presentation.screen.main.frgment.favor;

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
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentFavorBinding;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.presentation.screen.main.MainActivity;
import com.example.pocky.presentation.screen.main.frgment.main.MainFrgment;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class FavorFragment extends Fragment {

    private  FragmentFavorBinding binding;
    private FavorModalBottomsheet bottomsheet;
    private FavorViewModel viewModel ;
    private FavorAdapter favorAdapter;
    private Favor selectedFavor;  // 클릭된 Favor 데이터를 저장할 변수

    private MutableLiveData<List<Favor>> currentName;

    public MutableLiveData<List<Favor>> getCurrentName() {
        if (currentName == null) {
            currentName = new MutableLiveData<List<Favor>>();
        }
        return currentName;
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //바인딩 초기화
        binding = FragmentFavorBinding.inflate(getLayoutInflater());

        //뷰모델 초기화
        FavorViewModelFactory factory = new FavorViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(FavorViewModel.class);

        //QR 바텀 다이얼로그 초기화
        bottomsheet = new FavorModalBottomsheet();

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // FavorAdapter 초기화
        favorAdapter = new FavorAdapter(new FavorAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Favor favor) {
                Log.d("FavorFragment","선택된 데이터 : " + favor.getMenuName());
                selectedFavor = favor;
            }
        },viewModel);
        binding.favorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favorRecyclerView.setAdapter(favorAdapter);


        // ViewModel에서 데이터를 가져와서 RecyclerView에 반영
        viewModel.getFavorList().observe(getViewLifecycleOwner(), favors -> {

            if(favors == null || favors.isEmpty()){ // 즐겨찾기 내역이 있다면, 없다면
                binding.favorRecyclerView.setVisibility(View.INVISIBLE); // 내역 보여주는 리사이클러뷰 숨기기
                binding.emptyView.setVisibility(View.VISIBLE); // 주문유도창 보이기
            }else{
                binding.favorRecyclerView.setVisibility(View.VISIBLE); // 내역 보여주는 리사이클러뷰 보이기
                binding.emptyView.setVisibility(View.INVISIBLE);            // 주문유도창 숨기기
                favorAdapter.submitList(favors);  // 데이터를 어댑터에 설정
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 바인딩 해제
        binding = null;
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
        Button qrButton = view.findViewById(R.id.qrBtn);  // QR 코드 생성 버튼
        qrButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFavor != null) {
                    // 선택된 Favor 데이터로 QR 코드 생성
                    generateQrCode(selectedFavor);
                    Log.d("FavorFrgment","선택된 데이터 : " + selectedFavor.getMenuName());
                } else {
                    // 선택된 Favor 데이터가 없으면 메시지 출력
                    Toast.makeText(getContext(), "먼저 아이템을 선택하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // QR 코드 생성 처리 메서드
    private void generateQrCode(Favor favor) {
        bottomsheet.show(getParentFragmentManager(), FavorModalBottomsheet.TAG);
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기
            String content = ConvertQrValue(favor);
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);

            bottomsheet.setQrBitmap(bitmap);
            bottomsheet.show(getChildFragmentManager(),FavorModalBottomsheet.TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //최종적으로 qr 데이터 변환 메서드
    private String ConvertQrValue(Favor favor) {
        String temp = "";
        //메뉴 이름
        switch (favor.getMenuName()) {
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
        switch (favor.getBread()) {
            case "화이트": {
                temp += "B1 ";
                break;
            }
            case "위트": {
                temp += "B2 ";
                break;
            }
            case "파마산오레가노": {
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

        if (!favor.getToping().isEmpty()) {
            if (favor.getToping().size() == 2) {
                temp += "T00";
            } else if (favor.getToping().size() == 1) {
                temp += "T0000";
            } else {
                temp += "T";
            }

            for (int i = 0; i < favor.getToping().size(); i++) {
                // 토핑 이름
                switch (favor.getToping().get(i)) {
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
                    case "피멘토":
                        temp += "17";
                        break;
                    case "토마토":
                        temp += "18";
                        break;
                }
            }
        }


        if (!favor.getSauce().isEmpty()) {
            if (favor.getSauce().size() == 2) {
                temp += " SAU00";
            } else if (favor.getSauce().size() == 1) {
                temp += " SAU0000";
            } else {
                temp += " SAU";
            }

            for (int i = 0; i < favor.getSauce().size(); i++) {
                // 소스 이름
                switch (favor.getSauce().get(i)) {
                    case "BBQ":
                        temp += "01";
                        break;
                    case "허니머스터드":
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
                    case "스모크BBQ":
                        temp += "12";
                        break;
                    case "사우스웨스트":
                        temp += "13";
                        break;
                    case "간장":
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

        if (!favor.getSide().isEmpty()) {
            temp += " SI";
            // 사이드 이름
            switch (favor.getSide()) {
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
                case "초코칩":
                    temp += "05";
                    break;
                case "콘스프":
                    temp += "06";
                    break;
                case "더블초코칩":
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
                case "오트밀":
                    temp += "11";
                    break;
                case "라즈베리치즈쿠키":
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
        return temp;
    }
}
