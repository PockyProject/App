package com.example.pocky.presentation.screen.main.frgment.mypage.addfeeds.addFeedActivity;


import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.AMERICANCHEESE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.AVOCADO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.BACON;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.BACONCHEESEWEDGEPOTATO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.BBQ;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.BLTSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHEESEWEDGEPOTATO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHICKENAVOCADOSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHICKENBACONWRAP;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHICKENSLICESANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHICKENTERIYAKISANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CHOCOLATECHIP;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CORNSOUP;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.CUCUMBER;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.DOUBLECHOCOLATECHIP;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.EGGMAYO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.EGGMAYOSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.EGGSLICE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.EGGSLICESANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.FLATBREAD;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HAMSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HASHBROWN;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HEARTY;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HONEYMUSTARD;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HONEYOAT;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.HOTCHILI;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.ITALIANBMTSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.ITALIANDRESSING;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.JALAPENO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.KBBQSANDWITCH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.LETTUCE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MAYONNAISE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MEAT;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MILK;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MOZZARELLACHEESE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MUSHROOMSOUP;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.MUSTARD;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.OATMEAL;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.OLIVEOIL;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.OLIVES;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.OMELETTE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.ONION;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PAPER;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PARMESAN;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PEPPERONI;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PICKLE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PIMENTO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.PORKCHEESESANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.POTATOCHIPS;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.RANCH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.RASPBERRYCHEESECOOKIE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.REDWINE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.ROASTEDCHICKENSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.ROTISSERIEBBQCHICKEN;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SALT;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SHOEREDDCHEESE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SHRIMPSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SMOKEDBBQ;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SOUTHWEST;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SOY;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SPICYITALIANSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SPICYSHRIMPSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.STEAKCHEESESANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SUBWAYCLUBSANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SWEETCHILI;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.SWEETONION;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.TARTARE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.THOUSANDISLAND;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.TOMATO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.VEGGIESANDWICH;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.WASABIMAYO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.WEDGEPOTATO;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.WHEAT;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.WHITE;
import static com.example.pocky.presentation.screen.order.common.finalorder.qrOrderValue.WHITEMACADAMIACOOKIE;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.feed.FeedApiService;
import com.example.pocky.domain.model.feed.FeedData;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.ByteArrayOutputStream;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFeedViewModel extends AndroidViewModel {

    private static String TAG = "AddFeedViewModel";

    public interface isSuccessCallback<T> { // 피드 등록 성공, 실패를 확인하기 위한 콜백 인터페이스 정의
        void onSuccess(T result);
        void onFailure(T result);
    }


    public AddFeedViewModel(@NonNull Application application) {
        super(application);
    }

    //메뉴 인스턴스 가져와서 피드 모델로 변형
    public FeedData menuToFeed(Menu menu,String title, String content){
        FeedData data = new FeedData(
                UUID.randomUUID().toString(),
                UserInfo.getInstance().getUserId(),
                title,
                content,
                0,
                calcCurrentTime(),
                calcCurrentTime(),
                calcCurrentTime(),
                menuGenerateQrCode(menu),
                menu.getMenuImage()
        );

        return data;
    }

    //즐겨찾기 인스턴스 가져와서 피드 모델로 변경
    public FeedData favorToFeed(Favor favor,String title, String content){
        FeedData data = new FeedData(
                UUID.randomUUID().toString(),
                UserInfo.getInstance().getUserId(),
                title,
                content,
                0,
                calcCurrentTime(),
                calcCurrentTime(),
                calcCurrentTime(),
                favorGenerateQrCode(favor),
                favor.getMenuImage()
        );

        return data;
    }


    // 변경된 모델 DB에 저장
    public void storedFeedDB(FeedData data,isSuccessCallback<Boolean> callback){


        // ExecutorService 생성 (스레드 풀)
        ExecutorService executor = Executors.newSingleThreadExecutor();

        // 비동기 작업 시작
        executor.execute(() -> {
            // API 인터페이스 연결
            FeedApiService api = RetrofitService.getInstance().getRetrofit().create(FeedApiService.class);
            Log.d(TAG, "피드 데이터 : " + data.getFeedUid());
            Log.d(TAG, "피드 데이터 : " + data.getUserUid());
            Log.d(TAG, "피드 데이터 : " + data.getTitle());
            Log.d(TAG, "피드 데이터 : " + data.getContent());
            Log.d(TAG, "피드 데이터 : " + data.getDeleteAt());
            Log.d(TAG, "피드 데이터 : " + data.getLikeCount());
            Log.d(TAG, "피드 데이터 : " + data.getWritedDate());
            Log.d(TAG, "피드 데이터 : " + data.getUpdateAt());
            Log.d(TAG, "피드 데이터 이미지 : " + data.getMenuImage());
            api.postFeedData(data).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "피드 등록 성공 : " + response.message());
                        Log.d(TAG, "피드 등록 성공 : " + response.code());

                        // 콜백을 true로 설정
                        callback.onSuccess(true);
                    } else {
                        Log.d(TAG, "피드 등록 실패 : " + response.code());
                        Log.d(TAG, "피드 등록 실패 : " + response.errorBody());

                        // 콜백을 false로 설정
                        callback.onFailure(false);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.d(TAG, "피드 등록 실패 : " + t.getCause());
                    Log.d(TAG, "피드 등록 실패 : " + t.getMessage());

                }
            });
        });
    }


    // 저장한 데이터 로컬 캐시 처리

    // qr코드 변경 로직

    private byte[] menuGenerateQrCode(Menu menu) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기
            // qr 비트맵이미지 생성
            String content = menuConvertQrValue(menu);
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);

            // longblob형으로 넣기 위해
            // Bitmap을 byte[]로 변환
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();  // 바이트 배열로 반환

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] favorGenerateQrCode(Favor favor) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기
            String content = favorConvertQrValue(favor);
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);
            // Bitmap을 byte[]로 변환
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            return stream.toByteArray();  // 바이트 배열로 반환

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 현재 시간 구하는 함수
    public Timestamp calcCurrentTime(){

        // 현재 날짜와 시간 구하기
        LocalDateTime currentDateTime = LocalDateTime.now();

        // 정확한 형식으로 변환 (yyyy-MM-dd HH:mm:ss)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = currentDateTime.format(formatter);

        // 변환된 문자열을 Timestamp로 변환
        Timestamp timestamp = Timestamp.valueOf(formattedDateTime);

        // 로그 출력
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + currentDateTime);
        Log.d("AddFeedViewModel", "피드 등록 날짜 및 시간 : " + timestamp);

        return timestamp;
    }

    private String menuConvertQrValue(Menu menu){
        String temp = "";

        //메뉴 이름
        switch (menu.getQrMenuName()){
            case  BLTSANDWICH :{
                temp+="M1 ";
                break;

            }
            case  CHICKENAVOCADOSANDWICH :{
                temp+="M2 ";
                break;
            }
            case  CHICKENSLICESANDWICH :{
                temp+="M3 ";
                break;
            }
            case  CHICKENTERIYAKISANDWICH :{
                temp+="M4 ";
                break;
            }
            case  EGGSLICESANDWICH :{
                temp+="M5 ";
                break;
            }
            case  EGGMAYOSANDWICH :{
                temp+="M6 ";
                break;
            }
            case  HAMSANDWICH :{
                temp+="M7 ";
                break;
            }
            case  ITALIANBMTSANDWICH :{
                temp+="M8 ";
                break;
            }
            case  KBBQSANDWITCH :{
                temp+="M9 ";
                break;
            }
            case  PORKCHEESESANDWICH :{
                temp+="M10 ";
                break;
            }
            case  ROASTEDCHICKENSANDWICH :{
                temp+="M11 ";
                break;
            }
            case  ROTISSERIEBBQCHICKEN :{
                temp+="M12 ";
                break;
            }
            case  SHRIMPSANDWICH :{
                temp+="M13 ";
                break;
            }
            case  SPICYITALIANSANDWICH :{
                temp+="M14 ";
                break;
            }
            case  SPICYSHRIMPSANDWICH :{
                temp+="M15 ";
                break;
            }
            case  STEAKCHEESESANDWICH :{
                temp+="M16 ";
                break;
            }
            case  SUBWAYCLUBSANDWICH :{
                temp+="M17 ";
                break;
            }
            case  VEGGIESANDWICH :{
                temp+="M18 ";
                break;
            }


        }
        //빵 이름
        switch (menu.getQrBreadName()){
            case  WHITE :{
                temp+="B1 ";
                break;

            }
            case  WHEAT :{
                temp+="B2 ";
                break;
            }
            case  PARMESAN :{
                temp+="B3 ";
                break;
            }
            case  HONEYOAT :{
                temp+="B4 ";
                break;
            }
            case  HEARTY :{
                temp+="B5 ";
                break;
            }
            case  FLATBREAD :{
                temp+="B6 ";
                break;
            }

        }

        if(!menu.getQrToppingName().isEmpty()) {

            if(menu.getQrToppingName().size() == 2){
                temp+="T00";
            }else if(menu.getQrToppingName().size() == 1){
                temp+="T0000";
            }else {
                temp +="T";
            }

            for (int i = 0; i < menu.getQrToppingName().size(); i++) {
                //토핑 이름
                switch (menu.getQrToppingName().get(i)) {
                    case AVOCADO: {
                        temp += "01";
                        break;
                    }
                    case BACON: {
                        temp += "02";
                        break;
                    }
                    case EGGSLICE: {
                        temp += "03";
                        break;
                    }
                    case MEAT: {
                        temp += "04";
                        break;
                    }
                    case OMELETTE: {
                        temp += "05";
                        break;
                    }
                    case PEPPERONI: {
                        temp += "06";
                        break;
                    }
                    case EGGMAYO: {
                        temp += "07";
                        break;
                    }
                    case AMERICANCHEESE: {
                        temp += "08";
                        break;
                    }
                    case MOZZARELLACHEESE: {
                        temp += "09";
                        break;
                    }
                    case SHOEREDDCHEESE: {
                        temp += "10";
                        break;
                    }
                    case CUCUMBER: {
                        temp += "11";
                        break;
                    }
                    case JALAPENO: {
                        temp += "12";
                        break;
                    }
                    case LETTUCE: {
                        temp += "13";
                        break;
                    }
                    case OLIVES: {
                        temp += "14";
                        break;
                    }
                    case ONION: {
                        temp += "15";
                        break;
                    }
                    case PICKLE: {
                        temp += "16";
                        break;
                    }

                    case PIMENTO:{
                        temp += "17";
                        break;
                    }

                    case TOMATO: {
                        temp += "18";
                        break;
                    }

                }
            }
        }


        if(!menu.getQrSauceName().isEmpty()) {

            if(menu.getQrSauceName().size() == 2){
                temp+=" SAU00";
            }else if(menu.getQrSauceName().size() == 1){
                temp+=" SAU0000";
            }else{
                temp += " SAU";
            }

            for (int i = 0; i < menu.getQrSauceName().size(); i++) {
                //소스
                switch (menu.getQrSauceName().get(i)) {
                    case BBQ: {
                        temp += "01";
                        break;
                    }
                    case HONEYMUSTARD: {
                        temp += "02";
                        break;
                    }
                    case HOTCHILI: {
                        temp += "03";
                        break;
                    }
                    case ITALIANDRESSING: {
                        temp += "04";
                        break;
                    }
                    case MAYONNAISE: {
                        temp += "05";
                        break;
                    }
                    case MUSTARD: {
                        temp += "06";
                        break;
                    }
                    case OLIVEOIL: {
                        temp += "07";
                        break;
                    }
                    case PAPER: {
                        temp += "08";
                        break;
                    }
                    case RANCH: {
                        temp += "09";
                        break;
                    }
                    case REDWINE: {
                        temp += "10";
                        break;
                    }
                    case SALT: {
                        temp += "11";
                        break;
                    }
                    case SMOKEDBBQ: {
                        temp += "12";
                        break;
                    }
                    case SOUTHWEST: {
                        temp += "13";
                        break;
                    }
                    case SOY: {
                        temp += "14";
                        break;
                    }
                    case SWEETCHILI: {
                        temp += "15";
                        break;
                    }
                    case SWEETONION: {
                        temp += "16";
                        break;
                    }
                    case TARTARE: {
                        temp += "17";
                        break;
                    }
                    case THOUSANDISLAND: {
                        temp += "18";
                        break;
                    }
                    case WASABIMAYO: {
                        temp += "19";
                        break;
                    }

                }
            }
        }

        if(!menu.getQrSideName().isEmpty()) {
            temp += " SI";
            //사이드 이름
            switch (menu.getQrSideName()) {
                case BACONCHEESEWEDGEPOTATO: {
                    temp += "01";
                    break;
                }
                case CHEESEWEDGEPOTATO: {
                    temp += "02";
                    break;
                }
                case CHICKENBACONWRAP: {
                    temp += "03";
                    break;
                }
                case POTATOCHIPS: {
                    temp += "04";
                    break;
                }
                case CHOCOLATECHIP: {
                    temp += "05";
                    break;
                }
                case CORNSOUP: {
                    temp += "06";
                    break;
                }
                case DOUBLECHOCOLATECHIP: {
                    temp += "07";
                    break;
                }
                case HASHBROWN: {
                    temp += "08";
                    break;
                }
                case MILK: {
                    temp += "09";
                    break;
                }
                case MUSHROOMSOUP: {
                    temp += "10";
                    break;
                }
                case OATMEAL: {
                    temp += "11";
                    break;
                }
                case RASPBERRYCHEESECOOKIE: {
                    temp += "12";
                    break;
                }
                case WEDGEPOTATO: {
                    temp += "13";
                    break;
                }
                case WHITEMACADAMIACOOKIE: {
                    temp += "14";
                    break;
                }
            }
        }

        //음료 이름
        if(menu.getRequid()){
            temp+=" YES";
        }else{
            temp+=" NO";
        }

        Log.d(TAG,"qr값 : " + temp);

        return temp;
    }
    private String favorConvertQrValue(Favor favor){
        String temp = "";

        //메뉴 이름
        switch (favor.getQrMenuName()){
            case  BLTSANDWICH :{
                temp+="M1 ";
                break;

            }
            case  CHICKENAVOCADOSANDWICH :{
                temp+="M2 ";
                break;
            }
            case  CHICKENSLICESANDWICH :{
                temp+="M3 ";
                break;
            }
            case  CHICKENTERIYAKISANDWICH :{
                temp+="M4 ";
                break;
            }
            case  EGGSLICESANDWICH :{
                temp+="M5 ";
                break;
            }
            case  EGGMAYOSANDWICH :{
                temp+="M6 ";
                break;
            }
            case  HAMSANDWICH :{
                temp+="M7 ";
                break;
            }
            case  ITALIANBMTSANDWICH :{
                temp+="M8 ";
                break;
            }
            case  KBBQSANDWITCH :{
                temp+="M9 ";
                break;
            }
            case  PORKCHEESESANDWICH :{
                temp+="M10 ";
                break;
            }
            case  ROASTEDCHICKENSANDWICH :{
                temp+="M11 ";
                break;
            }
            case  ROTISSERIEBBQCHICKEN :{
                temp+="M12 ";
                break;
            }
            case  SHRIMPSANDWICH :{
                temp+="M13 ";
                break;
            }
            case  SPICYITALIANSANDWICH :{
                temp+="M14 ";
                break;
            }
            case  SPICYSHRIMPSANDWICH :{
                temp+="M15 ";
                break;
            }
            case  STEAKCHEESESANDWICH :{
                temp+="M16 ";
                break;
            }
            case  SUBWAYCLUBSANDWICH :{
                temp+="M17 ";
                break;
            }
            case  VEGGIESANDWICH :{
                temp+="M18 ";
                break;
            }


        }
        //빵 이름
        switch (favor.getQrBreadName()){
            case  WHITE :{
                temp+="B1 ";
                break;

            }
            case  WHEAT :{
                temp+="B2 ";
                break;
            }
            case  PARMESAN :{
                temp+="B3 ";
                break;
            }
            case  HONEYOAT :{
                temp+="B4 ";
                break;
            }
            case  HEARTY :{
                temp+="B5 ";
                break;
            }
            case  FLATBREAD :{
                temp+="B6 ";
                break;
            }

        }

        if(!favor.getQrToppingName().isEmpty()) {

            if(favor.getQrToppingName().size() == 2){
                temp+="T00";
            }else if(favor.getQrToppingName().size() == 1){
                temp+="T0000";
            }else {
                temp +="T";
            }

            for (int i = 0; i < favor.getQrToppingName().size(); i++) {
                //토핑 이름
                switch (favor.getQrToppingName().get(i)) {
                    case AVOCADO: {
                        temp += "01";
                        break;
                    }
                    case BACON: {
                        temp += "02";
                        break;
                    }
                    case EGGSLICE: {
                        temp += "03";
                        break;
                    }
                    case MEAT: {
                        temp += "04";
                        break;
                    }
                    case OMELETTE: {
                        temp += "05";
                        break;
                    }
                    case PEPPERONI: {
                        temp += "06";
                        break;
                    }
                    case EGGMAYO: {
                        temp += "07";
                        break;
                    }
                    case AMERICANCHEESE: {
                        temp += "08";
                        break;
                    }
                    case MOZZARELLACHEESE: {
                        temp += "09";
                        break;
                    }
                    case SHOEREDDCHEESE: {
                        temp += "10";
                        break;
                    }
                    case CUCUMBER: {
                        temp += "11";
                        break;
                    }
                    case JALAPENO: {
                        temp += "12";
                        break;
                    }
                    case LETTUCE: {
                        temp += "13";
                        break;
                    }
                    case OLIVES: {
                        temp += "14";
                        break;
                    }
                    case ONION: {
                        temp += "15";
                        break;
                    }
                    case PICKLE: {
                        temp += "16";
                        break;
                    }

                    case PIMENTO:{
                        temp += "17";
                        break;
                    }

                    case TOMATO: {
                        temp += "18";
                        break;
                    }

                }
            }
        }


        if(!favor.getQrSauceName().isEmpty()) {

            if(favor.getQrSauceName().size() == 2){
                temp+=" SAU00";
            }else if(favor.getQrSauceName().size() == 1){
                temp+=" SAU0000";
            }else{
                temp += " SAU";
            }

            for (int i = 0; i < favor.getQrSauceName().size(); i++) {
                //소스
                switch (favor.getQrSauceName().get(i)) {
                    case BBQ: {
                        temp += "01";
                        break;
                    }
                    case HONEYMUSTARD: {
                        temp += "02";
                        break;
                    }
                    case HOTCHILI: {
                        temp += "03";
                        break;
                    }
                    case ITALIANDRESSING: {
                        temp += "04";
                        break;
                    }
                    case MAYONNAISE: {
                        temp += "05";
                        break;
                    }
                    case MUSTARD: {
                        temp += "06";
                        break;
                    }
                    case OLIVEOIL: {
                        temp += "07";
                        break;
                    }
                    case PAPER: {
                        temp += "08";
                        break;
                    }
                    case RANCH: {
                        temp += "09";
                        break;
                    }
                    case REDWINE: {
                        temp += "10";
                        break;
                    }
                    case SALT: {
                        temp += "11";
                        break;
                    }
                    case SMOKEDBBQ: {
                        temp += "12";
                        break;
                    }
                    case SOUTHWEST: {
                        temp += "13";
                        break;
                    }
                    case SOY: {
                        temp += "14";
                        break;
                    }
                    case SWEETCHILI: {
                        temp += "15";
                        break;
                    }
                    case SWEETONION: {
                        temp += "16";
                        break;
                    }
                    case TARTARE: {
                        temp += "17";
                        break;
                    }
                    case THOUSANDISLAND: {
                        temp += "18";
                        break;
                    }
                    case WASABIMAYO: {
                        temp += "19";
                        break;
                    }

                }
            }
        }

        if(!favor.getQrSideName().isEmpty()) {
            temp += " SI";
            //사이드 이름
            switch (favor.getQrSideName()) {
                case BACONCHEESEWEDGEPOTATO: {
                    temp += "01";
                    break;
                }
                case CHEESEWEDGEPOTATO: {
                    temp += "02";
                    break;
                }
                case CHICKENBACONWRAP: {
                    temp += "03";
                    break;
                }
                case POTATOCHIPS: {
                    temp += "04";
                    break;
                }
                case CHOCOLATECHIP: {
                    temp += "05";
                    break;
                }
                case CORNSOUP: {
                    temp += "06";
                    break;
                }
                case DOUBLECHOCOLATECHIP: {
                    temp += "07";
                    break;
                }
                case HASHBROWN: {
                    temp += "08";
                    break;
                }
                case MILK: {
                    temp += "09";
                    break;
                }
                case MUSHROOMSOUP: {
                    temp += "10";
                    break;
                }
                case OATMEAL: {
                    temp += "11";
                    break;
                }
                case RASPBERRYCHEESECOOKIE: {
                    temp += "12";
                    break;
                }
                case WEDGEPOTATO: {
                    temp += "13";
                    break;
                }
                case WHITEMACADAMIACOOKIE: {
                    temp += "14";
                    break;
                }
            }
        }

        //음료 이름
        if(favor.getQrRequid()){
            temp+=" YES";
        }else{
            temp+=" NO";
        }

        Log.d(TAG,"qr값 : " + temp);

        return temp;
    }

}
