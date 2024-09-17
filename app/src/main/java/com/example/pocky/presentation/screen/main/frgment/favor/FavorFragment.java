package com.example.pocky.presentation.screen.main.frgment.favor;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.pocky.R;
import com.example.pocky.databinding.FragmentFavorBinding;
import com.example.pocky.domain.repository.Favor;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;

public class FavorFragment extends Fragment {

    private  FragmentFavorBinding binding;
    private FavorModalBottomsheet bottomsheet;
    private FavorViewModel viewModel ;
    private FavorAdapter favorAdapter;

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

        //뷰모델 초기화
        viewModel = new ViewModelProvider(requireActivity(), ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().getApplication()))
                .get(FavorViewModel.class);


        // RecyclerView와 FavorAdapter 초기화
        favorAdapter = new FavorAdapter();
        binding.favorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.favorRecyclerView.setAdapter(favorAdapter);

        // ViewModel에서 데이터를 가져와서 RecyclerView에 반영
        viewModel.getFavorList().observe(getViewLifecycleOwner(), favors -> {
            favorAdapter.submitList(favors);  // 데이터를 어댑터에 설정
        });

        //QR 버튼 정의
        binding.qrBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomsheet.show(getParentFragmentManager(),FavorModalBottomsheet.TAG);
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(
                            "content",
                            BarcodeFormat.QR_CODE, 300, 300);
                    ImageView imageViewQrcode = requireView().findViewById(R.id.imageQrCode);
                    imageViewQrcode.setImageBitmap(bitmap);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        });


        // 데이터 삽입 예시
        binding.orderBtn.setOnClickListener(v -> {
            Favor favor = new Favor(R.drawable.resize_foldfork,
                    "Burger",
                    1,
                    "bread",
                    "sauce",
                    "toping",
                    "side",
                    "requid");
            viewModel.insertFavor(favor);
        });

        // 데이터 삭제 예시 (첫 번째 아이템 삭제)
        binding.cancelBtn.setOnClickListener(v -> {
            if (favorAdapter.getCurrentList().size() > 0) {
                Favor favorToDelete = favorAdapter.getCurrentList().get(0);
                //Favor favorToDelete = favorAdapter.getCurrentList().get(0);
                viewModel.deleteFavor(favorToDelete);
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // 바인딩 해제
        binding = null;
    }
}
