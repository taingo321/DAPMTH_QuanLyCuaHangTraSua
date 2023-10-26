package com.example.quanlycuahangtrasua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlycuahangtrasua.Model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText login_username_input, login_password_input;
    private Button login_button, register_button;
    private ProgressDialog loadingBar;
    private String parentDBName = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        login_username_input = findViewById(R.id.login_username_input);
        login_password_input = findViewById(R.id.login_password_input);

        loadingBar = new ProgressDialog(this);


        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void loginUser() {
        String username = login_username_input.getText().toString();
        String password = login_password_input.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(this, "Vui lòng nhập tên tài khoản", Toast.LENGTH_SHORT).show();
        }

        else if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_SHORT).show();
        }

        else {
            loadingBar.setTitle("Đăng nhập tài khoản");
            loadingBar.setMessage("Vui lòng chờ");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            AllowAccessToAccount(username, password);
        }
    }

    private void AllowAccessToAccount(String username, String password) {

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.child(parentDBName).child(username).exists()){
                    Users usersData = snapshot.child(parentDBName).child(username).getValue(Users.class);

                    if (usersData.getUsername().equals(username)){
                        if (usersData.getPassword().equals(password)){
                            if (parentDBName.equals("Users")){
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }
                        else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                else {
                    Toast.makeText(LoginActivity.this, "Tài khoản " + username + " không tồn tại", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}