package uk.ac.tees.W9519946.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import uk.ac.tees.W9519946.chat.Adapters.FragementAdapter;
import uk.ac.tees.W9519946.chat.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        firebaseAuth = FirebaseAuth.getInstance();

        binding.ViewPager.setAdapter(new FragementAdapter(getSupportFragmentManager()));
        binding.TabLayout.setupWithViewPager(binding.ViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.setting:
                Intent setting = new Intent(MainActivity.this, Setting_Info.class);
                startActivity(setting);
                break;

            case R.id.Group_Chat:
                Intent intent = new Intent(MainActivity.this, GroupChatActivity.class);
                startActivity(intent);
                break;

            case R.id.logout:
                firebaseAuth.signOut();
                Intent logout = new Intent(MainActivity.this, SignIn.class);
                startActivity(logout);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
}