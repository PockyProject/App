package com.example.pocky.presentation.screen.order.common.finalorder;

import android.app.Application;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.pocky.domain.model.menu.Menu;
import com.example.pocky.domain.repository.favor.Favor;
import com.example.pocky.domain.repository.favor.FavorRepository;
import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.UUID;

public class FinalOrderViewModel extends AndroidViewModel implements FinalOrderValue {

    private FavorRepository repository;
    public FinalOrderViewModel(@NonNull Application application) {
        super(application);
        repository = new FavorRepository(application);
    }


    //즐겨찾기 정보 입력받기
    public void insertFavor(Menu menu) {
        menuNullCheck(menu);

        repository.insert(MenutoFavor(menu));
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
        return new Favor(
                menu.getMenuImage(),
                menu.getMenuName(),
                UUID.randomUUID().toString(),
                menu.getBreadName(),
                menu.getSauceName(),
                menu.getToppingName(),
                menu.getSideName(),
                menu.getRequid()
        );
    }


    //qr코드 생성
    public Bitmap generateQrCode(Menu menu) {
        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

            // favor에서 필요한 데이터를 QR 코드에 넣기
            // 한글 안됌
            String content = "test" + " - " + menu.getRequid();
            Bitmap bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, 300, 300);

            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    //최종적으로 qr 데이터 변환 메서드
    private String ConvertQrValue(Menu menu){
        String temp = "";

        //메뉴 이름
        switch (menu.getMenuName()){
            case  BLTSANDWICH :{
                temp+="M1";
                break;

            }
            case  CHICKENAVOCADOSANDWICH :{
                temp+="M2";
                break;
            }
            case  CHICKENSLICESANDWICH :{
                temp+="M3";
                break;
            }
            case  CHICKENTERIYAKISANDWICH :{
                temp+="M4";
                break;
            }
            case  EGGSLICESANDWICH :{
                temp+="M5";
                break;
            }
            case  EGGMAYOSANDWICH :{
                temp+="M6";
                break;
            }
            case  HAMSANDWICH :{
                temp+="M7";
                break;
            }
            case  ITALIANBMTSANDWICH :{
                temp+="M8";
                break;
            }
            case  PORKCHEESESANDWICH :{
                temp+="M9";
                break;
            }
            case  ROASTEDCHICKENSANDWICH :{
                temp+="M10";
                break;
            }
            case  ROTISSERIEBBQCHICKEN :{
                temp+="M11";
                break;
            }
            case  SHRIMPSANDWICH :{
                temp+="M12";
                break;
            }
            case  SPICYITALIANSANDWICH :{
                temp+="M13";
                break;
            }
            case  SPICYSHRIMPSANDWICH :{
                temp+="M14";
                break;
            }
            case  STEAKCHEESESANDWICH :{
                temp+="M15";
                break;
            }
            case  SUBWAYCLUBSANDWICH :{
                temp+="M16";
                break;
            }
            case  VEGGIESANDWICH :{
                temp+="M17";
                break;
            }


        }
        //빵 이름
        switch (menu.getBreadName()){
            case  WHITE :{
                temp+="B1";
                break;

            }
            case  WHEAT :{
                temp+="B2";
                break;
            }
            case  PARMESAN :{
                temp+="B3";
                break;
            }
            case  HONEYOAT :{
                temp+="B4";
                break;
            }
            case  HEARTY :{
                temp+="B5";
                break;
            }
            case  FLATBREAD :{
                temp+="B6";
                break;
            }

        }

        for(int i = 0; i < menu.getToppingName().size(); i++){
            //토핑 이름
            switch (menu.getToppingName().get(i)){
                case AVOCADO: {
                    temp+="T1";
                    break;
                }
                case BACON: {
                    temp+="T2";
                    break;
                }
                case EGGSLICE: {
                    temp+="T3";
                    break;
                }
                case MEAT: {
                    temp+="T4";
                    break;
                }
                case OMELETTE: {
                    temp+="T5";
                    break;
                }
                case PEPPERONI: {
                    temp+="T6";
                    break;
                }
                case EGGMAYO: {
                    temp+="T7";
                    break;
                }
                case AMERICANCHEESE: {
                    temp+="T8";
                    break;
                }
                case MOZZARELLACHEESE: {
                    temp+="T9";
                    break;
                }
                case SHRIMPCHEESE: {
                    temp+="T10";
                    break;
                }
                case CUCUMBER: {
                    temp+="T11";
                    break;
                }
                case JALAPENO: {
                    temp+="T12";
                    break;
                }
                case LETTUCE: {
                    temp+="T13";
                    break;
                }
                case OLIVES: {
                    temp+="T14";
                    break;
                }
                case ONION: {
                    temp+="T15";
                    break;
                }
                case GREENPEPPER: {
                    temp+="T16";
                    break;
                }
                case PICKLE: {
                    temp+="T17";
                    break;
                }
                case TOMATO: {
                    temp+="T18";
                    break;
                }

            }
        }
        //사이드 이름
        switch (menu.getSideName()){
            case BACONCHEESEWEDGEPOTATO: {
                temp+="SAU1";
                break;
            }
            case CHEESEWEDGEPOTATO: {
                temp+="SAU2";
                break;
            }
            case CHICKENBACONWRAP: {
                temp+="SAU3";
                break;
            }
            case POTATOCHIPS: {
                temp+="SAU4";
                break;
            }
            case CHOCOLATECHIP: {
                temp+="SAU5";
                break;
            }
            case CORNSOUP: {
                temp+="SAU6";
                break;
            }
            case DOUBLECHOCOLATECHIP: {
                temp+="SAU7";
                break;
            }
            case HASHBROWN: {
                temp+="SAU8";
                break;
            }
            case MILK: {
                temp+="SAU9";
                break;
            }
            case MUSHROOMSOUP: {
                temp+="SAU10";
                break;
            }
            case OATMEAL: {
                temp+="SAU11";
                break;
            }
            case RASPBERRYCHEESECOOKIE: {
                temp+="SAU12";
                break;
            }
            case WEDGEPOTATO: {
                temp+="SAU13";
                break;
            }
            case WHITEMACADAMIACOOKIE: {
                temp+="SAU14";
                break;
            }
        }
        //음료 이름
        if(menu.getRequid()){
            temp+="예";
        }else{
            temp+="아니오";
        }

        for (int i = 0; i < menu.getSauceName().size(); i++) {
            //소스
            switch (menu.getSauceName().get(i)){
                case BBQ: {
                    temp+="SAU1";
                    break;
                }
                case HONEYMUSTARD: {
                    temp+="SAU2";
                    break;
                }
                case HOTCHILI: {
                    temp+="SAU3";
                    break;
                }
                case ITALIANDRESSING: {
                    temp+="SAU4";
                    break;
                }
                case MAYONNAISE: {
                    temp+="SAU5";
                    break;
                }
                case MUSTARD: {
                    temp+="SAU6";
                    break;
                }
                case OLIVEOIL: {
                    temp+="SAU7";
                    break;
                }
                case PAPER: {
                    temp+="SAU8";
                    break;
                }
                case RANCH: {
                    temp+="SAU9";
                    break;
                }
                case REDWINE: {
                    temp+="SAU10";
                    break;
                }
                case SALT: {
                    temp+="SAU11";
                    break;
                }
                case SMOKEDBBQ: {
                    temp+="SAU12";
                    break;
                }
                case SOUTHWEST: {
                    temp+="SAU13";
                    break;
                }
                case SOY: {
                    temp+="SAU14";
                    break;
                }
                case SWEETCHILI: {
                    temp+="SAU15";
                    break;
                }
                case SWEETONION: {
                    temp+="SAU16";
                    break;
                }
                case TARTARE: {
                    temp+="SAU17";
                    break;
                }
                case THOUSANDISLAND: {
                    temp+="SAU18";
                    break;
                }
                case WASABIMAYO: {
                    temp+="SAU19";
                    break;
                }

            }
        }


        return temp;
    }
}
