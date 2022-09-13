package com.example.adroit_one_;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class SignUpActivity extends AppCompatActivity {

    private TextView tvLoginId;

    private CircleImageView Image;
    private EditText Name;
    private EditText Number;
    private EditText Email;
    private EditText Address;
    private EditText Password;
    private Button button;
    int Request;
    Bitmap bitmap;
    private byte[] bitmapdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tvLoginId = findViewById(R.id.tvLoginId);

        Image =findViewById(R.id.ciImageId);
        Name = findViewById(R.id.etNameId);
        Number = findViewById(R.id.etNumberId);
        Email = findViewById(R.id.etEmailId);
        Address = findViewById(R.id.etAddressId);
        Password = findViewById(R.id.etPasswordId);
        button = findViewById(R.id.btnSubmitId);

        SqLiteHelper sqLiteHelper = new SqLiteHelper(getApplicationContext(),"Product",null,1);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmap==null)
                {
                    Toast.makeText(getApplicationContext(), "Please Upload Your Profile", Toast.LENGTH_SHORT).show();
                }else
                {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG,10,byteArrayOutputStream);
                    bitmapdata = byteArrayOutputStream.toByteArray();
                    if (Name.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Name", Toast.LENGTH_SHORT).show();
                    }else if(Email.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please Enter Your Email Id", Toast.LENGTH_SHORT).show();
                    }else if(Address.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please Enter Your Address", Toast.LENGTH_SHORT).show();
                    }else if(Number.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please Enter Your Number", Toast.LENGTH_SHORT).show();
                    }else if(Password.getText().toString().isEmpty()){
                        Toast.makeText(getApplicationContext(), "Please Enter Your Password", Toast.LENGTH_SHORT).show();
                    }else{

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("Name",Name.getText().toString());
                        contentValues.put("Email",Email.getText().toString());
                        contentValues.put("Address",Address.getText().toString());
                        contentValues.put("Number",Number.getText().toString());
                        contentValues.put("Password",Password.getText().toString());
                        contentValues.put("Image",bitmapdata);

                        sqLiteDatabase.insert("User",null,contentValues);
                        Toast.makeText(getApplicationContext(), "SuccessFully Submitted", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }
                }
            }
        });

        Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Image = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(Image,Request);
            }
        });

        tvLoginId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            Image.setImageBitmap(bitmap);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}