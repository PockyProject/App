package com.example.pocky.presentation.screen.order.common.finalorder;

import android.app.Application;
import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pocky.domain.model.RetrofitService;
import com.example.pocky.domain.model.db.DBApiService;
import com.example.pocky.domain.model.db.DBData;
import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.model.user.UserInfo;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorRepository;
import com.example.pocky.domain.repository.orderList.Order;
import com.example.pocky.domain.repository.orderList.OrderRepository;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalOrderViewModel extends AndroidViewModel implements FinalOrderValue {

    private FavorRepository repository;
    private OrderRepository orderRepository;
    public FinalOrderViewModel(@NonNull Application application) {
        super(application);
        repository = new FavorRepository(application);
        orderRepository = new OrderRepository(application);
    }


    //즐겨찾기 정보 입력받기
    public void insertFavor(Menu menu) {
        menuNullCheck(menu);

        repository.insert(MenutoFavor(menu));
    }

    public void insertOrder(Menu menu){
        menuNullCheck(menu);

        orderRepository.insert(menuToOrder(menu));
    }

    public void storedDb(Menu menu){ //mysql 저장
        DBApiService api = RetrofitService.getInstance().getRetrofit().create(DBApiService.class);

        Call<Void> call = api.sendUserData( converteMenuToDB(menu));

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    Log.d("FinalOrderViewModel","데이터 전송 성공 : " + response.message());
                }else{
                    Log.d("FinalOrderViewModel","데이터 전송 실패 : " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                    Log.d("FinalOrderViewModel", "네트워크 오류 : " + t.getCause());
            }
        });
    }


    public Order menuToOrder(Menu menu){ // 주문내역 Room 저장 전 데이터 변환
        String temp = "";
        if(menu.getRequid()){
            temp = "음료 여부 : 예";
        }else{
            temp = "음료 여부 : 아니오";
        }


        Order order = new Order(
                UUID.randomUUID().toString(),
                menu.getMenuImage(),
                menu.getMenuName(),
                menu.getBreadName(),
                menu.getSauceName(),
                menu.getToppingName(),
                menu.getSideName(),
                temp
        );

        return order;
    }

    public DBData converteMenuToDB(Menu menu){ // mysql 저장 전 데이터 변환
        DBData db = new DBData(
                UserInfo.getInstance().getUserId().toString(),
                UserInfo.getInstance().getNickname().toString(),
                menu.getMenuImage(),
                menu.getBreadName(),
                menu.getSauceName(),
                menu.getToppingName(),
                menu.getRequid()
        );

        return db;
    }


    public void menuNullCheck(Menu menu){
        if(menu.getBreadName() == null){
            menu.setBreadName("");
        }

        if(menu.getToppingName() == null){
            menu.setToppingName("");
        }

        if(menu.getSauceName() == null){
            menu.setMenuName("");
        }
    }

    private Favor MenutoFavor(Menu menu){ // Menu 자료형 Favor로 변환
        String temp = "";
        if(menu.getRequid()){
            temp = "음료 여부 : 예";
        }else{
            temp = "음료 여부 : 아니오";
        }



        return new Favor(
                menu.getMenuImage(),
                menu.getMenuName(),
                UUID.randomUUID().toString(),
                menu.getBreadName(),
                menu.getSauceName(),
                menu.getToppingName(),
                menu.getSideName(),
                temp
        );
    }


    //qr코드 생성
    public Bitmap generateQrCode(Menu menu) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기
            // 한글 안됌
            String content = ConvertQrValue(menu);
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //최종적으로 qr 데이터 변환 메서드
    private String ConverListValuetoString(List<String> menu){
        String temp = "";

        for (int i = 0; i < menu.size(); i++) {
            temp += menu.get(i);
        }

        return temp;
    }

    private String ConvertBooleantoString(Boolean menu){
        if(menu){
            return "YES";
        }else{
            return "NO";
        }
    }
    //최종적으로 qr 데이터 변환 메서드
    private String ConvertQrValue(Menu menu){
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
            temp +="T";
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
            temp += " SAU";
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

        Log.d("FinalOrederViewModel","qr값 : " + temp);


        return temp;
    }
}
