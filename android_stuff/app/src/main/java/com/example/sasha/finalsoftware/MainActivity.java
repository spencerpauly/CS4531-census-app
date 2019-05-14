package com.example.sasha.finalsoftware;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.example.cs4531.finalsoftware.R;

public class MainActivity extends AppCompatActivity {
@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    DatabaseConnection.addUser(AuthenticatedUser.getInstance().account);
}
    public void switchToOneName(View myView) {
        Intent myIntent = new Intent(this, OneName.class);
        startActivity(myIntent);
    }

    public void switchToTwoNames(View myView) {
        Intent myIntent = new Intent(this, TwoName.class);
        startActivity(myIntent);
    }
    public void switchToWildCardNames(View myView) {
        Intent myIntent = new Intent(this, WildcardName.class);
        startActivity(myIntent);
    }
}
