package com.example.sasha.finalsoftware;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class AuthenticatedUser {
    private static AuthenticatedUser single_instance = null;

    // variable of type String
    public GoogleSignInAccount account;
    public boolean userInfoUpdated;

    // static method to create instance of Singleton class
    public static AuthenticatedUser getInstance()
    {
        if (single_instance == null)
            single_instance = new AuthenticatedUser();

        return single_instance;
    }
}
