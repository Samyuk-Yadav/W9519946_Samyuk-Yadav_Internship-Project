package uk.ac.tees.W9519946.chat.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import uk.ac.tees.W9519946.chat.Adapters.UsersAdapter;
import uk.ac.tees.W9519946.chat.Models_.Users;
import uk.ac.tees.W9519946.chat.databinding.FragmentEmergencyChatBinding;

public class Emergency_Chat extends Fragment {

    public Emergency_Chat() {
        // Required empty public constructor
    }

    FragmentEmergencyChatBinding Fragmentbinding;
    ArrayList<Users> list = new ArrayList<>();
    FirebaseDatabase firebaseDatabase;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Fragmentbinding = FragmentEmergencyChatBinding.inflate(inflater, container, false);
        firebaseDatabase = FirebaseDatabase.getInstance();

        UsersAdapter usersAdapter = new UsersAdapter(list, getContext());
        Fragmentbinding.RecyclerViewChat.setAdapter(usersAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        Fragmentbinding.RecyclerViewChat.setLayoutManager(linearLayoutManager);

        firebaseDatabase.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Users users = dataSnapshot.getValue(Users.class);
                    users.setUserId(dataSnapshot.getKey());
                    list.add(users);
                }
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        return Fragmentbinding.getRoot();

    }
}