package com.example.adroit_one_;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adroit_one_.ui.dashboard.DashBoardFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class AddProductActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText etDetailId;
    private EditText etPriceId;
    private EditText etNameId;
    private Button btnSubmitId;
    private EditText etQuantityId;
    String TableName;
    Bitmap bitmap1;
    String[] Category={"","Electronic","Clothes","Shoes","grocery"};

    static final int PICK_IMAGE_MULTIPLE = 1;
    static final int REQ = 1;
    ImageView image;
    byte[] bitmapdata;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        etDetailId = findViewById(R.id.etDetailId);
        etPriceId = findViewById(R.id.etPriceId);
        etNameId = findViewById(R.id.etNameId);
        etQuantityId = findViewById(R.id.etQuantityId);
        btnSubmitId = findViewById(R.id.btnSubmitId);
        image = findViewById(R.id.image);

        Spinner spin = (Spinner) findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Category);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

        SqLiteHelper sqLiteHelper = new SqLiteHelper(getApplicationContext(),"Product",null,1);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        btnSubmitId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bitmap1==null)
                {
                    Toast.makeText(getApplicationContext(), "Please Upload Item Photos", Toast.LENGTH_SHORT).show();
                }else {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 10, byteArrayOutputStream);
                    bitmapdata = byteArrayOutputStream.toByteArray();

                    if (etNameId.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Product Detail", Toast.LENGTH_SHORT).show();
                    }else if (etDetailId.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Product Price", Toast.LENGTH_SHORT).show();
                    }else if (etPriceId.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Product Price", Toast.LENGTH_SHORT).show();
                    }else if (etQuantityId.getText().toString().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Product Quantity", Toast.LENGTH_SHORT).show();
                    }else if (TableName=="") {
                        Toast.makeText(getApplicationContext(), "Please Enter Your Product Category", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("Name",etNameId.getText().toString());
                        contentValues.put("Price",etPriceId.getText().toString());
                        contentValues.put("Detail",etDetailId.getText().toString());
                        contentValues.put("Quantity",etQuantityId.getText().toString());
                        contentValues.put("Image",bitmapdata);
                        contentValues.put("Category",TableName);
                        sqLiteDatabase.insert("Product",null,contentValues);
                        Toast.makeText(getApplicationContext(), "Successfully Store Data", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddProductActivity.this, NavigationDrowerUsingForFragmentsActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 || resultCode == RESULT_OK) {
            List<Bitmap> bitmap = new ArrayList<>();
            ClipData clipData = data.getClipData();
            if (clipData != null) {
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri uri = clipData.getItemAt(i).getUri();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uri);
                        bitmap1 = BitmapFactory.decodeStream(inputStream);
                        bitmap.add(bitmap1);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                Uri uri = data.getData();
                try {
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    bitmap1 = BitmapFactory.decodeStream(inputStream);
                    bitmap.add(bitmap1);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (final Bitmap b : bitmap) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                image.setImageBitmap(b);
                            }
                        });
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }

      //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        TableName = Category[position];
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }
}