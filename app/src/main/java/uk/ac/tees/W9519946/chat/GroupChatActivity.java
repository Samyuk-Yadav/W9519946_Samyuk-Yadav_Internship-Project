package uk.ac.tees.W9519946.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.tees.W9519946.chat.Adapters.AdapterChat;
import uk.ac.tees.W9519946.chat.Models_.MessagesModel;
import uk.ac.tees.W9519946.chat.databinding.ActivityGroupChatBinding;

public class GroupChatActivity extends AppCompatActivity {
    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GroupChatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final ArrayList<MessagesModel> messagesModels = new ArrayList<>();

        final String senderId = FirebaseAuth.getInstance().getUid();
        binding.usernameChat.setText("Group Chat");

        final AdapterChat adapterChat = new AdapterChat(messagesModels, this);
        binding.RecyclerViewChat.setAdapter(adapterChat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.RecyclerViewChat.setLayoutManager(layoutManager);

        database.getReference().child("Group Chat")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModels.clear();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            MessagesModel model = dataSnapshot.getValue(MessagesModel.class);
                            messagesModels.add(model);
                        }
                        adapterChat.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        binding.sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.chatMessage.getText().toString().isEmpty()){
                    binding.chatMessage.setError("Enter something in the chat box");
                    return;
                }
                final String message = binding.chatMessage.getText().toString();
                final MessagesModel model = new MessagesModel(senderId, message);
                model.setTimeStamp(new Date().getTime());

                binding.chatMessage.setText("");

                database.getReference().child("Group Chat").push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {

                            }
                        });

            }
        });

    }
}