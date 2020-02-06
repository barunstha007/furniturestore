package com.example.myfurniturestore.BLL;

import java.io.IOException;

public class LoginBLL {
    private String username;
    private String password;
    boolean isSuccess = false;

    public LoginBLL(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean checkUser() {
        UserInterface userInterface = Url.getInstance().create(UserInterface.class);
        Call<LoginSignupResponse> usersCall = userInterface.checkUser(username, password);

        try {
            Response<LoginSignupResponse> imageResponseResponse = usersCall.execute();
            if (imageResponseResponse.body().getSuccess()) {
                Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
