package com.example.cositas;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class App extends Application {

    TodoApi mTodoService;
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient httpClient = new OkHttpClient.Builder()
                .build();
        ObjectMapper jacksonMapper = new ObjectMapper();
        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl("http://localhost:8080")
                .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                .build();
        mTodoService = retrofit.create(TodoApi.class);
    }
    public TodoApi getAPI() {
        return mTodoService;
    }
}
