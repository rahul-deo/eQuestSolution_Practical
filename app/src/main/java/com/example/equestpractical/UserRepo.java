package com.example.equestpractical;
import android.util.Log;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepo {
    ApiService apiService;

    public UserRepo(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://linode25.eqserver.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        //Log.d("UserRepo", "ApiService initialized: " + apiService);
    }

    public interface OnUsersLoadedListener {
        void onUsersLoaded(List<User> Users);
        void onError(Throwable throwable);
    }

    public void getUsers(OnUsersLoadedListener listener) {
        apiService.getUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    listener.onUsersLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }
}
