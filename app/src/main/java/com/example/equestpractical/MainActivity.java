package com.example.equestpractical;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserVM userViewModel;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserVM.class);

        observeViewModel();
    }
    private void observeViewModel() {
        userViewModel.getUserList().observe(this, userList -> {
            userAdapter = new UserAdapter(userList);
            recyclerView.setAdapter(userAdapter);
        });
    }
}