package com.app.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonplaceholder.typicode.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                JsonPlaceholderApi jsonPlaceholderApi = retrofit.create(JsonPlaceholderApi.class);

                Call<List<Post>> call = jsonPlaceholderApi.getAllPosts();

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        /*if(!response.isSuccessful()){

                        }*/
                        List<Post> posts = response.body();
                        for (Post post: posts) {
                            Log.i("success", String.valueOf(post.getTitle()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        Log.i("show_error", t.getMessage());
                    }
                });


                /*Pass dynamic value*/
                Call<List<Post>> CommentCall = jsonPlaceholderApi.getComment(2);

                CommentCall.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        /*if(!response.isSuccessful()){

                        }*/
                        List<Post> posts = response.body();
                        for (Post post: posts) {
                            Log.i("CommentSuccess", String.valueOf(post.getId()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        Log.i("show_error", t.getMessage());
                    }
                });
            }
        });
    }
}