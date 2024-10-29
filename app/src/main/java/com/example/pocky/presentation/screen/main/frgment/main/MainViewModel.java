package com.example.pocky.presentation.screen.main.frgment.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.pocky.R;
import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.recommend.RecommendDTO;
import com.example.pocky.domain.model.recommend.RecommendService;
import com.example.pocky.domain.model.recommend.viewRecommendDTO;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends ViewModel implements qrOrderValue {
    private static final String TAG = "MainViewModel";
    private final MutableLiveData<List<viewRecommendDTO>> recommendMenuImage = new MutableLiveData<>();

    public LiveData<List<viewRecommendDTO>> getRecommendMenuImage() {
        return recommendMenuImage;
    }


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
                        convertMenuInfo(temp);
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

    public void convertMenuInfo(RecommendDTO data){
        List<String> temp = new ArrayList<>();
        List<viewRecommendDTO> temp2 = new ArrayList<>();

        temp.add(data.getMenu1());
        temp.add(data.getMenu2());
        temp.add(data.getMenu3());
        Log.d(TAG,"temp 사이즈 : " + temp.size());



        for(int i = 0; i < temp.size(); i++){

            temp2.add(new viewRecommendDTO());

            switch (temp.get(i)){
                case BLTSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_bltsandwitch);
                    temp2.get(i).setMenuName("비엘티샌드위치");
                    break;
                }
                case CHICKENAVOCADOSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_chickenbaconavocadosandwitch);
                    temp2.get(i).setMenuName("치킨아보카도샌드위치");
                    break;
                }
                case CHICKENSLICESANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_chickenslicesandwitch);
                    temp2.get(i).setMenuName("치킨슬라이스샌드위치");
                    break;
                }
                case CHICKENTERIYAKISANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_chickenteriyakisandwitch);
                    temp2.get(i).setMenuName("치킨데리야끼샌드위치");
                    break;
                }
                case EGGSLICESANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_eggslicesandwitch);
                    temp2.get(i).setMenuName("에그슬라이스샌드위치");
                    break;
                }
                case EGGMAYOSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_eggmayosandwitch);
                    temp2.get(i).setMenuName("에그마요샌드위치");
                    break;
                }
                case HAMSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_hamsandwitch);
                    temp2.get(i).setMenuName("햄샌드위치");
                    break;
                }
                case ITALIANBMTSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_italianbmtsandwitch);
                    temp2.get(i).setMenuName("이탈리안비엠티샌드위치");
                    break;
                }
                case KBBQSANDWITCH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_kbbqsandwitch);
                    temp2.get(i).setMenuName("K비비큐샌드위치");
                    break;
                }
                case PORKCHEESESANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_pullporkcheesesandwitch);
                    temp2.get(i).setMenuName("폴포크치즈샌드위치");
                    break;
                }
                case ROASTEDCHICKENSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_roastedchickensandwitch);
                    temp2.get(i).setMenuName("로티세리샌드위치");
                    break;
                }
                case ROTISSERIEBBQCHICKEN : {
                    temp2.get(i).setMenuImage(R.drawable.resize_rotisseriebbqchickensandwitch);
                    temp2.get(i).setMenuName("로티세리비비큐샌드위치");
                    break;
                }
                case SHRIMPSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_shrimpsandwitch);
                    temp2.get(i).setMenuName("쉬림프샌드위치");
                    break;
                }
                case SPICYITALIANSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_spicyitaliansandwitch);
                    temp2.get(i).setMenuName("스파이시이탈리안샌드위치");
                    break;
                }
                case SPICYSHRIMPSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_spicyshrimpsandwitch);
                    temp2.get(i).setMenuName("스파이시쉬림프샌드위치");
                    break;
                }
                case STEAKCHEESESANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_steakandcheesesandwitch);
                    temp2.get(i).setMenuName("스테이크앤치즈샌드위치");
                    break;
                }
                case SUBWAYCLUBSANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_subwayclubsandwitch);
                    temp2.get(i).setMenuName("서브웨이클럽샌드위치");
                    break;
                }
                case VEGGIESANDWICH : {
                    temp2.get(i).setMenuImage(R.drawable.resize_veggiesandwitch);
                    temp2.get(i).setMenuName("베지샌드위치");
                    break;
                }
            }
        }

        recommendMenuImage.setValue(temp2);

    }

}
