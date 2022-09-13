package com.example.adroit_one_;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.adroit_one_.ui.dashboard.CategoryModel;

public class ShowDataDetailActivity extends AppCompatActivity {

    private TextView Buy;
    private TextView Cart;
    private TextView Category;
    private TextView Price;
    private TextView Detail;
    private TextView Name;
    private TextView Quantity;
    ImageView ivImageId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data_detail);
        ivImageId = findViewById(R.id.image);
        Quantity = findViewById(R.id.tvQuantityId);
        Name = findViewById(R.id.tvNameId);
        Detail = findViewById(R.id.tvDetailId);
        Price = findViewById(R.id.tvPriceId);
        Category = findViewById(R.id.tvCategoryId);
        Cart = findViewById(R.id.tvCartId);
        Buy = findViewById(R.id.tvBuyId);

        CategoryModel categoryModel = (CategoryModel) getIntent().getSerializableExtra("product");
        Name.setText(categoryModel.getName());
        Detail.setText(categoryModel.getDetail());
        Price.setText(categoryModel.getPrice());
        Category.setText(categoryModel.getCategory());
        Quantity.setText(categoryModel.getQuantity());
        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryModel.getImage(), 0, categoryModel.getImage().length);
        ivImageId.setImageBitmap(bitmap);

        Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ShowDataDetailActivity.this,SuccesfullyBuyActivity.class);
                startActivity(intent);
            }
        });

        Cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ShowDataDetailActivity.this,AddToCartActivity.class);
                startActivity(intent);
            }
        });
    }
}