package com.example.adroit_one_.ui.dashboard;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adroit_one_.R;
import com.example.adroit_one_.ShowDataDetailActivity;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.myHolder> {
    Context context;
    ArrayList<CategoryModel> CatModel;

    public CategoryAdapter(Context context, ArrayList<CategoryModel> catmodel) {
        this.CatModel = catmodel;
        this.context = context;
    }

    @NonNull
    @Override
    public myHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.itemviewgrid, null);
        return new myHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myHolder holder, int position) {
        CategoryModel categoryModel = CatModel.get(position);
        holder.Name.setText(categoryModel.Name);
        holder.Price.setText(categoryModel.Price);
       /* holder.Detail.setText(categoryModel.Detail);
        holder.Category.setText(categoryModel.Category);*/
        Bitmap bitmap = BitmapFactory.decodeByteArray(categoryModel.Image, 0, categoryModel.Image.length);
        holder.Image.setImageBitmap(bitmap);
        holder.cardviewId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowDataDetailActivity.class);
                intent.putExtra("product", categoryModel);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return CatModel.size();
    }

    public class myHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView Price;
        TextView Detail;
        TextView Category;
        ImageView Image;
        CardView cardviewId;
        View itemView;

        public myHolder(@NonNull View itemView) {
            super(itemView);
            Name = itemView.findViewById(R.id.tvNameId);
            Price = itemView.findViewById(R.id.tvPriceId);
           /* Detail = itemView.findViewById(R.id.tvDetailId);
            Category = itemView.findViewById(R.id.tvCategoryId);*/
            Image = itemView.findViewById(R.id.image);
            cardviewId = itemView.findViewById(R.id.cardviewId);
            this.itemView = itemView;

        }
    }
}
