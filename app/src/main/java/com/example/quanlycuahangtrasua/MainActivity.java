package com.example.quanlycuahangtrasua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivProduct, ivCart, ivInvoice, ivStaff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivProduct = findViewById(R.id.ivProduct);
        ivCart = findViewById(R.id.ivCart);
        ivInvoice = findViewById(R.id.ivInvoice);
        ivStaff = findViewById(R.id.ivStaff);

        ivProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductMenuSubModuleActivity.class);
                startActivity(intent);
            }
        });
    }
}
