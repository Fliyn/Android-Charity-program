package com.example.FliynProjesi;

import android.app.Application;

public class GlobalClass extends Application {
    public String UserName;


    public String GetUsername() {
        return UserName;
    }
    public void SetUserName(String _susername){
        UserName=_susername;
    }
}
