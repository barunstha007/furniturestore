package com.example.myfurniturestore.interfaces;

public interface UserInterface {
    @POST("users/signup")
    Call<RegistrationResponse> userRegistration (@Body User user);

    @FormUrlEncoded
    @POST("users/login")
    Call<LoginSignupResponse> checkUser(@Field("username") String username, @Field("password") String password);


    @PUT("/userprofile/updateProfile")
    Call<ResponseBody> updateProfile (@Header("Cookie") String cookie ,@Body User user);
}
