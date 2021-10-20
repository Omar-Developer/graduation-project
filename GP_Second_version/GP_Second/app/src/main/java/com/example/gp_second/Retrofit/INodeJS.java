package com.example.gp_second.Retrofit;

//import java.util.Observable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface INodeJS {
    @POST ("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("name") String name,
                                    @Field("password") String password);

    @POST ("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("name") String name,
                                    @Field("password") String password);
}
