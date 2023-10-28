package com.example.quanlycuahangtrasua;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quanlycuahangtrasua.Model.Products;
import com.example.quanlycuahangtrasua.ViewHolder.ProductForCartViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class ProductListActivity extends AppCompatActivity {
    private DatabaseReference ProductsForCartRef;
    private RecyclerView rvProductForCartList;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        ProductsForCartRef = FirebaseDatabase.getInstance().getReference().child("Products");
        rvProductForCartList = findViewById(R.id.rvProductForCartList);
        rvProductForCartList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvProductForCartList.setLayoutManager(layoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsForCartRef, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductForCartViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductForCartViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductForCartViewHolder holder, int position, @NonNull Products model) {
                holder.productNameCart.setText(model.getPname());
                holder.productPriceCart.setText(model.getPrice());
                holder.productIngreCart.setText(model.getPrice());
                Picasso.get().load(model.getImage()).into(holder.productImageCart);
            }

            @NonNull
            @Override
            public ProductForCartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_for_cart_layout, parent, false);
                ProductForCartViewHolder holder = new ProductForCartViewHolder(view);
                return holder;
            }
        };
        rvProductForCartList.setAdapter(adapter);
        adapter.startListening();
    }
}
