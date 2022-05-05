package uk.ac.tees.W9519946.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import uk.ac.tees.W9519946.chat.databinding.ActivityForgetPasswordNhsBinding;

public class Forget_Password_NHS extends AppCompatActivity {

    ActivityForgetPasswordNhsBinding forgetPasswordNhsBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        forgetPasswordNhsBinding = ActivityForgetPasswordNhsBinding.inflate(getLayoutInflater());
        setContentView(forgetPasswordNhsBinding.getRoot());
        getSupportActionBar().hide();

        forgetPasswordNhsBinding.backForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Forget_Password_NHS.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}