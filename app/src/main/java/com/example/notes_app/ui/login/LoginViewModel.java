package com.example.notes_app.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notes_app.data.repository.LoginRepository;

public class LoginViewModel extends AndroidViewModel {

    private final MutableLiveData<Boolean> loginResult = new MutableLiveData<>();
    private final LoginRepository loginRepository;

    public LoginViewModel(Application application){
        super(application);
        loginRepository = new LoginRepository(application);
    }

    public LiveData<Boolean> getLoginResult(){ //getLoginResult helps the UI to observe the value of loginResult
        return loginResult;
    }

    public void Login(String email,String password){
        boolean result  = loginRepository.isValidLoginCredentials(email,password);
        loginResult.setValue(result);
    }
}
