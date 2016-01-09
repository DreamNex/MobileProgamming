package com.sidm.mgpgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

/**
 * Created by Dream on 28/11/2015.
 */
public class Helppage extends Activity implements View.OnClickListener{

    //Declare buttons here
    private Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);// hide title
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //hide top bar
        setContentView(R.layout.mainmenu);

        btn_back = (Button)findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);
    }
    public void onClick(View v){

        //Follow mainmenu to give buttons an activity
        Intent intent = new Intent();
        if(v == btn_back)
        {
            intent.setClass(this, Mainmenu.class);
        }

        startActivity(intent);

    }

    protected void onPause(){
        super.onPause();
    }

    protected void onStop(){
        super.onStop();
    }

    protected void onDestroy(){
        super.onDestroy();
    }

}
