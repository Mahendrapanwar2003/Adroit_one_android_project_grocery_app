package com.example.adroit_one_;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adroit_one_.ui.dashboard.CategoryAdapter;
import com.example.adroit_one_.ui.dashboard.CategoryModel;

import java.util.ArrayList;

public class ShoesFragment extends Fragment {

    ArrayList<CategoryModel> Catmodel = new ArrayList<CategoryModel>();
    private RecyclerView recyclerviewId;
    private TextView tvCategoryId;
    private TextView tvElectronicId;
    private TextView tvClothesId;
    private TextView tvShoesId;
    private TextView tvgroceryId;
    private LinearLayout containerid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shoes, container, false);
        recyclerviewId = view.findViewById(R.id.recyclerviewId);
        tvCategoryId = view.findViewById(R.id.tvCategoryId);
        tvElectronicId = view.findViewById(R.id.tvElectronicId);
        tvClothesId = view.findViewById(R.id.tvClothesId);
        tvShoesId = view.findViewById(R.id.tvShoesId);
        tvgroceryId = view.findViewById(R.id.tvgroceryId);
        containerid = view.findViewById(R.id.containerid);
        recyclerviewId.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        SqLiteHelper sqLiteHelper = new SqLiteHelper(getActivity(), "Product", null, 1);
        SQLiteDatabase sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from Product where  Category=\"Shoes\"", null);

        while (cursor.moveToNext()) {
            CategoryModel categoryModel = new CategoryModel();
            categoryModel.Name = cursor.getString(1);
            categoryModel.Detail = cursor.getString(2);
            categoryModel.Price = cursor.getString(3);
            categoryModel.Image = cursor.getBlob(4);
            categoryModel.Category = cursor.getString(5);
            Catmodel.add(categoryModel);
        }
        CategoryAdapter categoryAdapter = new CategoryAdapter(getActivity(), Catmodel);
        recyclerviewId.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerviewId.setAdapter(categoryAdapter);

        tvElectronicId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ElectronicFragment electronicFragment = new ElectronicFragment();
                fragmentTransaction.replace(R.id.frament_dashboard, electronicFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Electronic", Toast.LENGTH_SHORT).show();
            }
        });
        tvShoesId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ShoesFragment shoesFragment = new ShoesFragment();
                fragmentTransaction.replace(R.id.frament_dashboard, shoesFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Shoes", Toast.LENGTH_SHORT).show();
            }
        });
        tvClothesId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ClothesFragment clothesFragment = new ClothesFragment();
                fragmentTransaction.replace(R.id.frament_dashboard, clothesFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "Clothes", Toast.LENGTH_SHORT).show();
            }
        });
        tvgroceryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                GroceryFragment groceryFragment = new GroceryFragment();
                fragmentTransaction.replace(R.id.frament_dashboard, groceryFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "grocery", Toast.LENGTH_SHORT).show();
            }
        });
        tvCategoryId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AllCategoryFragment allCategoryFragment = new AllCategoryFragment();
                fragmentTransaction.replace(R.id.frament_dashboard, allCategoryFragment);
                fragmentTransaction.commit();
                Toast.makeText(getActivity(), "All Category", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}