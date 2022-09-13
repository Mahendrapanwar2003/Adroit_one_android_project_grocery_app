package com.example.adroit_one_;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private TextView tvSignUpId;

    private EditText Password;
    private Button button;
    private EditText Number;

    String Name;
    String Email;
    String Address;
    String number;
    String password;
    byte[] Image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tvSignUpId = findViewById(R.id.tvSignUpId);

        Number = findViewById(R.id.etNumberId);
        Password = findViewById(R.id.etPasswordId);
        button = findViewById(R.id.btnLoginId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SqLiteHelper sqLiteHelper = new SqLiteHelper(getApplicationContext(),"Product",null,1);
                SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor cursor =sqLiteDatabase.rawQuery("select * from User where (Email = '"+Number.getText().toString()+"' and Password = '"+Password.getText().toString()+"')or(Number = '"+Number.getText().toString()+"' and Password = '"+Password.getText().toString()+"')",null);
                        if(cursor.getCount()!=0)
                        {
                            while(cursor.moveToNext())
                            {
                                Name = cursor.getString(1);
                                Email = cursor.getString(2);
                                Address = cursor.getString(3);
                                number = cursor.getString(4);
                                password = cursor.getString(5);
                                Image = cursor.getBlob(5);
                            }
                            Toast.makeText(getApplicationContext(), "SuccessFully Login", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this,NavigationDrowerUsingForFragmentsActivity.class);
                            startActivity(intent);
                        }else
                        {
                            Toast.makeText(getApplicationContext(), "Login Filed\nNumber Or Password Not Match", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

        tvSignUpId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}