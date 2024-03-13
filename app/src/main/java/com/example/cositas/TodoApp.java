package com.example.cositas;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

import android.app.Application;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.format.DateTimeFormatter;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class TodoApp extends Application {

    public static final DateTimeFormatter AppDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy - HH:mm:ss z");

    TodoApi mTodoService;

    @Override
    public void onCreate() {
        super.onCreate();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        ObjectMapper jacksonMapper =
                new ObjectMapper()
                        //.registerModule(new JavaTimeModule())
                        .configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        Retrofit retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(Global.BASE_URL_PORTFORWARDING)
                //.baseUrl(Global.BASE_URL_GENYMOTION)
                .addConverterFactory(JacksonConverterFactory.create(jacksonMapper))
                .build();

        mTodoService = retrofit.create(TodoApi.class);
    }

    public TodoApi getAPI() {
        return mTodoService;
    }
}
