package com.example.equestpractical;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class UserVM extends ViewModel {
     UserRepo userR;
     MutableLiveData<List<User>> userList = new MutableLiveData<>();

    public UserVM() {
        userR = new UserRepo();
        loadUsers();
        //Log.d("UserVM", "UserRepo initialized: " + userR);
    }

    private void loadUsers() {
        userR.getUsers(new UserRepo.OnUsersLoadedListener() {
            @Override
            public void onUsersLoaded(List<User> Users) {
                userList.setValue(Users);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("UserVM", "Error loading users", throwable);
            }
        });
    }

    public LiveData<List<User>> getUserList() {
        return userList;
    }
}
