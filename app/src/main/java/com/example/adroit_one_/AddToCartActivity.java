package com.example.adroit_one_;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.adroit_one_.ui.dashboard.CategoryModel;

public class AddToCartActivity extends AppCompatActivity {
    TextView Name;
    TextView Price;
    TextView Detail;
    TextView Category;
    ImageView Image;
    CardView cardviewId;
    Button btnCancelId;
    Button btnRemoveId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);
        Name = findViewById(R.id.tvNameId);
        Price = findViewById(R.id.tvPriceId);
        Detail = findViewById(R.id.tvDetailId);
        Category = findViewById(R.id.tvCategoryId);
        btnCancelId = findViewById(R.id.btnCancelId);
        btnRemoveId = findViewById(R.id.btnRemoveId);
        Image = findViewById(R.id.image);
        cardviewId = findViewById(R.id.cardviewId);

        CategoryModel categoryModel = (CategoryModel) getIntent().getSerializableExtra("product");
        Name.setText(categoryModel.getName());
        Detail.setText(categoryModel.getDetail());
        Price.setText(categoryModel.getPrice());
        Category.setText(categoryModel.getCategory());
        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryModel.getImage(), 0, categoryModel.getImage().length);
        Image.setImageBitmap(bitmap);

    }
}