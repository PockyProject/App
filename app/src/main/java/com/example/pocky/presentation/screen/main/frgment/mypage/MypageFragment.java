package com.example.pocky.presentation.screen.main.frgment.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pocky.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {
    private FragmentMypageBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMypageBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}
