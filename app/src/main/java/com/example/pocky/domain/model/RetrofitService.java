package com.example.pocky.domain.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetrofitService {
        private static Retrofit retrofit;
        private static RetrofitService rs = null;

        //에뮬레이터 : 10.0.2.2
        //실제 기기 : localhost
        private static String baseUrl = "http://121.143.11.133:5008";
        private static Gson gson = new GsonBuilder().setLenient().create();
        private RetrofitService() {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        public static RetrofitService getInstance(){
            if(rs == null){
                rs = new RetrofitService();
            }
            return rs;
        }
        public Retrofit getRetrofit(){
            return retrofit;
        }

}
