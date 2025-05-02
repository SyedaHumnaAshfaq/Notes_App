package com.example.notes_app.ui.login;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.notes_app.R;
import com.example.notes_app.ui.notes_details.Notes_Details;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    EditText email;
    EditText password;

    Button LoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        LoginBtn = findViewById(R.id.btnLogin);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String my_email =  email.getText().toString().trim();
                String my_password = password.getText().toString().trim();

                loginViewModel.Login(my_email,my_password);
            }
        });

        loginViewModel.getLoginResult().observe(this,isSuccess ->{
            if(isSuccess){
                Toast.makeText(this, "Login Successful",Toast.LENGTH_LONG).show();
                Intent intent =  new Intent(MainActivity.this, Notes_Details.class);

                startActivity(intent);
                finish();
            }else{
                Toast.makeText(this, "Login Failed",Toast.LENGTH_LONG).show();

            }
        });


    }
}