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
            String content = favor.getMenuName() + " - " + favor.getRequid();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);

            bottomsheet.setQrBitmap(bitmap);
            bottomsheet.show(getChildFragmentManager(),FavorModalBottomsheet.TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
