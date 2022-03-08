package uk.ac.tees.W9519946.chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import uk.ac.tees.W9519946.chat.Adapters.AdapterChat;
import uk.ac.tees.W9519946.chat.Models_.MessagesModel;
import uk.ac.tees.W9519946.chat.databinding.ActivityDetailEmergencyChatBinding;

public class Detail_Emergency_Chat extends AppCompatActivity {

    ActivityDetailEmergencyChatBinding emergencyChatBinding;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        emergencyChatBinding = ActivityDetailEmergencyChatBinding.inflate(getLayoutInflater());
        setContentView(emergencyChatBinding.getRoot());

        getSupportActionBar().hide();

        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        final String senderId = firebaseAuth.getUid();
        String recieveId = getIntent().getStringExtra("userId");
        String userName = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

        emergencyChatBinding.usernameChat.setText(userName);
        Picasso.get().load(profilePic).placeholder(R.drawable.user).into(emergencyChatBinding.profileImage);

        emergencyChatBinding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Detail_Emergency_Chat.this, MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessagesModel> messagesModels = new ArrayList<>();


        final AdapterChat adapterChat = new AdapterChat(messagesModels, this);
        emergencyChatBinding.RecyclerViewChat.setAdapter(adapterChat);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        emergencyChatBinding.RecyclerViewChat.setLayoutManager(layoutManager);

        final String senderSide = senderId + recieveId;
        final String recieveSide = recieveId + senderId;

        firebaseDatabase.getReference().child("Chats").child(senderSide)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        messagesModels.clear();
                        for (DataSnapshot snapShot1: snapshot.getChildren())
                        {
                         MessagesModel model = snapShot1.getValue(MessagesModel.class);
                         messagesModels.add(model);
                        }
                            adapterChat.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        emergencyChatBinding.sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = emergencyChatBinding.chatMessage.getText().toString();
                final MessagesModel modelMessage = new MessagesModel(senderId, message);
                modelMessage.setTimeStamp(new Date().getTime());

                emergencyChatBinding.chatMessage.setText("");

                firebaseDatabase.getReference().child("Chats").child(senderSide).push().setValue(modelMessage)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        firebaseDatabase.getReference().child("Chats").child(recieveSide).push().setValue(modelMessage)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {

                                    }
                                });
                    }
                });

            }
        });


    }
}