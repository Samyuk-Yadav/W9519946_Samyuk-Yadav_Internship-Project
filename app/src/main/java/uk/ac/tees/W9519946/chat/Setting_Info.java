package uk.ac.tees.W9519946.chat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.sax.StartElementListener;
import android.view.View;

import uk.ac.tees.W9519946.chat.databinding.ActivitySettingInfoBinding;

public class Setting_Info extends AppCompatActivity {
    ActivitySettingInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySettingInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        binding.backSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Setting_Info.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}