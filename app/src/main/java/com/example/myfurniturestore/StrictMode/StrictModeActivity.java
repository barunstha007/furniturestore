package com.example.myfurniturestore.StrictMode;

public class StrictModeActivity {
    public static void StrictMode(){
        android.os.StrictMode.ThreadPolicy policy =
                new android.os.StrictMode.ThreadPolicy.Builder().permitAll().build();
        android.os.StrictMode.setThreadPolicy(policy);

    }
}

