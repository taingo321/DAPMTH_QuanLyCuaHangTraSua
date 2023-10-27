package com.example.quanlycuahangtrasua;

public class ProductMenuSubModule extends AppCompatActivity {
    ImageView ivAddProductToCart, ivProductManage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_menu_sub_module);

        ivAddProductToCart = findViewById(R.id.ivAddProductToCart);
        ivProductManage = findViewById(R.id.ivProductManage);

        ivAddProductToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMenuSubModule.this, ProductList.class);
                startActivity(intent);
            }
        });

        ivProductManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMenuSubModule.this, ProductManagement.class);
                startActivity(intent);
            }
        });
    }
}
