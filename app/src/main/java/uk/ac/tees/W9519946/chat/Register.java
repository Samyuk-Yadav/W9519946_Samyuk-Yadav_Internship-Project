package uk.ac.tees.W9519946.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import uk.ac.tees.W9519946.chat.Models_.Users;
import uk.ac.tees.W9519946.chat.databinding.ActivityRegisterBinding;

public class Register extends AppCompatActivity {

    ActivityRegisterBinding binding;
    private FirebaseAuth mauth;
    FirebaseDatabase firebaseDatabase;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        mauth = FirebaseAuth.getInstance();
        firebaseDatabase = firebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(Register.this);
        progressDialog.setTitle("Creating NHS Account");
        progressDialog.setMessage("We are creating the account for you...");

        binding.buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                mauth.createUserWithEmailAndPassword(binding.editTextTextEmailAddress.getText().toString(),
                        binding.editTextTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if (task.isSuccessful()){
                            Users users = new Users(binding.editTextTextUserName.getText().toString(), binding.editTextNHSNumber.getText().toString(),
                                    binding.editTextTextEmailAddress.getText().toString(), binding.editTextTextPassword.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            firebaseDatabase.getReference().child("Users").child(id).setValue(users);


                            Toast.makeText(Register.this, "Successfully Created the User", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Register.this,task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.textViewAlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, SignIn.class);
                startActivity(intent);
            }
        });
    }
}