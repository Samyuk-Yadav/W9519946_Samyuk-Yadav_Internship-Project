package uk.ac.tees.W9519946.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    private static int timer = 3500;

    ImageView image_logo;
    TextView textView_front;
    TextView textView2_front;
    TextView power_line;
    Animation anim_down;
    Animation anim_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        image_logo =findViewById(R.id.image_logo);
        textView_front =findViewById(R.id.textView_front);
        textView2_front = findViewById(R.id.textView2_front);
        power_line = findViewById(R.id.power_line);

        anim_down = AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.anim_down);
        anim_left = AnimationUtils.loadAnimation(Splash_Screen.this, R.anim.anim_left);


        image_logo.setAnimation(anim_left);
        textView_front.setAnimation(anim_down);
        textView2_front.setAnimation(anim_left);
        power_line.setAnimation(anim_down);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Screen.this, SignIn.class);
                startActivity(i);
                finish();
            }
        }, timer);
    }
}