package com.example.equestpractical;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("ios-practical.json")
    Call<List<User>> getUsers();
}
