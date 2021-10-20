package com.example.gpproject.Retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    public static Retrofit getInstance() {
//        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .readTimeout(5, TimeUnit.MINUTES)
//                .connectTimeout(5, TimeUnit.MINUTES)
//                .build();

        if(instance == null)
            instance = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return instance;
    }
}
