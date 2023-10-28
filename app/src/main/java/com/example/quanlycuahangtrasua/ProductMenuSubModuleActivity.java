package com.example.quanlycuahangtrasua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ProductMenuSubModuleActivity extends AppCompatActivity {
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
                Intent intent = new Intent(ProductMenuSubModuleActivity.this, ProductForCartListActivity.class);
                startActivity(intent);
            }
        });

        ivProductManage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductMenuSubModuleActivity.this, ProductManagementActivity.class);
                startActivity(intent);
            }
        });
    }
}