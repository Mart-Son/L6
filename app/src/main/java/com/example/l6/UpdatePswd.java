package com.example.l6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UpdatePswd extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pswd);

        Button buttonUpdatePwd = findViewById(R.id.buttonUpd);
        Button cancel = findViewById(R.id.button);
        TextView username = findViewById(R.id.welcomeLabel3);
        EditText newPassword = findViewById(R.id.editTextLogin);
        Intent intent;
        DataBaseHandler db = new DataBaseHandler(this);

        intent = new Intent(this, MainActivity.class);
        Bundle arguments = getIntent().getExtras();
        username.setText(arguments.getString("userUpdPwd", "user"));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

        buttonUpdatePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Handler TH = new Handler();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("Обновление пароля","Поток начал работу");
                        TH.post(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(arguments.getString("userUpdPwd", "user"));
                                System.out.println(newPassword.getText().toString());
                                db.updatePassword((arguments.getString("userUpdPwd", "user")), newPassword.getText().toString());
                                Toast.makeText(UpdatePswd.this, "Пароль успешно изменен", Toast.LENGTH_LONG).show();
                                startActivity(intent);
                            }
                        });
                        Log.i("Обновление пароля","Поток закончил работу");
                    }
                }).start();
            }
        });
    }

}