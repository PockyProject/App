package com.example.pocky.presentation.screen.main.frgment.main;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.pocky.R;
import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.recommend.RecommendDTO;
import com.example.pocky.domain.model.recommend.RecommendService;
import com.example.pocky.domain.model.user.UserInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";


    public String initUserData(){
        if (UserInfo.getInstance() != null) {
            try{
                Log.e("MainActivity","userNickname : " + UserInfo.getInstance().getNickname());
                return UserInfo.getInstance().getNickname();
            }catch (NullPointerException e){
                Log.e("MainActivity","유저 정보 객체 호출 실패");
            }
        } else {
            Log.e("MainActivity","userInfo is null");
        }

        return "오류발생";
    }

    public List<String> initMenuName(){
        List<String> arr = new ArrayList<>();
        arr.add("아침메뉴");
        arr.add("샐러드");
        arr.add("샌드위치");
        arr.add("랩 및 기타");
        arr.add("그룹메뉴");
        arr.add("스마일 썹");

        return arr;
    };

    public List<Integer> initMenuImg(){
        List<Integer> arr = new ArrayList<>();
        arr.add(R.drawable.resize_hamcheeze);
        arr.add(R.drawable.resize_bltsalad);
        arr.add(R.drawable.resize_foldfork);
        arr.add(R.drawable.resize_shrimpeggmayorap);
        arr.add(R.drawable.groupmenu_bestpartyflatter);
        arr.add(R.drawable.resize_doublechokochip);

        return arr;
    };

    public void getCommentData(int age) {
        RecommendService api = RetrofitService.getInstance().getRetrofit().create(RecommendService.class);
        // ExecutorService 생성 (스레드 풀)
        ExecutorService executor = Executors.newSingleThreadExecutor();

        String comment = age+"대 고객들이 가장 많이 구매한 서브웨이에 있는 메뉴 상위 3가지를 알려주세요. 각 메뉴는 JSON 형식으로 menu를 key로 사용하고, value는\"BLTSANDWICH\", \"CHICKENAVOCADOSANDWICH\", \"CHICKENSLICESANDWICH\",  \"CHICKENTERIYAKISANDWICH\", \"EGGSLICESANDWICH\", \"EGGMAYOSANDWICH\",     \"HAMSANDWICH\", \"ITALIANBMTSANDWICH\", \"KBBQSANDWITCH\", \"PORKCHEESESANDWICH\", \"ROASTEDCHICKENSANDWICH\", \"ROTISSERIEBBQCHICKEN\",  \"SHRIMPSANDWICH\", \"SPICYITALIANSANDWICH\", \"SPICYSHRIMPSANDWICH\", \"STEAKCHEESESANDWICH\", \"SUBWAYCLUBSANDWICH\", \"VEGGIESANDWICH\"와 같은 메뉴명으로 반환해 주세요";

        // 네트워크 요청 비동기 처린
        executor.execute(() -> {
            api.getCommentData(comment).enqueue(new Callback<RecommendDTO>() {
                @Override
                public void onResponse(Call<RecommendDTO> call, Response<RecommendDTO> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "추천메뉴 받아오기 성공" + response.code());
                        RecommendDTO temp = response.body();
                        Log.d(TAG,"추천메뉴 받아오기 성공" + temp.getMenu1() + " " + temp.getMenu2() + " " + temp.getMenu3());
                    } else {
                        Log.d(TAG, "추천메뉴 받아오기 실패" + response.code());
                    }
                }

                @Override
                public void onFailure(Call<RecommendDTO> call, Throwable t) {
                    Log.d(TAG, "연결 실패" + t.getCause());
                }
            });
        });
    }





}
