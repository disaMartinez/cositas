package com.example.cositas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.cositas.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TodoApi mTodoService;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoService = ((TodoApp) this.getApplication()).getAPI();

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkUserInfo(String.valueOf(binding.editText.getText()));
            }
        });
    }

    public void checkUserInfo(String id){

        Call<User> call = mTodoService.getUser(id);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()) {
                    binding.textViewUsername.setText(response.body().username);
                    binding.textViewPass.setText(response.body().email);
                } else {
                    Toast toast = Toast.makeText(MainActivity.this, "Error no user found", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast toast = Toast.makeText(MainActivity.this, "Error getUser()", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


    }
}