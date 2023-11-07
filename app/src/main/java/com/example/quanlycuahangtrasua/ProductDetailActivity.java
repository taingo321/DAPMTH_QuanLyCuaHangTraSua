package com.example.quanlycuahangtrasua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quanlycuahangtrasua.Model.Products;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class activity_product_detail extends AppCompatActivity {

    private ImageView product_Image_Detail;
    private TextView product_Name_Detail, product_Ingre_Detail, product_Price_Detail;
    private String productID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        productID = getIntent().getStringExtra("pid");

        product_Image_Detail = findViewById(R.id.product_Image_Detail);
        product_Name_Detail = findViewById(R.id.product_Name_Detail);
        product_Ingre_Detail = findViewById(R.id.product_Ingre_Detail);
        product_Price_Detail = findViewById(R.id.product_Price_Detail);

        getProductDetails(productID);
    }

    private void getProductDetails(String productID) {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");

        productsRef.child(productID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    Products products = snapshot.getValue(Products.class);

                    product_Name_Detail.setText(products.getPname());
                    product_Ingre_Detail.setText(products.getIngre());
                    product_Price_Detail.setText(products.getPrice());
                    Picasso.get().load(products.getImage()).into(product_Image_Detail);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Xử lý lỗi (nếu cần)
            }
        });
    }

}