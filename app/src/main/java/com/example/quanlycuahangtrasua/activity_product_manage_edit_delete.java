package com.example.quanlycuahangtrasua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class activity_product_manage_edit_delete extends AppCompatActivity {

    public class ProductManageEditDelete extends AppCompatActivity {

        private Button apply_change_button, delete_button;
        private EditText product_Name_edit_delete, product_Price_edit_delete, product_Ingre_edit_delete;
        private ImageView product_Image_edit_delete;
        private String productID = "";
        private DatabaseReference productsRef;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_product_manage_edit_delete);

            productID = getIntent().getStringExtra("pid");
            productsRef = FirebaseDatabase.getInstance().getReference().child("Products").child(productID);

            apply_change_button = findViewById(R.id.apply_change_button);
            delete_button = findViewById(R.id.delete_button);

            product_Name_edit_delete = findViewById(R.id.product_Name_edit_delete);
            product_Price_edit_delete = findViewById(R.id.product_Price_edit_delete);
            product_Ingre_edit_delete = findViewById(R.id.product_Ingre_edit_delete);
            product_Ingre_edit_delete = findViewById(R.id.product_Ingre_edit_delete);

            displaySpecificProductInfo();

            apply_change_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    applyChanges();
                }
            });

            delete_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CharSequence options[] = new CharSequence[]{
                            "Yes",
                            "No"
                    };
                    AlertDialog.Builder builder = new AlertDialog.Builder(ProductManageEditDelete.this);
                    builder.setTitle("Do you want to delete this order?");

                    builder.setItems(options, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int i) {
                            if (i == 0){
                                deleteThisProduct();
                            }
                            else {
                                finish();
                            }
                        }
                    });
                    builder.show();

                    //deleteThisProduct();
                }
            });
        }

        private void deleteThisProduct() {
            productsRef.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Intent intent = new Intent(ProductManageEditDelete.this, ProductManagement.class);
                    startActivity(intent);
                    finish();

                    Toast.makeText(ProductManageEditDelete.this, "The Product is deleted successfully", Toast.LENGTH_SHORT).show();
                }
            });
        }

        private void applyChanges() {
            String pName = product_Name_edit_delete.getText().toString();
            String pPrice = product_Price_edit_delete.getText().toString();
            String pIngre = product_Ingre_edit_delete.getText().toString();

            if (pName.equals("")){
                Toast.makeText(this, "Please enter product name", Toast.LENGTH_SHORT).show();
            }else if (pPrice.equals("")){
                Toast.makeText(this, "Please enter product price", Toast.LENGTH_SHORT).show();
            }else if (pIngre.equals("")){
                Toast.makeText(this, "Please enter product ingredient", Toast.LENGTH_SHORT).show();
            }else {
                HashMap<String, Object> productMap = new HashMap<>();
                productMap.put("pid", productID);
                productMap.put("ingre", pIngre);
                productMap.put("price", pPrice);
                productMap.put("pname", pName);

                productsRef.updateChildren(productMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ProductManageEditDelete.this, "Changes applied successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(ProductManageEditDelete.this, ProductManagement.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        }

        private void displaySpecificProductInfo() {
            productsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()){
                        String pName = snapshot.child("pname").getValue().toString();
                        String pPrice = snapshot.child("price").getValue().toString();
                        String pIngre = snapshot.child("ingre").getValue().toString();
                        String pImage = snapshot.child("image").getValue().toString();

                        product_Name_edit_delete.setText(pName);
                        product_Price_edit_delete.setText(pPrice);
                        product_Ingre_edit_delete.setText(pIngre);
                        Picasso.get().load(pImage).into(product_Image_edit_delete);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }


}