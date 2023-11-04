package com.example.quanlycuahangtrasua;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;

public class ProductManagementActivity extends AppCompatActivity {
    private FloatingActionButton fabAddProduct;
    private DatabaseReference ProductsManagementRef;
    private RecyclerView rvProductListManagement;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);

        ProductsManagementRef = FirebaseDatabase.getInstance().getReference().child("Products");
        rvProductListManagement = findViewById(R.id.rvProductListManagement);
        rvProductListManagement.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvProductListManagement.setLayoutManager(layoutManager);

        fabAddProduct = findViewById(R.id.fabAddProduct);
        fabAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductManagement.this, AddNewProduct.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<Products> options = new FirebaseRecyclerOptions.Builder<Products>()
                .setQuery(ProductsManagementRef, Products.class).build();

        FirebaseRecyclerAdapter<Products, ProductViewHolder> adapter = new FirebaseRecyclerAdapter<Products, ProductViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ProductViewHolder holder, int position, @NonNull Products model) {
                holder.productName.setText(model.getPname());
                holder.productPrice.setText(model.getPrice());
                holder.productIngre.setText(model.getPrice());
                Picasso.get().load(model.getImage()).into(holder.productImage);

            }

            @NonNull
            @Override
            public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);
                ProductViewHolder holder = new ProductViewHolder(view);
                return holder;
            }
        };
        rvProductListManagement.setAdapter(adapter);
        adapter.startListening();
    }

}