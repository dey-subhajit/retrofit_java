package com.app.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface JsonPlaceholderApi {

    @GET("posts")
    Call<List<Post>> getAllPosts();

    @GET("posts/{id}/comments")
    Call<List<Post>> getComment(@Path("id") int id);
}
