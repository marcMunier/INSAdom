package ssu.insadomapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class Chose_home extends AppCompatActivity {
    private String URL;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_home);
        Bundle extra = getIntent().getExtras();

        name = new String((String) extra.get("Home_name"));
        URL =  new String((String) extra.get("Home_URL" ));

        Log.i("DEBUG_MUNIER", "onCreate_activity_chose_home: name:  " + name + " URL : " +  URL  );


    }
}
